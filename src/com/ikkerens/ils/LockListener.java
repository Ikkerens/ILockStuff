package com.ikkerens.ils;

import com.ikkerens.ils.commands.InteractionHandler;
import com.ikkerens.ils.model.Database;
import com.mbserver.api.events.BlockInteractEvent;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.game.Player;

public class LockListener implements Listener {
    private final Database db;

    public LockListener( final Database db ) {
        this.db = db;
    }

    @EventHandler
    public void onInteract( final BlockInteractEvent event ) {
        final Player player = event.getPlayer();

        if ( player.getMetaData( ILSPlugin.AWAITING_INTERACT_KEY, null ) != null ) {
            ( (InteractionHandler) player.getMetaData( ILSPlugin.AWAITING_INTERACT_KEY, null ) ).onInteract( event );
            player.removeMetaData( ILSPlugin.AWAITING_INTERACT_KEY );
            event.setCancelled( true );
        } else // See if it's locked
        if ( !this.db.canAccess( player, event.getLocation() ) && !player.hasPermission( "ikkerens.ilockstuff.admin" ) ) {
            player.sendMessage( "You do not have permission to use this block." );
            event.setCancelled( true );
        }
    }
}
