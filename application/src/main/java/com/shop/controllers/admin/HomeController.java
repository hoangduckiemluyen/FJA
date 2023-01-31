package com.shop.controllers.admin;

import com.shop.configs.RouterConfig;
import com.shop.core.__Controller__;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {
    RouterConfig.Controller.Admin.ADMIN_HOME,
    RouterConfig.Controller.Admin.ADMIN_HOME_ALL
})
public class HomeController extends __Controller__{

    @Override
    protected void doGet() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void doPost() {
        // TODO Auto-generated method stub
        
    }
    
}
