package com.shop.controllers.web;

import com.shop.configs.RouterConfig;
import com.shop.core.__Controller__;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {
    RouterConfig.Controller.Web.STORE, 
    RouterConfig.Controller.Web.STORE_ALL
})
public class StoreController extends __Controller__ {

    @Override
    protected void doGet() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void doPost() {
        // TODO Auto-generated method stub
        
    }
    
}
