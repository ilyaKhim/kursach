package ru.il.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.il.model.Department;
import ru.il.model.Equipment;
import ru.il.model.Maintenance;
import ru.il.model.dao.MaintenanceDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    private Maintenance mapRow(ResultSet rs) throws SQLException {
        Maintenance maintenance = new Maintenance();
        maintenance.setId(rs.getInt("id"));
        maintenance.setEquipmentId(rs.getInt("equipment_id"));
        maintenance.setCreateDate(rs.getDate("create_date"));
        maintenance.setDescription(rs.getString("description"));
        maintenance.setIssuedBy(rs.getInt("issued_by"));
        maintenance.setPerformedBy(rs.getInt("performed_by")); // Integer is nullable
        return maintenance;
    }

    public void save(Maintenance maintenance) {
        String sql;
        if (maintenance.getId() == 0) {
            sql = "INSERT INTO maintenance (equipment_id, create_date, description, issued_by, performed_by) " +
                    "VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, maintenance.getEquipmentId(), maintenance.getCreateDate(),
                    maintenance.getDescription(), maintenance.getIssuedBy(),
                    maintenance.getPerformedBy());
        } else {
            sql = "UPDATE maintenance SET equipment_id = ?, create_date = ?, description = ?, " +
                    "issued_by = ?, performed_by = ? WHERE id = ?";
            jdbcTemplate.update(sql, maintenance.getEquipmentId(), maintenance.getCreateDate(),
                    maintenance.getDescription(), maintenance.getIssuedBy(),
                    maintenance.getPerformedBy(), maintenance.getId());
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

    public Optional<Maintenance> findById(long id) {
        String sql = "SELECT * FROM maintenance WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, resultSet -> {
            if (resultSet.next()) {
                return Optional.of(mapRow(resultSet));
            } else {
                return Optional.empty();
            }
        });
    }
}
