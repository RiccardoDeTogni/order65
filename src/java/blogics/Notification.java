
package blogics;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.database.Database;
import services.database.exceptions.DuplicatedRecordDBException;
import services.database.exceptions.NotFoundDBException;
import services.database.exceptions.ResultSetDBException;
import services.log.LogTypes;
import services.log.Logs;

/**
 *
 * @author Riccardo
 */
public class Notification {
    private int type;
    private long id;
    private String message;
    private long id_user;
    private Timestamp time;
    
    public Notification(int type, String message, long id_user, Timestamp time){
        this.time = time;
        this.type = type;
        this.id_user = id_user;
        this.message = message;
    }
    
    public Notification(ResultSet rs){
    try {
            this.id = rs.getInt("ID");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
    try {
            this.id_user = rs.getInt("ID_USER");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
    try {
            this.time = rs.getTimestamp("Time");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
    try {
            this.message = rs.getString("message");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
    try {
            this.type = rs.getInt("Type");
        } catch (SQLException sqle) {
            Logs.printLog(LogTypes.WARNING, sqle.toString());
        }
    }
    
    public void insert(Database db) throws NotFoundDBException, DuplicatedRecordDBException, ResultSetDBException, SQLException {
               try{
                   String sql = "INSERT INTO"
                        + " user"
                        + " ("
                        + "     type,"
                        + "      message,"
                        + "      id_user,"
                        + "      time"
                        + " )"
                        + " VALUES"
                        + " ("
                        + "     ?,"
                        + "     ?,"
                        + "     ?,"
                        + "     ?"
                        + " )";
                PreparedStatement ps = db.getConnection().prepareStatement(sql);
                java.util.Date d = new java.util.Date();
                ps.setInt(1, this.type);
                ps.setString(2, this.message);
                ps.setLong(3, this.id_user);
                ps.setTimestamp(4, this.time);

                db.modify(ps);
        } catch (SQLException e) {
            throw new ResultSetDBException("User: insert(): Errore sul ResultSet: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
