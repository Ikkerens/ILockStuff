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

    public boolean isOwner( final String player ) {
        return this.owner.equalsIgnoreCase( player );
    }

    public void addMember( final String memberName ) {
        this.members.add( memberName.toLowerCase() );
    }

    public boolean canAccess( final String player ) {
        if ( this.isOwner( player ) )
            return true;
        else
            return this.members.contains( player.toLowerCase() );
    }

    @Override
    public boolean equals( final Object other ) {
        if ( ( other == null ) || !( other instanceof Lock ) )
            return false;

        return this.location.equals( ( (Lock) other ).getLocation() );
    }
}
