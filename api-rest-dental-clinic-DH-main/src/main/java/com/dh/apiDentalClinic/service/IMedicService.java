package com.dh.apiDentalClinic.service;

import com.dh.apiDentalClinic.DTO.MedicDTO;
import com.dh.apiDentalClinic.DTO.PageDTO;
import com.dh.apiDentalClinic.entity.Medic;

import java.util.Collection;
import java.util.List;


public interface IMedicService {

    PageDTO<MedicDTO> findAllMedics(int page, int size);
    MedicDTO findMedicById(Long id);

    void saveMedic(MedicDTO newMedicDTO);

    void deleteMedic(Long id);

    void updateMedic(MedicDTO newMedicDTO);

    MedicDTO convertEntityToDto(Medic medic);

    List<MedicDTO> searchMedics(String name, String lastName, String registrationNumber);
}
