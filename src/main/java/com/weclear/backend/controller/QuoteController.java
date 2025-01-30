package com.weclear.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weclear.backend.model.QuoteRequest;
import com.weclear.backend.service.EmailService;

@RestController
public class QuoteController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/request-quote")
    public String requestQuote(@RequestBody QuoteRequest quoteRequest) {
        // Assuming you have a QuoteRequest class that contains the necessary data
        String serviceName = quoteRequest.getServiceName();
        String customerName = quoteRequest.getCustomerName();
        String customerEmail = quoteRequest.getCustomerEmail();
        
        // Owner's email (this could be dynamic if you have multiple owners)
        String ownerEmail = "owner-email@example.com"; 
        
        // Send email to the owner
        emailService.sendQuoteRequestEmail(ownerEmail, serviceName, customerName, customerEmail);
        
        return "Quote request submitted successfully!";
    }
}
