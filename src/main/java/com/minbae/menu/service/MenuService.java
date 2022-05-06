package com.minbae.menu.service;

import com.minbae.menu.dao.MenuMapper;
import com.minbae.menu.dto.MenuSaveRequestDto;
import com.minbae.menu.entity.Menu;
import com.minbae.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    // 특정 가게의 메뉴목록 조회
    public List<Menu> getStoreMenuList(Long storeIdx) {
        return menuRepository.findAllByStoreIdx(storeIdx);
    }

    // 메뉴 생성
    public Menu createMenu(MenuSaveRequestDto requestDto, MultipartFile file) {
        copyInto(requestDto.getStoreIdx().getStoreIdx(),file);
        requestDto.setMenuImage(file.getOriginalFilename());
        Menu savedResult = requestDto.toEntity();
        return menuRepository.save(savedResult);
    }

    // 파일 복사 및 저장 메소드
    public void copyInto(Long writer, MultipartFile upload) {
        System.out.println("작성자:"+writer);
        System.out.println("올린파일명:"+upload.getOriginalFilename());

        try {
            byte[] bytes = upload.getBytes(); // 실제파일의 바이트 정보 취득
            File f = new File("C:\\workspace\\upload\\"+upload.getOriginalFilename()); // 현재 시간 추가하여 파일명 생성으로 수정할 것.
            FileOutputStream fos = new FileOutputStream(f); // 자원생성
            fos.write(bytes); // 복사
            fos.close(); // 자원종료
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 파일 삭제 메소드
    public void deleteFile(String menuImg){
        File deleteTarget = new File("C:\\workspace\\upload\\"+menuImg);
        if(deleteTarget.exists()){
            deleteTarget.delete();
        }else{
            System.out.println("존재하지 않는 파일");
        }
    }

    public int deleteMenuImageOnly(String menuImg, Long menuIdx) {
        int resultNum = menuMapper.deleteMenuImageOnly(menuIdx, menuImg);
        deleteFile(menuImg);
        return resultNum;
    }

    @Transactional
    public Menu updateMenu(MenuSaveRequestDto menuSaveRequestDto, MultipartFile file) throws IOException {
        // dto에서 menuIdx 꺼내서 기존 메뉴 객체 가져오기
        Menu existMenu = menuRepository.findById(menuSaveRequestDto.getMenuIdx()).orElse(null);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>새로 들어온 DTO의 menuIdx:"+menuSaveRequestDto.getMenuIdx());
        // 전달받은 새로운 메뉴 dto -> entity
        Menu newMenuEntity = menuSaveRequestDto.toEntity();

        Menu updatedMenu;
        if(file == null || file.getBytes().length == 0){ // 전달받은 이미지가 없다면 기존 이미지 유지 + 나머지 데이터 변경
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>설마 file null 여기 도달함?");
            //newMenuEntity.addExistImage(existMenu); // 이게 아닌거 같음
            existMenu.updateNewData(newMenuEntity); // 이게 맞는거 같음
            updatedMenu = menuRepository.save(existMenu);
        }else{ // 전달받은 이미지가 있다면
            // 저장소에 저장된 이미지가 있다면 삭제
            //File temp = new File("C:\\workspace\\upload\\"+existMenu.getMenuImg());
            if(existMenu.getMenuImg() != null){
                deleteFile(existMenu.getMenuImg());
            }
            // 전달받은 이미지 저장소에 저장

            copyInto(menuSaveRequestDto.getStoreIdx().getStoreIdx(),file);

            // 전달받은 이미지 및 데이터 DB에 저장
            newMenuEntity.setMenuImage(file.getOriginalFilename());
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>여기 도달함?");
            System.out.println("file.getOriginalFilename()>>"+file.getOriginalFilename());
            System.out.println("newMenuEntity.getMenuImg()>>"+newMenuEntity.getMenuImg());
            // 데이터 수정
            existMenu.updateNewData(newMenuEntity);
            updatedMenu = menuRepository.save(existMenu);
        }

        return updatedMenu;
    }

    @Transactional // 데이터를 건드리면 transactional 붙이기
    public Menu delete(Long menuIdx) {
        // 댓글이 존재하는지 조회
        Menu target = menuRepository.findById(menuIdx).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 메뉴")
        );
        // 이미지 존재여부 확인 및 삭제
        
        // DB 삭제
        menuRepository.delete(target);
        return target;
    }
}
