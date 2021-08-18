package me.madcabbage.tbrpg.server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RequestHandler {

    private final JSONParser parser;
    private final AccountController accManager;

    public RequestHandler(final JSONParser parser, final AccountController accManager) {
        this.parser = parser;
        this.accManager = accManager;
    }

    // { "type":"login", "payload":"{ "username":"testuser", "password":"testpassword" }" }
    public String handleRequest(String requestMsg) throws ParseException {
        JSONObject request = (JSONObject) parser.parse(requestMsg);
        String type = (String) request.get("type");

        String response = "";
        switch(type) {
            case "login":
                response = accManager.authenticate((String) request.get("payload"));
                break;
            case "register":
                response = accManager.createNewUser((String) request.get("payload"));
                break;
        }

        return response;
    }

}
