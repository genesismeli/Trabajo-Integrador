package com.dh.apiDentalClinic.repository;

import com.dh.apiDentalClinic.entity.ClinicalRecord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClinicalRecordRepository extends JpaRepository<ClinicalRecord, Long> {
        Optional<ClinicalRecord> findByPatientId(Long patientId);
    }



