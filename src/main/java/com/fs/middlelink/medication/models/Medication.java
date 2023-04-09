package com.fs.middlelink.medication.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.awt.*;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@RedisHash("Medication")
public class Medication {

//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private String medicationId;
    @Indexed
    private String medicationName;
    private Double weight;
//    @Lob
//    @Basic(fetch = FetchType.LAZY)
    private Byte[] medicationPicture;
}
