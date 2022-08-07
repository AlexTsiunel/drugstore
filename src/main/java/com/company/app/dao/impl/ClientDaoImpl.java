package com.company.app.dao.impl;

import com.company.app.dao.ClientDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.model.entity.Client;
import com.company.app.model.exception.NoCreatedOrUpdatedElementException;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ClientDaoImpl implements ClientDao {
    private final DataSource dataSource;


    private static final String SELECT_ALL = "SELECT c.id, c.first_name, c.last_name, c.email, c.password, c.deleted FROM clients c WHERE c.deleted = FALSE";
    private static final String SELECT_BY_ID = "SELECT c.id, c.first_name, c.last_name, c.email, c.password, c.deleted FROM clients c WHERE c.id = ? AND c.deleted = FALSE";
    private static final String INSERT = "INSERT INTO clients (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE clients SET first_name = ?, last_name = ?, email= ?, password = ? WHERE id = ? AND deleted = FALSE";
    private static final String DELETE = "UPDATE clients SET deleted = TRUE WHERE id = ? AND deleted = FALSE";


    public ClientDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Client getById(Long id) {
        log.debug("Database query. Table clients");
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
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
    public Client saveOrUpdate(Client entity) {
        log.debug("Database query. Table clients");
        PreparedStatement statement;
        try (Connection connection = dataSource.getConnection()) {
            if (entity.getId() == null) {
                statement = connection.prepareStatement(INSERT,
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
                statement = connection.prepareStatement(UPDATE);
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
    public List<Client> getAll() {
        log.debug("Database query. Table clients");
        try (Connection connection = dataSource.getConnection()) {
            List<Client> list = new ArrayList<>();
            Statement statement = connection.createStatement();
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
        log.debug("Database query. Table clients");
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE);
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

    private Client processEntity(ResultSet result) throws SQLException {
        Client client = new Client();
        client.setId(result.getLong("id"));
        client.setFirstName(result.getString("first_name"));
        client.setLastName(result.getString("last_name"));
        client.setEmail(result.getString("email"));
        client.setPassword(result.getString("password"));
        client.setDeleted(result.getBoolean("deleted"));
        return client;
    }

    private void processStatement(Client entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getEmail());
        statement.setString(4, entity.getPassword());
    }
}
