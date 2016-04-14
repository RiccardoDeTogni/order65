/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.database.exceptions;

import services.database.*;
import services.log.*;

/**
 *
 * @author nick
 */
public class DBException extends Exception {
    protected Database database = null;
    protected String logMsg = null;
    
    public DBException () {
        
    }
    
    public DBException (String msg) {
        this (msg, null);
    }
    
    public DBException (String msg, Database db) {
        super (msg);
        this.database = db;
        this.logMsg = "Database exception: " + msg;
        this.log ();
    }
    
    public void log () {
        Logs.printLog (LogTypes.ERROR, this.logMsg);
    }
}
