package com.minbae.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

@Getter
@Setter
@AllArgsConstructor
public class ReviewCountAndAvgStar {
    private Long store_idx;
    private int cou;
    private float avger_star;
}
