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
public class BadRecipientException extends MException implements IWarning {

    public BadRecipientException(String msg, String sMail) {
        super ("WARNING: " + msg, sMail);
        this.logMsg = "WARNING " + System.lineSeparator() + msg + System.lineSeparator();
    }

    @Override
    public String getLogMessage() {
        return this.logMsg;
    }

}
