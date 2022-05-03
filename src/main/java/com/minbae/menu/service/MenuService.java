package com.minbae.menu.service;

import com.minbae.menu.MenuSaveRequestDto;
import com.minbae.menu.entity.Menu;
import com.minbae.menu.repository.MenuRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    // 특정 가게의 메뉴목록 조회
    public List<Menu> getStoreMenuList(Long storeIdx) {
        return menuRepository.findAllByStoreIdx(storeIdx);
    }

    public Menu createMenu(MenuSaveRequestDto requestDto, MultipartFile file) {
        copyInto(requestDto.getStoreIdx().getStoreIdx(),file);
        requestDto.setMenuImage(file.getOriginalFilename());
        Menu savedResult = requestDto.toEntity();
        return menuRepository.save(savedResult);
    }

    public void copyInto(Long writer, MultipartFile upload) {
        System.out.println("작성자:"+writer);
        System.out.println("올린파일명:"+upload.getOriginalFilename());

        try {
            byte[] bytes = upload.getBytes(); // 실제파일의 바이트 정보 취득
            File f = new File("C:\\workspace\\upload\\"+upload.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(f); // 자원생성
            fos.write(bytes); // 복사
            fos.close(); // 자원종료
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
