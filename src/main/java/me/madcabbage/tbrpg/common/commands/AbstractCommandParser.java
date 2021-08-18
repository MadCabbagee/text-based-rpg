package me.madcabbage.tbrpg.common.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractCommandParser implements CommandListener {

    protected boolean listening = false;
    protected final List<ConsoleCommand> consoleCommands;

    public AbstractCommandParser() {
        consoleCommands = new ArrayList<>();
        registerCommands();
    }

    protected void parseCommand(String command) throws IOException, InterruptedException {
        // if it has a space i assume it has args
        String[] args = null;
        if (command.contains(" ")) {
            args = command.split(" ");
        }

        String name = null;
        if (args == null) {
            name = command;
        } else {
            name = args[0];
        }

        for (ConsoleCommand cmd : consoleCommands) {
            if (cmd.getAliases().contains(name)) {
                cmd.execute();
                System.out.println();
                return;
            }
        }

        System.out.println("Invalid command entered.\n");
    }

    protected final void registerCommands() {
        ConsoleCommand help = new ConsoleCommand("help", "?") {
            @Override
            public void execute() {
                for (ConsoleCommand cmd : consoleCommands) {
                    cmd.printUsage();
                }
            }
        };
        help.setDescription("Print all commands.");
        help.setUsage("help");
        consoleCommands.add(help);

        registerExtraCommands();
    }

    protected abstract void registerExtraCommands();

}
