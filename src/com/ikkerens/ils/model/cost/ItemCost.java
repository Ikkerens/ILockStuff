package com.ikkerens.ils.model.cost;

import com.mbserver.api.Constructors;
import com.mbserver.api.game.ItemStack;
import com.mbserver.api.game.Player;

public class ItemCost {
    private final short id;
    private final int   amount;

    public ItemCost() {
        this.id = 5;
        this.amount = 1;
    }

    public boolean chargeTo( final Player player ) {
        final ItemStack[] inventory = player.getInventory();
        for ( int i = 0; i < inventory.length; i++ )
            if ( inventory[ i ].getItemID() == this.id )
                if ( inventory[ i ].getAmount() >= this.amount ) {
                    final ItemStack newItem = Constructors.newItemStack( this.id, inventory[ i ].getAmount() - this.amount );
                    player.setItemSlot( i, newItem, true );
                    return true;
                }

        return false;
    }
}
