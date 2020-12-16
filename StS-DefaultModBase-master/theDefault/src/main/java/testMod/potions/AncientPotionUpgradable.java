package testMod.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AncientPotion;

public class AncientPotionUpgradable extends AncientPotion implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(AncientPotionUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString("Ancient Potion");

    public static final String NAME = oldPotionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 8;

    public AncientPotionUpgradable() {
        super();
    }
    @Override
    public void initializeData() {
        // this.name is not reverted by initializeData after an upgrade has been applied.
        this.name = "Ancient Potion";
        super.initializeData();

        // initializeData clears tips THEN adds the main PowerTip THEN the Stance PowerTip.
        this.tips.get(0).body += (potionStrings.DESCRIPTIONS[0] + maxPotionLevel);
    }

    @Override
    public boolean upgradePotion() {
        if(canUpgradePotion()) {
            potionLevel += 1;
            updatePowerTip();
            return true;
        }
        return false;
    }

    @Override
    public boolean canUpgradePotion() {
        if(getPotionLevel() < maxPotionLevel) {
            return true;
        }
        return false;
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascension) {
        return 1 + (int) (Math.floor(getPotionLevel() / 2));
    }

    @Override
    public int getPotionLevel() {
        return potionLevel;
    }

    @Override
    public void updatePowerTip() {
        initializeData();

        if(getPotionLevel() > 0) {
            this.tips.get(0).header += "+" + getPotionLevel();
        }

        //TODO: Perhaps add a CAP or MAX to the name when it is maxed out

        //the base game does this upon initializeData()... but I do not know where.
        //this.tips.get(0).body = "#pTestMod NL " + this.tips.get(0).body;

        this.name = this.tips.get(0).header;
        this.description = this.tips.get(0).body;
    }
}
