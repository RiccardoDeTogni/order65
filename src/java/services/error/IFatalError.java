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
public interface IFatalError {
    public String getLogMessage ();
    public void log ();
    public void mailError ();
    public void makeRollBack ();
}
