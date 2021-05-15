package ganymedes01.etfuturum;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import cpw.mods.fml.common.registry.GameRegistry;
import ganymedes01.etfuturum.blocks.*;
import ganymedes01.etfuturum.blocks.ores.BlockCopperOre;
import ganymedes01.etfuturum.blocks.ores.BlockDeepslateOre;
import ganymedes01.etfuturum.blocks.ores.BlockDeepslateRedstoneOre;
import ganymedes01.etfuturum.core.utils.Utils;
import ganymedes01.etfuturum.tileentities.TileEntityWoodSign;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;

public class ModBlocks {
	
	private static final List<Block> initList = new ArrayList<Block>();

	public static final Block stone = initBlock(new Stone());
	public static final Block prismarine = initBlock(new PrismarineBlocks());
	public static final Block sea_lantern = initBlock(new SeaLantern());
	public static final Block inverted_daylight_detector = initBlock(new InvertedDaylightDetector());
	public static final Block red_sandstone = initBlock(new RedSandstone());
	public static final Block brown_mushroom_block = initBlock(new BlockSilkedMushroom(Blocks.brown_mushroom_block, "brown"));
	public static final Block red_mushroom_block = initBlock(new BlockSilkedMushroom(Blocks.red_mushroom_block, "red"));
	public static final Block coarse_dirt = initBlock(new CoarseDirt());
	public static final Block banner = initBlock(new BlockBanner());
	public static final Block slime = initBlock(new SlimeBlock());
	public static final Block sponge = initBlock(new Sponge());
	public static final Block old_gravel = initBlock(new OldGravel());
	public static final Block beetroot = initBlock(new BlockBeetroot());
	public static final Block purpur_block = initBlock(new PurpurBlock());
	public static final Block purpur_pillar = initBlock(new PurpurPillar());
	public static final Block end_bricks = initBlock(new EndBricks());
	public static final Block grass_path = initBlock(new GrassPath());
	public static final Block end_rod = initBlock(new EndRod());
	public static final Block chorus_plant = initBlock(new ChorusPlant());
	public static final Block chorus_flower = initBlock(new ChorusFlower());
	public static final Block crying_obsidian = initBlock(new CryingObsidian());
	public static final Block rose = initBlock(new OldRose());
	
	public static final Block bone_block = initBlock(new BoneBlock());
	public static final Block new_nether_brick = initBlock(new NewNetherBrick());
	public static final Block nether_wart_block = initBlock(new NetherwartBlock());
	public static final Block ancient_debris = initBlock(new BlockAncientDebris());
	public static final Block netherite_block = initBlock(new BlockNetherite());
	public static final Block barrier = initBlock(new BlockBarrier());
	public static final Block nether_gold_ore = initBlock(new BlockOreNetherGold());
	public static final Block blue_ice = initBlock(new BlockBlueIce());
	public static final Block smooth_stone = initBlock(new BlockSmoothStone());
	public static final Block smooth_sandstone = initBlock(new BlockSmoothSandstone(0));
	public static final Block smooth_red_sandstone = initBlock(new BlockSmoothSandstone(1));
	public static final Block smooth_quartz = initBlock(new BlockSmoothQuartz());
	public static final Block quartz_bricks = initBlock(new BlockQuartzBricks());
	public static final Block log_stripped = initBlock(new BlockStrippedOldLog());
	public static final Block log2_stripped = initBlock(new BlockStrippedNewLog());
	public static final Block log_bark = initBlock(new BlockWoodBarkOld());
	public static final Block log2_bark = initBlock(new BlockWoodBarkNew());
	public static final Block wood_stripped = initBlock(new BlockStrippedOldWood());
	public static final Block wood2_stripped = initBlock(new BlockStrippedNewWood());
	public static final Block concrete = initBlock(new BlockConcrete());
	public static final Block concrete_powder = initBlock(new BlockConcretePowder());
	public static final Block copper_ore = initBlock(new BlockCopperOre());
	public static final Block deepslate_copper_ore = initBlock(new BlockDeepslateOre(ModBlocks.copper_ore));
	public static final Block cornflower = initBlock(new BlockCornflower());
	public static final Block lily_of_the_valley = initBlock(new BlockLilyOfTheValley());
	public static final Block wither_rose = initBlock(new BlockWitherRose());
	public static final Block sweet_berry_bush = initBlock(new BlockBerryBush());
	public static final Block white_glazed_terracotta = initBlock(new BlockGlazedTerracotta(0));
	public static final Block orange_glazed_terracotta = initBlock(new BlockGlazedTerracotta(1));
	public static final Block magenta_glazed_terracotta = initBlock(new BlockGlazedTerracotta(2));
	public static final Block light_blue_glazed_terracotta = initBlock(new BlockGlazedTerracotta(3));
	public static final Block yellow_glazed_terracotta = initBlock(new BlockGlazedTerracotta(4));
	public static final Block lime_glazed_terracotta = initBlock(new BlockGlazedTerracotta(5));
	public static final Block pink_glazed_terracotta = initBlock(new BlockGlazedTerracotta(6));
	public static final Block gray_glazed_terracotta = initBlock(new BlockGlazedTerracotta(7));
	public static final Block light_gray_glazed_terracotta = initBlock(new BlockGlazedTerracotta(8));
	public static final Block cyan_glazed_terracotta = initBlock(new BlockGlazedTerracotta(9));
	public static final Block purple_glazed_terracotta = initBlock(new BlockGlazedTerracotta(10));
	public static final Block blue_glazed_terracotta = initBlock(new BlockGlazedTerracotta(11));
	public static final Block brown_glazed_terracotta = initBlock(new BlockGlazedTerracotta(12));
	public static final Block green_glazed_terracotta = initBlock(new BlockGlazedTerracotta(13));
	public static final Block red_glazed_terracotta = initBlock(new BlockGlazedTerracotta(14));
	public static final Block black_glazed_terracotta = initBlock(new BlockGlazedTerracotta(15));
	public static final Block copper_block = initBlock(new BlockCopper());
	public static final Block lightning_rod = initBlock(new BlockLightningRod());
	public static final Block deepslate = initBlock(new BlockDeepslate());
	public static final Block cobbled_deepslate = initBlock(new BlockDeepslateCobbled());
	public static final Block polished_deepslate = initBlock(new BlockDeepslatePolished());
	public static final Block deepslate_bricks = initBlock(new BlockDeepslateBricks());
	public static final Block tuff = initBlock(new BlockTuff());
	
	//deepslate vanilla ores
	public static final Block deepslate_coal_ore = initBlock(new BlockDeepslateOre(Blocks.coal_ore));
	public static final Block deepslate_iron_ore = initBlock(new BlockDeepslateOre(Blocks.iron_ore));
	public static final Block deepslate_gold_ore = initBlock(new BlockDeepslateOre(Blocks.gold_ore));
	public static final Block deepslate_redstone_ore = initBlock(new BlockDeepslateRedstoneOre(false));
	public static final Block deepslate_lit_redstone_ore = initBlock(new BlockDeepslateRedstoneOre(true));
	public static final Block deepslate_lapis_ore = initBlock(new BlockDeepslateOre(Blocks.lapis_ore));
	public static final Block deepslate_diamond_ore = initBlock(new BlockDeepslateOre(Blocks.diamond_ore));
	public static final Block deepslate_emerald_ore = initBlock(new BlockDeepslateOre(Blocks.emerald_ore));
	
	//technical blocks
	public static final Block brewing_stand = initBlock(new NewBrewingStand());
	public static final Block beacon = initBlock(new NewBeacon());
	public static final Block enchantment_table = initBlock(new NewEnchantmentTable());
	public static final Block anvil = initBlock(new NewAnvil());
	public static final Block daylight_sensor = initBlock(new NewDaylightSensor());
	public static final Block frosted_ice = initBlock(new FrostedIce());
	public static final Block lava_cauldron = initBlock(new BlockLavaCauldron());
	
	//do slab/stairs
	public static final Block red_sandstone_stairs = initBlock(new BlockGenericStairs(red_sandstone, 0).setBlockName(Utils.getUnlocalisedName("red_sandstone_stairs")));
	public static final Block purpur_stairs = initBlock(new BlockGenericStairs(purpur_block, 0).setBlockName(Utils.getUnlocalisedName("purpur_stairs")));
	public static final Block red_sandstone_slab = initBlock(new RedSandstoneSlab(false));
	public static final Block double_red_sandstone_slab = initBlock(new RedSandstoneSlab(true));
	public static final Block purpur_slab = initBlock(new PurpurSlab(false));
	public static final Block double_purpur_slab = initBlock(new PurpurSlab(true));
	
	public static final Block generic_slab = initBlock(new BlockStoneSlab1(false));
	public static final Block double_generic_slab = initBlock(new BlockStoneSlab1(true));
	public static final Block stone_slab = initBlock(new BlockStoneSlab2(false));
	public static final Block double_stone_slab = initBlock(new BlockStoneSlab2(true));
	
	public static final Block rough_prismarine_stairs = initBlock(new BlockGenericStairs(prismarine, 0).setBlockName(Utils.getUnlocalisedName("prismarine_stairs")));
	public static final Block prismarine_brick_stairs = initBlock(new BlockGenericStairs(prismarine, 1).setBlockName(Utils.getUnlocalisedName("prismarine_stairs_brick")));
	public static final Block dark_prismarine_stairs = initBlock(new BlockGenericStairs(prismarine, 2).setBlockName(Utils.getUnlocalisedName("prismarine_stairs_dark")));
	public static final Block smooth_sandstone_stairs = initBlock(new BlockGenericStairs(smooth_sandstone, 0).setBlockName(Utils.getUnlocalisedName("smooth_sandstone_stairs")));
	public static final Block smooth_red_sandstone_stairs = initBlock(new BlockGenericStairs(smooth_red_sandstone, 0).setBlockName(Utils.getUnlocalisedName("smooth_red_sandstone_stairs")));
	public static final Block smooth_quartz_stairs = initBlock(new BlockGenericStairs(smooth_quartz, 0).setBlockName(Utils.getUnlocalisedName("smooth_quartz_stairs")));
	public static final Block red_nether_brick_stairs = initBlock(new BlockGenericStairs(new_nether_brick, 0).setBlockName(Utils.getUnlocalisedName("red_netherbrick_stairs")));
	public static final Block granite_stairs = initBlock(new BlockGenericStairs(stone, 1).setBlockName(Utils.getUnlocalisedName("granite_stairs")));
	public static final Block polished_granite_stairs = initBlock(new BlockGenericStairs(stone, 2).setBlockName(Utils.getUnlocalisedName("polished_granite_stairs")));
	public static final Block diorite_stairs = initBlock(new BlockGenericStairs(stone, 3).setBlockName(Utils.getUnlocalisedName("diorite_stairs")));
	public static final Block polished_diorite_stairs = initBlock(new BlockGenericStairs(stone, 4).setBlockName(Utils.getUnlocalisedName("polished_diorite_stairs")));
	public static final Block andesite_stairs = initBlock(new BlockGenericStairs(stone, 5).setBlockName(Utils.getUnlocalisedName("andesite_stairs")));
	public static final Block polished_andesite_stairs = initBlock(new BlockGenericStairs(stone, 6).setBlockName(Utils.getUnlocalisedName("polished_andesite_stairs")));
	public static final Block mossy_stone_brick_stairs = initBlock(new BlockGenericStairs(Blocks.stonebrick, 1).setBlockName(Utils.getUnlocalisedName("mossy_stone_brick_stairs")));
	public static final Block mossy_cobblestone_stairs = initBlock(new BlockGenericStairs(Blocks.mossy_cobblestone, 0).setBlockName(Utils.getUnlocalisedName("mossy_cobblestone_stairs")));
	public static final Block stone_stairs = initBlock(new BlockGenericStairs(Blocks.stone, 0).setBlockName(Utils.getUnlocalisedName("stone_stairs")));
	public static final Block end_brick_stairs = initBlock(new BlockGenericStairs(end_bricks, 0).setBlockName(Utils.getUnlocalisedName("end_brick_stairs")));
	
	public static final Block smooth_sandstone_slab = initBlock(new BlockSmoothSandstoneSlab(0, false));
	public static final Block double_smooth_sandstone_slab = initBlock(new BlockSmoothSandstoneSlab(0, true));
	public static final Block smooth_red_sandstone_slab = initBlock(new BlockSmoothSandstoneSlab(1, false));
	public static final Block double_smooth_red_sandstone_slab = initBlock(new BlockSmoothSandstoneSlab(1, true));
	public static final Block prismarine_slab = initBlock(new PrismarineSlab(false));
	public static final Block double_prismarine_slab = initBlock(new PrismarineSlab(true));
	public static final Block smooth_quartz_slab = initBlock(new BlockSmoothQuartzSlab(false));
	public static final Block double_smooth_quartz_slab = initBlock(new BlockSmoothQuartzSlab(true));
	public static final Block red_nether_brick_slab = initBlock(new BlockRedNetherBrickSlab(false));
	public static final Block double_red_nether_brick_slab = initBlock(new BlockRedNetherBrickSlab(true));
	public static final Block end_brick_slab = initBlock(new BlockEndBrickSlab(false));
	public static final Block double_end_brick_slab = initBlock(new BlockEndBrickSlab(true));
	public static final Block cut_copper_slab = initBlock(new BlockCutCopperSlab(false));
	public static final Block double_cut_copper_slab = initBlock(new BlockCutCopperSlab(true));
	public static final Block deepslate_slab = initBlock(new BlockDeepslateSlab(false, false));
	public static final Block double_deepslate_slab = initBlock(new BlockDeepslateSlab(true, false));
	public static final Block deepslate_brick_slab = initBlock(new BlockDeepslateSlab(false, true));
	public static final Block double_deepslate_brick_slab = initBlock(new BlockDeepslateSlab(true, true));
	public static final Block netherite_stairs = initBlock(new BlockNetheriteStairs());
	public static final Block cut_copper_stairs = initBlock(new BlockCutCopperStairs(4));
	public static final Block exposed_cut_copper_stairs = initBlock(new BlockCutCopperStairs(5));
	public static final Block weathered_cut_copper_stairs = initBlock(new BlockCutCopperStairs(6));
	public static final Block oxidized_cut_copper_stairs = initBlock(new BlockCutCopperStairs(7));
	public static final Block waxed_cut_copper_stairs = initBlock(new BlockCutCopperStairs(12));
	public static final Block waxed_exposed_cut_copper_stairs = initBlock(new BlockCutCopperStairs(13));
	public static final Block waxed_weathered_cut_copper_stairs = initBlock(new BlockCutCopperStairs(14));
	public static final Block cobbled_deepslate_stairs = initBlock(new BlockGenericStairs(ModBlocks.cobbled_deepslate, 0).setBlockName(Utils.getUnlocalisedName("cobbled_deepslate_stairs")));
	public static final Block polished_deepslate_stairs = initBlock(new BlockGenericStairs(ModBlocks.polished_deepslate, 0).setBlockName(Utils.getUnlocalisedName("polished_deepslate_stairs")));
	public static final Block deepslate_brick_stairs = initBlock(new BlockGenericStairs(ModBlocks.deepslate_bricks, 0).setBlockName(Utils.getUnlocalisedName("deepslate_brick_stairs")));
	public static final Block deepslate_tile_stairs = initBlock(new BlockGenericStairs(ModBlocks.deepslate_bricks, 2).setBlockName(Utils.getUnlocalisedName("deepslate_tile_stairs")));
	
	//Mechanic/Functional blocks
	public static final Block iron_trapdoor = initBlock(new IronTrapdoor());
	
	public static final Block magma_block = initBlock(new MagmaBlock());
	public static final Block barrel = initBlock(new BlockBarrel());
	public static final Block lantern = initBlock(new BlockLantern());
	public static final Block smoker = initBlock(new BlockSmoker(false));
	public static final Block lit_smoker = initBlock(new BlockSmoker(true));
	public static final Block blast_furnace = initBlock(new BlockBlastFurnace(false));
	public static final Block lit_blast_furnace = initBlock(new BlockBlastFurnace(true));
	
	public static final String[] woodTypes = new String[] {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak"/*, "crimson", "warped"*/};
	public static final Block[] doors = new Block[woodTypes.length - 1];
	public static final Block[] fences = new Block[woodTypes.length - 1];
	public static final Block[] gates = new Block[woodTypes.length - 1];
	
	public static final Block[] pressure_plates = new Block[woodTypes.length - 1];
	public static final Block[] buttons = new Block[woodTypes.length - 1];
	public static final Block[] trapdoors = new Block[woodTypes.length - 1];
	public static final Block[] signs = new Block[woodTypes.length - 1];
	public static final Block[] wall_signs = new Block[woodTypes.length - 1];

	static {
		for (int i = 0; i < signs.length; i++) {
			signs[i] = initBlock(new BlockWoodSign(TileEntityWoodSign.class, true, i + 1));
			wall_signs[i] = initBlock(new BlockWoodSign(TileEntityWoodSign.class, false, i + 1));
		}
		
		for (int i = 0; i < doors.length; i++)
			doors[i] = initBlock(new BlockWoodDoor(i + 1));

		for (int i = 0; i < fences.length; i++)
			fences[i] = initBlock(new BlockWoodFence(i + 1));

		for (int i = 0; i < gates.length; i++)
			gates[i] = initBlock(new BlockWoodFenceGate(i + 1));
		
		for (int i = 0; i < pressure_plates.length; i++)
			pressure_plates[i] = initBlock(new BlockWoodPressurePlate(i + 1));
		
		for (int i = 0; i < buttons.length; i++)
			buttons[i] = initBlock(new BlockWoodButton(i + 1));
		
		for (int i = 0; i < trapdoors.length; i++)
			trapdoors[i] = initBlock(new BlockWoodTrapdoor(i + 1));
	}
	
	private static Block initBlock(Block block) {
		if(!(block instanceof IConfigurable) || ((IConfigurable)block).isEnabled())
			initList.add(block);
		return block;
	}
	
	@SuppressWarnings("rawtypes")
	public static void init() {
		for(Block block : initList) {
			if (!(block instanceof IConfigurable) || ((IConfigurable) block).isEnabled()) {
				String name = block.getUnlocalizedName();
				String[] strings = name.split("\\.");

				if (block instanceof ISubBlocksBlock)
					GameRegistry.registerBlock(block, ((ISubBlocksBlock) block).getItemBlockClass(), strings[strings.length - 1]);
				else
					GameRegistry.registerBlock(block, strings[strings.length - 1]);

				if (block instanceof IBurnableBlock) {
					ImmutablePair i = ((IBurnableBlock) block).getFireInfo();
					if(i != null)
						Blocks.fire.setFireInfo(block, (Integer)i.getLeft(), (Integer)i.getRight());
				}
			}
		}
	}
	
	public static interface ISubBlocksBlock {
		Class<? extends ItemBlock> getItemBlockClass();
	}
	
	@SuppressWarnings("rawtypes")
	public static interface IBurnableBlock {
		ImmutablePair getFireInfo();
	}
}