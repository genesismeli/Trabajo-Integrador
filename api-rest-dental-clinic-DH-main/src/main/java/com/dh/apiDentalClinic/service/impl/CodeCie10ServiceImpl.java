package com.dh.apiDentalClinic.service.impl;

import com.dh.apiDentalClinic.entity.CodeCie10;
import com.dh.apiDentalClinic.repository.ICodeCie10Repository;
import com.dh.apiDentalClinic.repository.IDiagnosisRepository;
import com.dh.apiDentalClinic.service.ICodeCie10Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeCie10ServiceImpl implements ICodeCie10Service {

    private final ICodeCie10Repository codeCie10Repository;

    @Autowired
    public CodeCie10ServiceImpl(ICodeCie10Repository codeCie10Repository) {
        this.codeCie10Repository = codeCie10Repository;
    }

    @Autowired
    ObjectMapper mapper;

    @Override
    public List<CodeCie10> getAllCodeCie10Options() {
        return codeCie10Repository.findAll();
    }
}
