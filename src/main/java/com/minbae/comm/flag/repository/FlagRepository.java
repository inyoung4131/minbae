package com.minbae.comm.flag.repository;

import com.minbae.comm.flag.entity.Flag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlagRepository extends JpaRepository<Flag,Long> {
}
