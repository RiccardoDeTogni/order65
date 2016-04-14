/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.database.exceptions;

import services.error.IWarning;
import services.log.*;

/**
 *
 * @author Riccardo
 */
public class DuplicatedRecordDBException extends DBException implements IWarning {
  
  
  /** Creates new NotFoundDBException */
  public DuplicatedRecordDBException(String msg) {
    super("Warning: "+msg);
    this.logMsg="Warning\n"+msg+"\n";
  }
  
  /** Ritorna il messaggio di Errore corrispondente al Warning **/
  public String getLogMessage() {
    return logMsg;
  }
  
  
}
