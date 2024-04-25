package ru.il.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.il.model.User;

import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setDepartmentId(rs.getInt("department_id"));
        user.setPosition(rs.getString("position"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setIsAdmin(rs.getByte("is_admin"));
        return user;
    };

    public void save(User user) {
        String sql = "INSERT INTO users (first_name, last_name, department_id, position, login, password, is_admin) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getDepartmentId(),
                user.getPosition(), user.getLogin(), user.getPassword(), user.getIsAdmin());
    }

    public Optional<User> findByLogin(String username) {
        String sql = "SELECT * FROM users WHERE login = ?";
        return jdbcTemplate.query(sql, new Object[]{username}, rowMapper).stream().findFirst();
    }
}
