/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.database;

import java.io.*;
import java.sql.*;
import java.util.*;
import services.database.exceptions.*;

/**
 *
 * @author nick
 */
public class Database {

    private final Connection connection;
    private final List<PreparedStatement> statements;

    public Database(Connection connection) throws NotFoundDBException {
        try {
            if ((this.connection = connection) == null) {

            }
            this.connection.setAutoCommit(false);
            statements = new ArrayList<>();
        } catch (Exception e) {
            ByteArrayOutputStream stackTrace = new ByteArrayOutputStream();
            e.printStackTrace(new PrintWriter(stackTrace, true));
            throw new NotFoundDBException("DataBase: DataBase(): Impossibile Aprire la Connessione col DataBase: \n" + stackTrace);
        }
    }

    public ResultSet select(PreparedStatement ps) throws NotFoundDBException {

        ResultSet resultSet;

        try {
            resultSet = ps.executeQuery();
            this.statements.add(ps);

        } catch (SQLException ex) {
            throw new NotFoundDBException("DataBase: select(): Impossibile eseguire la query sul DB. Eccezione: " + ex + "\n " + ps.toString(), this);
        }

        return resultSet;
    }

    public int modify(PreparedStatement ps) throws NotFoundDBException {

        int recordsNumber;
        try {
            recordsNumber = ps.executeUpdate();
            this.statements.add(ps);
        } catch (SQLException ex) {

            throw new NotFoundDBException("DataBase: modify(): Impossibile eseguire la update sul DB. Eccezione: " + ex + "\n " + ps.toString(), this);

        }

        return recordsNumber;

    }

    public void commit() throws NotFoundDBException {

        try {

            connection.commit();
            for(PreparedStatement p : this.statements) {
                p.close();
            }
            this.statements.clear();
        } catch (SQLException ex) {
            throw new NotFoundDBException("DataBase: commit(): Impossibile eseguire la commit sul DB. Eccezione: " + ex, this);
        }

    }

    public void rollback() {

        try {
            connection.rollback();
            for(PreparedStatement p : this.statements) {
                p.close();
            }
            this.statements.clear();
        } catch (SQLException ex) {
            new NotFoundDBException("DataBase: rollback(): Impossibile eseguire il RollBack sul DB. Eccezione: " + ex);
        }
    }

    public void close() throws NotFoundDBException {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new NotFoundDBException("DataBase: close(): Impossibile chiudere il DB. Eccezione: " + ex);
        }
    }

    protected void finalize() throws Throwable {
        this.close();
    }

    public Connection getConnection() {
        return this.connection;
    }

}
