package com.dh.apiDentalClinic.controller;

import com.dh.apiDentalClinic.entity.CodeCie10;
import com.dh.apiDentalClinic.entity.Vademecum;
import com.dh.apiDentalClinic.service.ICodeCie10Service;
import com.dh.apiDentalClinic.service.IVademecumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;

@Tag(name = "Vademecum", description = "Operations about vademecum de medicamentos")
@RestController
@RequestMapping("/vademecum")

public class VamedecumController {

    @Autowired
    private final IVademecumService vademecumService;

    @Autowired
    public VamedecumController(IVademecumService vademecumService) {
        this.vademecumService = vademecumService;
    }

    @Operation(summary = "Consulta de vamedecum")
    @GetMapping("/options")
    public ResponseEntity<List<Vademecum>> getAllVamedecumOptions() {
        List<Vademecum> vamedecumOptions = vademecumService.getAllVademecumOptions();
        return ResponseEntity.ok(vamedecumOptions);
    }
}


