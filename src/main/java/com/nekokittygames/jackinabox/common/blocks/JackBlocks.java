package com.nekokittygames.jackinabox.common.blocks;

import com.nekokittygames.jackinabox.common.JackInABox;
import com.nekokittygames.jackinabox.common.tiles.TileSimpleJack;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by fiona on 04/04/2017.
 */
public class JackBlocks {

    public static SimpleJackBlock simple_jack;
    public static ItemBlock ib_simple_jack;


    public static void preInit() {
        simple_jack = new SimpleJackBlock();
        ib_simple_jack = new ItemBlock(simple_jack);
        GameRegistry.register(simple_jack);
        GameRegistry.registerTileEntity(TileSimpleJack.class, "simple_jack");
        GameRegistry.register(ib_simple_jack, simple_jack.getRegistryName());
    }

    public static void Init() {
        JackInABox.proxy.RegisterRender(simple_jack);
    }
}
