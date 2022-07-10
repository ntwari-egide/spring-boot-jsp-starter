package com.cpswork.backend.controllers;

import com.cpswork.backend.dtos.PatientDto;
import com.cpswork.backend.models.Patient;
import com.cpswork.backend.serviceImpl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    private PatientServiceImpl patientService;

    @GetMapping("/get-all")
    public List<Patient> getAll() {
        return patientService.findAll();
    }
    @GetMapping("/{id}")
    public Patient findById(@PathVariable UUID id) {
        return patientService.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PatientDto patientDto) {
        Patient patientSaved = patientService.create(patientDto);

        return ResponseEntity.ok(patientSaved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody PatientDto patientDto) {
        Patient patientUpdated = patientService.update(id, patientDto);

        return ResponseEntity.ok(patientUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Patient patientFound = patientService.delete(id);

        return ResponseEntity.ok(patientFound);
    }

}
