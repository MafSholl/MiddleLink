package com.fs.middlelink.medication.repository;

import com.fs.middlelink.medication.models.Medication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationRepository extends CrudRepository<Medication, String> {
    Optional<Medication> findByMedicationName(String medicationName);

    void deleteByMedicationName(String medicationName);
}
