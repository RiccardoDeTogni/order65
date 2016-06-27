/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bflows;

/**
 *
 * @author Riccardo
 */
import services.database.*;
import services.database.exceptions.*;
import services.session.*;
import blogics.*;
import java.sql.SQLException;
import javax.servlet.http.Cookie;
import services.hash.*;
import services.log.LogTypes;
import services.log.Logs;
import services.mail.MService;
import services.mail.exceptions.BadDeliveryException;
import global.Constants;
import java.sql.Date;
import services.mail.exceptions.BadRecipientException;

public class LogonManagement {

   
    private String oggetto;
    private String testo;
    private String insertType;
    private SessionType sType;
    private Cookie cookie;
    private SessionInfo info;
    private long id;
    private String username;
    private String passwd;
    private String first_name;
    private String telephone;
    private String city;
    private String surname;
    private String email;
    private Date reg_date;
    private int type;
    private Date last_mod;
    private String usrsession;
    private long sport;
    private boolean active;
    private long struttura;
    private String logtype;
    private String errorMessage;
    
    public void logon() {
        Database db = null;
        try {
            if (this.logtype.equals("user")) {
                this.sType = SessionType.USER;
            } else {
                throw new Exception();
            }
            db = DBService.getDatabase();
            ILoggableEntity le = null;
            if (sType == SessionType.USER) {
                le = UserService.getUser(db, this.username);
            }

            if (this.sType == SessionType.USER) {
                if (le == null || !le.getPasswd().equals(Sha512.hashText(Sha512.hashText(this.passwd)))) {
                    errorMessage = "Password Errata";
                    cookie = null;
                } else {
                    this.cookie = Session.createLoginCookie(db, this.username, this.sType, ((User) le).getCity());
                }
            }

            db.commit();
        } catch (NotFoundDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "LogonManagement(): logon(): Database not found");

        } catch (ResultSetDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "LogonManagement(): logon(): ResultSet Error");
        } catch (SQLException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "LogonManagement(): logon(): SQL Error");
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "LogonManagement(): logon(): Generic Exception: " + ex.toString() + "   ");

        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (NotFoundDBException e) {
                Logs.printLog(LogTypes.ERROR, "LogonManagement(): logon(): Database not found");
            }
        }

    }

    public void logout() throws NotFoundDBException, ResultSetDBException, SQLException {
        this.cookie = Session.deleteCookie(cookie);
        Database db = null;
        try {
            db = DBService.getDatabase();
            User u = UserService.getUser(db, this.username);
            u.invalidateSession(db);

            db.commit();
        } catch (NotFoundDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "Database not found");

        } catch (ResultSetDBException ex) {
            if (db != null) {
                db.rollback();
            }

            Logs.printLog(LogTypes.ERROR, "ResultSet Error");
        } catch (SQLException ex) {
            if (db != null) {
                db.rollback();
            }

            Logs.printLog(LogTypes.ERROR, "SQL Error");
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }

            Logs.printLog(LogTypes.ERROR, "LogonManagement: logout():  Generic Exception: " + ex.toString() + "   ");

        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (NotFoundDBException e) {
                Logs.printLog(LogTypes.ERROR, "Database not found");
            }
        }
    }

    public void sendMail() throws BadDeliveryException, BadRecipientException, Exception {
        MService mservice = new MService();
        String text = "NOME: " + this.first_name + System.lineSeparator()
                + "COGNOME: " + this.surname + System.lineSeparator()
                + "USERNAME: " + this.username + System.lineSeparator()
                + "EMAIL: " + this.email + System.lineSeparator() + System.lineSeparator()
                + "TESTO: " + System.lineSeparator() + this.testo;
        mservice.send(this.email, Constants.ADMIN_MAIL, this.oggetto, text);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    

    
    
    
    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getInsertType() {
        return insertType;
    }

    public void setInsertType(String insertType) {
        this.insertType = insertType;
    }

    public SessionType getsType() {
        return sType;
    }

    public void setsType(SessionType sType) {
        this.sType = sType;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public SessionInfo getInfo() {
        return info;
    }

    public void setInfo(SessionInfo info) {
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getLast_mod() {
        return last_mod;
    }

    public void setLast_mod(Date last_mod) {
        this.last_mod = last_mod;
    }

    public String getUsrsession() {
        return usrsession;
    }

    public void setUsrsession(String usrsession) {
        this.usrsession = usrsession;
    }

    public long getSport() {
        return sport;
    }

    public void setSport(long sport) {
        this.sport = sport;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getStruttura() {
        return struttura;
    }

    public void setStruttura(long struttura) {
        this.struttura = struttura;
    }

    public String getLogtype() {
        return logtype;
    }

    public void setLogtype(String logtype) {
        this.logtype = logtype;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    

}
