/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.mail.exceptions;

import services.error.*;

/**
 *
 * @author User
 */
public class BadDeliveryException extends MException implements IFatalError {

    public BadDeliveryException() {
    }
    public BadDeliveryException(String msg) {
        super ("Fatal Error: " + msg, "ALL");
        this.logMsg = "FATAL ERROR:" + System.lineSeparator() + msg + System.lineSeparator();
    }
    public BadDeliveryException(String msg, String mServer) {
        super ("Fatal Error: " + msg, mServer);
        this.logMsg = "FATAL ERROR:" + System.lineSeparator() + msg + System.lineSeparator();
    }
    
    @Override
    public String getLogMessage() {
        return this.logMsg;
    }

    @Override
    public void mailError() {
    }

    @Override
    public void makeRollBack() {
    }

}
