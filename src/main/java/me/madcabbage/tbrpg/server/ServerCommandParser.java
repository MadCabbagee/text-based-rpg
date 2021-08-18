package me.madcabbage.tbrpg.server;

import me.madcabbage.tbrpg.common.commands.AbstractCommandParser;
import me.madcabbage.tbrpg.common.commands.ConsoleCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerCommandParser extends AbstractCommandParser {

    private final TextBasedRpgServer server;

    public ServerCommandParser(final TextBasedRpgServer server) {
        super();
        this.server = server;
    }

    public void listen() throws IOException, InterruptedException {
        listening = true;
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type help to get a list of commands.");
        while (listening) {
            System.out.print("server::");
            System.out.flush();
            String command = cin.readLine();

            parseCommand(command);
        }
    }

    @Override
    protected void registerExtraCommands() {
        ConsoleCommand exit = new ConsoleCommand("exit", "close") {
            @Override
            public void execute() throws IOException, InterruptedException {
                server.stop();
                DatabaseController.close();
                listening = false;
            }
        };
        exit.setDescription("Close the websocket server, database connection and command listener.");
        exit.setUsage("exit");
        consoleCommands.add(exit);
    }
}
