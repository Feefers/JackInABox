package com.nekokittygames.jackinabox.common.compat;

import com.nekokittygames.jackinabox.common.tiles.BaseTileSimpleJack;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.WailaPlugin;

/**
 * Created by fiona on 31/05/2017.
 */
@WailaPlugin
public class JackInABoxWaila implements IWailaPlugin {
    @Override
    public void register(IWailaRegistrar iWailaRegistrar) {
        iWailaRegistrar.registerBodyProvider(new JackInABoxHUDBlock(), BaseTileSimpleJack.class);
    }
}
