package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Supplier;
import net.javaguides.springboot.repository.SupplierRepository;
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
