package com.shop.models.services;

import com.shop.configs.DatabaseConfig;
import com.shop.configs.RegEx;
import com.shop.configs.RequestConfig;
import com.shop.core.__Service__;
import com.shop.models.caches.UserCache;
import com.shop.models.dao.UserDAO;
import com.shop.models.entities.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This is account business logic class
 * @author Hoang Duc Kiem Luyen
 */
public class AccountService extends __Service__ {


/* ---- Static constants ---------------------------------------------------- */

    /**
     * This final class contains user table column
     * @author Hoang Duc Kiem Luyen
     */
    protected static final class TableColumn {

        public static final String 
            USERNAME    = DatabaseConfig.TCName_User.USERNAME;
        public static final String 
            PHONE       = DatabaseConfig.TCName_User.PHONE;
        public static final String
            CODE        = DatabaseConfig.TCName_User.CODE;
    } 

    /**
     * This final class contains $post request variable names
     * @author Hoang Duc Kiem Luyen
     */
    protected static final class Post {

        public static final String SUBMIT   = RequestConfig.Post.SUBMIT;
        public static final String USERNAME = RequestConfig.Post.USERNAME;
        public static final String PASSWORD = RequestConfig.Post.PASSWORD;
        public static final String PHONE    = RequestConfig.Post.PHONE;
        public static final String FULLNAME = RequestConfig.Post.FULLNAME;
    }

    /**
     * This final class contains account regular expressions patterns
     * @author Hoang Duc Kiem Luyen
     */
    protected static final class AccountRegex {
        public static final String 
            USERNAME    = RegEx.Account.USERNAME;
        public static final String 
            PASSWORD    = RegEx.Account.PASSWORD;
        public static final String 
            PHONE       = RegEx.Account.PHONE;
        public static final String 
            FULLNAME    = RegEx.Account.FULLNAME;
    }


/* ---- Static classes ------------------------------------------------------ */

    /**
     * This class contains request values & result string
     * @author Hoang Duc Kiem Luyen
     */
    public static class Result {
        
        private String  status      = "";
        private String  username    = "";
        private String  password    = "";
        private String  phone       = "";
        private String  fullname    = "";

        public void setStatus   (String value) { this.status    = value; }
        public void setUsername (String value) { this.username  = value; }
        public void setPassword (String value) { this.password  = value; }
        public void setPhone    (String value) { this.phone     = value; }
        public void setFullname (String value) { this.fullname  = value; }

        public String getStatus     () { return this.status;     }
        public String getUsername   () { return this.username;   }
        public String getPassword   () { return this.password;   }
        public String getPhone      () { return this.phone;      }
        public String getFullname   () { return this.fullname;   }
    }


/* ---- Instance properties ------------------------------------------------- */

    protected UserDAO           __UserDAO;
    protected UserCache         __UserCache;
    protected Result            __Result;

    protected boolean           __Submitted;
    protected String            __Username;
    protected String            __Password;
    protected String            __Phone;
    protected String            __Fullname;


/* ---- Constructor methods ------------------------------------------------- */

    /**
     * This method use for request (post or get) variable initialization
     * @param req - HttpServletRequest: request from controller or action
     * @return void
     * @author Hoang Duc Kiem Luyen
     */
    private void initRequest(HttpServletRequest req) {

        String submitted    = req.getParameter(Post.SUBMIT);
        String username     = utf8(req.getParameter(Post.USERNAME));
        String password     = utf8(req.getParameter(Post.PASSWORD));
        String phone        = utf8(req.getParameter(Post.PHONE));
        String fullname     = utf8(req.getParameter(Post.FULLNAME));

        this.__Submitted    = submitted != null;
        this.__Username     = username  != null ? username  : "";
        this.__Password     = password  != null ? password  : "";
        this.__Phone        = phone     != null ? phone     : "";
        this.__Fullname     = fullname  != null ? fullname  : "";
    }

    public AccountService(HttpServletRequest req, HttpServletResponse resp) {

        this.__UserDAO      = new UserDAO();
        this.__UserCache    = new UserCache(req, resp);
        this.__Result       = new Result();
        
        this.initRequest(req);

        if(this.__Submitted) {
            
            this.__Result.setUsername   (this.__Username);
            this.__Result.setPassword   (this.__Password);
            this.__Result.setPhone      (this.__Phone);
            this.__Result.setFullname   (this.__Fullname);
        }
    }
    

/* ---- Public: Business logic methods -------------------------------------- */

    public void AutoSignIn() {
    
        UserEntity
        entity = this.__UserDAO.getOne( TableColumn.CODE, 
                                        this.__UserCache.getBrowserCode());
        if(this.__UserCache.getServerID() == -1) {
            if(entity != null) {
                UserCache.SessionUserInfo in4 = new UserCache.SessionUserInfo();
                in4.setID       (entity.getID       ());
                in4.setCode     (entity.getCode     ());
                in4.setUsername (entity.getUsername ());
                in4.setFullname (entity.getName     ());
                in4.setIsAdmin  (entity.getIsAdmin  ());
                in4.setCartJson (entity.getCart     ());

                this.__UserCache.setServerUser(in4);
            }
            else {
                this.__UserCache.cleanCache();
            }
        }
    }

    public void signIn() {

        UserEntity  entity      = null;
        boolean     usernameOK  = false;
        boolean     passwordOK  = false;


        // username process
        if(!this.__Username.isBlank()) {
            entity = this.__UserDAO.getOne(TableColumn.USERNAME, this.__Username);
            if(entity != null) {
                if(entity.getUsername().equals(this.__Username)) {
                    usernameOK = true;
                }
                else {
                    this.__Result.setStatus("Khong tim thay tai khoan");
                }
            }
            else {
                this.__Result.setStatus("Khong tim thay tai khoan");
            }
        }
        else {
            this.__Result.setStatus("Tai khoan khong duoc de trong");
        }

        // password process
        if(usernameOK) {
            if( this.__Password != null && this.__Password != "") {
                String password = this.sha512(this.__Password);
                if(entity.getPassword().equals(password)) {
                    passwordOK = true;
                }
                else {
                    this.__Result.setStatus("Mat khau khong khop");
                }
            }
            else {
                this.__Result.setStatus("Mat khau khong duoc de trong");
            }
        }

        if(usernameOK && passwordOK) {

            UserCache.SessionUserInfo   sInfo = new UserCache.SessionUserInfo();
            UserCache.CookieUserInfo    bInfo = new UserCache.CookieUserInfo();

            sInfo.setID         (entity.getID());
            sInfo.setCode       (entity.getCode());
            sInfo.setUsername   (entity.getUsername());
            sInfo.setFullname   (entity.getName());
            sInfo.setIsAdmin    (entity.getIsAdmin());
            sInfo.setCartJson   (entity.getCart());

            bInfo.setCode       (entity.getCode());

            this.__UserCache.setServerUser(sInfo);
            this.__UserCache.setBrowserUser(bInfo);

            this.__Result = null;
        }
    }

    public void signOut() {

        this.__UserCache.cleanCache();
    }

    public void signUp() {

        UserEntity  entity      = null;
        boolean     usernameOK  = false;
        boolean     passwordOK  = false;
        boolean     phoneOK     = false;
        boolean     fullnameOK  = false;

        // username process
        if(!this.__Username.isBlank()) {
            if(this.__Username.matches(AccountRegex.USERNAME)) {
                entity = this.__UserDAO.getOne( TableColumn.USERNAME, 
                                                this.__Username);
                if(entity == null) {
                    usernameOK = true;
                }
                else {
                    this.__Result.setStatus("Ten tai khoan da ton tai");
                }
            }
            else {
                this.__Result.setStatus("Ten tai khoan sai cu phap");
            }
        }
        else {
            this.__Result.setStatus("Ten tai khoan khong duoc de trong");
        }
        
        // password process
        if(usernameOK && !this.__Password.isBlank()) {
            if(this.__Password.matches(AccountRegex.PASSWORD)) {
                passwordOK = true;
            }
            else {
                this.__Result.setStatus("Mat khau sai cu phap");
            }
        }
        else {
            this.__Result.setStatus("Mat khau khong duoc de trong");
        }

        // phone process
        if(passwordOK && !this.__Phone.isBlank()) {
            if(this.__Phone.matches(AccountRegex.PHONE)) {
                entity = this.__UserDAO.getOne(TableColumn.PHONE, this.__Phone);
                if(!this.__Phone.equals(entity.getPhone())) {
                    // accept phone with message
                    phoneOK = true;
                }
                else {
                    this.__Result.setStatus("So dien thoai da ton tai");
                }
            }
            else {
                this.__Result.setStatus("So dien thoai sai cu phap");
            }
        }
        else {
            this.__Result.setStatus("So dien thoai khong duoc de trong");
        }

        // fullname process
        if(phoneOK && !this.__Fullname.isBlank()) {
            if(this.__Fullname.matches(AccountRegex.FULLNAME)) {
                fullnameOK = true;
            }
            else {
                this.__Result.setStatus("Ho va ten sai cu phap");
            }
        }
        else {
            this.__Result.setStatus("Ho va ten khong duoc de trong");
        }

        // sign in process 
        if(usernameOK && passwordOK && phoneOK && fullnameOK) {

            entity = new UserEntity();
            entity.setUsername  (this.__Username);
            entity.setPassword  (this.sha512(this.__Password));
            entity.setPhone     (this.__Phone);
            entity.setName      (this.__Fullname);

            if(this.__UserDAO.add(entity) != -1) {
                this.__Result = null;
            }
            else {
                this.__Result.setStatus("Loi khong xac dinh");
            }
        }
    }

    public void phoneVerification() {

    }

    public void forgotPassword() {

    }

    public void changePassword() {

    }

    public void profile() {
        
    }

/* ---- Public: Getter ------------------------------------------------------ */

    /**
     * This method use for get result after call doService() method
     * @return Result: object (null for succed)
     */
    public Result getResult() { return this.__Result; }
    
}
