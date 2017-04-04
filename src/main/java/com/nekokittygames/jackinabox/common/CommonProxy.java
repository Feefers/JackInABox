package com.nekokittygames.jackinabox.common;

import com.nekokittygames.jackinabox.common.blocks.JackBlocks;
import net.minecraft.block.Block;

/**
 * Created by fiona on 04/04/2017.
 */
public class CommonProxy {

    public void preInit() {
        JackBlocks.preInit();
    }


    public void RegisterRender(Block block) {

    }
}
