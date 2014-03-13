package com.ikkerens.ils.commands;

import com.ikkerens.ils.ILSPlugin;
import com.ikkerens.ils.model.Lock;
import com.mbserver.api.events.BlockInteractEvent;
import com.mbserver.api.game.Player;

public class Unlock extends Command implements InteractionHandler {

    @Override
    public String getHelp() {
        return "Unlocks a block";
    }

    @Override
    public void executeCommand( final Player player, final String[] args ) {
        player.setMetaData( ILSPlugin.AWAITING_INTERACT_KEY, this );
        player.sendMessage( "Please interact with the block you wish to unlock." );
    }

    @Override
    public void onInteract( final BlockInteractEvent event ) {
        final Lock lock = this.plugin.getDatabase().getLock( event.getLocation() );

        if ( lock == null ) {
            event.getPlayer().sendMessage( "That block is not locked!" );
            return;
        }

        if ( !lock.isOwner( event.getPlayer().getLoginName() ) && !event.getPlayer().hasPermission( "ikkerens.ilockstuff.admin" ) ) {
            event.getPlayer().sendMessage( "You do not own this lock!" );
            return;
        }

        this.plugin.getDatabase().removeLock( lock );
        event.getPlayer().sendMessage( "The lock has been removed!" );
    }

}
