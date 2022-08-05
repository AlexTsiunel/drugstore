package com.company.app.dao.impl;

import com.company.app.dao.DrugDao;
import com.company.app.dao.OrderDao;
import com.company.app.dao.OrderInfoDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.model.entity.Drug;
import com.company.app.model.entity.OrderInfo;
import com.company.app.model.exception.NoCreatedOrUpdatedElementException;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log4j2
public class OrderInfoDaoImpl implements OrderInfoDao {
    private final DataSource dataSource;
    private final DrugDao drugDao;


    private static final String SELECT_ALL = "SELECT  oi.id, oi.drug_id, oi.order_id, oi.drug_quantity, oi.drug_price FROM order_infos oi WHERE oi.deleted = FALSE";
    private static final String SELECT_BY_ID = "SELECT  oi.id, oi.drug_id, oi.order_id, oi.drug_quantity, oi.drug_price FROM order_infos oi WHERE oi.id = ? AND oi.deleted = FALSE";
    private static final String INSERT = "INSERT INTO order_infos (drug_id, order_id, drug_quantity, drug_price) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE order_infos SET drug_id = ?, order_id = ?, drug_quantity= ?, drug_price = ? WHERE id = ? AND deleted = FALSE";
    private static final String DELETE = "UPDATE order_infos SET deleted = TRUE WHERE id = ? AND deleted = FALSE";
    private static final String SELECT_DRUGS_MAP = "SELECT  oi.drug_id, oi.drug_quantity FROM order_infos oi WHERE oi.order_id = ? AND oi.deleted = FALSE";


    public OrderInfoDaoImpl(DataSource dataSource, DrugDao drugDao) {
        this.dataSource = dataSource;
        this.drugDao = drugDao;
    }

    @Override
    public OrderInfo getById(Long id) {
        log.debug("Database query. Table order_infos");
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
    public OrderInfo saveOrUpdate(OrderInfo entity) {
        log.debug("Database query. Table order_infos");
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
        throw new NoCreatedOrUpdatedElementException("Failed to add or update order_info");
    }

    @Override
    public List<OrderInfo> getAll() {
        log.debug("Database query. Table order_infos");
        try {
            List<OrderInfo> list = new ArrayList<>();
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
        log.debug("Database query. Table order_infos");
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

    @Override
    public Map<Drug, Integer> getMapDrugs(Long id) {

        log.debug("Database query. Table order_infos");
        try {
            Map<Drug, Integer> map = new HashMap<>();
            Statement statement = dataSource.getConnection().createStatement();
            ResultSet result = statement.executeQuery(SELECT_ALL);
            while (result.next()) {
                Long drugId = result.getLong("drug_id");
                Integer drugQuantity = result.getInt("drug_quantity");
                map.put(drugDao.getById(drugId), drugQuantity);
            }
            log.debug("Executed method: getAll");
            return map;
        } catch (SQLException e) {
            log.error("Method failed: getAll", e);
        }
        return null;
    }

    private OrderInfo processEntity(ResultSet result) throws SQLException {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setDrug(drugDao.getById(result.getLong("drug_id")));
        orderInfo.setOrderId(result.getLong("order_id"));
        orderInfo.setDrugQuantity(result.getInt("drug_quantity"));
        orderInfo.setDrugPrice(result.getBigDecimal("drug_price"));
        orderInfo.setDeleted(result.getBoolean("deleted"));

        return orderInfo;
    }

    private void processStatement(OrderInfo entity, PreparedStatement statement) throws SQLException {
        statement.setLong(1, entity.getDrug().getId());
        statement.setLong(2, entity.getOrderId());
        statement.setInt(3, entity.getDrugQuantity());
        statement.setBigDecimal(4, entity.getDrugPrice());
    }
}
