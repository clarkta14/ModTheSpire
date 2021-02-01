package testMod.potions;

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
            case "GamblersBrew": {
                logger.info("UpgradeablePotionFactory> GamblersBrewUpgradable created.");
                return new GamblersBrewUpgradable();
            }
            case "GhostInAJar": {
                logger.info("UpgradeablePotionFactory> GhostInAJarUpgradable created.");
                return new GhostInAJarUpgradable();
            }
            case "HeartOfIron": {
                logger.info("UpgradeablePotionFactory> HeartOfIronUpgradable created.");
                return new HeartOfIronUpgradable();
            }
            case "LiquidBronze": {
                logger.info("UpgradeablePotionFactory> LiquidBronzeUpgradable created.");
                return new LiquidBronzeUpgradable();
            }
            case "LiquidMemories": {
                logger.info("UpgradeablePotionFactory> LiquidMemoriesUpgradable created.");
                return new LiquidMemoriesUpgradable();
            }
            case "PoisonPotion": {
                logger.info("UpgradeablePotionFactory> PoisonPotionUpgradable created.");
                return new PoisonPotionUpgradable();
            }
            case "PotionOfCapacity": {
                logger.info("UpgradeablePotionFactory> PotionOfCapacityUpgradable created.");
                return new PotionOfCapacityUpgradable();
            }
            case "PowerPotion": {
                logger.info("UpgradeablePotionFactory> PowerPotionUpgradable created.");
                return new PowerPotionUpgradable();
            }
            case "RegenPotion": {
                logger.info("UpgradeablePotionFactory> RegenPotionUpgradable created.");
                return new RegenPotionUpgradable();
            }
            case "SkillPotion": {
                logger.info("UpgradeablePotionFactory> SkillPotionUpgradable created.");
                return new SkillPotionUpgradable();
            }
            case "SmokeBomb": {
                logger.info("UpgradeablePotionFactory> SmokeBombUpgradable created.");
                return new SmokeBombUpgradable();
            }
            case "SneckoOil": {
                logger.info("UpgradeablePotionFactory> SneckoOilUpgradable created.");
                return new SneckoOilUpgradable();
            }
            case "SpeedPotion": {
                logger.info("UpgradeablePotionFactory> SpeedPotionUpgradable created.");
                return new SpeedPotionUpgradable();
            }
            case "StancePotion": {
                logger.info("UpgradeablePotionFactory> StancePotionUpgradable created.");
                return new StancePotionUpgradable();
            }
            case "SteroidPotion": {
                logger.info("UpgradeablePotionFactory> SteroidPotionUpgradable created.");
                return new SteroidPotionUpgradable();
            }
            case "StrengthPotion": {
                logger.info("UpgradeablePotionFactory> StrengthPotionUpgradable created.");
                return new StrengthPotionUpgradable();
            }
            case "SwiftPotion": {
                logger.info("UpgradeablePotionFactory> SwiftPotionUpgradable created.");
                return new SwiftPotionUpgradable();
            }
            case "WeakenPotion": {
                logger.info("UpgradeablePotionFactory> WeakenPotionUpgradable created.");
                return new WeakenPotionUpgradable();
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
        switch (random.nextInt(45)) {
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
            case 19:
                return new EssenceOfDarknessUpgradable();
            case 20:
                return new EssenceOfSteelUpgradable();
            case 21:
                return new ExplosivePotionUpgradable();
            case 22:
                return new FairyPotionUpgradable();
            case 23:
                return new FearPotionUpgradable();
            case 24:
                return new FocusPotionUpgradable();
            case 25:
                return new FruitJuiceUpgradable();
            case 26:
                return new GamblersBrewUpgradable();
            case 27:
                return new GhostInAJarUpgradable();
            case 28:
                return new HeartOfIronUpgradable();
            case 29:
                return new LiquidBronzeUpgradable();
            case 30:
                return new LiquidMemoriesUpgradable();
            case 31:
                return new PoisonPotionUpgradable();
            case 32:
                return new PotionOfCapacityUpgradable();
            case 33:
                return new PowerPotionUpgradable();
            case 34:
                return new RegenPotionUpgradable();
            case 35:
                return new SkillPotionUpgradable();
            case 36:
                return new SmokeBombUpgradable();
            case 37:
                return new SmokeGrenadeUpgradable();
            case 38:
                return new SneckoOilUpgradable();
            case 39:
                return new SpeedPotionUpgradable();
            case 40:
                return new StancePotionUpgradable();
            case 41:
                return new SteroidPotionUpgradable();
            case 42:
                return new StrengthPotionUpgradable();
            case 43:
                return new SwiftPotionUpgradable();
            case 44:
                return new WeakenPotionUpgradable();


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
     *         ELSE: null
     */
    public static AbstractPotion makeUpgradablePotionFromSimpleClassName(String potionClassName) {
        if (makeUpgradablePotionFromVanillaSimpleClassName(potionClassName) != null) {
            return makeUpgradablePotionFromVanillaSimpleClassName(potionClassName);
        }

        if (makeUpgradablePotionFromVanillaSimpleClassName(potionClassName.replace("Upgradable", "")) != null) {
            return makeUpgradablePotionFromVanillaSimpleClassName(potionClassName.replace("Upgradable", ""));
        }

        switch (potionClassName) {
            case "SmokeGrenadeUpgradable": {
                logger.info("UpgradeablePotionFactory> SmokeGrenadeUpgradable created.");
                return new SmokeGrenadeUpgradable();
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
