package com.nekokittygames.jackinabox.common.blocks;

import com.nekokittygames.jackinabox.common.JackInABox;
import com.nekokittygames.jackinabox.common.JackInABoxConfig;
import com.nekokittygames.jackinabox.common.libs.LibBlocks;
import com.nekokittygames.jackinabox.common.libs.References;
import com.nekokittygames.jackinabox.common.tiles.BaseTileSimpleJack;
import com.nekokittygames.jackinabox.common.tiles.TileSimpleDigger;
import com.nekokittygames.jackinabox.common.tiles.TileSimpleJack;
import com.nekokittygames.jackinabox.common.tiles.TileSimpleMine;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by fiona on 04/04/2017.
 */
public class JackBlocks {

    public static SimpleJackBlockBase simple_jack;
    public static ItemBlock ib_simple_jack;

    public static SimpleJackBlockBase extended_jack;
    public static ItemBlock ib_extended_jack;

    public static SimpleJackBlockBase extreme_jack;
    public static ItemBlock ib_extreme_jack;

    public static SimpleJackBlockBase simple_digger;
    public static ItemBlock ib_simple_digger;

    public static SimpleJackBlockBase extended_digger;
    public static ItemBlock ib_extended_digger;

    public static SimpleJackBlockBase extreme_digger;
    public static ItemBlock ib_extreme_digger;

    public static SimpleJackBlockBase simple_mine;
    public static ItemBlock ib_simple_mine;

    public static SimpleJackBlockBase extended_mine;
    public static ItemBlock ib_extended_mine;

    public static SimpleJackBlockBase extreme_mine;
    public static ItemBlock ib_extreme_mine;



    public static void preInit() {
        simple_jack = new SimpleJackBlock(References.SIMPLE_RANGE_X, References.SIMPLE_RANGE_Y, References.SIMPLE_RANGE_Z, JackInABox.config.simpleChance, LibBlocks.SIMPLE_JACK);
        ib_simple_jack = new ItemBlock(simple_jack);
        GameRegistry.register(simple_jack);
        GameRegistry.registerTileEntity(TileSimpleJack.class, "simple_jack");
        GameRegistry.register(ib_simple_jack, simple_jack.getRegistryName());

        extended_jack = new SimpleJackBlock(References.EXTENDED_RANGE_X, References.EXTENDED_RANGE_Y, References.EXTENDED_RANGE_Z, JackInABox.config.extendedChance, LibBlocks.EXTENDED_JACK);
        ib_extended_jack = new ItemBlock(extended_jack);
        GameRegistry.register(extended_jack);
        GameRegistry.register(ib_extended_jack, extended_jack.getRegistryName());

        extreme_jack = new SimpleJackBlock(References.EXTREME_RANGE_X, References.EXTREME_RANGE_Y, References.EXTREME_RANGE_Z, JackInABox.config.extremeChance, LibBlocks.EXTREME_JACK);
        ib_extreme_jack = new ItemBlock(extreme_jack);
        GameRegistry.register(extreme_jack);
        GameRegistry.register(ib_extreme_jack, extreme_jack.getRegistryName());

        simple_digger = new SimpleDiggerBlock(References.SIMPLE_RANGE_X, References.SIMPLE_RANGE_Y, References.SIMPLE_RANGE_Z, JackInABox.config.simpleChance, LibBlocks.SIMPLE_digger);
        ib_simple_digger = new ItemBlock(simple_digger);
        GameRegistry.register(simple_digger);
        GameRegistry.registerTileEntity(TileSimpleDigger.class, "simple_digger");
        GameRegistry.register(ib_simple_digger, simple_digger.getRegistryName());

        extended_digger = new SimpleDiggerBlock(References.EXTENDED_RANGE_X, References.EXTENDED_RANGE_Y, References.EXTENDED_RANGE_Z, JackInABox.config.extendedChance, LibBlocks.EXTENDED_digger);
        ib_extended_digger = new ItemBlock(extended_digger);
        GameRegistry.register(extended_digger);
        GameRegistry.register(ib_extended_digger, extended_digger.getRegistryName());

        extreme_digger = new SimpleDiggerBlock(References.EXTREME_RANGE_X, References.EXTREME_RANGE_Y, References.EXTREME_RANGE_Z, JackInABox.config.extremeChance, LibBlocks.EXTREME_digger);
        ib_extreme_digger = new ItemBlock(extreme_digger);
        GameRegistry.register(extreme_digger);
        GameRegistry.register(ib_extreme_digger, extreme_digger.getRegistryName());

        simple_mine = new SimpleMineBlock(References.SIMPLE_RANGE_X, References.SIMPLE_RANGE_Y, References.SIMPLE_RANGE_Z, JackInABox.config.simpleChance, LibBlocks.SIMPLE_mine);
        ib_simple_mine = new ItemBlock(simple_mine);
        GameRegistry.register(simple_mine);
        GameRegistry.registerTileEntity(TileSimpleMine.class, "simple_mine");
        GameRegistry.register(ib_simple_mine, simple_mine.getRegistryName());

        extended_mine = new SimpleMineBlock(References.EXTENDED_RANGE_X, References.EXTENDED_RANGE_Y, References.EXTENDED_RANGE_Z, JackInABox.config.extendedChance, LibBlocks.EXTENDED_mine);
        ib_extended_mine = new ItemBlock(extended_mine);
        GameRegistry.register(extended_mine);
        GameRegistry.register(ib_extended_mine, extended_mine.getRegistryName());

        extreme_mine = new SimpleMineBlock(References.EXTREME_RANGE_X, References.EXTREME_RANGE_Y, References.EXTREME_RANGE_Z, JackInABox.config.extremeChance, LibBlocks.EXTREME_mine);
        ib_extreme_mine = new ItemBlock(extreme_mine);
        GameRegistry.register(extreme_mine);
        GameRegistry.register(ib_extreme_mine, extreme_mine.getRegistryName());
    }

    public static void Init() {
        JackInABox.proxy.RegisterRender(simple_jack);
        JackInABox.proxy.RegisterRender(extended_jack);
        JackInABox.proxy.RegisterRender(extreme_jack);
        JackInABox.proxy.RegisterRender(simple_digger);
        JackInABox.proxy.RegisterRender(extended_digger);
        JackInABox.proxy.RegisterRender(extreme_digger);
        JackInABox.proxy.RegisterRender(simple_mine);
        JackInABox.proxy.RegisterRender(extended_mine);
        JackInABox.proxy.RegisterRender(extreme_mine);
        crafting();
    }

    private static void crafting() {
        GameRegistry.addShapedRecipe(new ItemStack(simple_jack), new Object[]{" T ", " A ", " T ", 'T', Items.GUNPOWDER, 'A', Items.STONE_AXE});
        GameRegistry.addShapedRecipe(new ItemStack(extended_jack), new Object[]{"T T", " A ", "T T", 'T', Blocks.TNT, 'A', Items.IRON_AXE});
        GameRegistry.addShapedRecipe(new ItemStack(extreme_jack), new Object[]{"TTT", "TAT", "TTT", 'T', Blocks.TNT, 'A', Items.DIAMOND_AXE});

        GameRegistry.addShapedRecipe(new ItemStack(simple_digger), new Object[]{" T ", " A ", " T ", 'T', Items.GUNPOWDER, 'A', Items.STONE_SHOVEL});
        GameRegistry.addShapedRecipe(new ItemStack(extended_digger), new Object[]{"T T", " A ", "T T", 'T', Blocks.TNT, 'A', Items.IRON_SHOVEL});
        GameRegistry.addShapedRecipe(new ItemStack(extreme_digger), new Object[]{"TTT", "TAT", "TTT", 'T', Blocks.TNT, 'A', Items.DIAMOND_SHOVEL});

        GameRegistry.addShapedRecipe(new ItemStack(simple_mine), new Object[]{" T ", " A ", " T ", 'T', Items.GUNPOWDER, 'A', Items.STONE_PICKAXE});
        GameRegistry.addShapedRecipe(new ItemStack(extended_mine), new Object[]{"T T", " A ", "T T", 'T', Blocks.TNT, 'A', Items.IRON_PICKAXE});
        GameRegistry.addShapedRecipe(new ItemStack(extreme_mine), new Object[]{"TTT", "TAT", "TTT", 'T', Blocks.TNT, 'A', Items.DIAMOND_PICKAXE});
    }
}
