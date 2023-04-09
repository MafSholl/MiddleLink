package com.fs.middlelink.medication.repository;

import com.fs.middlelink.medication.models.Medication;
import com.fs.middlelink.utils.RedisTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = RedisTestConfig.class)
public class MedicationRepositoryTest {

    @Autowired
    private MedicationRepository medicationRepository;

    @Test
    public void whenRepositoryMethodSaveCalled_ShouldSaveToRedis() {
        UUID id = UUID.randomUUID();
        Medication newMed = Medication.builder()
                .medicationId(id.toString())
                .medicationName("Piriton")
                .weight(100.00)
                .build();
        Medication savedMed = medicationRepository.save(newMed);
        assertNotNull(savedMed);
    }

    @Test
    public void embeddedRedis_doesNotPermanentlyPersist() {
        Optional<Medication> optionalMedication = medicationRepository.findByMedicationName("Piriton");
        assertTrue(optionalMedication.isEmpty());
    }

}
