package com.dh.apiDentalClinic.service;

import com.dh.apiDentalClinic.entity.CodeCie10;
import com.dh.apiDentalClinic.entity.Vademecum;
import com.dh.apiDentalClinic.repository.ICodeCie10Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IVademecumService {

    List<Vademecum> getAllVademecumOptions();


}
