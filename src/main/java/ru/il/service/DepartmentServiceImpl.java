package ru.il.service;

import ru.il.model.Department;
import ru.il.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void saveDepartment(Department department) {
        this.departmentRepository.save(department);
    }

    @Override
    public List<Department> findAll() {
        return this.departmentRepository.findAll();
    }
}
