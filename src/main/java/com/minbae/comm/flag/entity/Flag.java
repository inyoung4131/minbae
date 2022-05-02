package com.minbae.comm.flag.entity;

import com.minbae.store.entity.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Flag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flag_idx")
    private Long flagIdx;

    @ManyToOne
    @JoinColumn(name="store_idx")
    private Store storeIdx;
    @Column(name="flag_lat")
    private Double flagLat;
    @Column(name="flag_lng")
    private Double flagLng;
    @Column(name="flag_start_date")
    private LocalDateTime flagStartDate;

    @Builder
    public Flag(Store storeIdx,Double flagLat,Double flagLng){
        this.storeIdx=storeIdx;
        this.flagLat=flagLat;
        this.flagLng=flagLng;
        this.flagStartDate=LocalDateTime.now();
    }
}
