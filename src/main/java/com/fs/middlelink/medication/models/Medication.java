package com.fs.middlelink.medication.models;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long medicationId;
    private String medicationName;
    private Double weight;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] medicationPicture;
}
