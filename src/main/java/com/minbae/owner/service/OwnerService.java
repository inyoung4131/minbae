package com.minbae.owner.service;

import com.minbae.owner.dto.OwnerRequestDTO;
import com.minbae.owner.dto.OwnerResponseDTO;
import com.minbae.owner.entity.Owner;
import com.minbae.owner.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public OwnerResponseDTO loginOwner(OwnerRequestDTO ownerRequestDTO){
        String owner_email = ownerRequestDTO.getOwner_email();
        String owner_pwd = ownerRequestDTO.getOwner_pwd();

        // 아이디 비밀번호 조회
        Owner target = ownerRepository.findByEmailAndPwd(owner_email, owner_pwd);

        // entity -> dto
        OwnerResponseDTO ownerResponseDTO = OwnerResponseDTO.toOwnerResponseDTO(target);
        return ownerResponseDTO;
    }

}
