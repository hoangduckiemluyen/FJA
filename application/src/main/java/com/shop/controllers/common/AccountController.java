package com.shop.controllers.common;

import com.shop.actions.common.AccountAction;
import com.shop.configs.RouterConfig;
import com.shop.core.__Controller__;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {
    RouterConfig.Controller.Common.ACCOUNT,
    RouterConfig.Controller.Common.ACCOUNT_ALL
})
public class AccountController extends __Controller__ {

    @Override
    protected void doGet() {
        
        AccountAction 
        action = new AccountAction(this.__Request, this.__Response);

        action.setAction(this.__Action, this.__Parameters);
        action.doAction();
    }

    @Override
    protected void doPost() {

        AccountAction 
        action = new AccountAction(this.__Request, this.__Response);

        action.setAction(this.__Action, this.__Parameters);
        action.doAction();
    }
    
}
