package com.dh.apiDentalClinic.service.impl;

import com.dh.apiDentalClinic.entity.Vademecum;
import com.dh.apiDentalClinic.repository.IVademecumRepository;
import com.dh.apiDentalClinic.service.IVademecumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VademecumServiceImpl implements IVademecumService {

    private final IVademecumRepository vademecumRepository;

    @Autowired
    public VademecumServiceImpl(IVademecumRepository vademecumRepository) {
        this.vademecumRepository = vademecumRepository;
    }

    @Autowired
    ObjectMapper mapper;

    @Override
    public List<Vademecum> getAllVademecumOptions() {
        return vademecumRepository.findAll();
    }


}
