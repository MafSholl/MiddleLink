package com.fs.middlelink.medication.models;

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
    private byte[] medicationPicture;
    private Image image;
}
