/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import services.database.Database;
import services.database.exceptions.NotFoundDBException;
import services.database.exceptions.ResultSetDBException;
import services.log.LogTypes;
import services.log.Logs;
import util.DateUtils;

/**
 *
 * @author Riccardo
 */
public class ReservationService {
    
   public static Reservation insertReservation(Database db, Date data, Time ora_inizio, Time ora_fine, long id_campo, long id_user, boolean aperta, int num_partecipanti) throws SQLException, NotFoundDBException, ResultSetDBException{
       Reservation reserv = new Reservation(data, ora_inizio, ora_fine, id_campo, aperta, num_partecipanti, id_user);
       reserv.insertReservation(db);
       return reserv;
   }
   
   public static List<Reservation> getCurrentReservationFromCampo(Database db, long c, Date d) throws SQLException, NotFoundDBException{
       List<Reservation> resList = new ArrayList();
       ResultSet rs = null;
       String sql = "SELECT *"
                       + "  FROM prenotazione"
                       + "  WHERE id_campo = ? AND"
                       + "  data = ?";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, c);
        rs = db.select(ps);
        while(rs.next()){
            resList.add(new Reservation(rs));
        }
        return resList;
   }
   
   public static List<Campo> getFreeCampiFromDateTime(Database db, Date data, Time ora_inizio, Time ora_fine, String citta) throws SQLException, NotFoundDBException, ResultSetDBException{
       List<Struttura> strList = StrutturaService.getStrutturaListFromCity(db, citta);
       List<Campo> freeCampoList = new ArrayList();
       List<Campo> campoList = new ArrayList();
       ResultSet rs = null;
       boolean exist = false;
       for(Struttura s : strList){
           campoList = CampoService.getCampoListFromStruttura(db, s.getId());
           for(Campo c : campoList){
               String sql = "SELECT *"
                       + "  FROM prenotazione"
                       + "  WHERE id_campo = ? AND"
                       + "  data = ? AND"
                       + "  ((ora_inizio < ? AND ora_fine > ?) OR (ora_inizio < ? AND ora_fine > ? ) OR (ora_inizio > ? AND ora_fine < ? ))";
               PreparedStatement ps = db.getConnection().prepareStatement(sql);
               ps.setLong(1, c.getId());
               ps.setDate(2, data);
               ps.setTime(3, ora_inizio);
               ps.setTime(4, ora_inizio);
               ps.setTime(5, ora_fine);
               ps.setTime(6, ora_fine);
               ps.setTime(7, ora_inizio);
               ps.setTime(8, ora_fine);
               rs = db.select(ps);
               exist = rs.next();
               if(!exist){
                   c.setNome_struttura(s.getNome());
                   freeCampoList.add(c);
               }
           }
       }
       return freeCampoList;
    }
   
   public static void makeCampoUnavailable(Database db, Date d, Time init_time, Time end_time, long id_campo, long id_user) throws SQLException, NotFoundDBException, ResultSetDBException{
       Time inc = init_time;
       while(inc.before(end_time)){
           insertReservation(db, d, inc, inc = (new Time(inc.getTime() + 3600*1000)), id_campo, id_user, false, 0 );
       }
       
   }
   
   public static void deleteReservation(Database db, Reservation res) throws SQLException{
       String sql = "DELETE FROM prenotazione WHERE id_user = ? AND date = ? AND ora_inizio = ? AND ora_fine = ?";
       PreparedStatement ps = db.getConnection().prepareStatement(sql);
       ps.setLong(1, res.getId_user());
       ps.setDate(2, res.getData());
       ps.setTime(3, res.getOra_inizio());
       ps.setTime(4, res.getOra_fine());
   }
   
   public static List<Reservation> getCurrentReservationFromUser(Database db, long user_id) throws SQLException, NotFoundDBException{
       List<Reservation> resList = new ArrayList();
       ResultSet rs = null;
       String sql = "SELECT *"
                       + "  FROM prenotazione"
                       + "  WHERE id_user = ? AND"
                       + "  data >= CURDATE()";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, user_id);
        rs = db.select(ps);
        while(rs.next()){
            resList.add(new Reservation(rs));
        }
        return resList;
   }
   
   public static Reservation getReservation(Database db, long code, long user_id) throws SQLException, NotFoundDBException{
       ResultSet rs = null;
       Reservation res = null;
       String sql = "SELECT *"
                       + "  FROM prenotazione"
                       + "  WHERE id_user = ? AND code = ?"
                       + "  data >= CURDATE()";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, user_id);
        ps.setLong(2, code);
        rs = db.select(ps);
        if(rs.next()){
            res = new Reservation(rs);
        }
        return res;
   }
   

}