package com.minbae.menu.service;

import com.minbae.menu.entity.Menu;
import com.minbae.menu.repository.MenuRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    // 특정 가게의 메뉴목록 조회
    public List<Menu> getStoreMenuList(Long storeIdx) {
        return menuRepository.findAllByStoreIdx(storeIdx);
    }
}
