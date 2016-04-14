/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.mail.exceptions;

import services.log.LogTypes;
import services.log.Logs;

/**
 *
 * @author User
 */
public class MException extends Exception {

    protected String mailServer = null;
    protected String logMsg = null;

    public MException() {
    }

    public MException(String msg, String sMail) {
        super(msg);
        this.mailServer = sMail;
        this.logMsg = "Created Exception: Gateway: " + sMail + " Message: " + msg;
        this.log();
    }
    public void log() {
        Logs.printLog(LogTypes.ERROR, this.logMsg);
    }
}
