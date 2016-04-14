/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.database.exceptions;

import java.io.*;
import services.database.*;
import services.error.*;

/**
 *
 * @author nick
 */
public class NotFoundDBException extends DBException implements IFatalError {
    
    public NotFoundDBException (String msg) {
        this (msg, null);
    }
    public NotFoundDBException (String msg, Database db) {
        super ("Fatal Error: " + msg, db);
        this.database = db;
        this.logMsg = "Fatal Error:" + System.lineSeparator () + msg + System.lineSeparator ();
        ByteArrayOutputStream stackTrace = new ByteArrayOutputStream ();
        this.printStackTrace (new PrintWriter (stackTrace, true));
        this.logMsg += stackTrace.toString ();
    }
    
    @Override
    public String getLogMessage() {
        return this.logMsg;
    }

    @Override
    public void mailError() {
        try {
            
        } catch (Exception e) {
            System.out.println (e.getMessage ());
        }
    }

    @Override
    public void makeRollBack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
