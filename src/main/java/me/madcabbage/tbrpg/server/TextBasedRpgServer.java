package me.madcabbage.tbrpg.server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.net.InetSocketAddress;

public class TextBasedRpgServer extends WebSocketServer {

    private final RequestHandler reqHandler;

    public TextBasedRpgServer(int port, RequestHandler reqHandler) {
        super(new InetSocketAddress("0.0.0.0", port));
        this.reqHandler = reqHandler;
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake clientHandshake) {
        System.out.println("New connection established from: " + conn.getRemoteSocketAddress());
        System.out.println();
    }

    @Override
    public void onClose(WebSocket conn, int i, String reason, boolean b) {
        System.out.println("Existing connection to: " + conn.getRemoteSocketAddress() + " was closed.");
        System.out.println("\tReason: " + reason);
        System.out.println();
    }

    @Override
    public void onMessage(WebSocket conn, String request) {
        System.out.println("Message received from: " + conn.getRemoteSocketAddress());
        System.out.println("\tContent: " + request);

        String response = null;
        try {
            response = reqHandler.handleRequest(request);
        } catch (ParseException e) {
            System.out.println("\tInvalid request received from: " + conn.getRemoteSocketAddress());
            response = "{\"error\":\"Invalid Request\", \"msg\":\"Invalid json request was received.\"}";
            e.printStackTrace();
        }
        System.out.println("\tResponse: " + response);
        System.out.println();
        conn.send(response);
    }

    @Override
    public void onError(WebSocket conn, Exception msg) {
        System.out.println("Error from: " + conn.getRemoteSocketAddress());
        System.out.println("\t" + msg);
        System.out.println();
    }

    @Override
    public void onStart() {
        System.out.println("Server started.");
    }
}
