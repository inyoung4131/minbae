package com.minbae.comm.banner.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long banner_idx;
    @Column(nullable = false,unique = true)
    private Long banner_source;
    @Column(nullable = false,unique = true)
    private int banner_num;

}
