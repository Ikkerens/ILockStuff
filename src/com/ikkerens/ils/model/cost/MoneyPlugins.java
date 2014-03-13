package com.ikkerens.ils.model.cost;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.PluginManager;
import com.mbserver.api.Server;

public enum MoneyPlugins {
    MBES( "MBEssentials", MBEssentials.class );

    private final String                         manifestName;
    private final Class< ? extends MoneyPlugin > handler;

    private MoneyPlugins( final String manifestName, final Class< ? extends MoneyPlugin > handler ) {
        this.manifestName = manifestName;
        this.handler = handler;
    }

    public static MoneyPlugin getMoneyPlugin( final Server server ) {
        final PluginManager pluginMgr = server.getPluginManager();
        final ArrayList< MoneyPlugin > foundPlugins = new ArrayList< MoneyPlugin >();

        for ( final MoneyPlugins attempts : values() ) {
            final MBServerPlugin plugin = pluginMgr.getPlugin( attempts.manifestName );
            if ( plugin != null )
                try {
                    final Constructor< ? extends MoneyPlugin > constructor = attempts.handler.getConstructor( MBServerPlugin.class );
                    final MoneyPlugin wrappedPlugin = constructor.newInstance( plugin );
                    foundPlugins.add( wrappedPlugin );
                } catch ( final SecurityException e ) {
                } catch ( final NoSuchMethodException e ) {
                } catch ( final IllegalArgumentException e ) {
                } catch ( final InstantiationException e ) {
                } catch ( final IllegalAccessException e ) {
                } catch ( final InvocationTargetException e ) {
                }
        }

        if ( foundPlugins.size() == 0 )
            return null;
        else if ( foundPlugins.size() > 0 )
            server.getLogger().info( "[ILS] Found several money plugins, using " + foundPlugins.get( 0 ).getName() );

        return foundPlugins.get( 0 );
    }
}
