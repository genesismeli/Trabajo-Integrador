package com.dh.apiDentalClinic.repository;

import com.dh.apiDentalClinic.entity.Medic;
import com.dh.apiDentalClinic.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicRepository extends JpaRepository<Medic, Long> {
    Page<Medic> findAll(Pageable pageable);
    List<Medic> findByNameContainingOrLastNameContainingOrRegistrationNumberContaining(String name, String lastName, String registrationNumber);
}
