package icbmrl.core.common;

import java.util.ArrayList;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import boilerplate.common.baseclasses.items.BaseItemBlockWithMetadata;
import icbmrl.core.common.blocks.BlockCamouflage;
import icbmrl.core.common.blocks.BlockConcrete;
import icbmrl.core.common.blocks.BlockGlassButton;
import icbmrl.core.common.blocks.BlockGlassPressurePlate;
import icbmrl.core.common.blocks.BlockProximityDetector;
import icbmrl.core.common.blocks.BlockReinforcedGlass;
import icbmrl.core.common.blocks.BlockReinforcedRail;
import icbmrl.core.common.blocks.BlockSpikes;
import icbmrl.core.common.blocks.BlockSulfurOre;
import icbmrl.core.common.entity.EntityFlyingBlock;
import icbmrl.core.common.entity.EntityFragments;
import icbmrl.core.common.init.InitConfig;
import icbmrl.core.common.init.InitModMetadata;
import icbmrl.core.common.items.BaseItem;
import icbmrl.core.common.items.ItemAntidote;
import icbmrl.core.common.lib.ModInfo;

/**
 * Main class for ICBM core to run on. The core will need to be initialized by
 * each ICBM module.
 * 
 * @author Calclavia
 */
@Mod(modid = ModInfo.NAME, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies = "after:ResonantInduction|Atomic;required-after:ResonantEngine")
public final class ICBMCore
{
	@Instance(ModInfo.NAME)
	public static ICBMCore INSTANCE;

	@Metadata(ModInfo.NAME)
	public static ModMetadata metadata;

	@SidedProxy(clientSide = "icbm.core.ClientProxy", serverSide = "icbm.core.CommonProxy")
	public static CommonProxy proxy;

	// Blockss
	public static Block blockGlassPlate, blockGlassButton, blockProximityDetector, blockSpikes, blockCamo, blockConcrete, blockReinforcedGlass;

	// Itemss
	public static Item itemAntidote;
	public static Item itemSignalDisrupter;
	public static Item itemTracker;
	public static Item itemHackingComputer;

	public static Block blockSulfurOre, blockRadioactive, blockCombatRail, blockBox;

	public static Item itemSulfurDust, itemSaltpeterDust, itemPoisonPowder;

	public static final Logger LOGGER = Logger.getLogger(ModInfo.NAME);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		InitConfig.initConfig(event);
		// NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
		MinecraftForge.EVENT_BUS.register(INSTANCE);

		// Blockss
		blockSulfurOre = new BlockSulfurOre().setBlockName("blockSulfurOre");
		GameRegistry.registerBlock(blockSulfurOre, "BlockSulfurOre");
		blockGlassPlate = new BlockGlassPressurePlate().setBlockName("blockGlassPlate");
		GameRegistry.registerBlock(blockGlassPlate, "BlockGlassPlate");
		blockGlassButton = new BlockGlassButton().setBlockName("blockGlassButton");
		GameRegistry.registerBlock(blockGlassButton, "BlockGlassButton");
		blockProximityDetector = new BlockProximityDetector().setBlockName("blockProximityDetector");
		GameRegistry.registerBlock(blockProximityDetector, BaseItemBlockWithMetadata.class, "BlockProximityDetector");
		blockSpikes = new BlockSpikes().setBlockName("blockSpikes");
		GameRegistry.registerBlock(blockSpikes, BaseItemBlockWithMetadata.class, "BlockSpikes");
		blockCamo = new BlockCamouflage().setBlockName("blockCamo");
		GameRegistry.registerBlock(blockCamo, BaseItemBlockWithMetadata.class, "BlockCamo");
		blockConcrete = new BlockConcrete().setBlockName("blockConcrete");
		GameRegistry.registerBlock(blockConcrete, BaseItemBlockWithMetadata.class, "BlockConcrete");
		blockReinforcedGlass = new BlockReinforcedGlass().setBlockName("blockReinforcedGlass");
		GameRegistry.registerBlock(blockReinforcedGlass, "BlockReinforcedGlass");
		blockCombatRail = new BlockReinforcedRail().setBlockName("blockCombatRail");
		GameRegistry.registerBlock(blockCombatRail, "BlockCombatRail");

		// ITEMS
		itemPoisonPowder = new BaseItem().setUnlocalizedName("itemPoisonPowder");
		GameRegistry.registerItem(itemPoisonPowder, "ItemPoisonPowder");
		itemSulfurDust = new BaseItem().setUnlocalizedName("itemSulfurDust");
		GameRegistry.registerItem(itemSulfurDust, "ItemSulfurDust");
		itemSaltpeterDust = new BaseItem().setUnlocalizedName("itemSaltpeterDust");
		GameRegistry.registerItem(itemSaltpeterDust, "ItemSaltpeterDust");
		itemAntidote = new ItemAntidote().setUnlocalizedName("itemAntidote");
		GameRegistry.registerItem(itemAntidote, "ItemAntidote");
		// itemSignalDisrupter = new
		// ItemSignalDisrupter().setUnlocalizedName("itemSignalDisrupter");
		// GameRegistry.registerItem(itemSignalDisrupter,
		// "ItemSignalDisrupter");
		// itemTracker = new ItemTracker().setUnlocalizedName("itemTracker");
		// GameRegistry.registerItem(itemTracker, "ItemTracker");
		// itemHackingComputer = new
		// ItemComputer().setUnlocalizedName("itemHackingComputer");
		// GameRegistry.registerItem(itemHackingComputer,
		// "ItemHackingComputer");

		// sulfurGenerator = new OreGeneratorICBM("Sulfur Ore", "oreSulfur", new
		// ItemStack(blockSulfurOre), 0, 40, 20,
		// 4).enable(Settings.CONFIGURATION);

		/**
		 * Check for existence of radioactive block. If it does not exist, then
		 * create it.
		 */
		ArrayList<ItemStack> radList = OreDictionary.getOres("blockRadioactive");
		if (radList.size() > 0)
		{
			blockRadioactive = Block.getBlockFromItem(radList.get(0).getItem());
			LOGGER.fine("Detected radioative block from another mod, utilizing it.");
		}
		else
			blockRadioactive = Blocks.mycelium;

		/** Decrease Obsidian Resistance */
		// Blocks.obsidian.setResistance(Settings.CONFIGURATION.get(Configuration.CATEGORY_GENERAL,
		// "Reduce Obsidian Resistance", 45).getInt(45));
		LOGGER.fine("Changed obsidian explosive resistance to: " + Blocks.obsidian.getExplosionResistance(null));

		OreDictionary.registerOre("dustSulfur", new ItemStack(itemSulfurDust, 1, 0));
		OreDictionary.registerOre("dustSaltpeter", new ItemStack(itemSulfurDust, 1, 1));
		// OreGenerator.addOre(sulfurGenerator);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		InitModMetadata.setModMetadata(ModInfo.NAME, ModInfo.NAME, metadata);

		EntityRegistry.registerModEntity(EntityFlyingBlock.class, "ICBMGravityBlocks", 0, this, 50, 15, true);
		EntityRegistry.registerModEntity(EntityFragments.class, "ICBMFragment", 1, this, 40, 8, true);

		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		/** LOAD. */
		ArrayList dustCharcoal = OreDictionary.getOres("dustCharcoal");
		ArrayList dustCoal = OreDictionary.getOres("dustCoal");
		// Sulfur
		GameRegistry.addSmelting(blockSulfurOre, new ItemStack(itemSulfurDust, 4), 0.8f);
		GameRegistry.addSmelting(Items.reeds, new ItemStack(itemSulfurDust, 4, 1), 0f);
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.gunpowder, 2), new Object[] { "dustSulfur", "dustSaltpeter", Items.coal }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.gunpowder, 2),
				new Object[] { "dustSulfur", "dustSaltpeter", new ItemStack(Items.coal, 1, 1) }));

		if (dustCharcoal != null && dustCharcoal.size() > 0)
			GameRegistry.addRecipe(
					new ShapelessOreRecipe(new ItemStack(Items.gunpowder, 2), new Object[] { "dustSulfur", "dustSaltpeter", "dustCharcoal" }));
		if (dustCoal != null && dustCoal.size() > 0)
			GameRegistry
					.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.gunpowder, 2), new Object[] { "dustSulfur", "dustSaltpeter", "dustCoal" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(Blocks.tnt, new Object[] { "@@@", "@R@", "@@@", '@', Items.gunpowder, 'R', Items.redstone }));

		// Poison Powder
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(itemPoisonPowder, 3), new Object[] { Items.spider_eye, Items.rotten_flesh }));
		/** Add all Recipes */
		// Spikes
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(blockSpikes, 6), new Object[] { "CCC", "BBB", 'C', Blocks.cactus, 'B', Items.iron_ingot }));
		GameRegistry.addRecipe(new ItemStack(blockSpikes, 1, 1), new Object[] { "E", "S", 'E', itemPoisonPowder, 'S', blockSpikes });
		GameRegistry
				.addRecipe(new ShapedOreRecipe(new ItemStack(blockSpikes, 1, 2), new Object[] { "E", "S", 'E', itemSulfurDust, 'S', blockSpikes }));

		// Camouflage
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(blockCamo, 12), new Object[] { "WGW", "G G", "WGW", 'G', Blocks.vine, 'W', Blocks.wool }));

		// Tracker
		// GameRegistry.addRecipe(new ShapedOreRecipe(new
		// ItemStack(itemTracker), new Object[] { " Z ", "SBS", "SCS", 'Z',
		// Items.compass, 'C',
		// UniversalRecipe.CIRCUIT_T1.get(), 'B', UniversalRecipe.BATTERY.get(),
		// 'S', Items.ingotIron }));
		// GameRegistry.addRecipe(new ShapedOreRecipe(new
		// ItemStack(itemTracker), new Object[] { " Z ", "SBS", "SCS", 'Z',
		// Items.compass, 'C',
		// UniversalRecipe.CIRCUIT_T1.get(), 'B', Items.enderPearl, 'S',
		// Items.ingotIron }));

		// Glass Pressure Plate
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockGlassPlate, 1, 0), new Object[] { "##", '#', Blocks.glass }));

		// Glass Button
		GameRegistry.addRecipe(new ItemStack(blockGlassButton, 2), new Object[] { "G", "G", 'G', Blocks.glass });

		// Proximity Detector
		GameRegistry.addRecipe(
				new ShapedOreRecipe(blockProximityDetector, new Object[] { "SSS", "S?S", "SSS", 'S', Items.iron_ingot, '?', itemTracker }));

		// Signal Disrupter
		// GameRegistry.addRecipe(new ShapedOreRecipe(itemSignalDisrupter,
		// new Object[] { "WWW", "SCS", "SSS", 'S', Items.iron_ingot, 'C',
		// UniversalRecipe.CIRCUIT_T1.get(), 'W', UniversalRecipe.WIRE.get()
		// }));

		// Antidote
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemAntidote, 6), new Object[] { "@@@", "@@@", "@@@", '@', Items.pumpkin_seeds }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemAntidote), new Object[] { "@@@", "@@@", "@@@", '@', Items.wheat_seeds }));

		// Concrete
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockConcrete, 8, 0),
				new Object[] { "SGS", "GWG", "SGS", 'G', Blocks.gravel, 'S', Blocks.sand, 'W', Items.water_bucket }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockConcrete, 8, 1),
				new Object[] { "COC", "OCO", "COC", 'C', new ItemStack(blockConcrete, 1, 0), 'O', Blocks.obsidian }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockConcrete, 8, 2),
				new Object[] { "COC", "OCO", "COC", 'C', new ItemStack(blockConcrete, 1, 1), 'O', Items.iron_ingot }));

		// Reinforced rails
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockCombatRail, 16, 0),
				new Object[] { "C C", "CIC", "C C", 'I', new ItemStack(blockConcrete, 1, 0), 'C', Items.iron_ingot }));

		// Reinforced Glass
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockReinforcedGlass, 8),
				new Object[] { "IGI", "GIG", "IGI", 'G', Blocks.glass, 'I', Items.iron_ingot }));
	}

}