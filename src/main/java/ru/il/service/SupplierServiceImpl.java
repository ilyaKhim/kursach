package ru.il.service;

import ru.il.model.Supplier;
import ru.il.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public void saveSupplier(Supplier supplier) {
        this.supplierRepository.save(supplier);
    }
}
