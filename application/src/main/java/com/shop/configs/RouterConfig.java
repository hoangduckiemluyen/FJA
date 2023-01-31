package com.shop.configs;

/**
 * This final class declare & define route (controller & 
 * action).
 * Constant have "_ALL" suffix use for controller name with action.
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public class RouterConfig {
    

/* ---- Controller router --------------------------------------------------- */

    /**
     * This class contains controller constants
     * @author Hoang Duc Kiem Luyen
     */
    public final class Controller {


        /**
         * This final class contains WEB url patterns
         * @author Hoang Duc Kiem Luyen
         */
        public static class Web {
    
            public static final String HOME         = "/home";
            public static final String STORE        = "/store";
            public static final String STORE_ALL    = "/store/*";
            public static final String PRODUCT      = "/product";
            public static final String PRODUCT_ALL  = "/product/*";
            public static final String CART         = "/product";
            public static final String CONTACT      = "/contact";
            public static final String ABOUT        = "/about";
        }
    
        /**
         * This final class contains ADMIN url patterns
         * @author Hoang Duc Kiem Luyen
         */
        public static final class Admin {
    
            public static final String ADMIN_HOME       = "/admin";
            public static final String ADMIN_HOME_ALL   = "/admin/*";
        }
        
        /**
         * This final class contains COMMON url patterns
         * @author Hoang Duc Kiem Luyen
         */
        public static final class Common {

            public static final String ACCOUNT          = "/account";
            public static final String ACCOUNT_ALL      = "/account/*";
        }
        
    }


/* ---- Controller router --------------------------------------------------- */

    /**
     * This class contains action constants
     * @author Hoang Duc Kiem Luyen
     */
    public static final class Action {

        /**
         * This class contains account action route
         * @author Hoang Duc Kiem Luyen
         */
        public class Account {

            public static final String DEFAULT              = "default";
            public static final String SIGN_IN              = "sign-in";
            public static final String SIGN_UP              = "sign-up";
            public static final String SIGN_OUT             = "sign-out";
            public static final String PHONE_VERIFICATION   = "phone-verification";
            public static final String CHANGE_PASSWORD      = "change-password";
            public static final String FORGOT_PASSWORD      = "forgot-password";
            public static final String PROFILE              = "profile";
        }

    }

}
