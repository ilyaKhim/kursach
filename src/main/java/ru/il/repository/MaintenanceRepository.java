package ru.il.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.il.model.Department;
import ru.il.model.dao.MaintenanceDao;

import java.util.List;

@Repository
public class MaintenanceRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MaintenanceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<MaintenanceDao> rowMapper = (rs, rowNum) -> {
        MaintenanceDao maintenance = new MaintenanceDao();
        maintenance.setId(rs.getInt("id"));
        maintenance.setEquipmentSerialNumber(rs.getString("serial_number"));
        maintenance.setCreateDate(rs.getDate("create_date"));
        maintenance.setDescription(rs.getString("description"));
        String issuedByFullName = rs.getString("issuedByFirstName") + " " + rs.getString("issuedByLastName");
        String performedByFullName = rs.getString("performedByFirstName") + " " + rs.getString("performedByLastName");
        maintenance.setIssuedByFirstNameLastName(issuedByFullName);
        if (performedByFullName.contains("null")) {
            performedByFullName = "";
        }
        maintenance.setPerformedByFirstNameLastName(performedByFullName);
        return maintenance;
    };

    public void save(Department department) {
        String sql;
        if (department.getId() == 0) {
            sql = "INSERT INTO maintenance (dep_name, building, room) " +
                    "VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, department.getDepName(), department.getBuilding(), department.getRoom());
        } else {
            sql = "UPDATE departments SET dep_name = ?, building = ?, room = ? WHERE id = ?";
            jdbcTemplate.update(sql, department.getDepName(), department.getBuilding(), department.getId(), department.getId());
        }
    }

    public List<MaintenanceDao> findAll() {
        String sql = "SELECT m.id, e.serial_number, m.create_date, m.description,\n" +
                "       i.first_name AS issuedByFirstName, i.last_name AS issuedByLastName,\n" +
                "       p.first_name AS performedByFirstName, p.last_name AS performedByLastName\n" +
                "FROM maintenance m\n" +
                "         JOIN equipments e ON m.equipment_id = e.id\n" +
                "         JOIN users i ON m.issued_by = i.id\n" +
                "         LEFT JOIN users p ON m.performed_by = p.id;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
