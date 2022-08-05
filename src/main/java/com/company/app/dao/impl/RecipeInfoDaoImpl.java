package com.company.app.dao.impl;

import com.company.app.dao.DrugDao;
import com.company.app.dao.RecipeDao;
import com.company.app.dao.RecipeInfoDao;
import com.company.app.dao.connection.DataSource;
import com.company.app.model.entity.Drug;
import com.company.app.model.entity.RecipeInfo;
import com.company.app.model.exception.NoCreatedOrUpdatedElementException;
import lombok.extern.log4j.Log4j2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class RecipeInfoDaoImpl implements RecipeInfoDao {
    private final DataSource dataSource;
    private final DrugDao drugDao;

    private static final String SELECT_ALL = "SELECT ri.id, ri.drug_id, ri.recipe_id, ri.deleted FROM recipe_infos ri WHERE ri.deleted = FALSE";
    private static final String SELECT_BY_ID = "SELECT ri.id, ri.drug_id, ri.recipe_id, ri.deleted FROM recipe_infos ri WHERE ri.id = ? AND deleted = FALSE";
    private static final String INSERT = "INSERT INTO recipe_infos (drug_id, recipe_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE recipe_infos SET drug_id = ?, recipe_id = ?  WHERE id = ? AND deleted = FALSE";
    private static final String DELETE = "UPDATE recipe_infos SET deleted = TRUE WHERE id = ? AND deleted = FALSE";
    private static final String SELECT_ALL_DRUGS_BY_RECIPE_ID = "SELECT ri.drug_id FROM recipe_infos ri WHERE ri.recipe_id = ? AND deleted = FALSE";


    public RecipeInfoDaoImpl(DataSource dataSource, DrugDao drugDao) {
        this.dataSource = dataSource;
        this.drugDao = drugDao;
    }

    @Override
    public RecipeInfo getById(Long id) {
        log.debug("Database query. Table recipeInfos");
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
    public RecipeInfo saveOrUpdate(RecipeInfo entity) {
        log.debug("Database query. Table recipeInfos");
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
                statement.setLong(3, entity.getId());
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
    public List<RecipeInfo> getAll() {
        log.debug("Database query. Table recipeInfos");
        try {
            List<RecipeInfo> list = new ArrayList<>();
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
        log.debug("Database query. Table recipeInfos");
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

    private RecipeInfo processEntity(ResultSet result) throws SQLException {
        RecipeInfo recipeInfo = new RecipeInfo();
        recipeInfo.setId(result.getLong("id"));
        recipeInfo.setDrug(drugDao.getById(result.getLong("drug_id")));
        recipeInfo.setRecipeId(result.getLong("recipe_id"));
        recipeInfo.setDeleted(result.getBoolean("deleted"));
        return recipeInfo;
    }

    private void processStatement(RecipeInfo entity, PreparedStatement statement) throws SQLException {
        statement.setLong(1, entity.getDrug().getId());
        statement.setLong(2, entity.getRecipeId());
    }

    @Override
    public List<Drug> getAllByRecipeId(long id) {
        log.debug("Database query. Table recipe_infos");
        try {
            List<Drug> list = new ArrayList<>();
            PreparedStatement statement = dataSource.getConnection().prepareStatement(SELECT_ALL_DRUGS_BY_RECIPE_ID);
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                list.add(drugDao.getById(result.getLong("drug_id")));
            }
            log.debug("Executed method: getAllDrugsByRecipeId");
            return list;
        } catch (SQLException e) {
            log.error("Method failed: getAllDrugsByRecipeId", e);
        }
        return null;
    }
}
