package com.company.app.dao.impl;

import com.company.app.dao.DragDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.dao.entity.Drug;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DrugDaoImpl implements DragDao {
    private static final String SELECT_ALL = "SELECT d.id ,d.name, d.release_form, df.name AS dosage_form, ra.name AS route_administration, d.is_recipe, d.price, d.quantity_in_stock \n" +
            "FROM drugs d \n" +
            "JOIN dosage_form df \n" +
            "ON  df.id = d.dosage_form_id\n" +
            "JOIN route_administration ra \n" +
            "ON ra.id  = d.route_administration_id \n" +
            "WHERE d.deleted = FALSE;";
    private static final String SELECT_BY_ID = "SELECT d.id ,d.name, d.release_form, df.name AS dosage_form, ra.name AS route_administration, d.is_recipe, d.price, d.quantity_in_stock \n" +
            "FROM drugs d \n" +
            "JOIN dosage_form df \n" +
            "ON  df.id = d.dosage_form_id\n" +
            "JOIN route_administration ra \n" +
            "ON ra.id  = d.route_administration_id \n" +
            "WHERE d.id = ? AND d.deleted = FALSE;";
    private static final String INSERT = "INSERT INTO drugs d (d.name, d.release_form, d.dosage_form_id, d.route_administration_id, d.is_recipe, d.price, d.quantity_in_stock) VALUES (?, ?, (SELECT id FROM dosage_form df WHERE df.name = ?), (SELECT id FROM route_administration ra WHERE ra.name = ?) ?, ?, ?)";
    private static final String UPDATE = "UPDATE drugs SET id, name, release_form, dosage_form_id, route_administration_id, is_recipe, price, quantity_in_stock WHERE id = ? AND deleted = FALSE";
    private static final String DELETE = "UPDATE drugs SET deleted = TRUE WHERE id = ? AND deleted = FALSE";

    private final DataSource dataSource;
    private static Logger logger = LogManager.getLogger(DrugDaoImpl.class);

    public DrugDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Drug getById(Long id) {
        logger.debug("Database query. Table drugs.");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return process(result);
            }
        } catch (SQLException e) {
            logger.error("Failed to query the database. Table drugs.", e);
        }
        return null;
    }

    @Override
    public Drug saveOrUpdate(Drug entity) {
        logger.debug("Database query. Table drugs.");
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
                    return getById(id);
                }

            } else {
                statement = dataSource.getConnection().prepareStatement(UPDATE);
                processStatement(entity, statement);
                statement.setLong(8, entity.getId());
                statement.executeUpdate();
                return getById(entity.getId());
            }
        } catch (SQLException e) {
            logger.error("Failed to query the database. Table drugs.", e);
        }
        return null;
    }

    @Override
    public List<Drug> getAll() {
        logger.debug("Database query. Table drugs.");
        try {
            List<Drug> list = new ArrayList<>();
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet result = statement.executeQuery(SELECT_ALL);
            while (result.next()) {
                list.add(process(result));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        logger.debug("Database query. Table drugs.");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(DELETE);
            statement.setLong(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted == 1;

        } catch (SQLException e) {
            logger.error("Failed to query the database. Table drugs.", e);
        }
        return false;
    }
    private Drug process(ResultSet result) throws SQLException {
        Drug drug = new Drug();
        drug.setId(result.getLong("id"));
        drug.setName(result.getString("name"));
        drug.setReleaseForm(result.getString("release_form"));
        drug.setDosageForm(Drug.DosageForm.valueOf(result.getString("dosage_form")));
        drug.setRouteAdministration(Drug.RouteAdministration.valueOf(result.getString("route_administration")));
        drug.setIsRecipe(result.getBoolean("is_recipe"));
        drug.setPrice(result.getBigDecimal("price"));
        drug.setQuantityInStock(result.getInt("quantity_in_stock"));
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
