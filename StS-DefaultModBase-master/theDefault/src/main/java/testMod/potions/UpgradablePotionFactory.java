package testMod.potions;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import testMod.DefaultMod;

import java.util.Random;

public class UpgradablePotionFactory {
    private static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());

    //TODO: Never throw null. They should be replaced with Exceptions. Or possibly PotionSlots.
    /**
     * Creates an Upgradable version of a vanilla Potion. Returns null if it is unrecognized.
     * @param potionClassName the Simple Class Name of the desided potion.
     * @return IF potionClassName is recognized: AbstractPotion that implements UpgradablePotion.
     *         ELSE: Null
     */
    public static AbstractPotion makeUpgradablePotionFromVanillaSimpleClassName(String potionClassName) {
        switch (potionClassName) {
            case "FirePotion": {
                logger.info("UpgradeablePotionFactory> FirePotionUpgradable created.");
                return new FirePotionUpgradable();
            }
            case "Ambrosia": {
                logger.info("UpgradeablePotionFactory> AmbrosiaUpgradable created.");
                return new AmbrosiaUpgradable();
            }
            case "AncientPotion": {
                logger.info("UpgradeablePotionFactory> AncientPotionUpgradable created.");
                return new AncientPotionUpgradable();
            }
            case "AttackPotion": {
                logger.info("UpgradeablePotionFactory> AttackPotionUpgradable created.");
                return new AttackPotionUpgradable();
            }
            case "BlessingOfTheForge": {
                logger.info("UpgradeablePotionFactory> BlessingOfTheForgeUpgradable created.");
                return new BlessingOfTheForgeUpgradable();
            }
            case "BlockPotion": {
                logger.info("UpgradeablePotionFactory> BlockPotionUpgradable created.");
                return new BlockPotionUpgradable();
            }
            case "BloodPotion": {
                logger.info("UpgradeablePotionFactory> BloodPotionUpgradable created.");
                return new BloodPotionUpgradable();
            }
            case "BottledMiracle": {
                logger.info("UpgradeablePotionFactory> BottledMiracleUpgradable created.");
                return new BottledMiracleUpgradable();
            }
            case "ColorlessPotion": {
                logger.info("UpgradeablePotionFactory> ColorlessPotionUpgradable created.");
                return new ColorlessPotionUpgradable();
            }
            case "CultistPotion": {
                logger.info("UpgradeablePotionFactory> CultistPotionUpgradable created.");
                return new CultistPotionUpgradable();
            }
            case "CunningPotion": {
                logger.info("UpgradeablePotionFactory> CunningPotionUpgradable created.");
                return new CunningPotionUpgradable();
            }
            case "DexterityPotion": {
                logger.info("UpgradeablePotionFactory> DexterityPotionUpgradable created.");
                return new DexterityPotionUpgradable();
            }
            case "DistilledChaosPotion": {
                logger.info("UpgradeablePotionFactory> DistilledChaosPotionUpgradable created.");
                return new DistilledChaosPotionUpgradable();
            }
            case "DuplicationPotion": {
                logger.info("UpgradeablePotionFactory> DuplicationPotionUpgradable created.");
                return new DuplicationPotionUpgradable();
            }
            case "Elixir": {
                logger.info("UpgradeablePotionFactory> ElixirUpgradable created.");
                return new ElixirUpgradable();
            }
            case "EnergyPotion": {
                logger.info("UpgradeablePotionFactory> EnergyPotionUpgradable created.");
                return new EnergyPotionUpgradable();
            }
            case "EntropicBrew": {
                logger.info("UpgradeablePotionFactory> EntropicBrewUpgradable created.");
                return new EntropicBrewUpgradable();
            }
            case "EssenceOfDarkness": {
                logger.info("UpgradeablePotionFactory> EssenceOfDarknessUpgradable created.");
                return new EssenceOfDarknessUpgradable();
            }
            case "EssenceOfSteel": {
                logger.info("UpgradeablePotionFactory> EssenceOfSteelUpgradable created.");
                return new EssenceOfSteelUpgradable();
            }
            case "ExplosivePotion": {
                logger.info("UpgradeablePotionFactory> ExplosivePotionUpgradable created.");
                return new ExplosivePotionUpgradable();
            }
            case "FairyPotion": {
                logger.info("UpgradeablePotionFactory> FairyPotionUpgradable created.");
                return new FairyPotionUpgradable();
            }
            case "FearPotion": {
                logger.info("UpgradeablePotionFactory> FearPotionUpgradable created.");
                return new FearPotionUpgradable();
            }
            case "FocusPotion": {
                logger.info("UpgradeablePotionFactory> FocusPotionUpgradable created.");
                return new FocusPotionUpgradable();
            }
            case "FruitJuice": {
                logger.info("UpgradeablePotionFactory> FruitJuiceUpgradable created.");
                return new FruitJuiceUpgradable();
            }
            default: {
                logger.info("UpgradeablePotionFactory> There is no upgradable version of: " + potionClassName);
                return null;
            }
        }
    }


    /**
     * Returns a random UpgradablePotion. This includes Upgradable vanilla versions and potions unique to this mod.
     * @return AbstractPotion that implements UpgradablePotion.
     */
    public static AbstractPotion makeRandomUpgradablePotion() {
        Random random = new Random();
        //TODO: the hardcoded number here should be equal to the number of upgradable potions. Perhaps that can be calculated.
        switch (random.nextInt(10)) {
            case 0:
                return new FirePotionUpgradable();
            case 1:
                return new BottledLightning();
            case 2:
                return new AmbrosiaUpgradable();
            case 3:
                return new AncientPotionUpgradable();
            case 4:
                return new AttackPotionUpgradable();
            case 5:
                return new FibonacciPotion();
            case 6:
                return new BlessingOfTheForgeUpgradable();
            case 7:
                return new BlockPotionUpgradable();
            case 8:
                return new BloodPotionUpgradable();
            case 9:
                return new BottledMiracleUpgradable();
            case 10:
                return new ColorlessPotionUpgradable();
            case 11:
                return new CultistPotionUpgradable();
            case 12:
                return new CunningPotionUpgradable();
            case 13:
                return new DexterityPotionUpgradable();
            case 14:
                return new DistilledChaosPotionUpgradable();
            case 15:
                return new DuplicationPotionUpgradable();
            case 16:
                return new ElixirUpgradable();
            case 17:
                return new EnergyPotionUpgradable();
            case 18:
                return new EntropicBrewUpgradable();
            default: {
                logger.info("UpgradeablePotionFactory> Default case of makeRandomUpgradablePotion. Reduce random range.");
                return null;
            }
        }
    }

    /**
     * Returns a random UpgradablePotion at the desired level. This includes Upgradable versions of vanilla potions and
     * potions unique to this mod.
     * @param potionLevel Level the potion will be upgraded to.
     *                    If potionLevel is less than 0, the potion will be level 0.
     *                    If potionLevel is more than the potion's max level, the potion will be maxed.
     * @return AbstractPotion that implements UpgradablePotion.
     */
    public static AbstractPotion makeRandomUpgradablePotion(int potionLevel) {
        UpgradablePotion toReturn = (UpgradablePotion) makeRandomUpgradablePotion();

        if(toReturn == null) return null;

        while(toReturn.canUpgradePotion() && toReturn.getPotionLevel() < potionLevel) toReturn.upgradePotion();

        return (AbstractPotion) toReturn;
    }


    /**
     * Creates an Upgradable Potion. Returns null if it is unrecognized. Works with vanilla names.
     * @param potionClassName the Simple Class Name of the desired potion.
     * @return IF potionClassName is recognized: AbstractPotion that implements UpgradablePotion.
     *         ELSE: Null
     */
    public static AbstractPotion makeUpgradablePotionFromSimpleClassName(String potionClassName) {
        if (makeUpgradablePotionFromVanillaSimpleClassName(potionClassName) != null) {
            return makeUpgradablePotionFromVanillaSimpleClassName(potionClassName);
        }

        switch (potionClassName) {
            case "FirePotionUpgradable": {
                logger.info("UpgradeablePotionFactory> FirePotionUpgradable created.");
                return new FirePotionUpgradable();
            }
            case "AmbrosiaUpgradable": {
                logger.info("UpgradeablePotionFactory> AmbrosiaUpgradable created.");
                return new AmbrosiaUpgradable();
            }
            case "AncientPotionUpgradable": {
                logger.info("UpgradeablePotionFactory> AncientPotionUpgradable created.");
                return new AncientPotionUpgradable();
            }
            case "AttackPotionUpgradable": {
                logger.info("UpgradeablePotionFactory> AttackPotionUpgradable created.");
                return new AttackPotionUpgradable();
            }
            case "BlessingOfTheForgeUpgradable": {
                logger.info("UpgradeablePotionFactory> BlessingOfTheForgeUpgradable created.");
                return new BlessingOfTheForgeUpgradable();
            }
            case "BlockPotionUpgradable": {
                logger.info("UpgradeablePotionFactory> BlockPotionUpgradable created.");
                return new BlockPotionUpgradable();
            }
            case "BloodPotionUpgradable": {
                logger.info("UpgradeablePotionFactory> BloodPotionUpgradable created.");
                return new BloodPotionUpgradable();
            }
            case "BottledMiracleUpgradable": {
                logger.info("UpgradeablePotionFactory> BottledMiracleUpgradable created.");
                return new BottledMiracleUpgradable();
            }
            case "ColorlessPotionUpgradable": {
                logger.info("UpgradeablePotionFactory> ColorlessPotionUpgradable created.");
                return new ColorlessPotionUpgradable();
            }


            case "BottledLightning": {
                logger.info("UpgradeablePotionFactory> BottledLightning created.");
                return new BottledLightning();
            }
            case "FibonacciPotion": {
                logger.info("UpgradeablePotionFactory> FibonacciPotion created.");
                return new FibonacciPotion();
            }


            default: {
                logger.info("UpgradeablePotionFactory> There is no upgradable version of: " + potionClassName);
                return null;
            }
        }
    }

    /**
     * Creates an Upgradable Potion at the desired level. Returns null if it is unrecognized. Works with vanilla names.
     * @param potionClassName the Simple Class Name of the desired potion.
     * @param potionLevel Level the potion will be upgraded to.
     *                    If potionLevel is less than 0, the potion will be level 0.
     *                    If potionLevel is more than the potion's max level, the potion will be maxed.
     * @return IF potionClassName is recognized: AbstractPotion that implements UpgradablePotion. else: Null
     */
    public static AbstractPotion makeUpgradablePotionFromSimpleClassName(String potionClassName, int potionLevel) {
        UpgradablePotion toReturn = (UpgradablePotion) makeUpgradablePotionFromSimpleClassName(potionClassName);

        if(toReturn == null) return null;

        while(toReturn.canUpgradePotion() && toReturn.getPotionLevel() < potionLevel) toReturn.upgradePotion();

        return (AbstractPotion) toReturn;
    }
}
