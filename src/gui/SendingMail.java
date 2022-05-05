/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import javax.mail.MessagingException;


/**
 *
 * @author Mayssa
 */
public class SendingMail {
     public static String mailUsername;
    public static String mailPassword;
    public static String contenu;
    public static String destination;
    public static String subject;

    public SendingMail(String contenu, String destination, String subject) {
        mailUsername = "mohamedamine.mehrez@esprit.tn";
        mailPassword = "omarmotdepass";
        this.contenu = contenu;
        this.destination = destination;
        this.subject = subject;
    }

    public static void sendMail() {
        System.out.println("Preparing to send email");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");//587
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername,mailPassword);
            }
        });

        try {
            System.out.println("test1");
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(mailUsername));
            System.out.println("2");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));
            System.out.println("3");
            message.setSubject(subject);
            System.out.println("4");
            message.setText(contenu);
            System.out.println("5");
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
           e.printStackTrace();
        }

    }
    
}
