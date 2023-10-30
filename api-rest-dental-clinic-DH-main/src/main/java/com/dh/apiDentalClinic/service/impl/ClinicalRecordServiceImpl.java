package com.dh.apiDentalClinic.service.impl;


import com.dh.apiDentalClinic.DTO.ClinicalRecordDTO;
import com.dh.apiDentalClinic.DTO.DiagnosisDTO;
import com.dh.apiDentalClinic.DTO.MedicationDTO;
import com.dh.apiDentalClinic.DTO.PhysicalExamDTO;
import com.dh.apiDentalClinic.entity.ClinicalRecord;
import com.dh.apiDentalClinic.entity.Diagnosis;
import com.dh.apiDentalClinic.entity.Medication;
import com.dh.apiDentalClinic.entity.PhysicalExam;
import com.dh.apiDentalClinic.exception.ResourceNotFoundException;

import com.dh.apiDentalClinic.repository.IClinicalRecordRepository;
import com.dh.apiDentalClinic.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClinicalRecordServiceImpl implements IClinicalRecordService {

    @Autowired
    public ClinicalRecordServiceImpl(IClinicalRecordRepository clinicalRecordRepository) {
        this.clinicalRecordRepository = clinicalRecordRepository;
    }

    @Autowired
    private IClinicalRecordRepository clinicalRecordRepository;

    @Autowired
    private IDiagnosisService diagnosisService;

    @Autowired
    private IMedicationService medicationService;

    @Autowired
    private IPhysicalExamService physicalExamService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    ObjectMapper mapper;

    public void saveMethod(ClinicalRecordDTO clinicalRecordDTO) {
        if (clinicalRecordDTO != null) {
            ClinicalRecord clinicalRecord = mapper.convertValue(clinicalRecordDTO, ClinicalRecord.class);
            clinicalRecordRepository.save(clinicalRecord);
        } else {
            throw new ResourceNotFoundException("ClinicalRecord", "id", "id not found: " + clinicalRecordDTO.getId());
        }

    }

    @Override
    public Collection<ClinicalRecordDTO> findAllClinicalRecord() {
        List<ClinicalRecord> clinicalRecords = clinicalRecordRepository.findAll();
        Set<ClinicalRecordDTO> clinicalRecordDTO = new HashSet<>();

        for (ClinicalRecord clinicalRecord : clinicalRecords) {
            clinicalRecordDTO.add(mapper.convertValue(clinicalRecord, ClinicalRecordDTO.class));
        }
        return clinicalRecordDTO;

    }

    @Override
    public ClinicalRecordDTO findClinicalRecordById(Long id) {
        ClinicalRecord clinic = clinicalRecordRepository.findById(id).get();
        ClinicalRecordDTO clinicalRecordDTO = null;
        if (clinic != null) {
            clinicalRecordDTO = mapper.convertValue(clinic, ClinicalRecordDTO.class);
        }
        return clinicalRecordDTO;
    }

    @Override
    public void saveClinicalRecord(ClinicalRecordDTO newClinicalRecordDTO) {
        saveMethod(newClinicalRecordDTO);
    }

    @Override
    public void deleteClinicalRecord(Long id) {
        ClinicalRecord clinicalRecord = clinicalRecordRepository.findById(id).get();
        clinicalRecordRepository.deleteById(id);


    }

    @Override
    public void updateClinicalRecord(ClinicalRecordDTO newClinicalRecordDTO) {
        saveMethod(newClinicalRecordDTO);
    }

    @Override
    public List<ClinicalRecordDTO> findClinicalRecordsByPatientId(Long patientId) {
        Optional<ClinicalRecord> clinicalRecords = clinicalRecordRepository.findByPatientId(patientId);
        return clinicalRecords.stream()
                .map(clinicalRecord -> mapper.convertValue(clinicalRecord, ClinicalRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClinicalRecordDTO convertEntityToDto(ClinicalRecord clinicalRecord) {
        ClinicalRecordDTO clinicalRecordDTO = new ClinicalRecordDTO();
        clinicalRecordDTO.setId(clinicalRecord.getId());

        // Mapea el ID del paciente
        if (clinicalRecord.getPatient() != null) {
            clinicalRecordDTO.setPatient(patientService.convertEntityToDto(clinicalRecord.getPatient()));
        }

        // Mapea los PhysicalExams
        List<PhysicalExamDTO> physicalExamDTOs = new ArrayList<>();
        for (PhysicalExam physicalExam : clinicalRecord.getPhysicalExams()) {
            PhysicalExamDTO physicalExamDTO = physicalExamService.convertEntityToDto(physicalExam);
            physicalExamDTOs.add(physicalExamDTO);
        }
        clinicalRecordDTO.setPhysicalExams(physicalExamDTOs);

        // Mapea las Medications
        List<MedicationDTO> medicationDTOs = new ArrayList<>();
        for (Medication medication : clinicalRecord.getMedications()) {
            MedicationDTO medicationDTO = medicationService.convertEntityToDto(medication);
            medicationDTOs.add(medicationDTO);
        }
        clinicalRecordDTO.setMedications(medicationDTOs);

        // Mapea los Diagnoses
        List<DiagnosisDTO> diagnosisDTOs = new ArrayList<>();
        for (Diagnosis diagnosis : clinicalRecord.getDiagnoses()) {
          DiagnosisDTO diagnosisDTO = diagnosisService.convertEntityToDto(diagnosis);
          diagnosisDTOs.add(diagnosisDTO);
        }
        clinicalRecordDTO.setDiagnoses(diagnosisDTOs);

        return clinicalRecordDTO;
        }

    }



