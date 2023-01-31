package com.shop.actions.common;

import java.util.ArrayList;

import com.shop.configs.RouterConfig;
import com.shop.core.__Action__;
import com.shop.models.services.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AccountAction extends __Action__ {


/* ---- Static constants ---------------------------------------------------- */

    /**
     * This final class contains actions name
     * @author Hoang Duc Kiem Luyen
     */
    private static final class ActionName {

        public static final String 
            SIGN_IN             = RouterConfig.Action.Account.SIGN_IN;
        public static final String 
            SIGN_OUT            = RouterConfig.Action.Account.SIGN_OUT;
        public static final String 
            SIGN_UP             = RouterConfig.Action.Account.SIGN_UP;
        public static final String 
            PHONE_VERIFICATION  = RouterConfig.Action.Account.PHONE_VERIFICATION;
        public static final String 
            CHANGE_PASSWORD     = RouterConfig.Action.Account.CHANGE_PASSWORD;
        public static final String 
            FORGOT_PASSWORD     = RouterConfig.Action.Account.FORGOT_PASSWORD;
        public static final String 
            PROFILE             = RouterConfig.Action.Account.PROFILE;
        public static final String 
            DEFAULT             = RouterConfig.Action.Account.DEFAULT;

    }


/* ---- Instance properties ------------------------------------------------- */

    private AccountService      __AccountService;


/* ---- Instance methods ---------------------------------------------------- */

    public AccountAction(HttpServletRequest req, HttpServletResponse resp) {

        super(req, resp);

        this.__AccountService = new AccountService(req, resp);
    }


/* ---- Override: Instance methods ------------------------------------------ */

    @Override
    protected void setRequestAttribute(HttpServletRequest req) {

        AccountService.Result result = this.__AccountService.getResult();

        req.setAttribute("$req_Username",   result.getUsername  ());
        req.setAttribute("$req_Password",   result.getPassword  ());
        req.setAttribute("$req_Phone",      result.getPhone     ());
        req.setAttribute("$req_Fullname",   result.getFullname  ());
        req.setAttribute("Status",          result.getStatus    ());

    }

    @Override
    public void setAction(String action, ArrayList<String> params) {
    
        if(     action.equals(ActionName.SIGN_IN)) {
            this.signInAction();
        }
        else if(action.equals(ActionName.SIGN_OUT)) {
            this.signOutAction();
        }
        else if(action.equals(ActionName.SIGN_UP)) {
            this.signUpAction();
        }
        else if(action.equals(ActionName.PHONE_VERIFICATION)) {
            this.phoneVerification();
        }
        else if(action.equals(ActionName.CHANGE_PASSWORD)) {
            this.changePassword();
        }
        else if(action.equals(ActionName.FORGOT_PASSWORD)) {
            this.forgotPassword();
        }
        else if(action.equals(ActionName.PROFILE)) {
            this.profile();
        }
        else if(action.equals(ActionName.DEFAULT)) {
            this.profile();
        }
        else {
            this.profile();
        }
    }


/* ---- Private: Action methods --------------------------------------------- */    
    
    private void signInAction() {
        
        this.__AccountService.signIn();

        if(this.__AccountService.getResult().getStatus() == null) {
            this.setRedirect(null, null, null);
        }
        else {
            this.setJSP(null);
        }
    }

    private void signOutAction() {
        
        this.__AccountService.signOut();
        this.setRedirect(null, null, null);
    }

    private void signUpAction() {

        this.__AccountService.signUp();
        
        if(this.__AccountService.getResult().getStatus() == null) {
            this.setRedirect(null, null, null);
        }
        else {
            this.setJSP(null);
        }
    }

    private void phoneVerification() {
        
        this.__AccountService.phoneVerification();

        if(this.__AccountService.getResult().getStatus() == null) {
            this.setRedirect(null, null, null);
        }
        else {
            this.setJSP(null);
        }
    }

    private void changePassword() {

        this.__AccountService.changePassword();

        if(this.__AccountService.getResult().getStatus() == null) {
            this.setRedirect(null, null, null);
        }
        else {
            this.setJSP(null);
        }
    }

    private void forgotPassword() {

        this.__AccountService.forgotPassword();

        if(this.__AccountService.getResult().getStatus() == null) {
            this.setRedirect(null, null, null);
        }
        else {
            this.setJSP(null);
        }
    }

    private void profile() {

        this.__AccountService.profile();
    }
}   
