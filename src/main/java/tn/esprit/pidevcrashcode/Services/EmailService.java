package tn.esprit.pidevcrashcode.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendConfirmationEmail(String recipientEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Campsite Reservation Confirmation");
        message.setText("Thank you for booking your campsite reservation !");
        emailSender.send(message);
    }

    public void sendReminderEmail(String recipientEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Reservation Reminder");
        message.setText("Your reservation is coming up soon.Don't forget to pack your camping gear !");
        emailSender.send(message);
    }
}
