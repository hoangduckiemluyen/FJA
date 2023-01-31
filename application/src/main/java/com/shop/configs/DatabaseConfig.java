package com.shop.configs;

/**
 * This final class declare & define param use for connect to database.
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public final class DatabaseConfig {
    

/* ---- Public: JDBC parameters --------------------------------------------- */

    /**
     * This final class contains JDBC parameters
     * @author Hoang Duc Kiem Luyen
     */
    public static final class Param {

        public static final String DRIVER   = "com.mysql.jdbc.Driver";
        public static final String HOST     = "jdbc:mysql://localhost:3306";
        public static final String DATABASE = "shop";
        public static final String USERNAME = "root";
        public static final String PASSWORD = "210499";
    }


/* ---- Public: Table names ------------------------------------------------- */

    /**
     * This final class contains tables name
     * @author Hoang Duc Kiem Luyen
     */
    public static final class TName {

        public static final String USER      = "user";
        public static final String PRODUCT   = "product";
        public static final String CATEGORY  = "category";
        public static final String SIGNATURE = "signature";
    }
    


/* ---- Table column names -------------------------------------------------- */

    /**
     * This is common table column class
     * @author Hoang Duc Kiem Luyen
     */
    public static class __TableColumn__ {

        public static final String ID               = "id";
        public static final String CODE             = "code";
        public static final String NAME             = "name";
        public static final String CREATED_DATE     = "createddate";
    }

    /**
     * This final class contains table columns name of user table
     * @author Hoang Duc Kiem Luyen
     */
    public static final class TCName_User extends __TableColumn__ {

        public static final String USERNAME         = "username";
        public static final String PASSWORD         = "password";
        public static final String PHONE            = "phone";
        public static final String ADDRESS          = "address";
        public static final String IS_ADMIN         = "isadmin";
    }

    /**
     * This final class contains table columns name of product table
     * @author Hoang Duc Kiem Luyen
     */
    public static final class TCName_Product extends __TableColumn__ {

    }

    /**
     * This final class contains table columns name of category table
     * @author Hoang Duc Kiem Luyen
     */
    public static final class TCName_Category extends __TableColumn__ {

    }

}
