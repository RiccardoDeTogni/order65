/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.database.*;
import services.database.exceptions.*;
import services.log.*;
import util.*;


public class UserService {

    public static User insertUser(Database db, String username, String passwd, String first_name, String surname, String email, int type, String telephone, String city, long struttura, long sport, Date birthdate) throws Exception {
        User user = new User(username, passwd, first_name, surname, email, type, telephone, city, struttura, sport, birthdate);
        
        user.insert(db);
        return user;
    }

    public static User getUser(Database db, String username) throws NotFoundDBException, ResultSetDBException, SQLException {

        User user = null;

        String sql = " SELECT * "
                + "   FROM user AS u"
                + " WHERE "
                + "   u.username = ? ";

        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, username);

        ResultSet resultSet = db.select(ps);

        try {
            if (resultSet.next()) {
                user = new User(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw new ResultSetDBException("UserService: getUser():  ResultSetDBException: " + ex.getMessage(), db);
        }
        return user;

    }
    
    public static User getUser(Database db, long id) throws NotFoundDBException, ResultSetDBException, SQLException {

        User user = null;

        String sql = " SELECT * "
                + "   FROM user AS u"
                + " WHERE "
                + "   u.id = ? ";

        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, id);

        ResultSet resultSet = db.select(ps);

        try {
            if (resultSet.next()) {
                user = new User(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw new ResultSetDBException("UserService: getUser():  ResultSetDBException: " + ex.getMessage(), db);
        }
        return user;

    }

    public static List<User> getUserList(Database db) throws SQLException, NotFoundDBException, ResultSetDBException {
        List<User> ul = new ArrayList<>();
        String sql = "  SELECT *"
                + " FROM user AS u"
                + " WHERE u.fl_active = 1"
                + " AND u.type <> 1";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ResultSet resultSet = db.select(ps);
        try {
            while (resultSet.next()) {
                ul.add(new User(resultSet));
            }
            resultSet.close();
            return ul;
        } catch (SQLException ex) {
            throw new ResultSetDBException("UserService: getUserList():  ResultSetDBException: " + ex.getMessage(), db);
        }
    }

    public static boolean isActive(Database db, long id) throws NotFoundDBException, SQLException, Exception {
        String sql = "SELECT fl_active"
                + " FROM user"
                + " WHERE ID = ?";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = db.select(ps);
        if (rs.next()) {
            return rs.getBoolean("fl_active");
        } else {
            throw new Exception();
        }
    }
    
    public static List<User> getFriends(Database db, long user) throws ResultSetDBException, SQLException, NotFoundDBException{
        List<User> ul = new ArrayList<>();
        String sql = "  SELECT DISTINCT *"
                + " FROM user AS u JOIN amici as a"
                + " WHERE u.fl_active = 1 "
                + " AND (u.id = a.id_user1 OR u.id = a.id_user2)"
                + " AND u.type <> 1";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ResultSet resultSet = db.select(ps);
        try {
            while (resultSet.next()) {
                ul.add(new User(resultSet));
            }
            resultSet.close();
            return ul;
        } catch (SQLException ex) {
            throw new ResultSetDBException("UserService: getFriends():  ResultSetDBException: " + ex.getMessage(), db);
        }   
    }
    
    public static void addFriend(Database db, String username, long id_user) throws NotFoundDBException, ResultSetDBException, SQLException{
        try{
        User user2 = UserService.getUser(db, username);
        String sql = "INSERT INTO amici"
                + " (id_user1, id_user2)"
                + " VALUES (?, ?)";
        
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, id_user);
        ps.setLong(2, user2.getId());
        db.modify(ps);
    }catch (SQLException e) {
            throw new ResultSetDBException("User: addfriend(): Errore sul ResultSet: " + e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
