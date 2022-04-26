package edu.polytech.repo_ihm.account;

import java.time.LocalDate;
import java.util.Date;

public class User {
    String firstname;
    String name;
    String email;
    String address;
    String additional_address;
    String city;
    Integer postal;
    String dob;
    String session_token;

    public User(String firstname, String name, String email, String address, String additional_address, String city, Integer postal, String dob) {
        this.firstname = firstname;
        this.name = name;
        this.email = email;
        this.address = address;
        this.additional_address = additional_address;
        this.city = city;
        this.postal = postal;
        this.dob = dob;
    }

    public void setSessionToken(String session_token) {
        this.session_token = session_token;
    }

    public String getSessionToken() {
        return session_token;
    }
}
