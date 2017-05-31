package com.nekokittygames.jackinabox.common;

import com.nekokittygames.jackinabox.common.blocks.JackBlocks;
import com.nekokittygames.jackinabox.common.libs.References;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

/**
 * Created by fiona on 14/05/2017.
 */
@Config(modid = References.MOD_ID)
@Config.LangKey("jackinabox.config.title")
public class JackInABoxConfig {

    @Config.LangKey("jackinabox.config.simpleChance")
    @Config.RangeInt(min = 0, max = 100)
    @Config.Comment("Chance to drop items with a simple Box")
    public static float simpleChance = 0.25f;

    @Config.LangKey("jackinabox.config.extendedChance")
    @Config.RangeInt(min = 0, max = 100)
    @Config.Comment("Chance to drop items with a Extended Box")
    public static float extendedChance = 0.6f;

    @Config.LangKey("jackinabox.config.extremeChance")
    @Config.RangeInt(min = 0, max = 100)
    @Config.Comment("Chance to drop items with a Extreme Box")
    public static float extremeChance = 0.95f;

    @Mod.EventBusSubscriber
    private static class EventHandler {

        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(References.MOD_ID)) {
                ConfigManager.sync(References.MOD_ID, Config.Type.INSTANCE);
                JackBlocks.simple_jack.setChance(JackInABoxConfig.simpleChance);
                JackBlocks.extended_jack.setChance(JackInABoxConfig.extendedChance);
                JackBlocks.extreme_jack.setChance(JackInABoxConfig.extremeChance);

                JackBlocks.simple_digger.setChance(JackInABoxConfig.simpleChance);
                JackBlocks.extended_digger.setChance(JackInABoxConfig.extendedChance);
                JackBlocks.extreme_digger.setChance(JackInABoxConfig.extremeChance);

                JackBlocks.simple_mine.setChance(JackInABoxConfig.simpleChance);
                JackBlocks.extended_mine.setChance(JackInABoxConfig.extendedChance);
                JackBlocks.extreme_mine.setChance(JackInABoxConfig.extremeChance);
            }
        }
    }


}
