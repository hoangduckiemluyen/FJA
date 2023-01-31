package com.shop.configs;

/**
 * This final class declare & define session name & cookie name (max age).
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public final class CacheConfig {
    

/* ---- Public: Session name constants -------------------------------------- */

    /**
     * This final class contains session variables name
     * @author Hoang Duc Kiem Luyen
     */
    public static final class SName {

        public static final String  USER_ID         = "s_userid";
        public static final String  USER_CODE       = "s_usercode";
        public static final String  USER_USERNAME   = "s_userusername";
        public static final String  USER_FULLNAME   = "s_userusername";
        public static final String  USER_IS_ADMIN   = "s_userisadmin";
        public static final String  USER_CART       = "s_usercart";
    }


/* ---- Public: Cookie name constants --------------------------------------- */

    /**
     * This final class contains cookie variables name
     * @author Hoang Duc Kiem Luyen
     */
    public static final class CName {

        public static final String  USER_CODE       = "c_usercode";
    }


/* ---- Public: Cookie max age constants ------------------------------------ */

    /**
     * This final class contains values of cookie ages
     * @author Hoang Duc Kiem Luyen
     */
    public static final class CAge {
        public static final int     USER            = 60 * 60 * 24 * 30;
    }

}
