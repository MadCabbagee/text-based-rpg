package me.madcabbage.tbrpg.client;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputManager {

    public String getLoginPayload() throws IOException {
        JSONObject payload = new JSONObject();
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

        payload.put("username", getInput("Username: ", cin));
        payload.put("password", getInput("Password: ", cin));

        return payload.toJSONString();
    }

    public String getRegistrationPayload() throws IOException {
        JSONObject payload = new JSONObject();
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

        payload.put("first-name", getInput("First name: ", cin));
        payload.put("last-name", getInput("Last name: ", cin));
        payload.put("username", getInput("Username: ", cin));
        payload.put("email", getEmailAddress(cin));
        payload.put("password", getNewPassword(cin));

        return payload.toJSONString();
    }

    private String getNewPassword(BufferedReader cin) throws IOException {
        String password = "";
        while (password.isEmpty()) {
            System.out.print("password: ");
            System.out.flush();
            String p1 = cin.readLine();

            System.out.print("password again: ");
            System.out.flush();
            String p2 = cin.readLine();

            password = (p1.equals(p2) ? p1 : password);
            if (password.isEmpty()) {
                System.out.println("Passwords dont match.");
            }
        }
        return password;
    }

    private String getInput(String prompt, BufferedReader cin) throws IOException {
        System.out.print(prompt);
        System.out.flush();
        return cin.readLine();
    }

    private String getEmailAddress(BufferedReader cin) throws IOException {
        String emailAddress = "";
        while (emailAddress.isEmpty()) {
            System.out.print("Enter a valid email: ");
            System.out.flush();
            String e1 = cin.readLine();

            System.out.print("Enter email again: ");
            System.out.flush();
            String e2 = cin.readLine();

            emailAddress = (e1.equals(e2)) ? e1 : emailAddress;
            if (emailAddress.isEmpty()) {
                System.out.println("Emails dont match.");
            }
        }
        return emailAddress;
    }
}
