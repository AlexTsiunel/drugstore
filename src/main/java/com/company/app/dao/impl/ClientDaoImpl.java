package com.company.app.dao.impl;

import com.company.app.dao.ClientDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.model.entity.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private final DataSource dataSource;
    private static Logger logger = LogManager.getLogger(ClientDaoImpl.class);

    private static final String SELECT_ALL = "SELECT c.id, c.first_name, c.last_name, c.email, c.password FROM clients c WHERE c.deleted = FALSE";
    private static final String SELECT_BY_ID = "SELECT c.id, c.first_name, c.last_name, c.email, c.password FROM clients c WHERE c.id = ? AND c.deleted = FALSE";
    private static final String INSERT = "INSERT INTO clients (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE clients SET first_name = ?, last_name = ?, email= ?, password = ? WHERE id = ? AND deleted = FALSE";
    private static final String DELETE = "UPDATE clients SET deleted = TRUE WHERE id = ? AND deleted = FALSE";

    public ClientDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Client getById(Long id) {
        logger.debug("Database query. Table clients");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                logger.debug("Executed method: getById");
                return process(result);
            }
        } catch (SQLException e) {
            logger.error("Method failed: getById", e);
        }
        return null;
    }

    @Override
    public Client saveOrUpdate(Client entity) {
        logger.debug("Database query. Table clients");
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
                    logger.debug("Executed method: saveOrUpdate/save");
                    return getById(id);
                }

            } else {
                statement = dataSource.getConnection().prepareStatement(UPDATE);
                processStatement(entity, statement);
                statement.setLong(5, entity.getId());
                statement.executeUpdate();
                logger.debug("Executed method: saveOrUpdate/update");
                return getById(entity.getId());
            }
        } catch (SQLException e) {
            logger.error("Method failed: saveOrUpdate", e);
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        logger.debug("Database query. Table clients");
        try {
            List<Client> list = new ArrayList<>();
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet result = statement.executeQuery(SELECT_ALL);
            while (result.next()) {
                list.add(process(result));
            }
            logger.debug("Executed method: getAll");
            return list;
        } catch (SQLException e) {
            logger.error("Method failed: getAll", e);
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        logger.debug("Database query. Table clients");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(DELETE);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 1) {
                logger.debug("Executed method: delete");
                return true;
            }
            return false;

        } catch (SQLException e) {
            logger.error("Method failed: delete", e);
        }
        return false;
    }
    private Client process(ResultSet result) throws SQLException {
        Client client = new Client();
        client.setId(result.getLong("id"));
        client.setFirstName(result.getString("first_name"));
        client.setLastName(result.getString("last_name"));
        client.setEmail(result.getString("email"));
        client.setPassword(result.getString("password"));
        return client;
    }
    private void processStatement(Client entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getEmail());
        statement.setString(4, entity.getPassword());
    }
}
