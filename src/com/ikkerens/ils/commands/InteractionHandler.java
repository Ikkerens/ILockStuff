package com.ikkerens.ils.commands;

import com.mbserver.api.events.BlockInteractEvent;

public interface InteractionHandler {
    void onInteract( BlockInteractEvent event );
}
