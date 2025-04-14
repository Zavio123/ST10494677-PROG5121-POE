/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.mychatapp;

/**
 *
 * @author DOMINO
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {

    Login login = new Login();

    // Check if the username is correctly formatted
    @Test
    public void testCheckUserName() {
        assertTrue(login.checkUserName("kyl_1"));
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

  //Check password complexity
    @Test
    public void testCheckPasswordComplexity() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // Test: Check if the phone number is correctly formatted
    @Test
    public void testCheckCellPhoneNumber() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

   
    @Test
    public void testRegisterUser() {
        assertEquals("User registered successfully.", login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976"));
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.", login.registerUser("kyle", "Ch&&sec@ke99!", "+27838968976"));
    }

   
    @Test
    public void testLoginUser() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }
}
