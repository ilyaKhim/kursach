package ru.il.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.il.model.Equipment;
import org.springframework.stereotype.Repository;
import ru.il.model.dao.EquipmentDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<EquipmentDao> rowMapper = (rs, rowNum) -> {
        EquipmentDao equipmentDao = new EquipmentDao();
        equipmentDao.setId(rs.getInt("id"));
        equipmentDao.setEqName(rs.getString("eq_name"));
        equipmentDao.setSerialNumber(rs.getString("serial_number"));
        equipmentDao.setEqStatus(rs.getString("eq_status"));
        equipmentDao.setDepartmentName(rs.getString("dep_name"));
        equipmentDao.setUserFirstName(rs.getString("first_name"));
        equipmentDao.setUserLastName(rs.getString("last_name"));
        return equipmentDao;
    };

    private Equipment mapRowToEquipment(ResultSet resultSet) throws SQLException {
        Equipment equipment = new Equipment();
        equipment.setId(resultSet.getInt("id"));
        equipment.setEqName(resultSet.getString("eq_name"));
        equipment.setSerialNumber(resultSet.getString("serial_number"));
        equipment.setPurchaseDate(resultSet.getDate("purchase_date"));
        equipment.setWarrantyExpiration(resultSet.getDate("warranty_expiration"));
        equipment.setEqStatus(resultSet.getObject("eq_status"));
        equipment.setDepartmentId(resultSet.getInt("department_id"));
        equipment.setSupplierId(resultSet.getInt("supplier_id"));
        equipment.setUserId(resultSet.getInt("user_id"));
        return equipment;
    }

    @Autowired
    public EquipmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Page<EquipmentDao> findAll(Pageable pageable) {
        String sql = "SELECT e.id, e.eq_name, e.serial_number, e.eq_status, d.dep_name, u.first_name, u.last_name " +
                "FROM equipments e " +
                "JOIN departments d ON e.department_id = d.id " +
                "JOIN users u ON e.user_id = u.id";
//        String sql = "SELECT id, eq_name, serial_number, eq_status FROM equipments";

        // Add pagination
        sql += " LIMIT ? OFFSET ?";

        List<EquipmentDao> entities = jdbcTemplate.query(sql, new Object[]{pageable.getPageSize(), pageable.getOffset()}, rowMapper);

        // Fetch total count for pagination
        String countSql = "SELECT COUNT(*) FROM equipments";
        int totalCount = jdbcTemplate.queryForObject(countSql, Integer.class);

        return new PageImpl<>(entities, pageable, totalCount);
    }

    public void save(Equipment equipment) {
        String sql;
        if (equipment.getId() == 0) {
            sql = "INSERT INTO equipments (eq_name, serial_number, purchase_date, warranty_expiration, eq_status, department_id, supplier_id, user_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, equipment.getEqName(), equipment.getSerialNumber(), equipment.getPurchaseDate(),
                    equipment.getWarrantyExpiration(), equipment.getEqStatus(), equipment.getDepartmentId(),
                    equipment.getSupplierId(), equipment.getUserId());
        } else {
            sql = "UPDATE equipments SET eq_name = ?, serial_number = ?, purchase_date = ?, warranty_expiration = ?, eq_status = ?, department_id = ?, supplier_id = ?, user_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, equipment.getEqName(), equipment.getSerialNumber(), equipment.getPurchaseDate(),
                    equipment.getWarrantyExpiration(), equipment.getEqStatus(), equipment.getDepartmentId(),
                    equipment.getSupplierId(), equipment.getUserId(), equipment.getId());
        }
    }

    public Optional<Equipment> findById(long id) {
        String sql = "SELECT * FROM equipments WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, resultSet -> {
            if (resultSet.next()) {
                return Optional.of(mapRowToEquipment(resultSet));
            } else {
                return Optional.empty();
            }
        });
    }

    public void deleteById(long id) {
        String sql = "DELETE FROM equipments WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
