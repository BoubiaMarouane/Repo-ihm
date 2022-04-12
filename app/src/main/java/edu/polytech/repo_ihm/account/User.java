package edu.polytech.repo_ihm.account;

import java.util.Date;

public class User {
    String firstname;
    String name;
    String email;
    String address;
    String additional_address;
    String city;
    Integer postal;
    Date dob;

    String session_token = "1234";

    public User(String session_token) {
        if(this.session_token.equals(session_token)) {
            firstname = "John";
            name = "Smith";
            email = "john.smith@unice.fr";
        }
    }

    public static void main(String[] args) {
    }
}
