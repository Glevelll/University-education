package org.example;

import com.sun.mail.smtp.SMTPTransport;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
    public static void main(String[] args) throws IOException {
        final String username = "Мой адрес";
        final String password = "Мой пароль";
        String toEmail = "Кому отправляем";
        String subject = "Заголовок";
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy");
        Calendar calendar = new GregorianCalendar(2023, Calendar.MARCH, 22);
        String message = "Сегодня " + dateFormat.format(calendar.getTime()) + "\nСредняя температура сегодня: 4°C\n" +
                "Вероятность осадков: 3%\n" +
                "Влажность: 87%\n" +
                "Ветер: 6 м/с\n" +
                "Облачно";
        String fileJPG = "C:\\Users\\Глеб\\IdeaProjects\\emailProg\\src\\main\\java\\org\\example\\data\\cat.jpg";
        String fileZIP = "C:\\Users\\Глеб\\IdeaProjects\\emailProg\\src\\main\\java\\org\\example\\data\\emailProg.zip";
        sendEmail(username, password, toEmail, subject, message.toString(), fileJPG, fileZIP);
    }

    public static void sendEmail(String username, String password, String toEmail, String subject, String message, String fileJPG, String fileZIP) throws IOException {
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, null);
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject(subject);

            MimeBodyPart msgTXT = new MimeBodyPart();
            msgTXT.setText(message);

            MimeBodyPart msgJPG = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(fileJPG);
            msgJPG.setDataHandler(new DataHandler(fds));
            msgJPG.setFileName(fds.getName());

            MimeBodyPart msgZIP = new MimeBodyPart();
            FileDataSource zip = new FileDataSource(fileZIP);
            msgZIP.setDataHandler(new DataHandler(zip));
            msgZIP.setFileName(zip.getName());

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(msgTXT);
            mp.addBodyPart(msgJPG);
            mp.addBodyPart(msgZIP);

            msg.setContent(mp);


            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            t.connect("smtp.gmail.com", username, password);

            t.sendMessage(msg, msg.getAllRecipients());


            System.out.println("Email sent successfully to " + toEmail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}