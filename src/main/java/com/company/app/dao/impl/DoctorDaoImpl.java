package com.company.app.dao.impl;

import com.company.app.dao.DoctorDao;
import com.company.app.dao.RecipeDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.model.entity.Doctor;
import com.company.app.model.exception.NoCreatedOrUpdatedElementException;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DoctorDaoImpl implements DoctorDao {

    private final DataSource dataSource;

    private static final String SELECT_ALL = "SELECT d.id, d.first_name, d.last_name, d.email, d.password, d.deleted FROM doctors d WHERE d.deleted = FALSE";
    private static final String SELECT_BY_ID = "SELECT d.id, d.first_name, d.last_name, d.email, d.password, d.deleted FROM doctors d WHERE d.id = ? AND d.deleted = FALSE";
    private static final String INSERT = "INSERT INTO doctors (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE doctors SET first_name = ?, last_name = ?, email= ?, password = ? WHERE id = ? AND deleted = FALSE";
    private static final String DELETE = "UPDATE doctors SET deleted = TRUE WHERE id = ? AND deleted = FALSE";


    public DoctorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Doctor getById(Long id) {
        log.debug("Database query. Table doctors");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                log.debug("Executed method: getById");
                return processEntity(result);
            }
        } catch (SQLException e) {
            log.error("Method failed: getById", e);
        }
        return null;
    }

    @Override
    public Doctor saveOrUpdate(Doctor entity) {
        log.debug("Database query. Table doctors");
        PreparedStatement statement;
        try {
            if (entity.getId() == null) {
                statement = dataSource.getConnection().prepareStatement(INSERT,
                        Statement.RETURN_GENERATED_KEYS);
                processStatement(entity, statement);
                statement.executeUpdate();
                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    Long id = keys.getLong(1);
                    log.debug("Executed method: saveOrUpdate/save");
                    return getById(id);
                }

            } else {
                statement = dataSource.getConnection().prepareStatement(UPDATE);
                processStatement(entity, statement);
                statement.setLong(5, entity.getId());
                statement.executeUpdate();
                log.debug("Executed method: saveOrUpdate/update");
                return getById(entity.getId());
            }
        } catch (SQLException e) {
            log.error("Method failed: saveOrUpdate", e);
        }
        throw new NoCreatedOrUpdatedElementException("Failed to add or update client");
    }

    @Override
    public List<Doctor> getAll() {
        log.debug("Database query. Table doctors");
        try {
            List<Doctor> list = new ArrayList<>();
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet result = statement.executeQuery(SELECT_ALL);
            while (result.next()) {
                list.add(processEntity(result));
            }
            log.debug("Executed method: getAll");
            return list;
        } catch (SQLException e) {
            log.error("Method failed: getAll", e);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        log.debug("Database query. Table doctors");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(DELETE);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 1) {
                log.debug("Executed method: delete");
                return true;
            }
            return false;

        } catch (SQLException e) {
            log.error("Method failed: delete", e);
        }
        return false;
    }

    private Doctor processEntity(ResultSet result) throws SQLException {
        Doctor doctor = new Doctor();
        doctor.setId(result.getLong("id"));
        doctor.setFirstName(result.getString("first_name"));
        doctor.setLastName(result.getString("last_name"));
        doctor.setEmail(result.getString("email"));
        doctor.setPassword(result.getString("password"));
        doctor.setDeleted(result.getBoolean("deleted"));
        return doctor;
    }

    private void processStatement(Doctor entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getEmail());
        statement.setString(4, entity.getPassword());
    }
}
