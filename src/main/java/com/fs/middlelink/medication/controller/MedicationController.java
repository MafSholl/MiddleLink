package com.fs.middlelink.medication.controller;

import com.fs.middlelink.medication.dtos.CreateMedicationDto;
import com.fs.middlelink.medication.services.MedicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @PostMapping("/create-medication")
    public ResponseEntity<?> createMedication(@Valid @RequestBody CreateMedicationDto createMedicationDto) {
        return new ResponseEntity<>(medicationService.createMedication(createMedicationDto), HttpStatus.CREATED);
    }

    @GetMapping("/get-medication")
    public ResponseEntity<?> getMedication(@RequestParam @Valid String medicationName) {
        return new ResponseEntity<>(medicationService.getMedication(medicationName), HttpStatus.OK);
    }

    @DeleteMapping("/delete-medication/{name}")
    public ResponseEntity<?> deleteMedication(@PathVariable String name) {
        return new ResponseEntity<>(medicationService.deleteMedication(name), HttpStatus.OK);
    }
}
