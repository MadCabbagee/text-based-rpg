package me.madcabbage.tbrpg.common.commands;

import java.io.IOException;

public interface Executable {
    public void execute() throws IOException, InterruptedException;
}
