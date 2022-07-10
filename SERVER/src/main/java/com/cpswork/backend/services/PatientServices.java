package com.cpswork.backend.services;

import com.cpswork.backend.dtos.PatientDto;
import com.cpswork.backend.models.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PatientServices {

    public List<Patient> findAll();

    public Patient findById(UUID id);

    public Patient create(PatientDto patientDto);

    public Patient update(UUID id, PatientDto patient);

    public Patient delete(UUID id);


}
