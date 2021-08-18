package me.madcabbage.tbrpg.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.parser.JSONParser;

import java.net.URI;

public class TextBasedRpgClient extends WebSocketClient {

    private final ResponseHandler resHandler;
    private final JSONParser parser;

    public TextBasedRpgClient(URI serverUri, JSONParser parser, ResponseHandler responseHandler) {
        super(serverUri);
        this.parser = parser;
        this.resHandler = responseHandler;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("Connection established with server.");
        System.out.println("\tHandshake: " + serverHandshake.toString());
        System.out.println("\tHttp Status: " + serverHandshake.getHttpStatusMessage());
        System.out.println();
    }

    @Override
    public void onMessage(String response) {
        System.out.println("Response received:");
        System.out.println("\tContent: " + response);
        System.out.println();

        resHandler.handle(response);
    }

    @Override
    public void onClose(int i, String reason, boolean b) {
        System.out.println("Remote Connection was closed.");
        System.out.println("\tReason: " + reason);
        System.out.println();
    }

    @Override
    public void onError(Exception e) {
        System.out.println("Error: ");
        System.out.println("\t" + e.getMessage());
        System.out.println();
    }
}
