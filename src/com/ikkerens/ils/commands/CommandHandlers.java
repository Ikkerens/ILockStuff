package com.ikkerens.ils.commands;

import com.ikkerens.ils.ILSPlugin;

public enum CommandHandlers {
    HELP( new Help() ),
    LOCK( new LockCmd() ),
    ADDMEMBER( new AddMember() ),
    REMOVEMEMBER( new RemoveMember() ),
    UNLOCK( new Unlock() );

    private Command instance;

    private CommandHandlers( final Command instance ) {
        this.instance = instance;
    }

    public static void setPlugin( final ILSPlugin plugin ) {
        for ( final CommandHandlers handler : values() )
            handler.instance.setPlugin( plugin );
    }

    public Command getHandler() {
        return this.instance;
    }
}
