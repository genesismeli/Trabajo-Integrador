package com.dh.apiDentalClinic.service;

import com.dh.apiDentalClinic.DTO.ClinicalRecordDTO;
import com.dh.apiDentalClinic.entity.ClinicalRecord;


import java.util.Collection;
import java.util.List;

public interface IClinicalRecordService {

    List<ClinicalRecordDTO> findClinicalRecordsByPatientId(Long patientId);

    Collection<ClinicalRecordDTO> findAllClinicalRecord();

    ClinicalRecordDTO findClinicalRecordById(Long id);

    void saveClinicalRecord(ClinicalRecordDTO newClinicalRecordDTO);

    void deleteClinicalRecord(Long id);

    void updateClinicalRecord(ClinicalRecordDTO newClinicalRecordDTO);

    ClinicalRecordDTO convertEntityToDto(ClinicalRecord clinicalRecord);
}
