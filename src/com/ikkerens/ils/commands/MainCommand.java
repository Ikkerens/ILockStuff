package com.ikkerens.ils.commands;

import com.mbserver.api.CommandExecutor;
import com.mbserver.api.CommandSender;
import com.mbserver.api.game.Player;

public class MainCommand implements CommandExecutor {

    @Override
    public void execute( final String command, final CommandSender sender, final String[] args, final String label ) {
        if ( !( sender instanceof Player ) ) {
            sender.sendMessage( "This command cannot be used by the console." );
            return;
        }

        final Player player = (Player) sender;

        if ( !player.hasPermission( "ikkerens.ilockstuff.use" ) ) {
            player.sendMessage( "You do not have permission to use /" + label );
            return;
        }

        final Command handler;
        final String[] specArgs;
        if ( args.length == 0 ) {
            if ( command.equalsIgnoreCase( "lock" ) )
                handler = CommandHandlers.LOCK.getHandler();
            else
                handler = CommandHandlers.HELP.getHandler();
            specArgs = new String[ 0 ];
        } else
            try {
                final CommandHandlers handlers = CommandHandlers.valueOf( args[ 0 ].toUpperCase() );
                handler = handlers.getHandler();
                specArgs = new String[ args.length - 1 ];
                System.arraycopy( args, 1, specArgs, 0, specArgs.length );
            } catch ( final IllegalArgumentException e ) {
                player.sendMessage( "That is not a valid command, please refer to /ils help" );
                return;
            }

        handler.executeCommand( player, specArgs );
    }
}
