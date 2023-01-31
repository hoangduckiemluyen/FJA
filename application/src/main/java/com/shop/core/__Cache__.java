package com.shop.core;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class is cache abstract class.
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public abstract class __Cache__ {
    

/* ---- Instance properties ------------------------------------------------- */

    private HttpServletRequest  __Request;
    private HttpServletResponse __Response;


/* ---- Public: Instance methods -------------------------------------------- */

    protected __Cache__ (HttpServletRequest req, HttpServletResponse resp) {

        this.__Request  = req;
        this.__Response = resp;
    }

    /**
     * This method use for clear cache data (session & cookie)
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    public void cleanCache() {

        // clean session
        this.__Request.getSession().invalidate();

        // clean cookie
        Cookie[] cookies = this.__Request.getCookies();
        for(Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            this.__Response.addCookie(cookie);
        }
    }


/* ---- Protected: Instance methods ----------------------------------------- */

    /**
     * This method use for create or edit session variable
     * @param name - String: Session name will set
     * @param value - Object: Session value will set
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    protected void setSession(String name, Object value) {

        this.__Request.getSession().setAttribute(name, value);
    }

    /**
     * This method use for create or edit cookie variable
     * @param name - String: Cookie name will set
     * @param value - String: Cookie value will set
     * @param expiry - int: Cookie max age (seconds)
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    protected void setCookie(String name, String value, int expiry) {

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(expiry);
        this.__Response.addCookie(cookie);
    }

    /**
     * This method use for create or edit cookie variable
     * @param cookie - Cookie object
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    protected void setCookie(Cookie cookie) {

        this.__Response.addCookie(cookie);
    }

    /**
     * This method use for get session value
     * @param name - String: Session name
     * @return Object: session value
     * @author Hoang Duc Kiem Luyen
     */
    protected Object getSession(String name) {

        return this.__Request.getSession().getAttribute(name);
    }

    /**
     * This method use for get cookie value
     * @param name - String: cookie name
     * @return Object: cookie value
     * @author Hoang Duc Kiem Luyen
     */
    protected String getCookie(String name) {

        Cookie[]    cookies = this.__Request.getCookies();
        String      result  = null;

        for(Cookie cookie : cookies) {
            if(name.equals(cookie.getName())) {
                result = cookie.getValue();
            }
        }

        return result;
    }

}
