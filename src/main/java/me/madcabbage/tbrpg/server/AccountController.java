package me.madcabbage.tbrpg.server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AccountController {

    private DatabaseController db;
    private JSONParser parser;

    public AccountController(DatabaseController db, JSONParser parser) {
        this.db = db;
        this.parser = parser;
    }

    public String authenticate(String payload) throws ParseException {
        JSONObject request = (JSONObject) parser.parse(payload);
        String username = (String) request.get("username");
        String password = (String) request.get("password");

        String password = db.getPassword(username);

        // create a response
        // the password comes to us already hashed. so just compare it to the hashed password to the one in the database
        // if they are the same then generate a JWT and attach it to the response

        String response  = "";

        return response;
    }

    public String createNewUser(String payload) {
        String response  = "";

        return response;
    }
}
