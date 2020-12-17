package testMod.potions;

import com.megacrit.cardcrawl.potions.AbstractPotion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import testMod.DefaultMod;

import java.util.Random;

public class UpgradablePotionFactory {
    private static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());

    //TODO: Never throw null. They should be replaced with Exceptions. Or possibly PotionSlots.
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
            case "BlockPotion": {
                logger.info("UpgradeablePotionFactory> BlockPotionUpgradable created.");
                return new BlockPotionUpgradable();
            }

            default: {
                logger.info("UpgradeablePotionFactory> There is no upgradable version of: " + potionClassName);
                return null;
            }
        }
    }


    public static AbstractPotion makeRandomUpgradablePotion() {
        Random random = new Random();
        //TODO: the hardcoded number here should be equal to the number of upgradable potions. Perhaps that can be calculated.
        switch (random.nextInt(8)) {
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
            default: {
                logger.info("UpgradeablePotionFactory> Default case of makeRandomUpgradablePotion. Reduce random range.");
                return null;
            }
        }
    }
}
