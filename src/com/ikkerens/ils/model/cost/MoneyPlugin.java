package com.ikkerens.ils.model.cost;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import com.mbserver.api.game.Player;

public abstract class MoneyPlugin {
    private final String name;

    public MoneyPlugin( final MBServerPlugin plugin ) {
        this.name = plugin.getClass().getAnnotation( Manifest.class ).name();
        plugin.getLogger().info( String.format( "[ILS] Found money-plugin %s. Using this for the money_cost value.",
            this.name ) );
    }

    public final String getName() {
        return this.name;
    }

    public abstract boolean deduct( Player player, int amount );
}
