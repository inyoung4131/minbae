package com.minbae.user.repository;

import com.minbae.store.entity.Store;
import com.minbae.storedetail.entity.StoreDetail;
import com.minbae.user.dto.UserResponseStoreListDto;
import com.minbae.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserEmailAndUserPwd(String email,String pwd);

    User findByUserIdx(Long userIdx);

    User findByUserEmail(String userEmail);

    //  jpa 어려움 - 미해결 - 네이티브 쿼리로 대체


}
