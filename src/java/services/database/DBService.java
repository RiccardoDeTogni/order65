/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.database;

import java.sql.*;
import global.*;
import services.database.exceptions.NotFoundDBException;

/**
 *
 * @author Riccardo
 */
public class DBService {

    public static synchronized Database getDatabase() throws SQLException, NotFoundDBException {

        com.mysql.jdbc.Driver DBDriver = new com.mysql.jdbc.Driver();
        Connection connection = DriverManager.getConnection(Constants.DB_ADDRESS);
        Database db = new Database(connection);
        return db;
    }
}
