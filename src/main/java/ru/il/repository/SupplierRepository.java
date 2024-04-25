package ru.il.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.il.model.Department;
import ru.il.model.Supplier;

import java.util.List;

@Repository
public class SupplierRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SupplierRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Supplier> rowMapper = (rs, rowNum) -> {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getInt("id"));
        supplier.setCompanyName(rs.getString("company_name"));
        supplier.setContactInfo(rs.getString("contact_info"));
        supplier.setAddress(rs.getString("address"));
        return supplier;
    };

    public void save(Supplier supplier) {
        String sql = "INSERT INTO suppliers (company_name, contact_info, address) " +
                    "VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, supplier.getCompanyName(), supplier.getContactInfo(), supplier.getAddress());
    }

    public List<Supplier> findAll() {
        String sql = "SELECT * FROM suppliers";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
