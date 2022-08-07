package com.company.app.dao.impl;

import com.company.app.dao.ClientDao;
import com.company.app.dao.DoctorDao;
import com.company.app.dao.RecipeDao;
import com.company.app.dao.RecipeInfoDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.model.entity.Recipe;
import com.company.app.model.exception.NoCreatedOrUpdatedElementException;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class RecipeDaoImpl implements RecipeDao {
    private final DataSource dataSource;
    private final DoctorDao doctorDao;
    private final ClientDao clientDao;

    private static final String SELECT_ALL = "SELECT r.id, r.client_id, r.doctor_id, r.start_date, r.end_date, r.deleted  FROM recipes r WHERE r.deleted = FALSE";
    private static final String SELECT_BY_ID = "SELECT r.id, r.client_id, r.doctor_id, r.start_date, r.end_date, r.deleted  FROM recipes r WHERE r.id = ? AND r.deleted = FALSE";
    private static final String INSERT = "INSERT INTO recipes (client_id, doctor_id, start_date, end_date) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE recipes SET client_id = ?, doctor_id = ?, start_date= ?, end_date = ? WHERE id = ? AND deleted = FALSE";
    private static final String DELETE = "UPDATE recipes SET deleted = TRUE WHERE id = ? AND deleted = FALSE";
    private static final String SELECT_ALL_BY_DOCTOR_ID = "SELECT r.id, r.client_id, r.doctor_id, r.start_date, r.end_date, r.deleted  FROM recipes r WHERE r.doctor_id = ? AND r.deleted = FALSE";
    private static final String SELECT_ALL_BY_CLIENT_ID = "SELECT r.id, r.client_id, r.doctor_id, r.start_date, r.end_date, r.deleted  FROM recipes r WHERE r.client_id = ? AND r.deleted = FALSE";


    public RecipeDaoImpl(DataSource dataSource, DoctorDao doctorDao, ClientDao clientDao) {
        this.dataSource = dataSource;
        this.doctorDao = doctorDao;
        this.clientDao = clientDao;
    }

    @Override
    public Recipe getById(Long id) {
        log.debug("Database query. Table recipes");
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
    public Recipe saveOrUpdate(Recipe entity) {
        log.debug("Database query. Table recipes");
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
    public List<Recipe> getAll() {
        log.debug("Database query. Table recipes");
        try (Connection connection = dataSource.getConnection()) {
            List<Recipe> list = new ArrayList<>();
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
        log.debug("Database query. Table recipes");
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

    @Override
    public List<Recipe> getAllByDoctorId(long id) {
        log.debug("Database query. Table recipes");
        try (Connection connection = dataSource.getConnection()) {
            List<Recipe> list = getRecipes(connection, SELECT_ALL_BY_DOCTOR_ID, id);
            log.debug("Executed method: getAllByDoctorId");
            return list;
        } catch (SQLException e) {
            log.error("Method failed: getAllByDoctorId", e);
        }
        return null;
    }

    @Override
    public List<Recipe> getAllByClientId(long id) {
        log.debug("Database query. Table recipes");
        try (Connection connection = dataSource.getConnection()) {
            List<Recipe> list = getRecipes(connection, SELECT_ALL_BY_CLIENT_ID, id);
            log.debug("Executed method: getAllByClientId");
            return list;
        } catch (SQLException e) {
            log.error("Method failed: getAllByClientId", e);
        }
        return null;
    }

    private Recipe processEntity(ResultSet result) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(result.getLong("id"));
        recipe.setClient(clientDao.getById(result.getLong("client_id")));
        recipe.setDoctor(doctorDao.getById(result.getLong("doctor_id")));
        recipe.setStartDate(result.getDate("start_date"));
        recipe.setEndDate(result.getDate("end_date"));
        recipe.setDeleted(result.getBoolean("deleted"));
        return recipe;
    }

    private void processStatement(Recipe entity, PreparedStatement statement) throws SQLException {
        statement.setLong(1, entity.getClient().getId());
        statement.setLong(2, entity.getDoctor().getId());
        statement.setDate(3, (Date) entity.getStartDate());
        statement.setDate(4, (Date) entity.getStartDate());
    }

    private List<Recipe> getRecipes(Connection connection, String SqlQuery, long id) throws SQLException {
        List<Recipe> list = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SqlQuery);
        statement.setLong(1, id);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            list.add(processEntity(result));
        }
        return list;
    }
}

