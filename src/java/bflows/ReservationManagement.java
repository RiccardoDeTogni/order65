/*
PlayToday 2016
 */
package bflows;

import blogics.Campo;
import blogics.Reservation;
import blogics.ReservationService;
import blogics.Struttura;
import blogics.StrutturaService;
import blogics.UserService;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import services.database.DBService;
import services.database.Database;
import services.database.exceptions.NotFoundDBException;
import services.database.exceptions.ResultSetDBException;
import services.log.LogTypes;
import services.log.Logs;
import services.session.SessionType;

/**
 *
 * @author Riccardo
 */
public class ReservationManagement {
    private long id;
    private Date data;
    private Time ora_inizio;
    private Time ora_fine;
    private long id_campo;
    private boolean aperta;
    private int num_partecipanti;
    private long id_user;
    private List<Reservation> resList;
    private String citta;
    
    public void getReservationsFromCampo(){
      Database db = null;
        try {
            
            db = DBService.getDatabase();
            this.resList = ReservationService.getCurrentReservationFromCampo(db, this.id_campo, this.data);
            
            db.commit();

        } catch (NotFoundDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "Database not found");
        } catch (SQLException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "SQL Error");
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "UserManagement getReservationFromCampo(): Generic Exception: " + ex.getMessage());

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
    
    public List<Campo> getFreeCampoFromDateTime(){
      Database db = null;
      List<Campo> cl = null;
        try {
            
            db = DBService.getDatabase();
            cl = ReservationService.getFreeCampiFromDateTime(db, this.data, this.ora_inizio, this.ora_fine, this.citta);
            
            db.commit();

        } catch (NotFoundDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "Database not found");
        } catch (SQLException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "SQL Error");
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "ReservationManagement getReservationFromCampo(): Generic Exception: " + ex.getMessage());

        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (NotFoundDBException e) {
                Logs.printLog(LogTypes.ERROR, "Database not found");
            }
        }
        return cl;
    }
    
    public List<Struttura> getStrutturaListFromCity(){
      Database db = null;
      List<Struttura> sl = null;
        try {
            
            db = DBService.getDatabase();
            sl = StrutturaService.getStrutturaListFromCity(db, this.citta);
            
            db.commit();

        } catch (NotFoundDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "Database not found");
        } catch (SQLException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "SQL Error");
        } catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "UserManagement getReservationFromCampo(): Generic Exception: " + ex.getMessage());

        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (NotFoundDBException e) {
                Logs.printLog(LogTypes.ERROR, "Database not found");
            }
        }
        return sl;
    }
    
    public void insertReservation() {
        Database db = null;
        try {
            
            db = DBService.getDatabase();
            ReservationService.insertReservation(db, this.data, this.ora_inizio, this.ora_fine, this.id_campo, this.id_user, this.aperta, this.num_partecipanti);

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
            Logs.printLog(LogTypes.ERROR, "UserManagement insertReservation(): Generic Exception: " + ex.getMessage());

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
    
    public void makeCampoUnavailable(){
        Database db = null;
        try {
            
            db = DBService.getDatabase();
            ReservationService.makeCampoUnavailable(db, this.data, this.ora_inizio, this.ora_fine, this.id_campo, this.id_user);

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

    public List<Reservation> getResList() {
        return resList;
    }

    public void setResList(List<Reservation> resList) {
        this.resList = resList;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }
    
    
        
}
    
