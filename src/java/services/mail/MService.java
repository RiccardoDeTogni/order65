/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.mail;

import com.sun.mail.util.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import global.*;
import services.log.*;
import services.mail.exceptions.*;

/**
 *
 * @author nick
 */
public final class MService {

    private Properties oProps;
    private Session oSession;
    private Transport oTransport;

    public String sMailGateWay;

    private final String mailServer = Constants.MAIL_SERVER;

    public MService() throws BadDeliveryException, Exception {
        if (Constants.ENABLE_MAIL) {
            if (!InitMailer(this.mailServer)) {
                throw new BadDeliveryException("MService: MService(): Mail server not working!");
            }
        }else
            throw new Exception("MService: MService(): Mail service disabled");
    }

    private boolean InitMailer(String mailServer) {
        try {
            this.oProps = new Properties();
            this.oProps.put("mail.smtp.auth", "true");
            this.oProps.put("mail.smtp.host", mailServer);
            this.oProps.put("mail.smtp.port", Constants.SMTP_PORT);
            this.oProps.put("mail.smtp.starttls.enable", "true");

            MailSSLSocketFactory sf;

            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            this.oProps.put("mail.smtp.ssl.socketFactory", sf);

            this.oSession = Session.getInstance(this.oProps,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(Constants.USER_NAME, Constants.PASSWORD);
                        }
                    });

            this.oTransport = oSession.getTransport(this.oSession.getProvider("smtp"));
            this.oTransport.connect();
            if (!this.oTransport.isConnected()) {
                Logs.printLog(LogTypes.WARNING, "MService: InitMailer (): Mail Server " + mailServer + "not working.");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Logs.printLog(LogTypes.WARNING, "MService: InitMailer (): Mail Server " + mailServer + "not working.");
            return false;
        }
        return true;
    }

    public void send(String mailFrom, String rcptTo, String subject, String body) throws BadRecipientException, BadDeliveryException {
        try {
            this.sendMessage(mailFrom, rcptTo, subject, body);
        } catch (Exception e) {
            if (this.oTransport.isConnected())
                throw new BadRecipientException("MService: Send(): bad recipient, mail not sent. " + e.getMessage(), this.mailServer);
            else {
                Logs.printLog(LogTypes.WARNING, "MService: send(): Mail Server " + this.mailServer + "not working.");
                throw new BadDeliveryException("MService: send(): Mail Server unreachable. " + e.getMessage(), this.mailServer);
            }
        }
    }

    private void sendMessage(String mailFrom, String rcptTo, String subject, String body) throws Exception {
        if (!Constants.ENABLE_MAIL) {
            return;
        }
        MimeMessage msg = new MimeMessage(this.oSession);
        msg.setFrom(new InternetAddress(mailFrom));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(rcptTo));
        msg.setSubject(subject, "UTF-8");
        msg.setSentDate(new Date());
        msg.setText(body, "UTF-8");
        Transport.send(msg);
    }
    
}
