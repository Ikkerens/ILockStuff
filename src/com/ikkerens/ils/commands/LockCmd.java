package com.ikkerens.ils.commands;

import com.ikkerens.ils.Config;
import com.ikkerens.ils.ILSPlugin;
import com.ikkerens.ils.model.Lock;
import com.ikkerens.ils.model.cost.ItemCost;

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
        final Config config = this.plugin.getConfig();
        final Player player = event.getPlayer();
        final ItemCost[] cost = config.getItemCost();

        if ( !player.hasPermission( "ikkerens.ilockstuff.admin" ) ) {
            for ( final ItemCost item : cost )
                if ( !item.chargeTo( event.getPlayer() ) ) {
                    player.sendMessage( "You do not have the required items to make a lock." );
                    return;
                }

            if ( this.plugin.getMoneyPlugin() != null )
                if ( !this.plugin.getMoneyPlugin().deduct( player, config.getMoneyCost() ) ) {
                    player.sendMessage( "You do not have enough money to make a lock!" );
                    return;
                }
        }

        final Lock newLock = new Lock( player.getLoginName(), event.getLocation() );
        this.plugin.getDatabase().addLock( newLock );
        player.sendMessage( "The block has been locked." );
    }
}
