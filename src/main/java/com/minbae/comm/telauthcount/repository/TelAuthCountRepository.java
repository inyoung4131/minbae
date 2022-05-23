package com.minbae.comm.telauthcount.repository;

import com.minbae.comm.telauthcount.entity.TelAuthCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface TelAuthCountRepository extends JpaRepository<TelAuthCount,Long> {

    int countByTelAndDateTimeGreaterThan(String tel, LocalDateTime localDateTime);
}
