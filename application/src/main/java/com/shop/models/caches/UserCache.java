package com.shop.models.caches;

import org.json.JSONArray;

import com.shop.configs.CacheConfig;
import com.shop.core.__Cache__;
import com.shop.core.__System__;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This is user cache storage access. Extended __Cache__ abstract class
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public class UserCache extends __Cache__ {


/* ---- Static constants ---------------------------------------------------- */

    /**
     * This final class contains session name variables
     * @author Hoang Duc Kiem Luyen
     */
    private static final class SName {
        
        public static final String ID       = CacheConfig.SName.USER_ID;
        public static final String CODE     = CacheConfig.SName.USER_CODE;
        public static final String USERNAME = CacheConfig.SName.USER_USERNAME;
        public static final String FULLNAME = CacheConfig.SName.USER_FULLNAME;
        public static final String IS_ADMIN = CacheConfig.SName.USER_IS_ADMIN;
        public static final String CART     = CacheConfig.SName.USER_CART;
    }

    /**
     * This final class contains cookie name variables
     * @author Hoang Duc Kiem Luyen
     */
    private static final class CName {

        public static final String CODE     = CacheConfig.CName.USER_CODE;
    }

    private static final int COOKIE_MAX_AGE = CacheConfig.CAge.USER;


/* ---- Public: Static classes ---------------------------------------------- */

    /**
     * This static class use for collapse session parameters
     * @author Hoang Duc Kiem Luyen
     */
    public static class SessionUserInfo {

        private long        id;
        private String      code;
        private String      username;
        private String      fullname;
        private boolean     isAdmin;
        private JSONArray   cartJson;

        public void setID       (long value)        { this.id         = value; }
        public void setCode     (String value)      { this.code       = value; }
        public void setUsername (String value)      { this.username   = value; }
        public void setFullname (String value)      { this.fullname   = value; }
        public void setIsAdmin  (boolean value)     { this.isAdmin    = value; }
        public void setCartJson (JSONArray value)   { this.cartJson   = value; }

        public long         getID           () { return this.id; }
        public String       getCode         () { return this.code; }
        public String       getUsername     () { return this.username; }
        public String       getFullname     () { return this.fullname; }
        public boolean      getIsAdmin      () { return this.isAdmin; }
        public JSONArray    getCartJson     () { return this.cartJson; }
    }

    public static class CookieUserInfo {

        private String code;

        public void setCode(String value) { this.code = value; }

        public String   getCode() { return this.code; }
    }


/* ---- Public: Instance methods -------------------------------------------- */

    public UserCache(HttpServletRequest request, HttpServletResponse resp) {
        
        super(request, resp);
    }

    /**
     * This method use for set all value to session
     * @param in4 - SessionUserInfo object
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    public void setServerUser(SessionUserInfo in4) {

        this.setSession(SName.ID,         in4.getID());
        this.setSession(SName.CODE,       in4.getCode());
        this.setSession(SName.USERNAME,   in4.getUsername());
        this.setSession(SName.FULLNAME,   in4.getFullname());
        this.setSession(SName.IS_ADMIN,   in4.getIsAdmin());
        this.setSession(SName.CART,       in4.getCartJson());
    }

    /**
     * This method use for set all value to cookie
     * @param in4 - CookieUserInfo object
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    public void setBrowserUser(CookieUserInfo in4) {
        
        this.setCookie(CName.CODE, in4.getCode(), COOKIE_MAX_AGE);
    }

    /**
     * This method use for set user cart value to session
     * @param cartJson - JSONArray object
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    public void setServerCart(JSONArray cartJson) {

        this.setSession(SName.CART, cartJson.toString());
    }


/* ---- Public: Getter ------------------------------------------------------ */

    public long getServerID() {

        return (long) this.getSession(SName.ID);
    }

    public String getServerCode() {

        String result   = null;
        Object codeObj  = this.getSession(SName.CODE);

        if(codeObj != null) {
            result = codeObj.toString();
        }

        return result;
    }

    public String getServerUsername() {

        String result       = null;
        Object sessionObj   = this.getSession(SName.USERNAME);

        if(sessionObj != null) {
            result = sessionObj.toString();
        }

        return result;
    }

    public String getServerFullname() {

        String result       = null;
        Object sessionObj   = this.getSession(SName.FULLNAME);

        if(sessionObj != null) {
            result = sessionObj.toString();
        }

        return result;
    }

    public boolean getServerIsAdmin() {

        boolean result      = false;
        Object  sessionObj  = this.getSession(SName.IS_ADMIN);

        try {
            if(sessionObj != null) {
                String str = sessionObj.toString();
                result = Boolean.parseBoolean(str);
            }
        } 
        catch (Exception e) {
            result = false;
            __System__.PrintLog("UserCache: getIsAdmin: Failed to Parse");
        }

        return result;
    }

    public JSONArray getServerCart() {

        return new JSONArray(this.getSession(SName.CART));
    }


    public String getBrowserCode() {
        
        return this.getCookie(CName.CODE);
    }

}
