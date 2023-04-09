package com.fs.middlelink.medication.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMedicationDto {

    private String medicationId;
    private String medicationName;
    private Double weight;
    private Byte[] medicationPicture;
}
