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
        super.initializeData();
        // this clears tips and adds the main PowerTip. So we know tips.get(0) is what we want
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
        this.description = "#pTestMod NL " + oldPotionStrings.DESCRIPTIONS[0];
        this.description += DESCRIPTIONS[0];

        //upgrade level updates before the name.
        //TODO: maybe do a contains '+' here. There may be instances where a potion upgrades many times at once.
        if(getPotionLevel() == 1)
            this.name = this.name + "+" + getPotionLevel();
        else
            this.name = this.name.split("\\+")[0] + "+" + getPotionLevel();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }
}
