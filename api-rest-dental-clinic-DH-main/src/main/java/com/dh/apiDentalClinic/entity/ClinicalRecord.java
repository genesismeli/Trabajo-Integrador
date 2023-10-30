package com.dh.apiDentalClinic.entity;


import com.dh.apiDentalClinic.DTO.PatientDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "clinical_records")
@Data
@NoArgsConstructor
@Getter
@Setter

public class ClinicalRecord {
    @Id
    @Column(name = "clinical_records_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "clinicalRecord")
    private List<PhysicalExam> physicalExams;

    @OneToMany(mappedBy = "clinicalRecord")
    private List<Medication> medications;

    @OneToMany(mappedBy = "clinicalRecord")
    private  List<Diagnosis> diagnoses;




}

