/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.log;

import global.*;
import java.io.*;

/**
 *
 * @author nick
 */
public class Logs {

    private static String path;

    public static synchronized void printLog(LogTypes type, String msg) {
        try {
            path = Constants.LOG_INFO;
            if (type == LogTypes.ERROR) {
                path = Constants.LOG_ERROR;
            } else if (type == LogTypes.WARNING) {
                path = Constants.LOG_WARNING;
            } else if (type == LogTypes.DBINFO) {
                path = Constants.LOG_DBINFO;
            } else if (type == LogTypes.INFO) {
                path = Constants.LOG_INFO;
            }
            try (PrintWriter log = new PrintWriter(new FileOutputStream(path, true), true)) {
                java.util.Date now = new java.util.Date();
                log.println("[" + now + "]" + "\t" + msg);
                log.flush();
            }
        } catch (FileNotFoundException e) {
            System.out.println("logs: printLog (): Impossibile trovare il File di Log degli Errori: " + path + System.lineSeparator() + e.getMessage() + ".");
        }
    }
}
