package me.madcabbage.tbrpg.common.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface CommandListener {

    void listen() throws IOException, InterruptedException;
    //void registerCommands();
}
