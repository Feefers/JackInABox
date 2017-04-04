package com.nekokittygames.jackinabox.common;

import com.nekokittygames.jackinabox.common.blocks.JackBlocks;
import com.nekokittygames.jackinabox.common.libs.References;
import com.sun.org.apache.regexp.internal.RE;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * Created by Fiona with kind help from Nekosune on 04/04/2017.
 */
@Mod(modid = References.MOD_ID, version = References.MOD_VERSION, name = References.MOD_NAME)
public class JackInABox {

    @SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.COMMON_PROXY)
    public static CommonProxy proxy;

    public static Logger log;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        JackBlocks.preInit();
        log = event.getModLog();
    }


    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        JackBlocks.Init();
    }

}
