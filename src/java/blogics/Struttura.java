/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogics;

import java.sql.*;
import services.database.Database;
import services.database.exceptions.NotFoundDBException;
import services.database.exceptions.ResultSetDBException;
import services.log.LogTypes;
import services.log.Logs;

/**
 *
 * @author Riccardo
 */
public class Struttura {
    private long id;
    private String nome;
    private long id_user;
    private String citta;
    private boolean active;
    private String indirizzo;
    private String latitude;
    private String longitude;

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
    
    public Struttura(ResultSet rs){
        try {
                this.id = rs.getInt("ID");
            } catch (SQLException sqle) {
                Logs.printLog(LogTypes.WARNING, sqle.toString());
            }
        try {
            this.nome = rs.getString("name");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.indirizzo = rs.getString("indirizzo");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.latitude = rs.getString("latitude");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.longitude = rs.getString("longitude");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.id_user = rs.getInt("id_user");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.citta = rs.getString("city");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
        try {
            this.active = rs.getBoolean("active");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
    }
    
    
    
    public void delete(Database db)
            throws NotFoundDBException, ResultSetDBException, SQLException {

        String sql;

        sql = "UPDATE struttura "
                + " SET active= FALSE "
                + " WHERE "
                + " id=" + this.id + "";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        db.modify(ps);

    }
}
