package com.fs.middlelink.medication.dtos;

import lombok.*;

import java.awt.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CreateMedicationDto {
    private String medicationId;
    private String medicationName;
    private Double weight;
    private Byte[] medicationPicture;
    private Image image;
}
