package com.minbae.user.service;

import com.minbae.store.entity.Store;
import com.minbae.store.repository.StoreRepository;
import com.minbae.user.dto.UserAddrChangeDto;
import com.minbae.user.dto.UserResponseStoreListDto;
import com.minbae.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Service
public class YangsUserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public long userAddrChange(UserAddrChangeDto userAddrChangeDto){
        userRepository.findByUserIdx(userAddrChangeDto.toEntity());
        return userRepository.save(userAddrChangeDto.toEntity()).getUserIdx();
    }

    //기본순 출력
    public List<UserResponseStoreListDto> getStoreByCategoryAndStandard(int page, String category,int user_lat,int user_lng){
        List<UserResponseStoreListDto> list = userRepository.findStandardList(page,category,user_lat,user_lng);
        return list;
    }

    public Store getStoreinfo(long storeIdx){
        return storeRepository.findByStoreIdx(storeIdx);
    }

}
