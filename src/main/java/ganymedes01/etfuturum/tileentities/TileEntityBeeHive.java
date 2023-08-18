package ganymedes01.etfuturum.tileentities;

import com.google.common.collect.Lists;
import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.blocks.BlockBeeHive;
import ganymedes01.etfuturum.core.utils.ExternalContent;
import ganymedes01.etfuturum.core.utils.helpers.BlockPos;
import ganymedes01.etfuturum.entities.EntityBee;
import ganymedes01.etfuturum.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class TileEntityBeeHive extends TileEntity {
	private final List<TileEntityBeeHive.Bee> bees = Lists.newArrayList();
	private BlockPos flowerPos = null;
	private int honeyLevel = 0;

	public TileEntityBeeHive() {
		super();
	}

	public void markDirty() {
		if (this.isNearFire()) {
			this.angerBees(null, TileEntityBeeHive.State.EMERGENCY);
		}

		super.markDirty();
	}

	public boolean isNearFire() {
		if (this.getWorldObj() != null) {
			for (BlockPos blockpos : BlockPos.iterate(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 1, yCoord + 1, zCoord + 1)) {
				if (blockpos.getBlock(getWorldObj()).getMaterial() == Material.fire) {
					return true;
				}
			}

		}
		return false;
	}

	public boolean hasNoBees() {
		return this.bees.isEmpty();
	}

	public boolean isFullOfBees() {
		return getBeeCount() == 3;
	}

	public void angerBees(EntityPlayer p_226963_1_, TileEntityBeeHive.State p_226963_3_) {
		List<Entity> list = this.tryReleaseBee(p_226963_3_);
		if (p_226963_1_ != null) {
			for (Entity entity : list) {
				if (entity instanceof EntityBee) {
					EntityBee beeentity = (EntityBee) entity;
					if (beeentity.getDistanceSq(entity.posX, entity.posY, entity.posZ) <= 16.0D) {
						if (!this.isSmoked()) {
							beeentity.setBeeAttacker(p_226963_1_);
						} else {
							beeentity.setStayOutOfHiveCountdown(400);
						}
					}
				}
			}
		}

	}

	private List<Entity> tryReleaseBee(TileEntityBeeHive.State p_226965_2_) {
		List<Entity> list = Lists.newArrayList();
		this.bees.removeIf((p_226966_4_) -> {
			return releaseBee(p_226966_4_.entityData, list, p_226965_2_);
		});
		return list;
	}

	public void tryEnterHive(Entity p_226961_1_, boolean p_226961_2_) {
		this.tryEnterHive(p_226961_1_, p_226961_2_, 0);
	}

	public int getBeeCount() {
		return bees.size();
	}

	public int getHoneyLevel() {
		return honeyLevel;
	}

	public boolean isSmoked() {
		return isLitCampfireBelow(getWorldObj(), xCoord, yCoord, zCoord, 5);
	}

	public static boolean isLitCampfireBelow(World world, int x, int y, int z, int spacing) {
		List<Block> fires = Lists.newArrayList();
		if (EtFuturum.hasCampfireBackport) {
			fires.add(ExternalContent.Blocks.CFB_CAMPFIRE.get());
			fires.add(ExternalContent.Blocks.CFB_SOUL_CAMPFIRE.get());
		}
		for (int i = 1; i <= spacing; i++) {
			Block block = world.getBlock(x, y - i, z);
			if (block.isOpaqueCube()) break;
			if (fires.isEmpty()) {
				return block.getMaterial() == Material.fire;
			}
			if (fires.contains(block)) {
				return true;
			}
		}
		return false;
	}

	public void tryEnterHive(Entity p_226962_1_, boolean p_226962_2_, int p_226962_3_) {
		if (getBeeCount() < 3) {
			p_226962_1_.ridingEntity = null;
			p_226962_1_.riddenByEntity = null;
			NBTTagCompound compoundnbt = new NBTTagCompound();
//			p_226962_1_.writeUnlessPassenger(compoundnbt);
			this.bees.add(new TileEntityBeeHive.Bee(compoundnbt, p_226962_3_, p_226962_2_ ? 2400 : 600));
			if (this.getWorldObj() != null) {
				if (p_226962_1_ instanceof EntityBee) {
					EntityBee beeentity = (EntityBee) p_226962_1_;
					if (beeentity.hasFlower() && (!this.hasFlowerPos() || this.getWorldObj().rand.nextBoolean())) {
						this.flowerPos = beeentity.getFlowerPos();
					}
				}

				this.getWorldObj().playSoundEffect(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5,
						Reference.MCAssetVer + ":block.beehive.enter", 1.0F, 1.0F);
			}

			p_226962_1_.setDead();
		}
	}

	private boolean releaseBee(NBTTagCompound p_226967_2_, List<Entity> p_226967_3_, TileEntityBeeHive.State p_226967_4_) {
		if ((this.getWorldObj().isDaytime() && !this.getWorldObj().isRaining()) || p_226967_4_ == State.EMERGENCY) {
			p_226967_2_.removeTag("Passengers");
			p_226967_2_.removeTag("Leash");
			removeUniqueId(p_226967_2_, "UUID");
			EnumFacing direction = EnumFacing.getFront((getWorldObj().getBlockMetadata(xCoord, yCoord, zCoord) & 3) + 2);
			boolean flag = !this.getWorldObj().isAirBlock(xCoord + direction.getFrontOffsetX(), yCoord, zCoord + direction.getFrontOffsetZ());
			if (flag && p_226967_4_ != State.EMERGENCY) {
				return false;
			} else {
				Entity entity = EntityList.createEntityFromNBT(p_226967_2_, getWorldObj());
				if (entity != null) {
					float f = entity.width;
					double d0 = flag ? 0.0D : 0.55D + (double) (f / 2.0F);
					double d1 = (double) xCoord + 0.5D + d0 * (double) direction.getFrontOffsetX();
					double d2 = (double) yCoord + 0.5D - (double) (entity.height / 2.0F);
					double d3 = (double) zCoord + 0.5D + d0 * (double) direction.getFrontOffsetZ();
					entity.setLocationAndAngles(d1, d2, d3, entity.rotationYaw, entity.rotationPitch);
					if (!(entity instanceof EntityBee)) {
						return false;
					} else {
						EntityBee beeentity = (EntityBee) entity;
						if (this.hasFlowerPos() && !beeentity.hasFlower() && this.getWorldObj().rand.nextFloat() < 0.9F) {
							beeentity.setFlowerPos(this.flowerPos);
						}

						if (p_226967_4_ == State.HONEY_DELIVERED) {
							beeentity.onHoneyDelivered();
							if (getWorldObj().getBlock(xCoord, yCoord, zCoord) instanceof BlockBeeHive) {
								int i = getHoneyLevel();
								if (i < 5) {
									int j = this.getWorldObj().rand.nextInt(100) == 0 ? 2 : 1;
									if (i + j > 5) {
										--j;
									}

									setHoneyLevel(i + j);
									BlockBeeHive.updateHiveState(getWorldObj(), xCoord, yCoord, zCoord, getHoneyLevel() == 5);
								}
							}
						}

						beeentity.resetTicksWithoutNectar();
						if (p_226967_3_ != null) {
							p_226967_3_.add(beeentity);
						}

						this.getWorldObj().playSoundEffect(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5,
								Reference.MCAssetVer + ":block.beehive.exit", 1.0F, 1.0F);
						getWorldObj().spawnEntityInWorld(entity);
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}

	private boolean hasFlowerPos() {
		return this.flowerPos != null;
	}

	private void tickBees() {
		Iterator<TileEntityBeeHive.Bee> iterator = this.bees.iterator();

		while (iterator.hasNext()) {
			TileEntityBeeHive.Bee beehivetileentity$bee = iterator.next();
			if (beehivetileentity$bee.ticksInHive > beehivetileentity$bee.minOccupationTicks) {
				NBTTagCompound compoundnbt = beehivetileentity$bee.entityData;
				TileEntityBeeHive.State beehivetileentity$state = compoundnbt.getBoolean("HasNectar") ? TileEntityBeeHive.State.HONEY_DELIVERED : TileEntityBeeHive.State.BEE_RELEASED;
				if (releaseBee(compoundnbt, (List<Entity>) null, beehivetileentity$state)) {
					iterator.remove();
				}
			} else {
				beehivetileentity$bee.ticksInHive++;
			}
		}
	}

	public void updateEntity() {
		super.updateEntity();
		if (!this.getWorldObj().isRemote) {
			this.tickBees();
			if (getBeeCount() > 0 && this.getWorldObj().rand.nextDouble() < 0.005D) {
				double d0 = (double) xCoord + 0.5D;
				double d1 = yCoord;
				double d2 = (double) zCoord + 0.5D;
				this.getWorldObj().playSound(d0, d1, d2, Reference.MCAssetVer + ":block.beehive.work", 1.0F, 1.0F, true);
			}
		}
	}

	public void readFromNBT(NBTTagCompound compound) {
		this.bees.clear();
		NBTTagList listnbt = compound.getTagList("Bees", 10);

		for (int i = 0; i < listnbt.tagCount(); ++i) {
			NBTTagCompound compoundnbt = listnbt.getCompoundTagAt(i);
			TileEntityBeeHive.Bee beehivetileentity$bee = new TileEntityBeeHive.Bee(compoundnbt.getCompoundTag("EntityData"), compoundnbt.getInteger("TicksInHive"), compoundnbt.getInteger("MinOccupationTicks"));
			this.bees.add(beehivetileentity$bee);
		}

		this.flowerPos = null;
		if (compound.hasKey("FlowerPos")) {
			this.flowerPos = BlockPos.readFromNBT(compound.getCompoundTag("FlowerPos"));
		}
		if (compound.hasKey("honeyLevel")) {
			setHoneyLevel(MathHelper.clamp_int(compound.getInteger("honeyLevel"), 0, 5));
		}
		super.readFromNBT(compound);
	}

	public void writeToNBT(NBTTagCompound compound) {
		compound.setTag("Bees", this.getBees());
		if (this.hasFlowerPos()) {
			compound.setTag("FlowerPos", BlockPos.writeToNBT(this.flowerPos));
		}
		if (getHoneyLevel() > 0) {
			compound.setInteger("honeyLevel", honeyLevel);
		}
		super.writeToNBT(compound);
	}

	public NBTTagList getBees() {
		NBTTagList listnbt = new NBTTagList();

		for (TileEntityBeeHive.Bee beehivetileentity$bee : this.bees) {
			removeUniqueId(beehivetileentity$bee.entityData, "UUID");
			NBTTagCompound compoundnbt = new NBTTagCompound();
			compoundnbt.setTag("EntityData", beehivetileentity$bee.entityData);
			compoundnbt.setInteger("TicksInHive", beehivetileentity$bee.ticksInHive);
			compoundnbt.setInteger("MinOccupationTicks", beehivetileentity$bee.minOccupationTicks);
			listnbt.appendTag(compoundnbt);
		}

		return listnbt;
	}

	private static void removeUniqueId(NBTTagCompound nbt, String key) {
		nbt.removeTag(key + "Most");
		nbt.removeTag(key + "Least");
	}

	public void setHoneyLevel(int level) {
		honeyLevel = MathHelper.clamp_int(level, 0, 5);
	}

	static class Bee {
		private final NBTTagCompound entityData;
		private int ticksInHive;
		private final int minOccupationTicks;

		private Bee(NBTTagCompound nbt, int ticksinhive, int minoccupationticks) {
			removeUniqueId(nbt, "UUID");
			this.entityData = nbt;
			this.ticksInHive = ticksinhive;
			this.minOccupationTicks = minoccupationticks;
		}
	}

	public static enum State {
		HONEY_DELIVERED,
		BEE_RELEASED,
		EMERGENCY;
	}
}