/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blogics;

import java.sql.SQLException;
import services.database.*;
import services.database.exceptions.*;
import services.session.*;

/**
 *
 * @author User
 */
public interface ILoggableEntity {

    public long getId();

    public String getUsername();

    public String getPasswd();

    public String getUsrsession();

    public void insert(Database db) throws NotFoundDBException, DuplicatedRecordDBException, ResultSetDBException, SQLException;

    public void update(Database db) throws NotFoundDBException, DuplicatedRecordDBException, ResultSetDBException, SQLException;

    public void delete(Database db) throws NotFoundDBException, ResultSetDBException, SQLException;
    public String generateSession(Database db) throws NotFoundDBException, SQLException;

    public void invalidateSession(Database db) throws NotFoundDBException, SQLException;

    public SessionType getSessionType();
}
