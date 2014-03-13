package com.ikkerens.ils.commands;

import com.ikkerens.ils.ILSPlugin;
import com.mbserver.api.game.Player;

public abstract class Command {
    protected ILSPlugin plugin;

    void setPlugin( final ILSPlugin plugin ) {
        this.plugin = plugin;
    }

    public abstract String getHelp();

    public abstract void executeCommand( Player player, String[] args );
}
