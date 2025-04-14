package com.mycompany.mychatapp;

import java.util.regex.*;

public class Login {
    
    public boolean checkUserName(String username) {
        return username.matches("^[a-zA-Z0-9_]{1,5}$") && username.contains("_");
    }

    
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }

    
    public boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+27\\d{9}$");
    }

    
    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        } else if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        } else if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        } else {
            return "User registered successfully.";
        }
    }

    
    public boolean loginUser(String username, String password) {
        // In practice, this would check a database or stored data
        // For now, assume correct credentials
        return username.equals("kyl_1") && password.equals("Ch&&sec@ke99!");
    }

   
    public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome kyl_1, it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}

