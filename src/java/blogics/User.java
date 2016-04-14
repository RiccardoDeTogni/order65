/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogics;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.database.*;
import services.database.exceptions.*;
import services.hash.*;
import services.log.*;
import services.session.SessionToken;
import services.session.SessionType;
import util.Conversion;

/**
 *
 * @author Riccardo
 */
public class User implements ILoggableEntity{

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

    public long getStruttura() {
        return struttura;
    }

    public void setStruttura(long struttura) {
        this.struttura = struttura;
    }
    
    

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public long getSport() {
        return sport;
    }

    public void setSport(long sport) {
        this.sport = sport;
    }
    
    

    public User(String username, String passwd, String first_name, String surname, String email, int type, String telephone, String city, long struttura, long sport) throws Exception {
        this.first_name = first_name;
        this.username = username;
        this.passwd = Sha512.hashText(Sha512.hashText(passwd));
        this.surname = surname;
        this.email = email;
        this.type = type;
        this.telephone = telephone;
        this.city = city;
        this.sport = sport;
        this.struttura = struttura;
        

    }

    public User(ResultSet rs) {
        try {
            this.id = rs.getInt("ID");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.struttura = rs.getInt("struttura");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.first_name = rs.getString("first_name");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.username = rs.getString("username");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.passwd = rs.getString("passwd");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.email = rs.getString("email");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.reg_date = rs.getDate("reg_date");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.surname = rs.getString("surname");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.type = rs.getInt("type");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.last_mod = rs.getDate("last_mod");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.usrsession = rs.getString("session_token");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        
        try {
            this.telephone = rs.getString("telephone");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        
        try {
            this.city = rs.getString("city");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }

        try {
            this.sport = rs.getLong("sport");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.active = rs.getBoolean("fl_active");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public long getId() {
        return this.id;
    }

    public void setPasswd(String passwd) throws Exception {
        this.passwd = Sha512.hashText(Sha512.hashText(passwd));
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return this.type;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getEmail() {
        return this.email;
    }

    public Date getReg_date() {
        return this.reg_date;
    }

    public Date getLast_mod() {
        return this.last_mod;
    }

    @Override
    public String getPasswd() {
        return this.passwd;
    }

    @Override
    public String getUsrsession() {
        return this.usrsession;
    }

    @Override
    public void insert(Database db) throws NotFoundDBException, DuplicatedRecordDBException, ResultSetDBException, SQLException {
        String sql = "SELECT * FROM user WHERE username =?";
        Boolean exist = false;
        boolean active = false;
        ResultSet rs;
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, this.username);
        rs = db.select(ps);
        try {
            exist = rs.next();
            if (exist) {
                active = rs.getBoolean("fl_active");
            }

            if (exist && active) {
                throw new DuplicatedRecordDBException("User: insert(): Tentativo di inserimento di un utente già esistente.");
            } else if (exist && !active) {
                User u = new User(rs);
                u.setEmail(email);
                u.setFirst_name(first_name);
                u.passwd = this.passwd;
                u.setSurname(surname);
                u.setActive(true);
                u.setCity(city);
                u.setTelephone(telephone);
                u.setStruttura(this.struttura);
                u.setSport(this.sport);
                rs.close();
                u.update(db);
            } else {

                sql = "INSERT INTO"
                        + " user"
                        + " ("
                        + "     username,"
                        + "     passwd,"
                        + "     first_name,"
                        + "     surname,"
                        + "     last_mod,"
                        + "     reg_date,"
                        + "     email,"
                        + "     type,"
                        + "     fl_active,"
                        + "     sport,"
                        + "     telephone,"
                        + "     city,"
                        + "     struttura"
                        + " )"
                        + " VALUES"
                        + " ("
                        + "     ?,"
                        + "     ?,"
                        + "     ?,"
                        + "     ?,"
                        + "     ?,"
                        + "     ?,"
                        + "     ?,"
                        + "     ?,"
                        + "     ?,"
                        + "     TRUE,"
                        + "     ?,"
                        + "     ?,"
                        + "     ?,"
                        + "     ?"
                        + " )";
                ps = db.getConnection().prepareStatement(sql);
                java.util.Date d = new java.util.Date();
                ps.setString(1, this.username);
                ps.setString(2, this.passwd);
                ps.setString(3, this.first_name);
                ps.setString(4, this.surname);
                ps.setTimestamp(5, new Timestamp(d.getTime()));
                ps.setTimestamp(6, new Timestamp(d.getTime()));
                ps.setString(7, this.email);
                ps.setInt(8, this.type);
                ps.setLong(9, this.sport);
                ps.setString(10, this.telephone);
                ps.setString(11, this.city);
                ps.setLong(12, this.struttura);

                db.modify(ps);
            }
        } catch (SQLException e) {
            throw new ResultSetDBException("User: insert(): Errore sul ResultSet: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Database db) throws NotFoundDBException, DuplicatedRecordDBException, ResultSetDBException, SQLException {
        String sql = "";
        sql = "SELECT ID FROM user WHERE username =? AND id <> ? AND fl_active =TRUE";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, this.username);
        ps.setLong(2, id);
        Boolean exist = false;
        ResultSet rs;
        rs = db.select(ps);
        try {
            exist = rs.next();
            rs.close();
        } catch (SQLException e) {
            throw new ResultSetDBException("User: update(): Errore sul ResultSet.");
        }

        if (exist) {
            throw new DuplicatedRecordDBException("User: update(): Errore di modifica.");
        }

        sql = "UPDATE user"
                + " SET"
                + "     username = ?,"
                + "     first_name = ?,"
                + "     passwd = ?,"
                + "     surname = ?,"
                + "     last_mod = ?,"
                + "     email = ?,"
                + "     type = ?,"
                + "     sport = ?,"
                + "     fl_active = ?,"
                + "     telephone = ?,"
                + "     city = ?"
                + " WHERE"
                + "     ID = ?";

        ps = db.getConnection().prepareStatement(sql);
        java.util.Date d = new java.util.Date();
        ps.setString(1, this.username);
        ps.setString(3, this.passwd);
        ps.setString(2, this.first_name);
        ps.setString(4, this.surname);
        ps.setTimestamp(5, new Timestamp(d.getTime()));
        ps.setString(6, this.email);
        ps.setInt(7, this.type);
        ps.setLong(8, this.sport);
        ps.setBoolean(9, this.active);
        ps.setString(10, this.telephone);
        ps.setString(11, this.city);
        ps.setLong(12, this.id);
        db.modify(ps);
    }

    @Override
    public void delete(Database db)
            throws NotFoundDBException, ResultSetDBException, SQLException {

        String sql;

        sql = "UPDATE user "
                + " SET fl_active= FALSE "
                + " WHERE "
                + " id=" + this.id + "";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        db.modify(ps);

    }

    @Override
    public String generateSession(Database db) throws NotFoundDBException, SQLException {
        String token;
        String sql;
        PreparedStatement ps;

        token = this.usrsession;

        if (token == null) {
            token = SessionToken.generateSessionToken();
            sql = "UPDATE user"
                    + " SET"
                    + "     session_token = ?"
                    + " WHERE"
                    + "     ID = ?";
            ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, token);
            ps.setLong(2, this.id);
            db.modify(ps);
        }

        return token;
    }

    @Override
    public void invalidateSession(Database db) throws NotFoundDBException, SQLException {
        String sql = "UPDATE user"
                + " SET"
                + "     session_token = NULL"
                + " WHERE"
                + "     ID = ?";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, this.id);
        db.modify(ps);
    }

    public void re_activate(Database db)
            throws NotFoundDBException, ResultSetDBException, SQLException {

        String sql;
        sql = "UPDATE user "
                + " SET fl_active= TRUE"
                + " WHERE "
                + " id=" + this.id + "";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        db.modify(ps);

    }

    @Override
    public SessionType getSessionType() {
        return SessionType.USER;
    }

}
