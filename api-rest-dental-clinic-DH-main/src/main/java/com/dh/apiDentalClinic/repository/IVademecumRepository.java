package com.dh.apiDentalClinic.repository;


import com.dh.apiDentalClinic.entity.Vademecum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVademecumRepository extends JpaRepository<Vademecum, Long> {

    List<Vademecum> findAll();
}

