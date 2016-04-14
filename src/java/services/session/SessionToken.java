/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.session;

import java.math.*;
import java.security.*;

/**
 *
 * @author nick
 */
public class SessionToken {
    
    public static String generateSessionToken() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(256, random).toString(16);
    }

}
