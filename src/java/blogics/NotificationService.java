/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogics;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import services.database.Database;
import services.database.exceptions.DuplicatedRecordDBException;
import services.database.exceptions.NotFoundDBException;
import services.database.exceptions.ResultSetDBException;

/**
 *
 * @author Riccardo
 */
public class NotificationService {
    
    public static Notification insertNotification(Database db, String message, int type, long id_user, Timestamp time) throws NotFoundDBException, DuplicatedRecordDBException, ResultSetDBException, SQLException{
        Notification not = new Notification(type, message, id_user, time);
        not.insert(db);
        return not;
    }
    
    public static List<Notification> getUserNotification(Database db, long id_user, Timestamp data) throws SQLException, NotFoundDBException{
        List<Notification> nl = new ArrayList();
        String sql = "SELECT *"
                + "   FROM notifica"
                + "   WHERE id_user = ? AND time > ?";
        
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, id_user);
        ps.setTimestamp(2, data);
        ResultSet rs = db.select(ps);
        while(rs.next()){
            nl.add(new Notification(rs));
        }
        
        return nl;
    }
    
}
