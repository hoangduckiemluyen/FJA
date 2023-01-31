package com.shop.core;

import java.sql.Timestamp;

/**
 * This class is entity abstract class
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public abstract class __Entity__ {
    

/* ---- Instance properties ------------------------------------------------- */

    protected long                          __ID;
    protected String                        __Code;
    protected String                        __Name;
    protected Timestamp                     __CreatedDate;


/* ---- Public: Setter and getter methods ----------------------------------- */

    public void setID(long id)                  { this.__ID = id; }
    public void setCode(String code)            { this.__Code = code; }
    public void setName(String name)            { this.__Name = name; }
    public void setCreatedDate(Timestamp date)  { this.__CreatedDate = date; }

    public long getID()                         { return this.__ID; }
    public String getCode()                     { return this.__Code; }
    public String getName()                     { return this.__Name; }
    public Timestamp getCreatedDate()           { return this.__CreatedDate; }
}
