package com.shop.core;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

/**
 * This class is abstract business logic class
 * @version 1.0-SNAPSHOT
 * @author Hoang Duc Kiem Luyen
 */
public abstract class __Service__ {


/* ---- Protected: Instance methods ----------------------------------------- */

    /**
     * This method use for encode other charset string to UTF-8 string
     * @param str - StringOther charset string
     * @return String: UTF-8 encoded
     * @author Hoang Duc Kiem Luyen
     */
    protected String utf8(String str) {

        byte[] strBytes = str.getBytes();
        return new String(strBytes, StandardCharsets.UTF_8);
    }

    /**
     * This method use for hash string to sha256 string
     * @param str - String: string need hash to sha256
     * @return String: hashed sha256 string
     * @author Hoang Duc Kiem Luyen
     */
    protected String sha256(String str) {
        
        return Hashing.sha256()
        .hashString(str, StandardCharsets.UTF_8).toString();
    }

    /**
     * This method use for hash string to sha512 string
     * @param str - String: string need hash to sha512
     * @return String: hashed sha512 string
     * @author Hoang Duc Kiem Luyen
     */
    protected String sha512(String str) {
        
        return Hashing.sha512()
        .hashString(str, StandardCharsets.UTF_8).toString();
    }
}
