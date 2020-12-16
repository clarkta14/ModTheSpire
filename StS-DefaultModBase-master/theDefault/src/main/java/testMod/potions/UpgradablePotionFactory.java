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
                logger.info("UpgradeablePotionFactory> FirePotion upgraded.");
                return new FirePotionUpgradable();
            }
            case "Ambrosia": {
                logger.info("UpgradeablePotionFactory> Ambrosia upgraded.");
                return new AmbrosiaUpgradable();
            }
            case "AncientPotion": {
                logger.info("UpgradeablePotionFactory> AncientPotion upgraded.");
                return new AncientPotionUpgradable();
            }
            case "AttackPotion": {
                logger.info("UpgradeablePotionFactory> AttackPotion upgraded.");
                return new AttackPotionUpgradable();
            }
            default: {
                logger.info("UpgradeablePotionFactory> There is no upgradable version of: " + potionClassName);
                return null;
            }
        }
    }

    //TODO: use for Grab Bag after testing.
    public static AbstractPotion makeRandomUpgradablePotion() {
        Random random = new Random();
        //TODO: the hardcoded number here should be equal to the number of upgradable potions. Perhaps that can be calculated.
        switch (random.nextInt(5)) {
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
            default: {
                logger.info("UpgradeablePotionFactory> Default case of makeRandomUpgradablePotion. Reduce random range.");
                return null;
            }
        }
    }
}
