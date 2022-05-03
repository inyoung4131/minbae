package com.minbae.owner.repository;

import com.minbae.owner.entity.Owner;
import com.minbae.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner,Long> {

    Owner findByOwnerEmailAndOwnerPwd(String owner_email, String owner_pwd);

}
