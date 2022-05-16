package com.minbae.owner.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface OwnerMapper {
    Map<String, Object> findByKakaoEmailAndKakoPwd(Object email, Object pwd);

    void insertKakaoOwner(Object email, Object nickname, Object pwd);
}
