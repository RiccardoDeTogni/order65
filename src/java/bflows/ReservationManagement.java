/*
 PlayToday 2016
 */
package bflows;

import blogics.Campo;
import blogics.CampoService;
import blogics.Reservation;
import blogics.ReservationService;
import blogics.Struttura;
import blogics.StrutturaService;
import blogics.UserService;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import services.database.DBService;
import services.database.Database;
import services.database.exceptions.DuplicatedRecordDBException;
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
    private String citta;
    private String data_temp;
    private String ora_inizio_temp;
    private String ora_fine_temp;
    private String nome_struttura;

    public List<Reservation> getReservationsFromCampo() {
        Database db = null;
        List<Reservation> resList = null;
        try {

            db = DBService.getDatabase();
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            this.data = new java.sql.Date(formatter.parse(this.data_temp).getTime());
            resList = ReservationService.getCurrentReservationFromCampo(db, this.id_campo, this.data);

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
        return resList;
    }
    
    public Campo getNomeCampo_StrutturaFromId() {
        Database db = null;
        Campo c = null;
        
        try {

            db = DBService.getDatabase();
            c = CampoService.getCampo(db, this.id_campo);
            
            String nome_struttura = StrutturaService.getStrutturaFromCampoId(db, c.getId_struttura()).getNome();
            
            c.setNome_struttura(nome_struttura);
            
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
            Logs.printLog(LogTypes.ERROR, "ReservationManagement getNomeCampo&Struttura(): Generic Exception: " + ex.getMessage());

        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (NotFoundDBException e) {
                Logs.printLog(LogTypes.ERROR, "Database not found");
            }
        }
        return c;
    }
    
    public List<Campo> getCampoListFromStruttura() {
        Database db = null;
        List<Campo> cl = new ArrayList();
        try {

            db = DBService.getDatabase();
            cl = CampoService.getCampoListFromStruttura(db, StrutturaService.getStrutturaIDFromNome(db, this.nome_struttura));
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
            Logs.printLog(LogTypes.ERROR, "ReservationManagement getNomeCampoListFromStruttura(): Generic Exception: " + ex.getMessage());

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
    
    public List<Reservation> getReservationsFromUser() {
        Database db = null;
        List<Reservation> resList = null;
        try {

            db = DBService.getDatabase();
            resList = ReservationService.getCurrentReservationFromUser(db, this.id_user);
            

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
            Logs.printLog(LogTypes.ERROR, "ReservationManagement getReservationFromUser(): Generic Exception: " + ex.getMessage());

        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (NotFoundDBException e) {
                Logs.printLog(LogTypes.ERROR, "Database not found");
            }
        }
        return resList;
    }
    
    public Reservation insertReservation() {
        Database db = null;
        Reservation res = null;
        try {

            db = DBService.getDatabase();
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            this.data = new java.sql.Date(formatter.parse(this.data_temp).getTime());
            formatter = new SimpleDateFormat("HH.mm");
            this.ora_inizio =  new java.sql.Time(formatter.parse(this.ora_inizio_temp).getTime());
            this.ora_fine = new java.sql.Time(formatter.parse(this.ora_fine_temp).getTime());
            res = ReservationService.insertReservation(db, this.data, this.ora_inizio, this.ora_fine, this.id_campo, this.id_user, this.aperta, this.num_partecipanti);

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
        } catch (DuplicatedRecordDBException ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "Duplicate Error");
        }catch (Exception ex) {
            if (db != null) {
                db.rollback();
            }
            Logs.printLog(LogTypes.ERROR, "ReservationManagement insertReservation(): Generic Exception: " + ex.getMessage());

        } finally {
            try {
                if (db != null) {
                    db.close();
                }
            } catch (NotFoundDBException e) {
                Logs.printLog(LogTypes.ERROR, "Database not found");
            }
        }
        return res;
    }


    public List<Campo> getFreeCampoFromDateTime() {
        Database db = null;
        List<Campo> cl = null;
        try {

            db = DBService.getDatabase();
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            this.data = new java.sql.Date(formatter.parse(this.data_temp).getTime());
            formatter = new SimpleDateFormat("HH.mm");
            this.ora_inizio =  new java.sql.Time(formatter.parse(this.ora_inizio_temp).getTime());
            this.ora_fine = new java.sql.Time(formatter.parse(this.ora_fine_temp).getTime());
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
            Logs.printLog(LogTypes.ERROR, "ReservationManagement getFreeCampi(): Generic Exception: " + ex.getMessage());

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

    public List<Struttura> getStrutturaListFromCity() {
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
            Logs.printLog(LogTypes.ERROR, "ReservationManagement getStrutturaListFromCity(): Generic Exception: " + ex.getMessage());

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

    

    public void makeCampoUnavailable() {
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
            Logs.printLog(LogTypes.ERROR, "ReservationManagement makeCampoUnavailable(): Generic Exception: " + ex.getMessage());

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

    public String getData_temp() {
        return data_temp;
    }

    public void setData_temp(String data_temp) {
        this.data_temp = data_temp;
    }

    public String getOra_inizio_temp() {
        return ora_inizio_temp;
    }

    public void setOra_inizio_temp(String ora_inizio_temp) {
        this.ora_inizio_temp = ora_inizio_temp;
    }

    public String getOra_fine_temp() {
        return ora_fine_temp;
    }

    public void setOra_fine_temp(String ora_fine_temp) {
        this.ora_fine_temp = ora_fine_temp;
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

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNome_struttura() {
        return nome_struttura;
    }

    public void setNome_struttura(String nome_struttura) {
        this.nome_struttura = nome_struttura;
    }
    
    

}
