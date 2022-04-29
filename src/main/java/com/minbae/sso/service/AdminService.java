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
    private final DeliverRepository deliverRepository;
    private final OwnerRepository ownerRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String login(String role, String email,String pwd) {
        User getUser=null;
        String token="";
        if(role.equals("User")) {
            getUser = userRepository.findByUserEmailAndUserPwd(email,pwd);
            if(getUser!=null){
                token=jwtTokenProvider.createToken(email,getUser.getUserIdx());
            }
        }
//        else if(role.equals("Deliver")){
//            getUser = deliverRepository.findByDeliverEmailAndDeliverPwd(user.getUserEmail(), user.getUserPwd());
//        }else{
//            getUser = ownerRepository.findByOwnerEmailAndOwnerPwd(user.getUserEmail(),user.getUserPwd());
//        }



        return token;
    }
}