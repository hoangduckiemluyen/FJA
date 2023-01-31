package com.shop.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class is abstract data access class
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public abstract class __DAO__<Entity> extends __DBAccess__ {


/* ---- Instance properties --------------------------------------------------*/

    private String __TableName;

/* ---- Protected: Abstract instance methods -------------------------------- */
    
    /**
     * -------------- Not allowed to call -------------- <br> 
     * Mapping all data from table to java object list (Entity classes list). 
     * called (loop) by read(SQL_Additional, true (false)) method
     * Note: use ResultSet(rs) to mapping with EntityClass(entity)
     * @param rs - result data from table
     * @return Entity Object: Mapped Entity with ResultSet (rs)
     * @author Hoang Duc Kiem Luyen
     * @throws SQLException
     */
    protected abstract Entity mapping(ResultSet rs) throws SQLException;


/* ---- Protected: Instance methods ----------------------------------------- */

    protected __DAO__(String tableName) {
        
        this.__TableName = tableName;
    }

    /**
     * This method use to insert data into table
     * @param parameters - Map of column names & column values: 
     * key = column name, value = column value
     * @return int: amount created rows (-1 is failed);
     * @author Hoang Duc Kiem Luyen
     */ 
    protected int create(Map<String, Object> parameters) {

        int result      = -1;
        String sql      = "Insert Into `" + this.__TableName + "`";
        String sqlName  = " (";
        String sqlValue = " Values (";

        // set names & values SQL
        byte index = 0;
        for(Map.Entry<String, Object> element : parameters.entrySet()) {
            if(index != 0) {
                sqlName     += ", ";
                sqlValue    += ", ";
            }
            sqlName     += "`" + element.getKey() + "`";
            sqlValue    += "'" + element.getValue() + "'";
            index       ++;
        }
        sqlName     += ")";
        sqlValue    += ")";
        sql         += sqlName + sqlValue;

        result = this.executeUpdate(sql);

        return result;
    }

    /**
     * This method use to get data from table.
     * @param additional - SQLAdditional object
     * @return ArrayList<EntityClass>: entity array list object
     * @author Hoang Duc Kiem Luyen
     */ 
    protected ArrayList<Entity> read(QueryAdditional additional) {

        ArrayList<Entity>  result    = new ArrayList<>();

        String sql = "Select * From `" 
        + this.__TableName + "` "      
        + additional.getSQLCondition()
        + additional.getSQLSort() 
        + additional.getSQLLimit();

        ResultSet rs = this.executeQuery(sql);

        try {
            while(rs.next()) {
                result.add(mapping(rs));
            }
        } catch (SQLException e) {
            result = new ArrayList<>();
            __System__.PrintLog("Error __DAO__: execute update");
        }

        return result;
    }

    /**
     * This method use to get "only one row" data from table.
     * @param columnName - column name will query
     * @param columnValue - column value will query
     * @return Entity: entity object
     * @author Hoang Duc Kiem Luyen
     */ 
    protected Entity readOne(String columnName, Object columnValue) {

        Entity result   = null;
        String sql      = "Select * From `" + this.__TableName + "` Where `" 
                        + columnName + "` = '" + columnValue + "'";

        ResultSet rs = this.executeQuery(sql);
        try {
            rs.next();
            result = this.mapping(rs);
        } catch (SQLException e) {
            __System__.PrintLog("Error __DAO__: execute update");
            result = null;
        }

        return result;
    }

    /**
     * This method use to get counted rows from table.
     * @param additional - SQLAdditional object
     * @return long: counted items
     * @author Hoang Duc Kiem Luyen
     */ 
    protected long getCountedItems(QueryAdditional additional) {

        long    result = 0;

        String  sql = "Select count(*) as count From `"
        + this.__TableName + "` "      
        + additional.getSQLCondition()
        + additional.getSQLSort() 
        + additional.getSQLLimit();

        ResultSet rs = executeQuery(sql);
        try {
            rs.next();
            result = rs.getLong("count");
        } catch (SQLException e) {
            __System__.PrintLog("Error __DAO__: execute query");
            result = 0;
        }

        return result;
    }

    /**
     * This method use to edit table data.
     * @param columnName - column name will update
     * @param columnValue - column value will update
     * @param parameters - Map of column names & column values: 
     * key = column name, value = column value
     * @return int: amount updated rows (-1 is failed)
     * @author Hoang Duc Kiem Luyen
     */ 
    protected int update(   String columnName, 
                            Object columnValue, 
                            Map<String, Object> parameters) {

        int result      = -1;
        String sql      = "Update Table `" + this.__TableName + "` Set ";

        // set names values to SQL varivable
        byte index = 0;
        for(Map.Entry<String, Object> element : parameters.entrySet()) {
            if(index != 0) {
                sql += ", ";
            }
            sql     += "`" + element.getKey() + "` = '" 
                    + element.getValue() + "'";
            index   ++;
        }
        sql         += " Where `" + columnName + "` = '" + columnValue + "'";

        result = this.executeUpdate(sql);

        return result;
    }

    /**
     * This method use to delete data from database.
     * @param columnName - column name will delete
     * @param columnValue - column value will delete
     * @return int: amount deleted rows (-1 is failed)
     * @author Hoang Duc Kiem Luyen
     */ 
    protected int delete(String columnName, Object columnValue) {

        int result = -1;
        String sql = "Delete `" + this.__TableName + "` Where `" 
        + columnName + "` = '" + columnValue + "'";

        result = this.executeUpdate(sql);
        
        return result;
    }

}
