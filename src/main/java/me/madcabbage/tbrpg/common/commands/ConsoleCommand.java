package me.madcabbage.tbrpg.common.commands;

import me.madcabbage.tbrpg.common.commands.Executable;

import java.util.Arrays;
import java.util.List;

public abstract class ConsoleCommand implements Executable {
    private List<String> aliases;
    private String description;
    private String usage;


    public ConsoleCommand(String... aliases) {
        this.aliases = Arrays.stream(aliases).toList();
    }

    public void setAlias(String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }

    public void setUsage(final String usage) {
        this.usage = usage;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void printUsage() {
        // name | aliases
        // description
        // usage
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aliases.size(); i++) {
            if (i == 0) {
                sb.append(aliases.get(i));
                sb.append(" | ");
            }
            if (i == aliases.size() - 1) {
                sb.append(aliases.get(i));
            } else {
                sb.append(aliases.get(i));
                sb.append(", ");
            }
        }
        sb.append("\n\t" + "Description: " + description +
                "\n\t" + "Usage: " + usage);
        System.out.println(sb.toString());
    }

    public List<String> getAliases() {
        return this.aliases;
    }
}
