package com.shop.core;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.View;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class is controller abstract class,
 * extended HttpServlet
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
@SuppressWarnings("unused")
public abstract class __Controller__ extends HttpServlet {


/* ------ Instance properties ----------------------------------------------- */

    protected String                __Controller;
    protected String                __Action;
    protected ArrayList<String>     __Parameters;
    protected HttpServletRequest    __Request;
    protected HttpServletResponse   __Response;


/* ------ Protected: Abstract instance methods ------------------------------ */

    /**
     * This method is collapsed version of servlet doGet() method
     * called by servlet doGet()
     * @author Hoang Duc Kiem Luyen
     * @return void
     */
    protected abstract void doGet();

     /**
     * This method is collapsed version of servlet doPost() method,
     * called by servlet doPost()
     * @author Hoang Duc Kiem Luyen
     * @return void
     */
    protected abstract void doPost();


/* ------ Protected: Servlet instance methods ----------------p--------------- */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
       
        try {
            this.initializeInstances(req, resp);
            this.urlProcess(req);
            this.doGet();
        } 
        catch (Exception e) {
            __System__.PrintLog("Error __Controller__: doGet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        
        try {
            this.initializeInstances(req, resp);
            this.urlProcess(req);
            this.doPost();
        } 
        catch (Exception e) {
            __System__.PrintLog("Error __Controller__: doPost");
        }
    }

    /**
	* redirect website to url
    * @param url - url
	* @author Hoang Duc Kiem Luyen
	* @return void
	* */
    protected void redirect(String url) {
        
        try {
            this.__Response.sendRedirect(url);
        } 
        catch (IOException e) {
            __System__.PrintLog("Failed to redirect! url: " + url);
        }
    }

    /**
	* redirect website to controller/action
    * @param controller - controller name in url
    * @param action - action name in url
	* @author Hoang Duc Kiem Luyen
	* @return void
	* */
    protected void redirect(String controller, String action) {
        
        String url = this.__Request.getContextPath() 
        + controller + '/' + action + '/';
        this.redirect(url);
    }

    /**
	* forward a jsp file
    * @param path - path to jsp file
	* @author Hoang Duc Kiem Luyen
	* @return void
	* */
    protected void view(String path) {

        try {
            this.__Request.getRequestDispatcher(path)
            .forward(__Request, __Response);
        } 
        catch (ServletException | IOException e) {
            __System__.PrintLog("Failed to request dispatcher! path: " + path);
        }
    }


/* ------ Private: pre-processor methods ------------------------------------ */

    /**
     * initialize instance variables,
     * called by servlet doPost() and servlet doGet()
     * @param req - HttpServletRequest variable
     * @param res - HttpServletResponse variable
     * @author Hoang Duc Kiem Luyen
     * @return void
     */
    private void initializeInstances(   HttpServletRequest req, 
                                        HttpServletResponse res) {

        this.__Request      = req;
        this.__Response     = res;
        this.__Controller   = "";
        this.__Action       = "";
        this.__Parameters   = new ArrayList<>();

        this.urlProcess(req);
    }

    /**
	* export controller, action and parameters from url to instance variables,
    * called by servlet doPost() and servlet doGet()
    * @param req - HttpServletRequest variable
	* @author Hoang Duc Kiem Luyen
	* @return void
	* */
	private void urlProcess(HttpServletRequest req) {
		
		this.__Controller 	= "";
		this.__Action		= "";
		this.__Parameters	= new ArrayList<>();
		
		String searchContextPath 	= req.getContextPath();
		String searchMultiSlash 	= "/{2}";
		String searchBeginSlash		= "^/";
		String searchEndSlash 		= "/$";
		String searchSpace			= " ";
		
		String url 	= req.getRequestURI();
		url			= url.replaceAll(searchContextPath, "");
		url			= url.replaceAll(searchMultiSlash, "/");
		url			= url.replaceAll(searchBeginSlash, "");
		url			= url.replaceAll(searchEndSlash, "");
		url			= url.replaceAll(searchSpace, "");
		
		String[] elements = url.split("/");
		
		for(byte i = 0; i < elements.length; i++) {
			if(i == 0) {
				this.__Controller = elements[i];
			} else if(i == 1) {
				this.__Action = elements[i];
			} else {
				this.__Parameters.add(elements[i]);
			}
		}
	}
}
