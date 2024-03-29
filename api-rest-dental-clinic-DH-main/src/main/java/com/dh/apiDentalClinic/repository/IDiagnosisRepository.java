package com.dh.apiDentalClinic.repository;

import com.dh.apiDentalClinic.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface IDiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    List<Diagnosis> findByCodeCie10CodeOrCodeCie10Description(String code, String description);

}
