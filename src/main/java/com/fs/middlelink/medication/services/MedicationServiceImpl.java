package com.fs.middlelink.medication.services;

import com.fs.middlelink.medication.dtos.GetMedicationDto;
import com.fs.middlelink.medication.exception.exceptions.MedicationException;
import com.fs.middlelink.medication.models.CreateMedicationDto;
import com.fs.middlelink.medication.models.DeleteMedicationDto;
import com.fs.middlelink.medication.models.Medication;
import com.fs.middlelink.medication.repository.MedicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicationServiceImpl implements MedicationService{

    private MedicationRepository medicationRepository;
    private ModelMapper modelMapper;

    public MedicationServiceImpl(MedicationRepository medicationRepository, ModelMapper modelMapper) {
        this.medicationRepository = medicationRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public CreateMedicationDto createMedication(CreateMedicationDto createMedicationRequest) {
        if (createMedicationRequest == null) throw new MedicationException("Request cannot be null");
        Optional<Medication> foundMedication = medicationRepository.findByMedicationName(
                createMedicationRequest.getMedicationName());
        if (foundMedication.isPresent()) throw new MedicationException("Medication already exist!!!");
        Medication newMed = modelMapper.map(createMedicationRequest, Medication.class);
        Medication savedMed = medicationRepository.save(newMed);
        return modelMapper.map(savedMed, CreateMedicationDto.class);
    }

    @Override
    public GetMedicationDto getMedication(String medicationName) {
        if (medicationName == null) throw new MedicationException("Request cannot be null");
        Optional<Medication> foundMedication = medicationRepository.findByMedicationName(medicationName);
        if (foundMedication.isEmpty()) throw new MedicationException("Medication does not exist!");
        return modelMapper.map(foundMedication.get(), GetMedicationDto.class);
    }

    @Override
    public DeleteMedicationDto deleteMedication(String medicationName) {
        DeleteMedicationDto deleteMedicationDto = null;
        try {
            getMedication(medicationName);
            medicationRepository.deleteByMedicationName(medicationName);
            String message = "Medication deleted successfully.";
            deleteMedicationDto = new DeleteMedicationDto(message, true);
        }
        catch (MedicationException e) {
            if (e.getMessage().equals("Medication does not exist!")){
                deleteMedicationDto = new DeleteMedicationDto(e.getMessage(), false);
                return deleteMedicationDto;
            }
        }
        return deleteMedicationDto;
    }
}
