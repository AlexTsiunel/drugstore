package com.company.app.dao.impl;

import com.company.app.dao.OrderDao;
import com.company.app.dao.PharmacistDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.model.entity.Pharmacist;
import com.company.app.model.exception.NoCreatedOrUpdatedElementException;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class PharmacistDaoImpl implements PharmacistDao {
    private final DataSource dataSource;


    private static final String SELECT_ALL = "SELECT p.id, p.first_name, p.last_name, p.email, p.password FROM pharmacists p WHERE p.deleted = FALSE";
    private static final String SELECT_BY_ID = "SELECT p.id, p.first_name, p.last_name, p.email, p.password FROM pharmacists p WHERE p.id = ? p.deleted = FALSE";
    private static final String INSERT = "INSERT INTO pharmacists (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE pharmacists SET first_name = ?, last_name = ?, email= ?, password = ? WHERE id = ? AND deleted = FALSE";
    private static final String DELETE = "UPDATE pharmacists SET deleted = TRUE WHERE id = ? AND deleted = FALSE";

    public PharmacistDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Pharmacist getById(Long id) {
        log.debug("Database query. Table pharmacists");
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
    public Pharmacist saveOrUpdate(Pharmacist entity) {
        log.debug("Database query. Table pharmacists");
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
    public List<Pharmacist> getAll() {
        log.debug("Database query. Table pharmacists");
        try {
            List<Pharmacist> list = new ArrayList<>();
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
        log.debug("Database query. Table pharmacists");
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
    private Pharmacist processEntity(ResultSet result) throws SQLException {
        Pharmacist pharmacist = new Pharmacist();
        pharmacist.setId(result.getLong("id"));
        pharmacist.setFirstName(result.getString("first_name"));
        pharmacist.setLastName(result.getString("last_name"));
        pharmacist.setEmail(result.getString("email"));
        pharmacist.setPassword(result.getString("password"));
        pharmacist.setDeleted(result.getBoolean("deleted"));
        return pharmacist;
    }

    private void processStatement(Pharmacist entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getEmail());
        statement.setString(4, entity.getPassword());
    }
}
