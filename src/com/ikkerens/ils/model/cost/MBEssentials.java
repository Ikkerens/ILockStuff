package com.ikkerens.ils.model.cost;

import plugins.mbes.MBEPlugin;
import plugins.mbes.managers.MoneyManager;
import plugins.mbes.misc.MoneyAccount;

import com.mbserver.api.MBServerPlugin;
import com.mbserver.api.game.Player;

public class MBEssentials extends MoneyPlugin {
    private final MoneyManager mgr;

    public MBEssentials( final MBServerPlugin plugin ) {
        super( plugin );
        this.mgr = ( (MBEPlugin) plugin ).getMoneyManager();
    }

    @Override
    public boolean deduct( final Player player, final int amount ) {
        final MoneyAccount acc = new MoneyAccount( player );

        if ( this.mgr.getMoney( acc ) >= amount )
            return false;

        this.mgr.removeMoney( acc, amount );
        return true;
    }

}
