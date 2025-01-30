package com.weclear.backend.model;

import jakarta.persistence.*;

@Entity
public class QuoteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // You can also use GenerationType.AUTO or others as needed
    private Long id;

    private String ServiceName;

    private String customerEmail;

    private String customerName;

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    
}
