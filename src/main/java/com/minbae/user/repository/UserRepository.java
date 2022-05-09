package com.minbae.user.repository;

import com.minbae.user.dto.UserResponseStoreListDto;
import com.minbae.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserEmailAndUserPwd(String email,String pwd);

    User findByUserIdx(User user);

    User findByUserEmail(String userEmail);

    //  jpa 어려움 - 미해결 - 네이티브 쿼리로 대체

    @Query(value = "select s.store_idx \n" +
            ",s.store_name \n" +
            ",sd.store_detail_minimum_price\n" +
            ",sd.store_detail_info_img \n" +
            ",IFNULL(\n" +
            "\tmin(ST_DISTANCE_SPHERE(POINT(f.flag_lng,f.flag_lat),ST_GEOMFROMTEXT('POINT('+:user_lat+' '+:user_lng+')')))\n" +
            "\t, ST_DISTANCE_SPHERE(POINT(store_lng ,store_lat),ST_GEOMFROMTEXT('POINT('+:user_lat+' '+:user_lng+')'))\n" +
            "\t) AS dist\n" +
            ",sd.store_detail_minimum_price \n" +
            ",count(s.store_idx) cou\n" +
            "from store s left join flag f on s.store_idx = f.store_idx left join store_detail sd on sd.store_idx = s.store_idx\n" +
            "where s.store_category LIKE \":category\"\n" +
            "group by s.store_idx \n" +
            "HAVING dist<1500\n" +
            "order by cou desc,dist,dist\n" +
            "limit 0,:page+20", nativeQuery = true)
    public List<UserResponseStoreListDto> findStandardList(int page,String category,int user_lat,int user_lng);

}
