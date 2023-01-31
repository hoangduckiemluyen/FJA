package com.shop.core;

import com.shop.configs.SystemConfig;

/**
 * This class use for process system 
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public class __System__ {

    
/* ---- Private: Static properties ------------------------------------------ */

    private static boolean  s_HeaderLevel   = true;
    private static long     s_Order         = 1;
    private static AppMode  s_AppMode       = SystemConfig.APPLICATION_MODE;


/* ---- Public: Static declaration ------------------------------------------ */

    /**
     * This enum contains application running mode.
     * @apiNote DEBUG - application run on debug mode
     * @apiNote RELEASE - appplication run on release mode (speed up)
     * @author Hoang Duc Kiem Luyen
     */
    public static enum AppMode { DEBUG, RELEASE }


/* ---- Public: Static methods ---------------------------------------------- */

    /**
     * This method print log ordered by number if debug mode enabled.
     * @param log - log string
     * @author Hoang Duc Kiem Luyen
     * @return void
     */
    public static void PrintLog(String log) {

        if(s_HeaderLevel) {
            Header();
        }

        if(s_AppMode.equals(AppMode.DEBUG)) {
            String string = "[" + (s_Order ++) + "] " + log;
            System.out.println(string);
        }
    }

    /**
     * This method print logger header if debug mode enabled.
     * @author Hoang Duc Kiem Luyen
     * @return void
     */
    public static void Header() {

        if(s_AppMode.equals(AppMode.DEBUG)) {
            String str  =   "------------------------------------------------";
            str         +=  "                   DEBUG MODE                   ";
            str         +=  " Switch to release mode to speed up application ";
            str         +=  "------------------------------------------------";
            System.out.println(str);
            System.out.println();
        }
    }
}
