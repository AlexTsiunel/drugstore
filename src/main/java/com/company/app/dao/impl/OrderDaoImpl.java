package com.company.app.dao.impl;

import com.company.app.dao.ClientDao;
import com.company.app.dao.OrderDao;
import com.company.app.dao.OrderInfoDao;
import com.company.app.dao.PharmacistDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.model.entity.Order;
import com.company.app.model.exception.NoCreatedOrUpdatedElementException;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class OrderDaoImpl implements OrderDao {
    private final DataSource dataSource;
    private final ClientDao clientDao;
    private final PharmacistDao pharmacistDao;
    private final OrderInfoDao orderInfoDao;

    private static final String SELECT_ALL = "SELECT o.id, o.pharmacist_id, o.client_id, o.total_coast, o.orderstatus_id FROM orders o WHERE o.deleted = FALSE";
    private static final String SELECT_BY_ID = "SELECT o.id, o.pharmacist_id, o.client_id, o.total_coast, o.orderstatus_id FROM orders o WHERE o.id = ? o.deleted = FALSE";
    private static final String INSERT = "INSERT INTO orders (pharmacist_id, client_id, total_coast, orderstatus_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE orders SET pharmacist_id = ?, client_id = ?, total_coast= ?, orderstatus_id = ? WHERE id = ? AND deleted = FALSE";
    private static final String DELETE = "UPDATE orders SET deleted = TRUE WHERE id = ? AND deleted = FALSE";
    private static final String SELECT_ALL_BY_CLIENT_ID = "SELECT o.id, o.pharmacist_id, o.client_id, o.total_coast, o.orderstatus_id FROM orders o WHERE o.client_id = ? AND o.deleted = FALSE";
    private static final String SELECT_ALL_BY_PHARMACIST_ID = "SELECT o.id, o.pharmacist_id, o.client_id, o.total_coast, o.orderstatus_id FROM orders o WHERE o.pharmacist_id = ? AND o.deleted = FALSE";

    public OrderDaoImpl(DataSource dataSource, ClientDao clientDao, PharmacistDao pharmacistDao, OrderInfoDao orderInfoDao) {
        this.dataSource = dataSource;
        this.clientDao = clientDao;
        this.pharmacistDao = pharmacistDao;
        this.orderInfoDao = orderInfoDao;
    }

    @Override
    public Order getById(Long id) {
        log.debug("Database query. Table orders");
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
    public Order saveOrUpdate(Order entity) {
        log.debug("Database query. Table orders");
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
    public List<Order> getAll() {
        log.debug("Database query. Table orders");
        try {
            List<Order> list = new ArrayList<>();
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
        log.debug("Database query. Table orders");
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
    public List<Order> getAllByClientId(long id) {
        log.debug("Database query. Table orders");
        try {
            List<Order> list = getOrders(SELECT_ALL_BY_CLIENT_ID, id);
            log.debug("Executed method: getAllByClientId");
            return list;
        } catch (SQLException e) {
            log.error("Method failed: getAllByClientId", e);
        }
        return null;
    }

    @Override
    public List<Order> getAllByPharmacistId(long id) {
        log.debug("Database query. Table orders");
        try {
            List<Order> list = getOrders(SELECT_ALL_BY_PHARMACIST_ID, id);
            log.debug("Executed method: getAllByPharmacistId");
            return list;
        } catch (SQLException e) {
            log.error("Method failed: getAllByPharmacistId", e);
        }
        return null;
    }

    private Order processEntity(ResultSet result) throws SQLException {
        Order order = new Order();
        order.setClient(clientDao.getById(result.getLong("client_id")));
        order.setPharmacist(pharmacistDao.getById(result.getLong("pharmacist_id")));
        order.setDrugs(orderInfoDao.getMapDrugs(result.getLong("id")));
        order.setTotalCoast(result.getBigDecimal("total_coast"));
        order.setStatus(Order.OrderStatus.valueOf(result.getString("status")));
        order.setDeleted(result.getBoolean("deleted"));
        return order;
    }

    private void processStatement(Order entity, PreparedStatement statement) throws SQLException {
        statement.setLong(1, entity.getClient().getId());
        statement.setLong(2, entity.getClient().getId());
        statement.setBigDecimal(3, entity.getTotalCoast());
        statement.setString(4, entity.getStatus().toString());
    }

    private List<Order> getOrders(String SqlQuery, long id) throws SQLException {
        List<Order> list = new ArrayList<>();
        PreparedStatement statement = dataSource.getConnection().prepareStatement(SqlQuery);
        statement.setLong(1, id);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            list.add(processEntity(result));
        }
        return list;
    }
}
