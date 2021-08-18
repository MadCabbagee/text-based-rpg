package me.madcabbage.tbrpg.client;

import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ClientDriver {

    // { "type":"usercommand", "payload":"command" }
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        // ws://madcabbage.ovh:8000
        // ws://localhost:8000
        final JSONParser parser = new JSONParser();
        final InputManager inputManager = new InputManager();
        final ResponseHandler resHandler = new ResponseHandler(parser, inputManager);

        TextBasedRpgClient client = new TextBasedRpgClient(new URI("ws://localhost:8000"), parser, resHandler);
        client.connect();

        Thread.sleep(1500);

        ClientCommandParser cmdHandler = new ClientCommandParser(client);
        cmdHandler.listen();
    }

}
