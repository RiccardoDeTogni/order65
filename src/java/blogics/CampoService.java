/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogics;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import services.database.Database;
import services.database.exceptions.NotFoundDBException;
import services.database.exceptions.ResultSetDBException;

/**
 *
 * @author Riccardo
 */
public class CampoService {
    
    public static List<Campo> getCampoListFromStruttura(Database db, long id_struttura) throws ResultSetDBException, SQLException, NotFoundDBException{
        List<Campo> cl = new ArrayList<>();
        String sql = "  SELECT *"
                + " FROM campo AS c"
                + " WHERE id_struttura = ?";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, id_struttura);
        ResultSet resultSet = db.select(ps);
        try {
            while (resultSet.next()) {
                cl.add(new Campo(resultSet));
            }
            resultSet.close();
            return cl;
        } catch (SQLException ex) {
            throw new ResultSetDBException("UserService: getCampoListFromStruttura():  ResultSetDBException: " + ex.getMessage(), db);
        }
    }
    
    public static Campo getCampo(Database db, long id) throws NotFoundDBException, ResultSetDBException, SQLException {

        Campo campo = null;

        String sql = " SELECT * "
                + "   FROM campo AS c"
                + " WHERE "
                + "   c.id = ? ";

        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, id);

        ResultSet resultSet = db.select(ps);

        try {
            if (resultSet.next()) {
                campo = new Campo(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw new ResultSetDBException("UserService: getUser():  ResultSetDBException: " + ex.getMessage(), db);
        }
        return campo;

    }
    }
