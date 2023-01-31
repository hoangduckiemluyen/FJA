package com.shop.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.shop.configs.DatabaseConfig;
import com.shop.core.QueryAdditional;
import com.shop.core.__DAO__;
import com.shop.models.entities.UserEntity;

/**
 * This class use to access user table data from database.
 * Extended __DAO__ abstract class
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public class UserDAO extends __DAO__<UserEntity> {

/* ---- Static constants ---------------------------------------------------- */

    /**
     * This final class contains table columns name
     * @author Hoang Duc Kiem Luyen
     */
    private static final class TCName {

        public static final String 
            ID = DatabaseConfig.TCName_User.ID;
        public static final String 
            CODE = DatabaseConfig.TCName_User.CODE;
        public static final String 
            NAME = DatabaseConfig.TCName_User.NAME;
        public static final String 
            USERNAME = DatabaseConfig.TCName_User.USERNAME;
        public static final String 
            PASSWORD = DatabaseConfig.TCName_User.PASSWORD;
        public static final String 
            PHONE = DatabaseConfig.TCName_User.PHONE;
        public static final String 
            ADDRESS = DatabaseConfig.TCName_User.ADDRESS;
        public static final String 
            IS_ADMIN = DatabaseConfig.TCName_User.IS_ADMIN;
        public static final String 
            CREATED_DATE = DatabaseConfig.TCName_User.CREATED_DATE;
    }


/* ---- Protected: Not allowed to calls ------------------------------------- */

    @Override
    protected UserEntity mapping(ResultSet rs) throws SQLException {

        UserEntity result = new UserEntity();

        result.setID(           rs.getLong(     TCName.ID));
        result.setCode(         rs.getString(   TCName.CODE));
        result.setName(         rs.getString(   TCName.NAME));
        result.setUsername(     rs.getString(   TCName.USERNAME));
        result.setPassword(     rs.getString(   TCName.PASSWORD));
        result.setPhone(        rs.getString(   TCName.PHONE));
        result.setAddress(      rs.getString(   TCName.ADDRESS));
        result.setIsAdmin(      rs.getBoolean(  TCName.IS_ADMIN));
        result.setCreatedDate(  rs.getTimestamp(TCName.CREATED_DATE));
        return result;
    }


/* ---- Public: Instance methods -------------------------------------------- */

    public UserDAO() {
        
        super(DatabaseConfig.TName.USER);
    }

    /**
     * This method use for get data with SQL additional
     * @param additional - Aditional for SQL Query
     * @return - ArrayList<UserEntity>: user entity array list object
     */
    public ArrayList<UserEntity> get(QueryAdditional additional) {

        return this.read(additional);
    }

    /**
     * This method use for get only one row data
     * @param columnName - column name will get data
     * @param columnValue - column value will get data
     * @return - UserEntity: user entity object
     */
    public UserEntity getOne(String columnName, Object columnValue) {

        return this.readOne(columnName, columnValue);
    }

    /**
     * This method use for add row data to table
     * @param entity - user entity object
     * @return - int: amount updated row (-1 is failed)
     */
    public int add(UserEntity entity) {

        Map<String, Object> parameters = new HashMap<>();

        // do not insert id, isadmin and createddate because they create auto
        parameters.put(TCName.CODE,         entity.getCode());
        parameters.put(TCName.NAME,         entity.getName());
        parameters.put(TCName.USERNAME,     entity.getUsername());
        parameters.put(TCName.PASSWORD,     entity.getPassword());
        parameters.put(TCName.PHONE,        entity.getPhone());
        parameters.put(TCName.ADDRESS,      entity.getAddress());

        return this.create(parameters);
    }
    
    /**
     * This method use for edit row data from table
     * @param entity - user entity object
     * @return - int: amount updated row (-1 is failed)
     */
    public int edit(UserEntity entity) {

        String columnName               = TCName.ID;
        Object columnValue              = entity.getID();
        Map<String, Object> parameters  = new HashMap<>();

        // must not edit code, username, isadmin and createddate
        parameters.put(TCName.NAME,     entity.getName());
        parameters.put(TCName.PASSWORD, entity.getPassword());
        parameters.put(TCName.PHONE,    entity.getPhone());
        parameters.put(TCName.ADDRESS,  entity.getAddress());

        return this.update( columnName, columnValue, parameters);
    }

    /**
     * This method use for delete row by id in table
     * @param id - row id
     * @return - int: amount updated row (-1 is failed)
     */
    public int delete(long id) {

        return this.delete(TCName.ID, id);
    }
}
