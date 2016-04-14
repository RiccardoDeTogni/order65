/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.error;

/**
 *
 * @author nick
 */
public interface IGeneralError {
    public String getLogMessage ();
    public void log ();
    public void makeRollBack ();
}
