package com.fs.middlelink.medication.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DeleteMedicationDto {
    private String message;
    private boolean delete;
}
