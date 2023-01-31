package com.shop.core;

import java.io.IOException;
import java.util.ArrayList;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class is abstract action class
 */
public abstract class __Action__ {
    
/* ---- Instance properties ------------------------------------------------- */

    private     boolean             __IsRedirect;
    private     String              __JSPPath;
    private     String              __RedirectPath;

    protected   HttpServletRequest  __Request;
    protected   HttpServletResponse __Response;


/* ---- Abstract methods ---------------------------------------------------- */

    /**
     * -------------------------- NOT ALLOWED TO CALL --------------------------
     * This method was called by doAction() method.
     * Define JSP implicit in this method.
     * @param req - HttpServletRequest: Object (using this object to set request
     * attributes (JSP implicit))
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    protected abstract void setRequestAttribute(HttpServletRequest req);

    /**
     * Use this method to call all "private" action instance methods follow 
     * parameter value
     * @param action - String: call action instance method follow action value
     * @param params - ArrayList<String>: url parameters
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    public abstract void setAction(String action, ArrayList<String> params);

/* ---- Protected: Instance methods ----------------------------------------- */

    protected __Action__(HttpServletRequest req, HttpServletResponse resp) {

        this.__Request  = req;
        this.__Response = resp;
    }

    /**
     * 
     * @param path
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    protected void setJSP(String path) {

        this.__JSPPath = path;
    }

    /**
     * 
     * @param url
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    protected void setRedirect(String url) {

        this.__RedirectPath = url;
        this.__IsRedirect   = true;
    }

    /**
     * 
     * @param controller
     * @param action
     * @param parameters
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    protected void setRedirect( String controller, 
                                String action, 
                                ArrayList<String> parameters) {

        final String SLASH  = "/";
        String urlString    = this.__Request.getContextPath();
        urlString           += controller   + SLASH;
        urlString           += action       + SLASH;
        if(parameters != null) {
            for(String element : parameters) {
                urlString += element + SLASH;
            }
        }
        this.setRedirect(urlString);
    }

/* ---- Public: Instance methods -------------------------------------------- */

    /**
     * This method make an action work, call this method in the last line of 
     * controller doPost() or doGet() method
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    public void doAction() {

        try {
            this.setRequestAttribute(__Request);
            if(this.__IsRedirect) {
                this.__Response.sendRedirect(this.__RedirectPath);
            }
            else {
                this.__Request.getRequestDispatcher(this.__JSPPath)
                .forward(__Request, __Response);
            }
        } catch (ServletException | IOException e) {
            __System__.PrintLog("__Action__: Failed to do action");
        }
    }
}
