/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.hash;

import java.security.*;
import services.log.LogTypes;
import services.log.Logs;

/**
 *
 * @author nick
 */
public class Sha512 {

    private static String convertByteToHex(byte data[]) {
        StringBuilder hexData = new StringBuilder();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++) {
            hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));
        }
        return hexData.toString();
    }

    public static String hashText(String textToHash) throws Exception {
        final MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
        sha512.update(textToHash.getBytes());

        return convertByteToHex(sha512.digest());
    }
}
