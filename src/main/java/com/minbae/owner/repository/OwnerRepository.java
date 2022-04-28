package com.minbae.owner.repository;

import com.minbae.owner.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
}
