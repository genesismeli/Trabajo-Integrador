package com.dh.apiDentalClinic.repository;

import com.dh.apiDentalClinic.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAll(Pageable pageable);
    List<Patient> findByNameContainingOrLastNameContainingOrDniContaining(
            String name, String lastName, String dni);
    boolean existsByDni(String dni);
}
