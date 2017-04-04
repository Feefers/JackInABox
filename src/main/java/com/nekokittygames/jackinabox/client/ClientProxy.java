package com.nekokittygames.jackinabox.client;

import com.nekokittygames.jackinabox.common.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by fiona on 04/04/2017.
 */
public class ClientProxy extends CommonProxy {


    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void RegisterRender(Block block) {
        super.RegisterRender(block);
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));

    }
}
