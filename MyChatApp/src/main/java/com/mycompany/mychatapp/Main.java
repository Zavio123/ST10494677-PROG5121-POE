
package com.mycompany.mychatapp;

    import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        // Registration
        System.out.println("Create an account: ");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Cell phone number: ");
        String phoneNumber = scanner.nextLine();

        String registrationResult = login.registerUser(username, password, phoneNumber);
        System.out.println(registrationResult);

        // Login
        if (registrationResult.equals("User registered successfully.")) {
            System.out.println("\nNow, login to your account: ");
            System.out.print("Username: ");
            String loginUsername = scanner.nextLine();
            System.out.print("Password: ");
            String loginPassword = scanner.nextLine();

            boolean loginSuccess = login.loginUser(loginUsername, loginPassword);
            System.out.println(login.returnLoginStatus(loginSuccess));
        }

        scanner.close();
    }
}

