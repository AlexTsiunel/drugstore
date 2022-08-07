package com.company.app.dao.impl;

import com.company.app.dao.DrugDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.model.entity.Drug;
import com.company.app.model.exception.NoCreatedOrUpdatedElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrugDaoImpl implements DrugDao {
    private static final String SELECT_ALL = "SELECT d.id ,d.name, d.release_form, df.name AS dosage_form, ra.name AS route_administration, d.is_recipe, d.price, d.quantity_in_stock, d.deleted \n" +
            "FROM drugs d \n" +
            "JOIN dosage_form df \n" +
            "ON  df.id = d.dosage_form_id\n" +
            "JOIN route_administration ra \n" +
            "ON ra.id  = d.route_administration_id \n" +
            "WHERE d.deleted = FALSE;";
    private static final String SELECT_BY_ID = "SELECT d.id ,d.name, d.release_form, df.name AS dosage_form, ra.name AS route_administration, d.is_recipe, d.price, d.quantity_in_stock, d.deleted \n" +
            "FROM drugs d \n" +
            "JOIN dosage_form df \n" +
            "ON  df.id = d.dosage_form_id\n" +
            "JOIN route_administration ra \n" +
            "ON ra.id  = d.route_administration_id \n" +
            "WHERE d.id = ? AND d.deleted = FALSE;";
    private static final String INSERT = "INSERT INTO drugs (name, release_form, dosage_form_id, route_administration_id, is_recipe, price, quantity_in_stock) VALUES (?, ?, (SELECT id FROM dosage_form df WHERE df.name = ?), (SELECT id FROM route_administration ra WHERE ra.name = ?), ?, ?, ?);";
    private static final String UPDATE = "UPDATE drugs SET name = ?, release_form = ?, dosage_form_id = (SELECT id FROM dosage_form df WHERE df.name = ?), route_administration_id = (SELECT id FROM route_administration ra WHERE ra.name = ?), is_recipe = ?, price = ?, quantity_in_stock = ? WHERE id = ? AND deleted = FALSE";
    private static final String DELETE = "UPDATE drugs SET deleted = TRUE WHERE id = ? AND deleted = FALSE";

    private final DataSource dataSource;
    private static Logger logger = LogManager.getLogger(DrugDaoImpl.class);

    public DrugDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Drug getById(Long id) {
        logger.debug("Database query. Table drugs");
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                logger.debug("Executed method: getById");
                return processEntity(result);
            }
        } catch (SQLException e) {
            logger.error("Method failed: getById", e);
        }
        return null;
    }

    @Override
    public Drug saveOrUpdate(Drug entity) {
        logger.debug("Database query. Table drugs");
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
                    logger.debug("Executed method: saveOrUpdate/save");
                    return getById(id);
                }

            } else {
                statement = connection.prepareStatement(UPDATE);
                processStatement(entity, statement);
                statement.setLong(8, entity.getId());
                statement.executeUpdate();
                logger.debug("Executed method: saveOrUpdate/update");
                return getById(entity.getId());
            }
        } catch (SQLException e) {
            logger.error("Method failed: saveOrUpdate", e);
        }
        throw new NoCreatedOrUpdatedElementException("Failed to add or update drug");
    }

    @Override
    public List<Drug> getAll() {
        logger.debug("Database query. Table drugs");
        try (Connection connection = dataSource.getConnection()) {
            List<Drug> list = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SELECT_ALL);
            while (result.next()) {
                list.add(processEntity(result));
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
        logger.debug("Database query. Table drugs");
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE);
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

    private Drug processEntity(ResultSet result) throws SQLException {
        Drug drug = new Drug();
        drug.setId(result.getLong("id"));
        drug.setName(result.getString("name"));
        drug.setReleaseForm(result.getString("release_form"));
        drug.setDosageForm(Drug.DosageForm.valueOf(result.getString("dosage_form")));
        drug.setRouteAdministration(Drug.RouteAdministration.valueOf(result.getString("route_administration")));
        drug.setIsRecipe(result.getBoolean("is_recipe"));
        drug.setPrice(result.getBigDecimal("price"));
        drug.setQuantityInStock(result.getInt("quantity_in_stock"));
        drug.setDeleted(result.getBoolean("deleted"));
        return drug;
    }

    private void processStatement(Drug entity, PreparedStatement statement) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getReleaseForm());
        statement.setString(3, entity.getDosageForm().toString());
        statement.setString(4, entity.getRouteAdministration().toString());
        statement.setBoolean(5, entity.getIsRecipe());
        statement.setBigDecimal(6, entity.getPrice());
        statement.setInt(7, entity.getQuantityInStock());
    }
}
