package ru.il.service;

import ru.il.model.Supplier;

import java.util.List;

public interface SupplierService {
    void saveSupplier(Supplier supplier);

    List<Supplier> findAll();
}
