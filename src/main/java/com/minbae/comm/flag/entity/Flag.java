package com.minbae.comm.flag.entity;

import com.minbae.store.entity.Store;
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
    private int flagLat;
    @Column(name="flag_lng")
    private int flagLng;
    @Column(name="flag_start_date")
    private LocalDateTime flagStartDate;
}
