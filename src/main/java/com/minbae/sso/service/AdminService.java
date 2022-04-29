package com.minbae.sso.service;

import com.minbae.deliver.entity.Deliver;
import com.minbae.deliver.repository.DeliverRepository;
import com.minbae.owner.entity.Owner;
import com.minbae.owner.repository.OwnerRepository;
import com.minbae.sso.jwt.JwtTokenProvider;
import com.minbae.user.entity.User;
import com.minbae.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class AdminService {
    private final UserRepository userRepository;
    private final DeliverRepository deliverRepository;
    private final OwnerRepository ownerRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String login(String role, String email,String pwd) {
        User userInfo=null;
        Deliver deliverInfo=null;
        Owner ownerInfo=null;
        String token="";
        if(role.equals("User")) {
            userInfo = userRepository.findByUserEmailAndUserPwd(email,pwd);
            if(userInfo!=null){
                token=jwtTokenProvider.createToken(email,userInfo.getUserIdx(),role);
            }
        }
        else if(role.equals("Deliver")){
            deliverInfo = deliverRepository.findByDeliverEmailAndDeliverPwd(deliverInfo.getDeliverEmail(), deliverInfo.getDeliverPwd());
            if(deliverInfo!=null){
                token=jwtTokenProvider.createToken(email,deliverInfo.getDeliverIdx(),role);
            }
        }else if(role.equals("Owner")){
            ownerInfo = ownerRepository.findByEmailAndPwd(ownerInfo.getOwnerEmail(),ownerInfo.getOwnerPwd());
            if(ownerInfo!=null){
                token=jwtTokenProvider.createToken(email,ownerInfo.getOwnerIdx(),role);
            }
        }
        return token;
    }
}