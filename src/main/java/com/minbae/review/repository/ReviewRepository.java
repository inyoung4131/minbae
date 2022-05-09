package com.minbae.review.repository;

import com.minbae.user.dto.UserResponseStoreListDto;
import com.minbae.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query(value = "select store_idx ,avg(review_star),count(store_idx) from review r where store_idx in () group by store_idx",nativeQuery = true)
    public List<UserResponseStoreListDto> findReviewStar(@Param("store_idx") long store_idx);
}
