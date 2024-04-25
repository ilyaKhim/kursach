package ru.il.service;

import ru.il.model.Department;

import java.util.List;

public interface DepartmentService {

    void saveDepartment(Department department);

    List<Department> findAll();
}
