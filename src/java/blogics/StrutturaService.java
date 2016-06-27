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
public class StrutturaService {

    public static List<Struttura> getStrutturaListFromCity(Database db, String citta) throws SQLException, NotFoundDBException, ResultSetDBException {
        List<Struttura> sl = new ArrayList<>();
        String sql = "  SELECT *"
                + " FROM struttura AS s"
                + " WHERE s.active = 1"
                + " AND s.city = ?";
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, citta);
        ResultSet resultSet = db.select(ps);
        try {
            while (resultSet.next()) {
                sl.add(new Struttura(resultSet));
            }
            resultSet.close();
            return sl;
        } catch (SQLException ex) {
            throw new ResultSetDBException("StrutturaService: getStrutturaListFromCity():  ResultSetDBException: " + ex.getMessage(), db);
        }
    }
//prende la struttura dal database con l'id struttura presente nello user
    public static Struttura getStrutturaFromUser(Database db, long id) throws NotFoundDBException, ResultSetDBException, SQLException {

        Struttura struttura = null;

        String sql = " SELECT * "
                + "   FROM struttura "
                + " WHERE id = ?";

        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, id);

        ResultSet resultSet = db.select(ps);

        try {
            if (resultSet.next()) {
                struttura = new Struttura(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw new ResultSetDBException("StrutturaService: getStrutturaFromCampoId():  ResultSetDBException: " + ex.getMessage(), db);
        }
        return struttura;

    }

    public static Struttura getStrutturaFromCampoId(Database db, long id) throws NotFoundDBException, ResultSetDBException, SQLException {

        Struttura struttura = null;

        String sql = " SELECT * "
                + "   FROM struttura "
                + " WHERE id = ?";

        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setLong(1, id);

        ResultSet resultSet = db.select(ps);

        try {
            if (resultSet.next()) {
                struttura = new Struttura(resultSet);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw new ResultSetDBException("StrutturaService: getStrutturaFromCampoId():  ResultSetDBException: " + ex.getMessage(), db);
        }
        return struttura;

    }

    public static long getStrutturaIDFromNome(Database db, String nome) throws NotFoundDBException, ResultSetDBException, SQLException {

        long ret = 0;

        String sql = " SELECT id "
                + "   FROM struttura "
                + " WHERE name = ?";

        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, nome);

        ResultSet resultSet = db.select(ps);

        try {
            if (resultSet.next()) {
                ret = resultSet.getLong("id");
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw new ResultSetDBException("StrutturaService: getStrutturaFromCampoId():  ResultSetDBException: " + ex.getMessage(), db);
        }
        return ret;

    }

}
