package com.ikkerens.ils.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.mbserver.api.game.Location;
import com.mbserver.api.game.Player;

public class Database {
    private final ArrayList< Lock >             locks;
    private transient HashMap< Location, Lock > locatedLocks;

    public Database() {
        this.locks = new ArrayList< Lock >();
        this.locatedLocks = new HashMap< Location, Lock >();
    }

    public void init() {
        for ( final Lock lock : this.locks )
            this.locatedLocks.put( lock.getLocation(), lock );
    }

    public void addLock( final Lock lock ) {
        this.locks.add( lock );
        this.locatedLocks.put( lock.getLocation(), lock );
    }

    public boolean canAccess( final Player player, final Location location ) {
        final String name = player.getLoginName();
        final Lock lock = this.locatedLocks.get( location );

        if ( lock == null )
            return true;
        else
            return lock.canAccess( name );
    }
}
