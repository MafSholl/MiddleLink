package com.fs.middlelink.medication.services;

import com.fs.middlelink.medication.models.CreateMedicationDto;
import com.fs.middlelink.medication.models.Medication;
import com.fs.middlelink.medication.repository.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicationServiceImplTest {
    @Autowired
    private MedicationService medicationService;
    @Autowired
    private MedicationRepository medicationRepository;

    @Test
    void MedicationExistTest() {
        Medication medication = new Medication();
        assertThat(medication).isNotNull();
    }

    @Test
    void createMedicationTest() {
        CreateMedicationDto request = CreateMedicationDto.builder()
                .medicationName("Novagin")
                .weight(13.4)
                .build();

        CreateMedicationDto response = medicationService.createMedication(request);
        Optional<Medication> repoMed = medicationRepository.findByMedicationName("Novagin");
        assertAll("properties",
                () -> assertEquals(response.getMedicationName(), repoMed.get().getMedicationName()),
                () -> assertEquals(response.getWeight(), repoMed.get().getWeight()),
                () -> assertEquals(response.getMedicationPicture(), repoMed.get().getMedicationPicture()),
                () -> assertNotNull(repoMed.get().getMedicationId())
        );
    }
}