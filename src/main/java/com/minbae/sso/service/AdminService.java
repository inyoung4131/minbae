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
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class AdminService {
    private final UserRepository userRepository;
    private final DeliverRepository deliverRepository;
    private final OwnerRepository ownerRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(String role, String email,String pwd) {
        User userInfo=null;
        Deliver deliverInfo=null;
        Owner ownerInfo=null;
        String token="";
        if(role.equals("user")) {
            userInfo = userRepository.findByUserEmailAndUserPwd(email,pwd);
            if(userInfo!=null){
                token=jwtTokenProvider.createToken(email,userInfo.getUserIdx());
            }
        }
        else if(role.equals("deliver")){
            deliverInfo = deliverRepository.findByDeliverEmailAndDeliverPwd(email,pwd);
            if(deliverInfo!=null){
                token=jwtTokenProvider.createToken(email,deliverInfo.getDeliverIdx());
            }
        }else if(role.equals("owner")){
            ownerInfo = ownerRepository.findByOwnerEmailAndOwnerPwd(email,pwd);
            if(ownerInfo!=null){
                token=jwtTokenProvider.createToken(email,ownerInfo.getOwnerIdx());
            }
        }
        return token;
    }

    public Map getMemberInfo(String role, String email, String pwd){
        User userInfo=null;
        Deliver deliverInfo=null;
        Owner ownerInfo=null;
        Map memberInfo=new HashMap();
        if(role.equals("user")) {
            userInfo = userRepository.findByUserEmailAndUserPwd(email,pwd);
            if(userInfo!=null){
                memberInfo.put("memberData",userInfo);
            }
        }
        else if(role.equals("deliver")){
            deliverInfo = deliverRepository.findByDeliverEmailAndDeliverPwd(email,pwd);
            if(deliverInfo!=null){
                memberInfo.put("memberData",deliverInfo);
            }
        }else if(role.equals("owner")){
            ownerInfo = ownerRepository.findByOwnerEmailAndOwnerPwd(email,pwd);
            if(ownerInfo!=null){
                memberInfo.put("memberData",ownerInfo);
            }
        }
        return memberInfo;
    }
}