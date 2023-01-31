package com.shop.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shop.configs.DatabaseConfig;

/**
 * This class is abstract database access class
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public abstract class __DBAccess__ {
    
/* ---- Static constants ---------------------------------------------------- */

    private static final String DRIVER      = DatabaseConfig.Param.DRIVER;
    private static final String HOST        = DatabaseConfig.Param.HOST;
    private static final String DATABASE    = DatabaseConfig.Param.DATABASE;
    private static final String USERNAME    = DatabaseConfig.Param.USERNAME;
    private static final String PASSWORD    = DatabaseConfig.Param.PASSWORD;


/* ---- Instance properties ------------------------------------------------- */

    private     Connection              __Conn;     // connection variable
    private     PreparedStatement       __Stmt;     // statement variable
    private     ResultSet               __Rset;     // result set varivable


/* ---- private: Instance methods ----------------------------------------- */

    /**
     * This method create connection to database.
     * @return boolean (false for failed)
     * @author Hoang Duc Kiem Luyen
     */
    private boolean connect() {
        
        boolean result = false;

        try {
            // delete host name end slash, url = host_url/database
            String url = HOST.replaceAll("/*$", "") + "/" + DATABASE;

            Class.forName(DRIVER);
            this.__Conn = DriverManager.getConnection(url, USERNAME, PASSWORD);
            result = true;
        } 
        catch (   ClassNotFoundException | SQLException e) {
            __System__.PrintLog("Error __DBAccess__: Failed to connect");
            result = false;
        }

        return result;
    }

    /**
     * This method close connection to database.
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    private void disconect() {

        try {
            if(this.__Conn  != null)    this.__Conn.close();
            if(this.__Stmt  != null)    this.__Stmt.close();
            if(this.__Rset  != null)    this.__Rset.close();
        } 
        catch (SQLException e) {
            __System__.PrintLog("Error __DBAccess__: Failed to disconect");
        }
    }


/* ---- Protected: Instance methods ----------------------------------------- */

    /**
     * This method use for update data (create, edit, delete)
     * @param sql - execute update SQL string
     * @return int: amount updated rows (-1 is failed)
     * @author Hoang Duc Kiem Luyen
     */
    protected int executeUpdate(String sql) {

        int result = -1;

        if(this.connect()) {
            try {
                this.__Stmt = this.__Conn.prepareStatement(sql);
                result = this.__Stmt.executeUpdate();
            }
            catch(SQLException e) {
                __System__.PrintLog("Error __DBAccess__: failed to execute update: " 
                + sql);
                result = -1;
            }
        }
        this.disconect();

        return result;
    }

    /**
     * This method use for query data
     * @param sql - execute query SQL string
     * @return ArrayList<Entity class>: Array list Object
     * @author Hoang Duc Kiem Luyen
     */
    protected ResultSet executeQuery(String sql) {

        if(this.connect()) {
            try {
                this.__Stmt = this.__Conn.prepareStatement(sql);
                this.__Rset = this.__Stmt.executeQuery();
            } 
            catch (Exception e) {
                __System__.PrintLog("Error __DBAccess__: failed to execute query: " 
                + sql);
                this.__Rset = null;
            }
        }
        this.disconect();

        return this.__Rset;
    }

    /**
     * This method use for count items
     * @param sql - execute query SQL string
     * @param countColumn - "count" table column name
     * @return long: Counted items
     * @author Hoang Duc Kiem Luyen
     * @deprecated
     */
    @Deprecated
    protected long executeCountItems(String sql, String countColumn) {

        long result = 0;

        if(this.connect()) {
            try {
                this.__Stmt = this.__Conn.prepareStatement(sql);
                this.__Rset = this.__Stmt.executeQuery();
                this.__Rset.next();
                result = this.__Rset.getLong(countColumn);
            } 
            catch (Exception e) {
                __System__.PrintLog("Error __DBAccess__: failed to execute count: " 
                + sql);
                result = 0;
            }
        }
        this.disconect();

        return result;
    }
}
