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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    // 특정 가게의 메뉴목록 조회
    public List<Menu> getStoreMenuList(Long storeIdx) {
        return menuRepository.findAllByStoreIdxOrderByMenuSunbunAsc(storeIdx);
    }

    public List<Menu> getStoreMenuListOrderSunbun(Long storeIdx) {
        return menuMapper.findAllByStoreIdxOrderSunbun(storeIdx);
    }

    // 메뉴 생성
    @Transactional
    public Menu createMenu(MenuSaveRequestDto requestDto, MultipartFile file) throws IOException {
        if(file == null || file.getBytes().length == 0) {
            requestDto.setMenuImage(null);
        }else{
            String savedFileName = copyInto(requestDto.getStoreIdx().getStoreIdx(), file);
            requestDto.setMenuImage(savedFileName);
        }
        // 메뉴 순번 세팅
        Integer highestSunbun = menuMapper.findHighestSunbunByStoreIDx(requestDto.getStoreIdx().getStoreIdx());
        if(highestSunbun==null){ // 어떠한 메뉴도 없기에 null 나옴
            requestDto.setMenuSunbun(0);
        }else {
            requestDto.setMenuSunbun(highestSunbun + 1);
        }
        Menu savedResult = requestDto.toEntity();
        return menuRepository.save(savedResult);
    }

    // 파일 복사 및 저장 메소드
    public String copyInto(Long writer, MultipartFile upload) {
        String formatedNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일HH시mm분ss초"));
        String originFileName = upload.getOriginalFilename();
        String savedFileName = formatedNow+originFileName;

        try {
            byte[] bytes = upload.getBytes(); // 실제파일의 바이트 정보 취득
            File f = new File("C:\\workspace\\upload\\"+savedFileName); // 현재 시간 추가하여 파일명 생성으로 수정할 것.
            FileOutputStream fos = new FileOutputStream(f); // 자원생성
            fos.write(bytes); // 복사
            fos.close(); // 자원종료
        } catch (IOException e) {
            e.printStackTrace();
        }

        return savedFileName;
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
        Menu existMenu = menuRepository.findById(menuSaveRequestDto.getMenuIdx()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 메뉴 입니다.")
                );
        //System.out.println(">>>>>>>>>>>>새로 들어온 DTO의 menuIdx:"+menuSaveRequestDto.getMenuIdx());
        // 전달받은 새로운 메뉴 dto -> entity
        Menu newMenuEntity = menuSaveRequestDto.toEntity();

        Menu updatedMenu;
        if(file == null || file.getBytes().length == 0){ // 전달받은 이미지가 없다면 기존 이미지 유지 + 나머지 데이터 변경
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>설마 file null 여기 도달함?");
            //newMenuEntity.addExistImage(existMenu); // 이게 아닌거 같음
            existMenu.updateNewData(newMenuEntity); // 이게 맞는거 같음
            updatedMenu = menuRepository.save(existMenu);
        }else{ // 전달받은 이미지가 있다면
            // 전달받은 이미지 저장소에 저장
            String savedImageName = copyInto(menuSaveRequestDto.getStoreIdx().getStoreIdx(), file);

            // 저장소에 저장된 이미지가 있다면 삭제
            //File temp = new File("C:\\workspace\\upload\\"+existMenu.getMenuImg());
            if(existMenu.getMenuImg() != null){
                deleteFile(existMenu.getMenuImg());
            }

            // 전달받은 이미지 및 데이터 DB에 저장
            newMenuEntity.setMenuImage(savedImageName);
            //System.out.println(">>>>>>>>>>>>>>>>>여기 도달함?");
            //System.out.println("file.getOriginalFilename()>>"+file.getOriginalFilename());
            //System.out.println("newMenuEntity.getMenuImg()>>"+newMenuEntity.getMenuImg());
            // 데이터 수정
            existMenu.updateNewData(newMenuEntity);
            updatedMenu = menuRepository.save(existMenu);
        }

        return updatedMenu;
    }

    @Transactional // 데이터를 건드리면 transactional 붙이기
    public Menu delete(Long menuIdx) {
        // 요청한 메뉴가 존재하는지 조회
        Menu target = menuRepository.findById(menuIdx).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 메뉴")
        );
        // 이미지 존재여부 확인 및 저장소에서 삭제
        if(target.getMenuImg() != null){
            deleteFile(target.getMenuImg());
        }
        
        // DB 삭제
        menuRepository.delete(target);
        return target;
    }

    @Transactional
    public Integer changeMenuSunbun(String storeIdx, List<String> sunbunList) {
        Integer listSize = sunbunList.size();
        Integer resultNum = 0;
        // 0번째 인덱스 값 = 메뉴idx = 순번0
        for(int i = 0; i<listSize; i++){
            //System.out.println(i+"번째 인덱스 값 = 메뉴idx"+sunbunList.get(i)+"= 순번"+i);
            resultNum = menuMapper.updateSunbun(i, sunbunList.get(i));
            if(resultNum < 1){
                return null;
            }
        }
        return resultNum;
    }
}
