package com.nekokittygames.jackinabox.common;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by fiona on 14/05/2017.
 */
public class JackInABoxConfig {
    public float simpleChance = 0.25f;
    public float extendedChance = 0.6f;
    public float extremeChance = 0.95f;
    protected Configuration config;

    public JackInABoxConfig(File configurationFile) {
        config = new Configuration(configurationFile);
        simpleChance = config.getFloat("Simple Chance", Configuration.CATEGORY_GENERAL, 0.25f, 0f, 1f, "Chance to drop items with a simple Box");
        extendedChance = config.getFloat("Extended Chance", Configuration.CATEGORY_GENERAL, 0.6f, 0f, 1f, "Chance to drop items with a extreme Box");
        extremeChance = config.getFloat("Ultimate Chance", Configuration.CATEGORY_GENERAL, 0.95f, 0f, 1f, "Chance to drop items with a ultimate Box");
        config.save();
    }

}
