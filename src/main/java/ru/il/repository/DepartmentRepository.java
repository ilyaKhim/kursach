package ru.il.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.il.model.Department;
import ru.il.model.Equipment;

@Repository
public class DepartmentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Department department) {
        String sql;
        if (department.getId() == 0) {
            sql = "INSERT INTO departments (dep_name, building, room) " +
                    "VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, department.getDepName(), department.getBuilding(), department.getRoom());
        } else {
            sql = "UPDATE departments SET dep_name = ?, building = ?, room = ? WHERE id = ?";
            jdbcTemplate.update(sql, department.getDepName(), department.getBuilding(), department.getId(), department.getId());
        }
    }
}
