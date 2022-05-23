package com.minbae.comm.telauthcount.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Entity
@Table(name="tel_auth_count")
public class TelAuthCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String tel;
    @Setter
    LocalDateTime dateTime;

}


























































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































