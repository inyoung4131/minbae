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
    private Long flag_idx;

    @ManyToOne
    @JoinColumn(name="store_idx")
    private Store store_idx;
    @Column
    private int flag_lat;
    @Column
    private int flag_lng;
    @Column
    private LocalDateTime flag_start_date;
}
