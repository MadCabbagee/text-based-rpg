package me.madcabbage.tbrpg.client;

import org.json.simple.parser.JSONParser;

public class ResponseHandler {

    private final InputManager inputMan;
    private final JSONParser parser;

    public ResponseHandler(JSONParser parser, InputManager im) {
        this.parser = parser;
        this.inputMan = im;
    }

    public void handle(String response) {

    }
}
