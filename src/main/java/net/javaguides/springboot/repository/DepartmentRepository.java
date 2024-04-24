package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Department;
import net.javaguides.springboot.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
