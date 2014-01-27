package com.ikkerens.ils.commands;

import com.mbserver.api.game.Player;

public class Help extends Command {

    @Override
    public String getHelp() {
        return "Returns this list.";
    }

    @Override
    public void executeCommand( final Player player, final String[] args ) {
        for ( final CommandHandlers cmd : CommandHandlers.values() )
            player.sendMessage( String.format( "/%s - %s", cmd.name().toLowerCase(), cmd.getHandler().getHelp() ) );
    }

}
