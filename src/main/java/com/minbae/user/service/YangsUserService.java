package com.minbae.user.service;

import com.minbae.store.repository.StoreRepository;
import com.minbae.user.dto.UserAddrChangeDto;
import com.minbae.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
//    public List getStoreByCategoryAndStandard(int page, String category,int user_lat,int user_lng){
//
//
//    }
}
