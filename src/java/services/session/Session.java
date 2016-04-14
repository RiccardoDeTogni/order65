/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.session;

import services.error.*;
import services.database.*;
import services.database.exceptions.*;
import blogics.*;
import global.Constants;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.*;
import services.hash.Sha512;
import services.log.LogTypes;
import services.log.Logs;

/**
 *
 * @author Riccardo
 */
public class Session {

    public static Cookie createLoginCookie(Database db, String username, SessionType sType, String citta) throws NotFoundDBException, ResultSetDBException, SQLException, Exception {
        Cookie cookie = null;
        String token = "";
        ILoggableEntity le = null;
        if (sType == SessionType.USER) {
            le = UserService.getUser(db, username);
        }
        
        if (le != null) {
            token = le.generateSession(db);
            cookie = new Cookie(Constants.COOKIE_NAME, le.getId() + "|" + username + "|" + sType.toString() + "|" + token + "|" + citta);
            cookie.setPath("/");
        }
        return cookie;

    }

    public static Cookie deleteCookie(Cookie cookie) {
        cookie.setValue("");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;

    }

    public static String getValue(Cookie cookie, String name, int position) {
        String value = null;

        if (cookie.getName().equals(name)) {
            List<String> oV = util.Conversion.tokenizeString(cookie.getValue(), "|");
            value = oV.get(position);
        }

        return value;

    }

    public static long getId(Cookie cookie) {
        return Long.parseLong(getValue(cookie, Constants.COOKIE_NAME, 0));
    }

    public static String getType(Cookie cookie) {
        return getValue(cookie, Constants.COOKIE_NAME, 2);
    }

    public static String getUsername(Cookie cookie) {
        return getValue(cookie, Constants.COOKIE_NAME, 1);
    }

    public static String getSession(Cookie cookie) {
        return getValue(cookie, Constants.COOKIE_NAME, 3);
    }

    public static String getCitta(Cookie cookie) {
        return getValue(cookie, Constants.COOKIE_NAME, 4);
    }

    public static ILoggableEntity validateSession(Database db, Cookie cookie) throws NotFoundDBException, ResultSetDBException, SQLException, Exception {
        ILoggableEntity le = null;
        if (Session.getType(cookie).equals(SessionType.USER.toString())) {
            le = UserService.getUser(db, Session.getUsername(cookie));
            return (le.getUsrsession()).equals(Session.getSession(cookie)) ? le : null;
        }
        return le;
    }
}
