package com.ucb.demo.dto;

import java.sql.Date;

import com.ethlo.time.DateTime;

public class ResponseLogin {
    private String token;
    private DateTime date;
    public ResponseLogin(String token, DateTime date) {
        this.token = token;
        this.date = date;
    }

    
}
