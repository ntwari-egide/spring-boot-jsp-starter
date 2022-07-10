package com.cpswork.backend.serviceImpl;

import com.cpswork.backend.dtos.PatientDto;
import com.cpswork.backend.exceptions.PatientNotFoundException;
import com.cpswork.backend.models.Patient;
import com.cpswork.backend.repositories.PatientRepository;
import com.cpswork.backend.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientServices {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient checkExistence(UUID id) {
        Optional<Patient> patientFound = patientRepository.findById(id);

        if(!patientFound.isPresent()) throw new PatientNotFoundException("Patient with id " + id+ " is not found");

        return patientFound.get();
    }

    @Override
    public Patient findById(UUID id) {
        return checkExistence(id);
    }

    @Override
    public Patient create(PatientDto patientDto) {

        Patient patient = new Patient(
                patientDto.getFullName(),
                patientDto.getAge(),
                patientDto.getPhoneNumber()
        );

        return patientRepository.save(patient);
    }

    @Override
    public Patient update(UUID id, PatientDto patient) {

        // checking existence

        Patient patientFound = checkExistence(id);

        patientFound.setFullName(patient.getFullName());
        patientFound.setAge(patient.getAge());
        patientFound.setPhoneNumber(patient.getPhoneNumber());

        return patientRepository.save(patientFound);
    }

    @Override
    public Patient delete(UUID id) {
        // check existence of patient

        Patient patientFound = checkExistence(id);

        patientRepository.deleteById(id);

        return patientFound;
    }
}
