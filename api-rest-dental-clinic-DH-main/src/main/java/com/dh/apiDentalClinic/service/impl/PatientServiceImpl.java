package com.dh.apiDentalClinic.service.impl;

import com.dh.apiDentalClinic.DTO.MedicDTO;
import com.dh.apiDentalClinic.DTO.PatientDTO;

import com.dh.apiDentalClinic.entity.Medic;
import com.dh.apiDentalClinic.entity.Patient;
import com.dh.apiDentalClinic.exception.ResourceNotFoundException;
import com.dh.apiDentalClinic.repository.IPatientRepository;
import com.dh.apiDentalClinic.service.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PatientServiceImpl implements IPatientService {

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    ObjectMapper mapper;

    public void saveMethod(PatientDTO patientDTO) {
        if (patientDTO != null) {
            Patient patient = mapper.convertValue(patientDTO, Patient.class);
            patientRepository.save(patient);
        } else {
            throw new ResourceNotFoundException("Medic", "id", "id not found: " + patientDTO.getId());
        }

    }


    @Override
    public Collection<PatientDTO> findAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        Set<PatientDTO> patientDTO = new HashSet<>();
        for (Patient patient : patients) {
            patientDTO.add(mapper.convertValue(patient, PatientDTO.class));
        }
        return patientDTO;

    }

    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }


    @Override
    public PatientDTO findPatientById(Long id) {

        Patient patient = patientRepository.findById(id).get();
        PatientDTO patientDTO = null;
        if (patient != null) {
            patientDTO = mapper.convertValue(patient, PatientDTO.class);
        }
        return patientDTO;
    }

    @Override
    public void savePatient(PatientDTO newPatientDTO) {
        saveMethod(newPatientDTO);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id).get();
        patientRepository.deleteById(id);
    }

    @Override
    public void updatePatient(PatientDTO newPatientDTO) {
        saveMethod(newPatientDTO);
    }

    @Override
    public PatientDTO convertEntityToDto(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setName(patient.getName());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setDni(patient.getDni());
        patientDTO.setBirthdate(patient.getBirthdate());
        patientDTO.setGender(patient.getGender());
        patientDTO.setAdress(patient.getAdress());
        patientDTO.setPhone(patient.getPhone());
        patientDTO.setEmail(patient.getEmail());

        // Mapea cualquier otro campo que pueda tener la entidad Patient a su DTO correspondiente

        return patientDTO;
    }

}
