package com.minbae.deliver.repository;

import com.minbae.deliver.entity.Deliver;
import com.minbae.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliverRepository extends JpaRepository<Deliver,Long> {

    Optional<Deliver> findByDeliverEmailAndDeliverPwd(String email, String pwd);
}
