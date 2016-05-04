/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogics;

import global.Constants;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.database.Database;
import services.database.exceptions.DuplicatedRecordDBException;
import services.database.exceptions.NotFoundDBException;
import services.database.exceptions.ResultSetDBException;
import services.log.LogTypes;
import services.log.Logs;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Riccardo
 */
public class Reservation {

    private long id;
    private Date data;
    private Time ora_inizio;
    private Time ora_fine;
    private long id_campo;
    private boolean aperta;
    private int num_partecipanti;
    private long id_user;
    private long code;

    public Reservation(Date data, Time ora_inizio, Time ora_fine, long id_campo, boolean aperta, int num_partecipanti, long id_user) {
        this.data = data;
        this.ora_inizio = ora_inizio;
        this.ora_fine = ora_fine;
        this.id_campo = id_campo;
        this.aperta = aperta;
        this.num_partecipanti = num_partecipanti;
        this.id_user = id_user;
    }

    public Reservation(ResultSet rs) {
        try {
            this.id = rs.getLong("ID");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.data = rs.getDate("data");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.ora_inizio = rs.getTime("ora_inizio");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.ora_fine = rs.getTime("ora_fine");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.id_campo = rs.getLong("id_campo");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.id_user = rs.getLong("id_user");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.code = rs.getLong("code");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.aperta = rs.getBoolean("aperta");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.num_partecipanti = rs.getInt("num_partecipanti");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
    }

    public void insertReservation(Database db) throws SQLException, NotFoundDBException, ResultSetDBException {
        ResultSet rs = null;
        String sql = "SELECT * from prenotazione where data = ? AND ? < ora_inizio < ? AND id_campo = ?";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setDate(1, this.data);
        ps.setTime(2, ora_inizio);
        ps.setTime(3, ora_fine);
        ps.setLong(4, id_campo);
        rs = db.select(ps);
        try {
            boolean exist = rs.next();
            if (exist) {
                throw new DuplicatedRecordDBException("Reservation: insert(): Campo già occupato");
            } else {
                sql = "INSERT INTO"
                        + " prenotazione"
                        + "("
                        + "data,"
                        + "ora_inizio,"
                        + "ora_fine,"
                        + "id_campo,"
                        + "id_user,"
                        + "aperta,"
                        + "num_partecipanti,"
                        + "codice"
                        + ")"
                        + "VALUES"
                        + "("
                        + "?,"
                        + "?,"
                        + "?,"
                        + "?,"
                        + "?,"
                        + "?,"
                        + "?,"
                        + "?"
                        + ")";
                ps = db.getConnection().prepareStatement(sql);
                ps.setDate(1, this.data);
                ps.setTime(2, ora_inizio);
                ps.setTime(3, ora_fine);
                ps.setLong(4, id_campo);
                ps.setLong(5, id_user);
                ps.setBoolean(6, aperta);
                ps.setInt(7, num_partecipanti);
                ps.setLong(8, ThreadLocalRandom.current().nextInt(Constants.MIN, Constants.MAX + 1));
            db.modify(ps);
            }
        } catch (SQLException e) {
            throw new ResultSetDBException("Reservation: insert(): Errore sul ResultSet: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Database db)
            throws NotFoundDBException, ResultSetDBException, SQLException {

        String sql;

        sql = "DELETE FROM prenotazione "
                + "WHERE id = ?";

        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, this.id);
        db.modify(ps);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getOra_inizio() {
        return ora_inizio;
    }

    public void setOra_inizio(Time ora_inizio) {
        this.ora_inizio = ora_inizio;
    }

    public Time getOra_fine() {
        return ora_fine;
    }

    public void setOra_fine(Time ora_fine) {
        this.ora_fine = ora_fine;
    }

    public long getId_campo() {
        return id_campo;
    }

    public void setId_campo(long id_campo) {
        this.id_campo = id_campo;
    }

    public boolean isAperta() {
        return aperta;
    }

    public void setAperta(boolean aperta) {
        this.aperta = aperta;
    }

    public int getNum_partecipanti() {
        return num_partecipanti;
    }

    public void setNum_partecipanti(int num_partecipanti) {
        this.num_partecipanti = num_partecipanti;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

}
