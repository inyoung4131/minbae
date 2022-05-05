package com.minbae.user.service;

import com.minbae.user.dto.UserAddrChangeDto;
import com.minbae.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class YangsUserService {

    private final UserRepository userRepository;

    public long userAddrChange(UserAddrChangeDto userAddrChangeDto){
        return userRepository.findByUserIdx(userAddrChangeDto.toEntity()).getUserIdx();
    }
}
