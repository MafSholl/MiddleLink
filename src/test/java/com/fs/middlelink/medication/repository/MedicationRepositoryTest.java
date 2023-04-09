package com.fs.middlelink.medication.repository;

import com.fs.middlelink.medication.models.Medication;
import com.fs.middlelink.utils.RedisTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The repository test here picks up the values set in the
 * application.properties in path src/test/resources
 *
 * MedicationRepository here makes use of the redis
 * embedded server that is configured using the RedisTestConfig.java.
 * RedisTestConfig makes use of RedisProperties.java default
 * encapsulated field value - specifically the port.
 * RedisProperties in turn initializes values into its field variables
 * by reading the application.properties file it picks up
 * during context load using the@Value annotation.
 *
 * We had to go through these troubles because the
 * Ozimov embedded database is not as sophisticated as H2.
 * Hence, we have to declare some configurations explicitly
 * otherwise our test will rather use the local redis server.
 *
 *
 * Since my test passed by about 2pm, Something interesting
 * I've seen and been at since about 4pm that I resumed.
 * I changed the a single letter in the name of the second test,
 * the "...Redis_d..." to "...Redis_D..." and the test
 * would not pass anymore. I changed any other letter, the test passed.
 * This is a very funny and strange behaviour in a code I've never seen .
 */
@SpringBootTest(classes = RedisTestConfig.class)
//@ActiveProfiles("dev")
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
