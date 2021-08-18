package me.madcabbage.tbrpg.client;

import me.madcabbage.tbrpg.common.commands.AbstractCommandParser;
import me.madcabbage.tbrpg.common.commands.ConsoleCommand;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientCommandParser extends AbstractCommandParser {

    private boolean listening;
    private final TextBasedRpgClient client;

    public ClientCommandParser(TextBasedRpgClient client) {
        listening = false;
        this.client = client;
    }

    public void listen() throws IOException, InterruptedException {
        listening = true;
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type help to get a list of commands.");
        while(listening) {
            System.out.print("Console: ");
            System.out.flush();
            String command = cin.readLine();

            parseCommand(command);
        }
    }

    protected void registerExtraCommands() {
        ConsoleCommand exit = new ConsoleCommand("exit", "close") {
            @Override
            public void execute() throws IOException, InterruptedException {
                client.close();
                listening = false;
            }
        };
        exit.setDescription("Close the websocket server, database connection and command listener.");
        exit.setUsage("exit");
        consoleCommands.add(exit);

        ConsoleCommand login = new ConsoleCommand() {
            @Override
            public void execute() throws IOException, InterruptedException {
                InputManager im = new InputManager();
                String payload = im.getLoginPayload();

                JSONObject loginRequest = new JSONObject();
                loginRequest.put("type", "login");
                loginRequest.put("payload", payload);
                //System.out.println(request.toJSONString());

                client.send(loginRequest.toJSONString());
            }
        };
        login.setAlias("login", "sign in", "auth");
        login.setDescription("Used to authenticate yourself on the remote server.");
        login.setUsage("'login -u <user_name> -p <password>' - WIP for now use 'login'.");
        consoleCommands.add(login);

        ConsoleCommand register = new ConsoleCommand() {
            @Override
            public void execute() throws IOException, InterruptedException {
                InputManager im = new InputManager();
                String payload = im.getRegistrationPayload();

                JSONObject registrationRequest = new JSONObject();
                registrationRequest.put("type", "register");
                registrationRequest.put("payload", payload);

                client.send(registrationRequest.toJSONString());
            }
        };
        register.setAlias("register", "create account", "reg", "create");
        register.setDescription("Used to register a new account so you can login.");
        register.setUsage("'register'");
        consoleCommands.add(register);
    }


}
