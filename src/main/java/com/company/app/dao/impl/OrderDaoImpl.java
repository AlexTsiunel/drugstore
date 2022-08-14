package com.company.app.dao.impl;

import com.company.app.dao.ClientDao;
import com.company.app.dao.OrderDao;
import com.company.app.dao.OrderInfoDao;
import com.company.app.dao.PharmacistDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.model.entity.Order;
import com.company.app.model.entity.OrderInfo;
import com.company.app.model.entity.Pharmacist;
import com.company.app.model.exception.NoCreatedOrUpdatedElementException;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class OrderDaoImpl implements OrderDao {
    private final DataSource dataSource;
    private final ClientDao clientDao;
    private final PharmacistDao pharmacistDao;
    private final OrderInfoDao orderInfoDao;

    private static final String SELECT_ALL = "SELECT o.id, o.pharmacist_id, o.client_id, o.total_coast, os.name AS status, o.deleted  \n" +
            "FROM orders o \n" +
            "JOIN orderstatuses os \n" +
            "ON os.id = o.orderstatus_id \n" +
            "WHERE o.deleted = FALSE";
    private static final String SELECT_BY_ID = "SELECT o.id, o.pharmacist_id, o.client_id, o.total_coast, os.name AS status, o.deleted \n" +
            "FROM orders o \n" +
            "JOIN orderstatuses os \n" +
            "ON os.id = o.orderstatus_id \n" +
            "WHERE o.id = ? AND o.deleted = FALSE";
    private static final String INSERT = "INSERT INTO orders (pharmacist_id, client_id, total_coast, orderstatus_id) VALUES (?, ?, ?, (SELECT id FROM orderstatuses WHERE name = ?))";
    private static final String UPDATE = "UPDATE orders SET pharmacist_id = ?, client_id = ?, total_coast= ?, orderstatus_id = (SELECT id FROM orderstatuses WHERE name = ?) WHERE id = ? AND deleted = FALSE";
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
    public Order saveOrUpdate(Order entity) {
        log.debug("Database query. Table orders");
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
                    List<OrderInfo> orderInfoList = entity.getOrderInfoList();
                    for (OrderInfo orderInfo : orderInfoList) {
                        orderInfo.setOrderId(id);
                        orderInfoDao.saveOrUpdate(orderInfo);
                    }
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
    public List<Order> getAll() {
        log.debug("Database query. Table orders");
        try (Connection connection = dataSource.getConnection()) {
            List<Order> list = new ArrayList<>();
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
        log.debug("Database query. Table orders");
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
    public List<Order> getAllByClientId(long id) {
        log.debug("Database query. Table orders");
        try (Connection connection = dataSource.getConnection()) {
            List<Order> list = getOrders(connection, SELECT_ALL_BY_CLIENT_ID, id);
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
        try (Connection connection = dataSource.getConnection()) {
            List<Order> list = getOrders(connection, SELECT_ALL_BY_PHARMACIST_ID, id);
            log.debug("Executed method: getAllByPharmacistId");
            return list;
        } catch (SQLException e) {
            log.error("Method failed: getAllByPharmacistId", e);
        }
        return null;
    }

    private Order processEntity(ResultSet result) throws SQLException {
        Order order = new Order();
        long id = result.getLong("id");
        order.setId(id);
        order.setClient(clientDao.getById(result.getLong("client_id")));
        try {
            long pharmacistId = result.getLong("pharmacist_id");
            order.setPharmacist(pharmacistDao.getById(pharmacistId));
        } catch (Exception e) {
            order.setPharmacist(null);
        }
        order.setTotalCoast(result.getBigDecimal("total_coast"));
        order.setStatus(Order.OrderStatus.valueOf(result.getString("status")));
        order.setOrderInfoList(orderInfoDao.getByOrderId(id));
        order.setDeleted(result.getBoolean("deleted"));
        return order;
    }

    private void processStatement(Order entity, PreparedStatement statement) throws SQLException {
        statement.setLong(2, entity.getClient().getId());
        Pharmacist pharmacist = entity.getPharmacist();
        if (pharmacist == null) {
            statement.setNull(1, Types.BIGINT);
        } else {
            statement.setLong(1, entity.getPharmacist().getId());
        }
        statement.setBigDecimal(3, entity.getTotalCoast());
        statement.setString(4, entity.getStatus().toString());

    }

    private List<Order> getOrders(Connection connection, String SqlQuery, long id) throws SQLException {
        List<Order> list = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(SqlQuery);
        statement.setLong(1, id);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            list.add(processEntity(result));
        }
        return list;
    }
}
