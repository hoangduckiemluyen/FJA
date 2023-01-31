package com.shop.core;

/**
 * Additonal for read data from database.
 * @author Hoang Duc Kiem Luyen
 */
public class QueryAdditional {
    



/* ---- Private: Instance properties ---------------------------------------- */

    
    private String      __SQLQueryColumns;
    private String      __SQLWhereCondition;
    private String      __SQLSortByColumn;
    private String      __SQLLimit;


/* ---- Public: Static declarations ----------------------------------------- */

    /**
     * This enum contains sql where condition options type.
     * @apiNote LIKE - Where column Like ... condition
     * @apiNote SINGLE - Where column = ... condition
     * @apiNote MULTI - Where column in (...) condition
     * @author Hoang Duc Kiem Luyen
     */
    public static enum ConditionType{ LIKE, SINGLE, MULTI }


/* ---- Public: Instance methods -------------------------------------------- */

    public QueryAdditional() {
        
        this.__SQLQueryColumns      = "";
        this.__SQLWhereCondition    = "";
        this.__SQLSortByColumn      = "";
        this.__SQLLimit             = "";
    }


/* ---- Public: Getter & setter --------------------------------------------- */

    /**
     * Set sql query columns (default is *) after 'Select' keyword.
     * Example: No call or no parameters then sql = Select * From `table_name`.
     * @param queryColumns - columns need select in table
     * @return void
     * @author Hoang Duc Kiem Luyen
     * @deprecated
     */
    @Deprecated
    public void setQueryColumn(String... queryColumns) {

        if(queryColumns.length > 0) {
            for (byte i = 0; i < queryColumns.length; i++) {
                if(i > 0) {
                    this.__SQLQueryColumns += ", ";
                }
                this.__SQLQueryColumns += queryColumns[i];
            }
        } 
        else {
            this.__SQLQueryColumns = " * ";
        }
    }

    /**
     * Set sql query condition.
     * @param type - condition type (Value = MULTI to match all condition column
     *  values to condition column name)
     * @param columnName - column name in condition
     * @param columnValue - one or multiple condition value
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    public void setCondition(   ConditionType type, 
                                String columnName, 
                                String... columnValue) {

        this.__SQLWhereCondition = columnName;
        if(columnName != null && columnValue != null) {

            if(type.equals(ConditionType.LIKE)) {
                if(columnValue.length < 1) {
                    return;
                }
                // read only first columnValue element
                this.__SQLWhereCondition = " Where `" 
                + columnName + "' Like '"   + columnValue[0] + "%' Or `" 
                + columnName + "` Like '%"  + columnValue[0] + "' Or `" 
                + columnName + "` LIKE '%"  + columnValue[0] + "%' ";
            } 
            else if(type.equals(ConditionType.SINGLE)) {
                if(columnValue.length < 1) {
                    return;
                }
                // read only first columnValue element
                this.__SQLWhereCondition = " Where `" + columnName 
                + "` = '" + columnValue[0] + "' ";
            } 
            else if(type.equals(ConditionType.MULTI)) {
                this.__SQLWhereCondition = " Where `" + columnName + "` in (";
                for(int i = 0; i < columnValue.length; i++) {
                    if(i > 0) {
                        this.__SQLWhereCondition += ", ";
                    }
                    this.__SQLWhereCondition += columnValue[i];
                }
                this.__SQLWhereCondition += ") ";
            }
        }
    }

    /**
     * Set sql order by column name and ascending or descending
     * @param column - column name in table
     * @param asc - ascending. if value equal true then query data ascending
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    public void setSort(String column, boolean asc) {

        if(column != null && !column.equals("")) {
            this.__SQLSortByColumn += " Order By `" + column
            + "` " + (asc ? " ASC " :" DESC ");
        }
    }

    /**
     * Set sql limit with limit & offset
     * @param offset - query begin position of row
     * @param limit - amount row will query
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    public void setLimit(long offset, byte limit) {
        
        if(offset >= 0 && limit >=0) {
            this.__SQLLimit += " limit " + offset + "," + limit;
        }
    }

    /**
     * Return SQL Query Column(s) string
     * Example: Select query_columns from table_name
     * @deprecated
     * @author Hoang Duc Kiem Luyen
     * @deprecated
     */
    @Deprecated
    public String getSQLQueryColumns() {

        return  this.__SQLQueryColumns;
    }

    /**
     * Return SQL Query Column(s) string
     * Example: Select query_columns from table_name
     * @return String - sql where condition
     * @author Hoang Duc Kiem Luyen
     */
    public String getSQLCondition() {

        return  this.__SQLWhereCondition;
    }

    /**
     * Generate sql sort element
     * @return String - order by column_name asc (desc)
     * @author Hoang Duc Kiem Luyen
     */
    public String getSQLSort() {

        return this.__SQLSortByColumn;
    }

    /**
     * Generate sql limit elements
     * @return String - limit offset, limit
     * @author Hoang Duc Kiem Luyen
     */
    public String getSQLLimit() {

        return this.__SQLLimit;
    }

}
