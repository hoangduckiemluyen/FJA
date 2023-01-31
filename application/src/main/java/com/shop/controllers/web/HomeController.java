package com.shop.controllers.web;

import com.shop.configs.RouterConfig;
import com.shop.core.__Controller__;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {
    RouterConfig.Controller.Web.HOME
})
public class HomeController extends __Controller__ {

    @Override
    protected void doGet() {

        this.view("/views/master-layout.jsp");
    }

    @Override
    protected void doPost() {
        
        this.view("/views/master-layout.jsp");
    }
}
