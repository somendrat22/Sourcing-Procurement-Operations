package com.spo.core_app.services;

import com.spo.core_app.models.SupplierCompany;
import com.spo.core_app.repostories.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierCompany> getAllSuppliers(){
        return this.supplierRepository.findAll();
    }

}
