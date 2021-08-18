package me.madcabbage.tbrpg.server;

import org.json.simple.parser.JSONParser;

import java.io.IOException;

public class ServerDriver {

    public static void main(String[] args) throws IOException, InterruptedException {
        // load settings from config.properties
        final SettingsFile config = new SettingsFile();
        config.read();

        final JSONParser parser = new JSONParser();
        final DatabaseController db = new DatabaseController(config);
        final AccountController accManager = new AccountController(db, parser);
        final RequestHandler reqHandler = new RequestHandler(parser, accManager);


        // start game server
        final TextBasedRpgServer server = new TextBasedRpgServer(config.getPort(), reqHandler);
        server.start();

        // give server time to fully connect
        Thread.sleep(1500);

        // start command loop
        final ServerCommandParser cmdHandler = new ServerCommandParser(server);
        cmdHandler.listen();
    }
}
