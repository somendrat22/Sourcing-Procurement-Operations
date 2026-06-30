package com.spo.core_app.services;

import com.spo.core_app.models.Operation;
import com.spo.core_app.repostories.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Operation> fetchOperationsByName(List<String> operationNames){
        List<Operation> operations = new ArrayList<>();
        for(String operation : operationNames){
            java.util.Optional<Operation> operation1 = operationRepository.findByOperationName(operation);
            if (operation1.isEmpty()){
                log.error("Operation with name " + operation + "does not exist");
                continue;
            }
            operations.add(operation1.get());
        }
        return operations;
    }

}
