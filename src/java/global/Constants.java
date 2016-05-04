/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package global;

/**
 *
 * @author nick
 */
public class Constants {
    public static final String COOKIE_NAME = "_playtoday_session_";
    
    //public static final String DB_USERNAME = "cias";
    //public static final String DB_PASSWD = "C1as2015";
    //public static final String DB_ADDRESS = "jdbc:mysql://localhost/cias?user=" + Constants.DB_USERNAME + "&password="+ Constants.DB_PASSWD;
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWD = "";
    public static final String DB_ADDRESS = "jdbc:mysql://localhost/playtoday?user=" + Constants.DB_USERNAME + "&password="+ Constants.DB_PASSWD;

    public static final Boolean ENABLE_LOGS = true;
    public static final String LOG_FOLDER = "C:\\Users\\Riccardo\\Documents\\Logs\\";
    public static final String LOG_DBINFO = Constants.LOG_FOLDER + "dbinfo.log";
    public static final String LOG_ERROR = Constants.LOG_FOLDER + "error.log";
    public static final String LOG_WARNING = Constants.LOG_FOLDER + "warning.log";
    public static final String LOG_INFO = Constants.LOG_FOLDER + "info.log";
    
    public static final boolean ENABLE_MAIL = true;
    public static final String MAIL_SERVER = "pigreco.xyz";
    public static final String USER_NAME = "logger@pigreco.xyz";
    public static final String MAIL_FROM = "logger@pigreco.xyz";
    public static final String PASSWORD = "tttpwd314";
    public static final String SMTP_PORT = "587";
    
    public static final int MIN = 10000;
    public static final int MAX = 99999;
    
    public static final String ADMIN_MAIL = "detogni.riccardo@gmail.com";
    
}
