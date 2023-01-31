package com.shop.models.entities;

import org.json.JSONArray;

import com.shop.core.__Entity__;

public class UserEntity extends __Entity__ {
 

/* ---- Instance properties ------------------------------------------------- */

    private String      __Username;
    private String      __Password;
    private String      __Phone;
    private String      __Address;
    private boolean     __IsAdmin;
    private JSONArray   __Cart;


/* ---- Public: Setters and getters methods --------------------------------- */

    public void setUsername (String username)   { this.__Username = username; }
    public void setPassword (String password)   { this.__Password = password; }
    public void setPhone    (String phone)      { this.__Phone = phone; }
    public void setAddress  (String address)    { this.__Address = address; }
    public void setIsAdmin  (boolean isAdmin)   { this.__IsAdmin = isAdmin; }
    public void setCart     (JSONArray json)    { this.__Cart = json; }

    public String       getUsername()               { return this.__Username; }
    public String       getPassword()               { return this.__Password; }
    public String       getPhone()                  { return this.__Phone; }
    public String       getAddress()                { return this.__Address; }
    public boolean      getIsAdmin()                { return this.__IsAdmin; }
    public JSONArray    getCart()                   { return this.__Cart; }
}
