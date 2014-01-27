package com.ikkerens.ils.commands;

import com.ikkerens.ils.ILSPlugin;
import com.ikkerens.ils.model.Lock;

import com.mbserver.api.events.BlockInteractEvent;
import com.mbserver.api.game.Player;

public class LockCmd extends Command implements InteractionHandler {

    @Override
    public String getHelp() {
        return "Locks an interactable block to you.";
    }

    @Override
    public void executeCommand( final Player player, final String[] args ) {
        player.setMetaData( ILSPlugin.AWAITING_INTERACT_KEY, this );
        player.sendMessage( "Please interact with the block you wish to lock." );
    }

    @Override
    public void onInteract( final BlockInteractEvent event ) {
        final Lock newLock = new Lock( event.getPlayer().getLoginName(), event.getLocation() );
        this.plugin.getDatabase().addLock( newLock );
        event.getPlayer().sendMessage( "The block has been locked." );
    }
}
