package services.error;

import java.util.*;
import java.io.*;
import org.xml.sax.*;

import global.*;
import services.log.*;
import services.mail.*;
import services.mail.exceptions.*;

public class EService {

    /**
     * Rappresenta un errore ingestibile.
     * <p>
     * Ogni volta che nel flusso del sito si verifica una eccezione che
     * rappresenta una anormale condizione del flusso tale da non poter essere
     * gestita, l'errore è classificato con questa costante affinché il flusso
     * venga redirezionato verso una pagina di errore.
     *
     */
    public static final int UNRECOVERABLE_ERROR = -1;

    /**
     * Rappresenta un errore gestibile.
     * <p>
     * Ogni volta che nel flusso del sito si verifica una eccezione che
     * rappresenta una anormale condizione del flusso tale da poter essere
     * gestita, l'errore è classificato con questa costante affinché il flusso
     * lo possa gestire.
     *
     */
    public static final int RECOVERABLE_ERROR = -2;

    /**
     * Class Constructor.
     * <p>
     * Non utilizzato in quanto i metodi della classe sono statici.
     *
     */
    public EService() {
    }

    /**
     * Gestisce il recover di un Fatal Error.
     * <p>
     * Gestisce il recover di un Fatal Error facendo la RollBack, inviando una
     * mail di notifica al responsabile e loggando l'errore sia sul log dei
     * fatal error che su quello della sezione di codice relativa all'errore.
     * <p>
     * @param fatalError L'errore da gestire
     *
     * @see FatalError
     *
     */
    public static void logAndRecover(IFatalError fatalError) {

        fatalError.makeRollBack();
        fatalError.mailError();
        fatalError.log();

        Logs.printLog(LogTypes.ERROR, fatalError.getLogMessage());

    }

    /**
     * Gestisce il recover di un General Error.
     * <p>
     * Gestisce il recover di un General Error facendo la RollBack e loggando
     * l'errore sia sul log dei General Error che su quello della sezione di
     * codice relativa all'errore.
     * <p>
     * @param generalError L'errore da gestire
     *
     * @see GeneralError
     *
     */
    public static void logAndRecover(IGeneralError generalError) {

        generalError.makeRollBack();
        generalError.log();

        Logs.printLog(LogTypes.ERROR, generalError.getLogMessage());

    }

    public static void logAndRecover(IWarning warning) {

        warning.log();

        Logs.printLog(LogTypes.WARNING, warning.getLogMessage());

    }

    public static void logAndRecover(Exception ex) {

        Logs.printLog(LogTypes.ERROR, ex.getMessage());

    }

    public static void logFrontendException(Throwable exception, HashMap info, List parameters) throws Exception {

        StringBuilder parametersView = new StringBuilder();
        int i;

        String message = exception.getMessage();

        ByteArrayOutputStream stackTrace = new ByteArrayOutputStream();
        exception.printStackTrace(new PrintWriter(stackTrace, true));

        for (i = 0; i < parameters.size(); i++) {
            parametersView.append(parameters.get(i)).append(System.lineSeparator());
        }

        String dnl = System.lineSeparator() + System.lineSeparator();
        Logs.printLog(LogTypes.ERROR, message + dnl + stackTrace.toString() + dnl + info.toString() + dnl + parametersView.toString());

        try {
            MService mailer = new MService();
            mailer.send("error@error.com", "TODO", "Frontend Error", message + dnl + stackTrace.toString() + dnl + info.toString() + dnl + parametersView.toString());
        } catch (BadDeliveryException | BadRecipientException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void logFrontend(String sMesg, HashMap info, List parameters) throws Exception {

        StringBuilder parametersView = new StringBuilder();
        int i;

        String message = sMesg;

        for (i = 0; i < parameters.size(); i++) {
            parametersView.append(parameters.get(i)).append(System.lineSeparator());
        }

        String dnl = System.lineSeparator() + System.lineSeparator();
        Logs.printLog(LogTypes.ERROR, message + dnl + info.toString() + dnl + parametersView.toString());

        try {
            MService mailer = new MService();
            mailer.send("error@error.com", "TODO", "Frontend Error", message + dnl + info.toString() + dnl + parametersView.toString());
        } catch (BadDeliveryException | BadRecipientException ex) {
            System.out.println(ex.getMessage());
        }

    }

  // End of Class
}
