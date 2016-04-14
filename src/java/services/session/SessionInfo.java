/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.session;

import blogics.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.*;
import services.database.*;
import services.database.exceptions.*;
import services.log.*;

public class SessionInfo {

    private final Cookie cookie;
    private String dhPwd;
    private SessionType sType;
    private String username;
    private int usrType;
    private long id;
    private long sport;

    public long getSport() {
        return sport;
    }

    public void setSport(long sport) {
        this.sport = sport;
    }
    
    

    public SessionInfo(Cookie cookie) {
        this.cookie = cookie;
    }

    
    public String getDhPwd() {
        return this.dhPwd;
    }

    public Cookie getCookie() {
        return this.cookie;
    }

    public SessionType getsType() {
        return this.sType;
    }

    public int getUsrType() {
        return this.usrType;
    }

    public String getUsername() {
        return this.username;
    }
    
    public long getId() {
        return id;
    }

    public boolean isLoggedon() {
        if (cookie == null) {
            return false;
        }
        Database db = null;
        boolean loggedon = false;
        ILoggableEntity le;
        try {
            db = DBService.getDatabase();
            loggedon = ((le = Session.validateSession(db, this.cookie)) != null);
            String sessiontype = Session.getType(this.cookie);
            this.sType = SessionType.valueOf(sessiontype);

            if (this.sType == SessionType.USER) {
                this.username = le.getUsername();
                this.id = le.getId();
                this.usrType = ((User) le).getType();
                this.sport = ((User) le).getSport();
            }
            this.dhPwd = le.getPasswd();
            db.commit();
        } catch (NotFoundDBException ex) {
            Logs.printLog(LogTypes.ERROR, "Database not found: " + ex.getMessage());
        } catch (ResultSetDBException ex) {
            Logs.printLog(LogTypes.ERROR, "ResultSet Error: " + ex.getMessage());
        } catch (SQLException ex) {
            Logs.printLog(LogTypes.ERROR, "SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            Logs.printLog(LogTypes.ERROR, "SessionInfo: Generic Exception: " + ex.getMessage());
            try {
                ex.printStackTrace(new PrintWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\Riccardo\\Documents\\Logs\\t_err.log"))));
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(SessionInfo.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (NotFoundDBException e) {
                Logs.printLog(LogTypes.ERROR, "Database not found");
            }
        }
        return loggedon;
    }
}
