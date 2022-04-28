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
    @Column(name="banner_idx")
    private Long bannerIdx;
    @Column(nullable = false,unique = true,name="banner_source")
    private Long bannerSource;
    @Column(nullable = false,unique = true,name="banner_num")
    private int bannerNum;

}
