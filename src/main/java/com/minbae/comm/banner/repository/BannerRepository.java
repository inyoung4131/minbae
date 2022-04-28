package com.minbae.comm.banner.repository;

import com.minbae.comm.banner.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner,Long> {
}
