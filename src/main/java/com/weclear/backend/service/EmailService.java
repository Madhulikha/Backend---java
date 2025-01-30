package com.weclear.backend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendQuoteRequestEmail(String ownerEmail, String serviceName, String customerName, String customerEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(ownerEmail);
        message.setSubject("New Quote Request: " + serviceName);
        message.setText("You have received a new quote request for the service: " + serviceName + "\n\n" +
                "Customer Name: " + customerName + "\n" +
                "Customer Email: " + customerEmail + "\n\n" +
                "Please respond to the customer to discuss the quote.");
        javaMailSender.send(message);
    }
}
