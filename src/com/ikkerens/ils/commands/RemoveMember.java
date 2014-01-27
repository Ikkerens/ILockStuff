package com.ikkerens.ils.commands;

import com.ikkerens.ils.ILSPlugin;
import com.ikkerens.ils.model.Lock;

import com.mbserver.api.events.BlockInteractEvent;
import com.mbserver.api.game.Player;

public class RemoveMember extends Command implements InteractionHandler {
    private static final String DELMEM_KEY = "ILockStuff.MemberManage";

    @Override
    public String getHelp() {
        return "Removes a member from your lock.";
    }

    @Override
    public void executeCommand( final Player player, final String[] args ) {
        if ( args.length == 0 )
            player.sendMessage( "Please enter a name: /ils removemember <playername>" );
        else {
            player.setMetaData( ILSPlugin.AWAITING_INTERACT_KEY, this );
            player.setMetaData( DELMEM_KEY, args[ 0 ] );
            player.sendMessage( "Please interact with the block you wish to remove a member from." );
        }
    }

    @Override
    public void onInteract( final BlockInteractEvent event ) {
        final Lock lock = this.plugin.getDatabase().getLock( event.getLocation() );

        if ( !lock.isOwner( event.getPlayer().getLoginName() ) ) {
            event.getPlayer().sendMessage( "You do not own this lock!" );
            return;
        }

        final String memName = event.getPlayer().getMetaData( DELMEM_KEY, null );

        if ( memName != null ) {
            lock.addMember( memName );
            event.getPlayer().sendMessage( String.format( "Player %s has been removed as a member from this lock." ) );
        } else
            event.getPlayer().sendMessage( "Oops! Something went wrong!" );
    }

}
