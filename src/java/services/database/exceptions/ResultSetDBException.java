/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.database.exceptions;

/**
 *
 * @author Riccardo
 */
import services.error.*;
import services.log.*;
import services.database.Database;

public class ResultSetDBException extends DBException implements IGeneralError {
    
    public ResultSetDBException(String msg,Database db){
        super("General Error" + msg,db);
        this.logMsg = "General Error\n" + msg + "\n";
        this.database = db;
    }
    
    public ResultSetDBException(String msg) {    
    this(msg,null);    
  }
    
    @Override
    public String getLogMessage() {
        return this.logMsg;
    }

    @Override
    public void makeRollBack() {
        if(this.database != null){
            this.database.rollback();
        }
    }
    
}
