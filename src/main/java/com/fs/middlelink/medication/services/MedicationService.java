package com.fs.middlelink.medication.services;

import com.fs.middlelink.medication.models.CreateMedicationDto;
import com.fs.middlelink.medication.dtos.GetMedicationDto;
import com.fs.middlelink.medication.models.DeleteMedicationDto;

public interface MedicationService {

    CreateMedicationDto createMedication(CreateMedicationDto createMedicationRequest);
    GetMedicationDto getMedication(String medicationName);

    DeleteMedicationDto deleteMedication(String medicationName);
}
