package ganymedes01.etfuturum;

import com.google.common.io.Files;
import ganymedes01.etfuturum.compat.CompatMisc;
import ganymedes01.etfuturum.configuration.ConfigBase;
import ganymedes01.etfuturum.configuration.configs.ConfigEnchantsPotions;
import ganymedes01.etfuturum.configuration.configs.ConfigEntities;
import ganymedes01.etfuturum.configuration.configs.ConfigMixins;
import ganymedes01.etfuturum.lib.Reference;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static java.nio.file.Files.walk;

public class EtFuturumMixinPlugin implements IMixinConfigPlugin {

    public static final MixinEnvironment.Side side = MixinEnvironment.getCurrentEnvironment().getSide();
    private static final Path MODS_DIRECTORY_PATH = new File(Launch.minecraftHome, "mods/").toPath();
    private static final Logger LOG = LogManager.getLogger(Reference.MOD_ID + " mixins");


    @Override
    public void onLoad(String mixinPackage) {
        final String configDir = "config" + File.separator + Reference.MOD_ID;

//      File from before Et Futurum Requiem (Not in a subdirectory)
        File olderFile = new File(Launch.minecraftHome, "config" + File.separator + "etfuturum.cfg");
//      File from before Et Futurum Requiem 2.2.2
        File oldFile = new File(Launch.minecraftHome, configDir + File.separator + "etfuturum.cfg");

        oldFile.getParentFile().mkdirs();
        if (olderFile.exists()) {
            try {
                Files.copy(olderFile, oldFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            olderFile.delete();
            Reference.launchConfigWarning = true;
        }

        if (oldFile.exists()) {
            Reference.launchConfigWarning = true;
        }

        ConfigBase.initializeConfigs();

//      if(oldFile.exists()) {
//          ConfigBase.loadBaseConfig(oldFile);
//      }

        CompatMisc.doLwjgl3ifyCompat();
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        List<String> mixins = new ArrayList<>();

        if (loadJarOf(TargetedMod.EXTRAUTILITIES)) {
            mixins.add("compat.extrautilities.ItemDivisionSigilMixin");
            mixins.add("compat.extrautilities.EventHandlerServerMixin");
        }

        if (ConfigMixins.endPortalFix) {
            mixins.add("endportal.MixinBlockEndPortal");
        }

        if (ConfigMixins.fenceWallConnectFix) {
            mixins.add("fencewallconnect.MixinBlockWall");
            mixins.add("fencewallconnect.MixinBlockFence");
        }

        if (ConfigMixins.stepHeightFix) {
            mixins.add("stepfix.MixinEntity");
        }

        if (ConfigMixins.enableSpectatorMode) {
            mixins.add("spectator.MixinGameType");
            mixins.add("spectator.MixinEntity");
            mixins.add("spectator.MixinWorld");
            mixins.add("spectator.MixinEntityPlayer");
            mixins.add("spectator.MixinNetHandlerPlayServer");
            mixins.add("spectator.MixinInventoryPlayer");
            mixins.add("spectator.MixinContainerChest");
            if (side == MixinEnvironment.Side.CLIENT) {
                mixins.add("spectator.client.MixinEntityRenderer");
                mixins.add("spectator.client.MixinEntityPlayer");
                mixins.add("spectator.client.MixinWorldRenderer");
            }
        }

        if (ConfigMixins.avoidDroppingItemsWhenClosing) {
            mixins.add("closedrops.MixinEntityPlayerMP");
        }

        if (ConfigMixins.enableElytra) {
            mixins.add("backlytra.MixinEntityPlayer");
            mixins.add("backlytra.MixinEntityLivingBase");
            mixins.add("backlytra.MixinNetHandlerPlayServer");
            mixins.add("backlytra.MixinEntityTrackerEntry");
            if (doesClassExist("thaumcraft/common/lib/events/EventHandlerEntity")) {
                mixins.add("backlytra.thaumcraft.MixinEventHandlerEntity");
            }
            if (side == MixinEnvironment.Side.CLIENT) {
                mixins.add("backlytra.client.MixinAbstractClientPlayer");
                mixins.add("backlytra.client.MixinEntityPlayerSP");
                mixins.add("backlytra.client.MixinRenderPlayer");
                mixins.add("backlytra.client.MixinModelBiped");
                mixins.add("backlytra.client.MixinEntityRenderer");
            }
        }

        if (ConfigMixins.enableDoWeatherCycle) {
            mixins.add("doweathercycle.MixinCommandHandler");
            mixins.add("doweathercycle.MixinWorldInfo");
        }

        if (ConfigMixins.enableRandomTickSpeed) {
            mixins.add("randomtickspeed.MixinWorldServer");
        }

        if (ConfigMixins.creativeFlightSpeedModifier > 1) {
            mixins.add("flyspeed.MixinEntityPlayer");
        }

        if (ConfigMixins.bouncyBeds) {
            mixins.add("bouncybeds.MixinBlockBed");
        }

        if (ConfigMixins.newHurtSounds) {
            mixins.add("sounds.MixinEntityPlayer");
            if (side == MixinEnvironment.Side.CLIENT) {
                mixins.add("sounds.client.MixinEntityClientPlayerMP");
            }
        }

        if (ConfigMixins.newMobSounds) {
            mixins.add("sounds.MixinEntitySnowman");
            mixins.add("sounds.MixinEntitySkeleton");
            mixins.add("sounds.MixinEntitySquid");
            mixins.add("sounds.MixinEntityWitch");
        }

        if (ConfigMixins.floorCeilingButtons) {
            mixins.add("floorceilbutton.MixinBlockButton");
        }

        if (ConfigMixins.newEnderEyeSounds) {
            mixins.add("sounds.MixinItemEnderEye");
            mixins.add("sounds.MixinEntityEnderEye");
        }

        if (ConfigMixins.newEnchantingSounds) {
            mixins.add("sounds.MixinContainerEnchantment");
        }

        if (ConfigMixins.newFishingRodSounds) {
            mixins.add("sounds.MixinItemFishingRod");
        }

        if (ConfigMixins.newBeaconSounds) {
            mixins.add("sounds.MixinTileEntityBeacon");
        }

        if (ConfigMixins.hoeTilling) {
            mixins.add("sounds.MixinItemHoe");
        }

        if (ConfigMixins.enableObservers) {
            mixins.add("observer.MixinWorld");
            mixins.add("observer.MixinWorldServer");
            mixins.add("observer.MixinChunk");
        }

        if (ConfigMixins.arrowFallingFix) {
            mixins.add("fallingarrowfix.MixinEntityArrow");
        }

        if (ConfigMixins.blockHopperInteraction) {
            mixins.add("blockinventories.MixinTileEntityHopper");
        }

        if (ConfigMixins.collidedThrowableFix) {
            mixins.add("projectilecollidefix.MixinEntityThrowable");
        }

        if (ConfigMixins.postTreeGenEvent && ConfigEntities.enableBees) {
            mixins.add("posttreegen.MixinWorldGenAbstractTree");
        }

        if (ConfigMixins.ladderTrapdoors) {
            mixins.add("laddertrapdoors.MixinBlockLadder");
            mixins.add("laddertrapdoors.MixinBlockTrapdoor");
        }

        if (ConfigMixins.betterPistons) {
            mixins.add("backinslime.MixinBlockPistonBase");
        }

        if (ConfigMixins.soulFire) {
            mixins.add("soulfire.MixinBlockFire");
            mixins.add("soulfire.MixinEntity");
            if (side == MixinEnvironment.Side.CLIENT) {
                mixins.add("soulfire.MixinRenderBlocks");
            }
        }

        if (ConfigMixins.hideSingleLevelEnchants) {
            mixins.add("singlelevel.MixinEnchantment");
        }

        if (ConfigMixins.fireproofItems) {
            mixins.add("uninflammableitem.MixinEntityItem");
        }

        if (false) { //Does not work for some reason, investigate in 2.6.1
            mixins.add("darkspawns.MixinEntityMob");
        }

        if (side == MixinEnvironment.Side.CLIENT) {
            if (ConfigMixins.dustUnderFallingBlocks) {
                mixins.add("blockfallingparticles.client.MixinBlockFalling");
            }

            if (ConfigMixins.adjustedAttenuation) {
                mixins.add("sounds.client.MixinSoundManager_Attenuation");
            }

            if (ConfigMixins.boundedBlockBreakingParticles) {
                mixins.add("boundedparticles.client.MixinEffectRenderer");
            }

            if (ConfigMixins.furnaceCrackle) {
                mixins.add("sounds.client.MixinBlockFurnace");
            }

            if (ConfigEnchantsPotions.enableSwiftSneak) {
                mixins.add("swiftsneak.client.MixinMovementInputFromOptions");
            }

            if (ConfigMixins.flowerPotFixes) {
                mixins.add("flowerpotfix.client.MixinRenderBlocks");
            }

            if (ConfigMixins.interpolatedTextures) {
                mixins.add("interpolatedtexturemap.client.MixinTextureMap");
            }
        }

        return mixins;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private boolean doesClassExist(String path) {
        return EtFuturum.class.getResource("/" + path + ".class") != null;
    }

    @SuppressWarnings("deprecation")
    private boolean loadJarOf(final TargetedMod mod) {
        try {
            File jar = findJarOf(mod);
            if (jar == null) {
                LOG.info("Jar not found for " + mod);
                return false;
            }

            LOG.info("Attempting to add " + jar + " to the URL Class Path");
            if (!jar.exists()) {
                throw new FileNotFoundException(jar.toString());
            }
            MinecraftURLClassPath.addJar(jar);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("resource")
    public static File findJarOf(final TargetedMod mod) {
        try {
            return walk(MODS_DIRECTORY_PATH)
                    .filter(mod::isMatchingJar)
                    .map(Path::toFile)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public enum TargetedMod {

        //
        // IMPORTANT: Do not make any references to any mod from this file. This file is loaded quite early on and if
        // you refer to other mods you load them as well. The consequence is: You can't inject any previously loaded classes!
        // Exception: Tags.java, as long as it is used for Strings only!
        //

        VANILLA("Minecraft", "unused", true),
        EXTRAUTILITIES("Extra Utilities", "extrautilities", true, "ExtraUtilities");

        public final String modName;
        public final String jarNamePrefixLowercase;
        // Optional dependencies can be omitted in development. Especially skipping GT5U will drastically speed up your game start!
        public final boolean loadInDevelopment;
        public final String[] modId;

        TargetedMod(String modName, String jarNamePrefix, boolean loadInDevelopment, String... modId) {
            this.modName = modName;
            this.jarNamePrefixLowercase = jarNamePrefix.toLowerCase();
            this.loadInDevelopment = loadInDevelopment;
            this.modId = modId;
        }

        public boolean isMatchingJar(Path path) {

            final String pathString = path.toString();
            final String nameLowerCase = Files.getNameWithoutExtension(pathString).toLowerCase();
            final String fileExtension = Files.getFileExtension(pathString);

            String modIdString = null;

            if (modId != null) {
                try {
                    modIdString = getModID(path.toFile());
                } catch (Exception ignored) {
                }
            }

            return (nameLowerCase.startsWith(jarNamePrefixLowercase) || (modIdString != null && Arrays.asList(modId).contains(modIdString))) && "jar".equals(fileExtension);
        }

        private static String getModID(File file) throws IOException {

            try (ZipFile zipFile = new ZipFile(file)) {

                Enumeration<? extends ZipEntry> zipEntryEnumeration = zipFile.entries();

                while (zipEntryEnumeration.hasMoreElements()) {

                    ZipEntry zipEntry = zipEntryEnumeration.nextElement();

                    if (zipEntry != null && zipEntry.getName().equalsIgnoreCase("mcmod.info")) {

                        try (
                                InputStream inputStream = zipFile.getInputStream(zipEntry);
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
                        ) {
                            String read;

                            while ((read = bufferedReader.readLine()) != null) {
                                if (read.contains("\"modid\"")) {
                                    return read
                                            .replaceAll("\"", "")
                                            .replaceAll("modid", "")
                                            .replaceAll(":", "")
                                            .replaceAll(",", "")
                                            .replaceAll("\t", "")
                                            .replaceAll("\n", "")
                                            .replaceAll(" ", "")
                                            .replaceAll("\\{", "")
                                            .replaceAll("}", "");
                                }
                            }
                        }
                    }
                }
            }

            return null;
        }

        @Override
        public String toString() {
            return "TargetedMod{" +
                    "modName='" + modName + '\'' +
                    ", jarNamePrefixLowercase='" + jarNamePrefixLowercase + '\'' +
                    '}';
        }
    }
}
