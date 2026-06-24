package com.spo.core_app.services;

import com.spo.core_app.models.Operation;
import com.spo.core_app.repostories.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OperationService {

    private OperationRepository operationRepository;

    @Autowired
    public OperationService(OperationRepository operationRepository){
        this.operationRepository = operationRepository;
    }

    public List<Operation> fetchAllProcurementCompanyMaintOperations(){
        return operationRepository.findAll();
    }

}
