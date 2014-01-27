package com.ikkerens.ils.model;

import java.util.HashSet;

import com.mbserver.api.game.Location;

public class Lock {
    private String                  owner;
    private final HashSet< String > members;
    private Location                location;

    public Lock() {
        this.members = new HashSet< String >();
    }

    public Lock( final String owner, final Location location ) {
        this();
        this.owner = owner;
        this.location = location;
    }

    public Location getLocation() {
        return this.location;
    }

    public boolean canAccess( final String player ) {
        if ( this.owner.equalsIgnoreCase( player ) )
            return true;
        else
            return this.members.contains( player );
    }
}
