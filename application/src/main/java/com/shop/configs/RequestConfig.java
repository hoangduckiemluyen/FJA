package com.shop.configs;

/**
 * This final class declare & define $post & $get variable name.
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public final class RequestConfig {
    

/* ---- Post & Get variable names ------------------------------------------- */

    /**
     * This class contain $post variable name
     * @author Hoang Duc Kiem Luyen
     */
    public static final class Post {

        public static final String SUBMIT       = "$post_submit";
        public static final String USERNAME     = "$post_username";
        public static final String PASSWORD     = "$post_password";
        public static final String PHONE        = "$post_phone";
        public static final String FULLNAME     = "$post_fullname";
    }

    /**
     * This class contain $get variable name
     * @author Hoang Duc Kiem Luyen
     */
    public static final class Get {

        public static final String VARIABLENAME = "$get_variablename";
    }
}
