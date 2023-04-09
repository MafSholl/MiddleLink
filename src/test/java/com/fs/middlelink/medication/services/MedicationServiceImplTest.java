package com.fs.middlelink.medication.services;

import com.fs.middlelink.medication.dtos.CreateMedicationDto;
import com.fs.middlelink.medication.dtos.DeleteMedicationDto;
import com.fs.middlelink.medication.models.Medication;
import com.fs.middlelink.medication.repository.MedicationRepository;
import com.fs.middlelink.utils.RedisTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import redis.embedded.RedisServer;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RedisTestConfig.class)
@ActiveProfiles("dev")
class MedicationServiceImplTest {
    @Autowired
    private MedicationService medicationService;
    @Autowired
    private MedicationRepository medicationRepository;
    private RedisServer redisServer;

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
        assertNotNull(response);
        assertEquals("Novagin", response.getMedicationName());

        Optional<Medication> repoMed = medicationRepository.findByMedicationName("Novagin");
        assertAll("properties",
                () -> assertTrue(repoMed.isPresent()),
                () -> assertEquals(response.getMedicationName(), repoMed.get().getMedicationName()),
                () -> assertEquals(response.getWeight(), repoMed.get().getWeight()),
                () -> assertEquals(response.getMedicationPicture(), repoMed.get().getMedicationPicture()),
                () -> assertNotNull(repoMed.get().getMedicationId())
        );
    }

    @Test
    void deleteMedicationTest() {
        CreateMedicationDto request = CreateMedicationDto.builder()
                .medicationName("Paracetamol")
                .weight(13.4)
                .build();

        medicationService.createMedication(request);
        DeleteMedicationDto deleteMedicationDto = medicationService.deleteMedication("Paracetamol");

        assertAll("result",
                () -> assertTrue(deleteMedicationDto.isDelete()),
                () -> assertEquals("Medication deleted successfully.", deleteMedicationDto.getMessage())
        );
    }
}