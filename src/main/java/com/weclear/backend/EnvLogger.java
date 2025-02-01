package com.weclear.backend;


import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class EnvLogger {

    @PostConstruct
    public void logEnvVars() {
        System.out.println("MYSQLHOST: " + System.getenv("MYSQLHOST"));
        System.out.println("MYSQLPORT: " + System.getenv("MYSQLPORT"));
        System.out.println("MYSQLDATABASE: " + System.getenv("MYSQLDATABASE"));
        System.out.println("MYSQLUSER: " + System.getenv("MYSQLUSER"));
        System.out.println("MYSQLPASSWORD: " + System.getenv("MYSQLPASSWORD"));
        String jdbcUrl = "jdbc:mysql://" + System.getenv("MYSQLHOST") + ":" +
        System.getenv("MYSQLPORT") + "/" + System.getenv("MYSQLDATABASE");
System.out.println("Resolved JDBC URL: " + jdbcUrl);
    }
}