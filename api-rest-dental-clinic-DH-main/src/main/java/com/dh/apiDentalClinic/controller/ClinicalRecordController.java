package com.dh.apiDentalClinic.controller;

import com.dh.apiDentalClinic.DTO.*;
import com.dh.apiDentalClinic.entity.ClinicalRecord;
import com.dh.apiDentalClinic.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;


@Tag(name = "ClinicalRecord", description = "Operations about clinical Records")
@RestController
@RequestMapping("/clinical")
public class ClinicalRecordController {


    @Autowired
    IClinicalRecordService iclinicalRecordService;

    @Autowired
    IMedicationService medicationService;

    @Autowired
    IDiagnosisService diagnosisService;

    @Autowired
    IPhysicalExamService physicalExamService;

    @Autowired
    IPatientService patientService;

    @Autowired
    public ClinicalRecordController(IClinicalRecordService clinicalRecordService, IPatientService patientService) {
        this.iclinicalRecordService = clinicalRecordService;
        this.patientService = patientService;
    }


    @Operation(summary = "Add clinicalRecord",
            parameters = @Parameter(name = "Authorization", in = HEADER, description = "Json web token required", required = true),
            security = @SecurityRequirement(name = "jwtAuth"))
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ClinicalRecordDTO>> addClinicalRecord(@RequestBody ClinicalRecordDTO clinicalRecordDTO) {
        iclinicalRecordService.saveClinicalRecord(clinicalRecordDTO);
        ApiResponse<ClinicalRecordDTO> response = new ApiResponse<>("Clinical Record created successfully!", clinicalRecordDTO);
        return ResponseEntity.ok(response);
    }


        @Operation(summary = "Find all clinicalRecord")
        @GetMapping("/all")
        public ResponseEntity<Collection<ClinicalRecordDTO>> getAllClinicalRecord() {
            return ResponseEntity.ok(iclinicalRecordService.findAllClinicalRecord());
        }


        @Operation(summary = "Find clinical Record by id",
                parameters = @Parameter(name = "Authorization", in = HEADER, description = "Json web token required", required = true),
                security = @SecurityRequirement(name = "jwtAuth"))
        @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        @GetMapping("/{id}")
        public ResponseEntity<?> getClinicalRecord(@PathVariable Long id) {
            ClinicalRecordDTO clinicalRecordDTO = iclinicalRecordService.findClinicalRecordById(id);
            return new ResponseEntity<>(clinicalRecordDTO, HttpStatus.OK);
        }


        @Operation(summary = "Update an existing clinical Record",
                parameters = @Parameter(name = "Authorization", in = HEADER, description = "Json web token required", required = true),
                security = @SecurityRequirement(name = "jwtAuth"))
        @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        @PutMapping("/update")
        public ResponseEntity<?> updateClinicalRecord(@RequestBody ClinicalRecordDTO clinicalRecordDTO) {
            ResponseEntity<String> response;
            if (iclinicalRecordService.findClinicalRecordById(clinicalRecordDTO.getId()) != null) {
                iclinicalRecordService.updateClinicalRecord(clinicalRecordDTO);
                response = new ResponseEntity<>("Update clinical Record", HttpStatus.CREATED);
            } else {
                response = new ResponseEntity<>("Failed to update, check sent values and id", HttpStatus.BAD_REQUEST);
            }
            return response;

        }

        @Operation(summary = "Update  for id an existing clinical Record",
                parameters = @Parameter(name = "Authorization", in = HEADER, description = "Json web token required", required = true),
                security = @SecurityRequirement(name = "jwtAuth"))
        @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
        @PutMapping("/update/{id}") // Agrega el parámetro {id} en la URL
        public ResponseEntity<?> updateClinicalRecord(@PathVariable Long id, @RequestBody ClinicalRecordDTO clinicalRecordDTO) {
            ResponseEntity<String> response;
            ClinicalRecordDTO existingClinicalRecord = iclinicalRecordService.findClinicalRecordById(id);

            if (existingClinicalRecord != null) {

                clinicalRecordDTO.setId(id); // Asegúrate de establecer el ID del DTO con el ID de la URL
                iclinicalRecordService.updateClinicalRecord(clinicalRecordDTO);
                response = new ResponseEntity<>("clinical Record updated", HttpStatus.CREATED);
            } else {
                response = new ResponseEntity<>("Failed to update clinical Record, check sent values and id", HttpStatus.BAD_REQUEST);
            }

            return response;
        }

        @Operation(summary = "Delete a existing clinical Record",
                parameters = @Parameter(name = "Authorization", in = HEADER, description = "Json web token required", required = true),
                security = @SecurityRequirement(name = "jwtAuth")
        )
        @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('USER') ")
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteClinicRecord(@PathVariable Long id) {
            ResponseEntity<String> response;
            if (iclinicalRecordService.findClinicalRecordById(id) != null) {
                iclinicalRecordService.deleteClinicalRecord(id);
                response = new ResponseEntity<>("Deleted ClinicalRecord with id: " + id, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>("It is not find the clinical Record with the id: " + id, HttpStatus.NOT_FOUND);
            }

            return response;
        }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ClinicalRecordDTO>> getClinicalRecordsByPatientId(@PathVariable Long patientId) {
        List<ClinicalRecordDTO> clinicalRecords = iclinicalRecordService.findClinicalRecordsByPatientId(patientId);
        if (clinicalRecords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clinicalRecords);
    }


}


