package com.fs.middlelink.medication.services;

import com.fs.middlelink.medication.models.CreateMedicationDto;
import com.fs.middlelink.medication.dtos.GetMedicationDto;

public interface MedicationService {

    CreateMedicationDto createMedication(CreateMedicationDto createMedicationRequest);
    GetMedicationDto getMedication(String medicationName);
}
