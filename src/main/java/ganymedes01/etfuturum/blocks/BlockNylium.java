package ganymedes01.etfuturum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.client.sound.ModSounds;
import ganymedes01.etfuturum.configuration.configs.ConfigBlocksItems;
import ganymedes01.etfuturum.world.generate.decorate.WorldGenNetherGrass;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockNetherrack;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

public class BlockNylium extends BaseSubtypesBlock implements IGrowable {

	private IIcon[] topIcons;

	public BlockNylium() {
		super(Material.rock, "crimson_nylium", "warped_nylium");
		setHardness(0.4F);
		setResistance(0.4F);
		setNames("nylium");
		setCreativeTab(EtFuturum.creativeTabBlocks);
		setBlockSound(ModSounds.soundNylium);
		setTickRandomly(true);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		super.updateTick(world, x, y, z, rand);
		Block block = world.getBlock(x, y + 1, z);
		if (block.isOpaqueCube() || block.getMaterial() == Material.snow || block.getMaterial() == Material.craftedSnow) {
			world.setBlock(x, y, z, Blocks.netherrack, 0, 2);
		}
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(Blocks.netherrack);
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
		return plantable.getPlantType(world, x, y, z) == EnumPlantType.Nether || plantable instanceof BlockMushroom;
	}

	public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ) {
		if (world.rand.nextBoolean()) {
			world.setBlock(x, y, z, Blocks.netherrack, 0, 2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		if (ConfigBlocksItems.enableCrimsonBlocks) {
			list.add(new ItemStack(item, 1, 0));
		}
		if (ConfigBlocksItems.enableWarpedBlocks) {
			list.add(new ItemStack(item, 1, 1));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		switch (side) {
			case 1:
				return topIcons[meta % topIcons.length];
			case 0:
				return blockIcon;
			default:
				return getIcons()[meta % getIcons().length];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		setIcons(new IIcon[getTypes().length]);
		topIcons = new IIcon[getTypes().length];
		for (int i = 0; i < getIcons().length; i++) {
			getIcons()[i] = reg.registerIcon(getTypes()[i] + "_side");
			topIcons[i] = reg.registerIcon(getTypes()[i]);
		}
		blockIcon = Blocks.netherrack.getIcon(0, 0);
	}

	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean p_149851_5_) {
		if (world.getBlockMetadata(x, y, z) == 0 && !ConfigBlocksItems.enableCrimsonBlocks) {
			return false;
		}
		if (world.getBlockMetadata(x, y, z) == 1 && !ConfigBlocksItems.enableWarpedBlocks) {
			return false;
		}
		return true;
	}

	@Override
	public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return true;
	}

	private final WorldGenerator crimsonGrass = new WorldGenNetherGrass(true);
	private final WorldGenerator warpedGrass = new WorldGenNetherGrass(false);

	@Override
	public void func_149853_b(World world, Random rand, int x, int y, int z) {
		if (world.getBlockMetadata(x, y, z) == 0) {
			crimsonGrass.generate(world, rand, x, y + 1, z);
		} else {
			if (rand.nextInt(40) == 0) {
				int vineX = x + MathHelper.getRandomIntegerInRange(rand, -4, 4);
				int vineZ = z + MathHelper.getRandomIntegerInRange(rand, -4, 4);
				Block block = world.getBlock(x, y + 1, z);
				if (block instanceof BlockNylium || block instanceof BlockNetherrack) {
					world.setBlock(vineX, y + 1, vineZ, ModBlocks.TWISTING_VINES.get());
				}
			}
			warpedGrass.generate(world, rand, x, y + 1, z);
		}
	}
}
