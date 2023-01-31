package com.shop.core;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This is pre-processor (filter) abstract class,
 * implemented filter interface
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public abstract class __PreProcessor__ implements Filter {
    

/* ---- Instance properties ------------------------------------------------- */

    protected HttpServletRequest    __Request;
    protected HttpServletResponse   __Response;
    protected FilterChain           __Chain;
    protected FilterConfig          __Config;


/* ---- Protected: Abstract instance methods -------------------------------- */

    /**
     * This method is collapsed version of init() Filter method.
     * Called by init() Filter method
     * @author Hoang Duc Kiem Luyen
     * @return void
     */
    protected abstract void initialize();

    /**
     * This method is collapsed version of doFilter() Filter method.
     * Called by doFilter() Filter method
     * @author Hoang Duc Kiem Luyen
     * @return void
     */
    protected abstract void processor();

    /**
     * This method is collapsed version of destroy() Filter method.
     * Called by destroy() Filter method
     * @author Hoang Duc Kiem Luyen
     * @return void
     */
    protected abstract void terminate();


/* ---- Public: Override instance methods ----------------------------------- */

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
        this.__Config = filterConfig;

        this.initialize();
    }

    @Override
    public void doFilter(   ServletRequest request, 
                            ServletResponse response, FilterChain chain) 
                            throws IOException, ServletException {
        
        this.__Request  = (HttpServletRequest)  request;
        this.__Response = (HttpServletResponse) response;
        this.__Chain    = chain;

        this.processor();
    }

    @Override
    public void destroy() {
       
        this.terminate();
    }

    
/* ---- Protected: Main process instance methods ---------------------------- */

    protected void abc() {

    }

}
