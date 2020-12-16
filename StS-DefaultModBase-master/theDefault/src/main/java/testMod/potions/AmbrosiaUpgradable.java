package testMod.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.Ambrosia;

public class AmbrosiaUpgradable extends Ambrosia implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(AmbrosiaUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString("Ambrosia");

    public static final String NAME = oldPotionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    private int potionLevel = 0;

    public AmbrosiaUpgradable() {
        super();
    }

    @Override
    public void use(AbstractCreature target) {
        super.use(target);
        if(getPotionLevel() >= 20) {
            //TODO: enter Divinity again next turn
        }
    }

    @Override
    public void initializeData() {
        // this.name is not reverted by initializeData after an upgrade has been applied.
        this.name = "Ambrosia";
        super.initializeData();

        // initializeData clears tips THEN adds the main PowerTip THEN the Stance PowerTip.
        this.tips.get(0).body += potionStrings.DESCRIPTIONS[0];
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
        if(getPotionLevel() < 20) {
            return true;
        }
        return false;
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

        //the base game does this upon initializeData()... but I do not know where.
        //this.tips.get(0).body = "#pTestMod NL " + this.tips.get(0).body;

        this.name = this.tips.get(0).header;
        this.description = this.tips.get(0).body;
    }
}
