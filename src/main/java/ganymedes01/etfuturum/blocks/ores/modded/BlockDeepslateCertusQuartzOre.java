package ganymedes01.etfuturum.blocks.ores.modded;

import ganymedes01.etfuturum.blocks.IEmissiveLayerBlock;
import ganymedes01.etfuturum.blocks.ores.BaseSubtypesDeepslateOre;
import ganymedes01.etfuturum.compat.ExternalContent;
import ganymedes01.etfuturum.lib.RenderIDs;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockDeepslateCertusQuartzOre extends BaseSubtypesDeepslateOre implements IEmissiveLayerBlock {

	public BlockDeepslateCertusQuartzOre() {
		super("deepslate_certus_quartz_ore", "deepslate_charged_certus_quartz_ore");
		setResistance(5.0F);
		setHardness(4.5F);
	}

	@Override
	public String getTextureSubfolder() {
		return "ae2";
	}

	@Override
	protected Block getBase(int meta) {
		if (meta == 1) {
			return ExternalContent.Blocks.AE2_CHARGED_CERTUS_QUARTZ_ORE.get();
		}
		return ExternalContent.Blocks.AE2_CERTUS_QUARTZ_ORE.get();
	}

	@Override
	public IIcon getEmissiveLayerIcon(int side, int meta) {
		return getBase(meta).getIcon(side, getBaseMeta(meta));
	}

	//Not sure how to make this work rn
	@Override
	public int getEmissiveMinBrightness(IBlockAccess world, int x, int y, int z) {
		return 1;
	}

	@Override
	public int getRenderType() {
		return RenderIDs.EMISSIVE_DOUBLE_LAYER;
	}
}
