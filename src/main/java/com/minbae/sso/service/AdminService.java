package com.minbae.sso.service;

import com.minbae.deliver.repository.DeliverRepository;
import com.minbae.owner.repository.OwnerRepository;
import com.minbae.sso.jwt.JwtTokenProvider;
import com.minbae.user.entity.User;
import com.minbae.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AdminService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String login(String role, User user) {
        Optional<User> getUser=null;
        String token="";
            getUser= userRepository.findByUserEmail(user.getUserEmail());


        if(getUser!=null){
            token=jwtTokenProvider.createToken(user.getUserEmail(),user.getUserIdx());
        }

        return token;
    }
}