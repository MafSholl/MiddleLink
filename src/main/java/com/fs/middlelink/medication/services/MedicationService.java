package com.fs.middlelink.medication.services;

import com.fs.middlelink.medication.models.CreateMedicationDto;

public interface MedicationService {

    CreateMedicationDto createMedication(CreateMedicationDto createMedicationRequest);
}
