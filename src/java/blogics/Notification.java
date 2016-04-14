
package blogics;

import java.sql.*;
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
    
    public Notification(int type, long id, String message, long id_user, Timestamp time){
        this.time = time;
        this.type = type;
        this.id = id;
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
    
}
