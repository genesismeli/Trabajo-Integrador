package com.dh.apiDentalClinic.DTO;

import com.dh.apiDentalClinic.entity.Vademecum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@Getter
@Setter
public class MedicationDTO {

    private Long id;
    private Vademecum vademecum;



}
