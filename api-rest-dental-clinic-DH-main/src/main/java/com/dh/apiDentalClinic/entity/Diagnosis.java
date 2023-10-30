package com.dh.apiDentalClinic.entity;

import com.dh.apiDentalClinic.converters.DiagnosisStatusConverter;
import com.dh.apiDentalClinic.enums.DiagnosisStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "diagnosis") // Nombre de la tabla en la base de datos
public class Diagnosis {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Convert(converter = DiagnosisStatusConverter.class)
    @Column(name = "status")
    private DiagnosisStatus status;

    @Column(name = "date")
    private Date date;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "clinical_records_id")
    private ClinicalRecord clinicalRecord;

}

