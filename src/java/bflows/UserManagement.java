/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bflows;

import blogics.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.Cookie;
import services.database.DBService;
import services.database.Database;
import services.database.exceptions.DuplicatedRecordDBException;
import services.database.exceptions.NotFoundDBException;
import services.database.exceptions.ResultSetDBException;
import services.hash.Sha512;
import services.log.LogTypes;
import services.log.Logs;
import services.session.Session;
import services.session.SessionInfo;
import services.session.SessionType;

/**
 *
 * @author Riccardo
 */
public class UserManagement {

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
    private String reg_date;
    private int type;
    private Date last_mod;
    private String usrsession;
    private long sport;
    private boolean active;
    private long struttura;
    private Date birthdate;
    private String data_temp;

    public String getData_temp() {
        return data_temp;
    }

    public void setData_temp(String data_temp) {
        this.data_temp = data_temp;
    }

    
    
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    private String tmp_name;

    public String getTmp_name() {
        return tmp_name;
    }

    public void setTmp_name(String tmp_name) {
        this.tmp_name = tmp_name;
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

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
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

    public void addFriend() {
        Database db = null;
        try {
            db = DBService.getDatabase();

            UserService.addFriend(db, username, id);
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
            Logs.printLog(LogTypes.ERROR, "UserManagement addfriend(): Generic Exception: " + ex.getMessage());

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

    public void getFriends() {
        Database db = null;
        try {
            db = DBService.getDatabase();

            UserService.getFriends(db, id);
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
            Logs.printLog(LogTypes.ERROR, "UserManagement getFriends(): Generic Exception: " + ex.getMessage());

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

    public void register() {
        Database db = null;
        try {
            if (this.insertType.equals("user")) {
                this.sType = SessionType.USER;
            } else {
                throw new Exception();
            }
            db = DBService.getDatabase();
            if (sType == SessionType.USER) {
                DateFormat formatter;
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date data = new java.sql.Date(formatter.parse(this.reg_date).getTime());
                UserService.insertUser(db, this.username, this.passwd, this.first_name, this.surname, this.email, this.type, this.telephone, this.city, this.struttura, this.sport, this.birthdate);
            }

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
            Logs.printLog(LogTypes.ERROR, "UserManagement register(): Generic Exception: " + ex.getMessage());

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

    public void modify() throws NotFoundDBException, ResultSetDBException, SQLException, DuplicatedRecordDBException, Exception {
        Database db = null;
        try {
            db = DBService.getDatabase();
            User usr = UserService.getUser(db, this.username);
            
            if (this.info.isLoggedon()) {

                /*if (!this.email.equals("")) {
                    Logs.printLog(LogTypes.INFO, "email" + this.email);
                    usr.setEmail(this.email);
                } */
                if (!this.first_name.equals("")) {
                    
                    usr.setFirst_name(this.first_name);
                }
                if (!this.surname.equals("")) {
                    
                    usr.setSurname(this.surname);
                }
                if (!this.city.equals("")) {
                    
                    usr.setCity(this.city);
                }
                if (!this.telephone.equals("")) {
                    
                    usr.setTelephone(this.telephone);
                }
                
                DateFormat formatter;
                formatter = new SimpleDateFormat("yyyy-MM-dd");
                
                usr.setBirthdate(new java.sql.Date(formatter.parse(this.data_temp).getTime()));
                usr.setType(usr.getType());
                
                usr.update(db);
                

            }
            db.commit();
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "UserManagement modify(): Generic Exception: " + ex.getMessage());
        }
        try {
            db.close();
        } catch (NotFoundDBException e) {
            Logs.printLog(LogTypes.ERROR, "Database not found");
        }
    }

    public void modifyPassword() throws NotFoundDBException, ResultSetDBException, SQLException, DuplicatedRecordDBException, Exception {
        User usr = null;
        Database db = null;
        try {
            db = DBService.getDatabase();
            usr = UserService.getUser(db, this.username);
            
            if (this.info.isLoggedon()) {
                Logs.printLog(LogTypes.INFO,"  " + this.passwd + "   " + Sha512.hashText(Sha512.hashText(this.passwd)));
                usr.setPasswd(this.passwd);
                usr.update(db);
                
            }
            db.commit();

            try {
                db.close();
            } catch (NotFoundDBException e) {
                if (db != null) {
                    db.rollback();
                }
                Logs.printLog(LogTypes.ERROR, "Database not found");
            }
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "UserManagement: Modify Password: " + ex.getMessage());
        }
    }

    public void delete() throws NotFoundDBException, ResultSetDBException, SQLException, DuplicatedRecordDBException, Exception {
        Database db = null;
        try {
            db = DBService.getDatabase();
            User usr = UserService.getUser(db, this.username);
            if (this.info.isLoggedon() && (this.info.getUsrType() == 2 || this.info.getUsrType() == 1)) {
                usr.delete(db);
            }
            db.commit();

        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "UserManagement: Delete(): " + ex.getMessage());
        }
        try {
            db.close();
        } catch (NotFoundDBException e) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "UserManagement: delete(): Database not found");
        }
    }

    /*public void deleteStr() throws NotFoundDBException, ResultSetDBException, SQLException, DuplicatedRecordDBException, Exception {
     Database db = null;
     try{
     db = DBService.getDatabase();
     Struttura str = StrutturaService.getStruttura(db, this.username);
     if (this.info.isLoggedon() && (this.info.getUsrType() == 1)) {
     str.delete(db);
     }
     db.commit();
     }catch (Exception ex) {
     if (db != null) {
     db.rollback();
     }
     Logs.printLog(LogTypes.ERROR, "UserManagement: DeleteStr(): " + ex.getMessage());
     }
     try {
     db.close();
     } catch (NotFoundDBException e) {
     Logs.printLog(LogTypes.ERROR, "Database not found");
     }
     }
     */
    public List<User> getUserList() {
        List<User> ul = null;
        Database db = null;
        try {
            db = DBService.getDatabase();
            ul = UserService.getUserList(db);
            if (this.info.isLoggedon()) {
                db.commit();
            } else {
                ul = null;
            }
        } catch (NotFoundDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "Database not found: " + ex.getMessage());
        } catch (ResultSetDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "ResultSet Error: " + ex.getMessage());
        } catch (SQLException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "UserManagement getUserList: Generic Exception: " + ex.getMessage());
        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (NotFoundDBException ex) {
                Logs.printLog(LogTypes.ERROR, "Database not found: " + ex.getMessage());
            }
        }
        return ul;
    }

    public User getUser() {
        User u = null;
        Database db = null;
        try {
            db = DBService.getDatabase();
            u = UserService.getUser(db, this.username);
            if (this.info.isLoggedon()) {
                db.commit();
            } else {
                u = null;
            }
        } catch (NotFoundDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "Database not found: " + ex.getMessage());
        } catch (ResultSetDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "ResultSet Error: " + ex.getMessage());
        } catch (SQLException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "UserManagement getUser(): Generic Exception: " + ex.getMessage());
        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (NotFoundDBException ex) {
                Logs.printLog(LogTypes.ERROR, "Database not found: " + ex.getMessage());
            }
        }
        return u;
    }

    public void refreshCookie() {
        Database db = null;
        try {
            db = DBService.getDatabase();
            this.insertType = Session.getType(cookie);
            if (this.insertType.equals("USER")) {
                this.sType = SessionType.USER;
                this.cookie = Session.deleteCookie(this.cookie);
                this.cookie = Session.createLoginCookie(db, this.tmp_name, this.sType, this.passwd);

            }

        } catch (NotFoundDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "Database not found: " + ex.getMessage());
        } catch (SQLException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "SQL Error: " + ex.getMessage());
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "UserManagement getUser(): Generic Exception: " + ex.getMessage());
        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (NotFoundDBException ex) {
                Logs.printLog(LogTypes.ERROR, "Database not found: " + ex.getMessage());
            }
        }

    }

}
