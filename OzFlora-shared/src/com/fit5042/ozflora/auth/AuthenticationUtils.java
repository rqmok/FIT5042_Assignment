/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5042.ozflora.auth;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Zeeshan
 */
public final class AuthenticationUtils {

    /**
     * Returns SHA-256 encoded string.
     *
     * @param password - the string to be encoded.
     * @return SHA-256 encoded string.
     * @throws UnsupportedEncodingException if UTF-8 is not supported by the
     * system.
     * @throws NoSuchAlgorithmException if SHA-256 is not supported by the
     * system.
     */
    public static String encodeSHA256(String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(password.getBytes("UTF-8"));
        return DatatypeConverter.printBase64Binary(messageDigest.digest());
    }

}
