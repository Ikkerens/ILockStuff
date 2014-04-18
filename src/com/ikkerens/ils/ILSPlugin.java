package com.ikkerens.ils;

import com.ikkerens.ils.commands.CommandHandlers;
import com.ikkerens.ils.commands.MainCommand;
import com.ikkerens.ils.model.Database;
import com.ikkerens.ils.model.cost.MoneyPlugin;
import com.ikkerens.ils.model.cost.MoneyPlugins;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.Manifest;
import com.mbserver.api.PluginManager;
import com.mbserver.api.events.EventHandler;
import com.mbserver.api.events.Listener;
import com.mbserver.api.events.WorldSaveEvent;

@Manifest( name = "ILockStuff", config = Config.class )
public class ILSPlugin extends MBServerPlugin implements Listener {
    public static final String AWAITING_INTERACT_KEY = "ILockStuff.WaitingForInteract";
    private Database           database;
    private MoneyPlugin        moneyPlugin;

    @Override
    public void onEnable() {
        final Config config = this.getConfig();
        this.saveConfig(); // Create the config

        if ( config.getMoneyCost() > 0 ) {
            this.moneyPlugin = MoneyPlugins.getMoneyPlugin( this.getServer() );

            if ( this.moneyPlugin == null )
                this.getLogger().warning( "[POOP] No useable money plugin found, not charging players money for locks!" );
        }

        // Load the database
        this.database = this.getServer().getConfigurationManager().load( this, Database.class );
        this.database.init();
        this.getServer().getConfigurationManager().save( this, this.database );

        final PluginManager pm = this.getPluginManager();
        // Register event handlers
        pm.registerEventHandler( this );
        pm.registerEventHandler( new LockListener( this.database ) );

        // Register the commands
        CommandHandlers.setPlugin( this );
        final MainCommand mc = new MainCommand();
        pm.registerCommand( "ilockstuff", new String[] { "ils" }, mc );
        pm.registerCommand( "lock", mc ); // Seperated because of its slightly different functionality.
    }

    @Override
    public void onDisable() {
        this.onSave( null );
    }

    @EventHandler
    public void onSave( final WorldSaveEvent event ) {
        if ( ( event == null ) || ( event.getWorld() == this.getServer().getMainWorld() ) ) {
            this.saveConfig();
            this.getServer().getConfigurationManager().save( this, this.database );
        }
    }

    public Database getDatabase() {
        return this.database;
    }

    public MoneyPlugin getMoneyPlugin() {
        return this.moneyPlugin;
    }
}
