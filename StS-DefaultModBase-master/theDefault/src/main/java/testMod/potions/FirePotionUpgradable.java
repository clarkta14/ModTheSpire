package testMod.potions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.FirePotion;

public class FirePotionUpgradable extends FirePotion implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(FirePotionUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString("Fire Potion");

    public static final String NAME = oldPotionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    private int potionLevel = 0;

    public FirePotionUpgradable() {
        super();
    }

    @Override
    public void use(AbstractCreature target) {
        // The vanilla use of this potion does not update potency before execution.
        this.potency = this.getPotency();
        super.use(target);
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
        if(getPotionLevel() < 5) {
            return true;
        }
        return false;
    }

    @Override
    public int getPotionLevel() {
        return potionLevel;
    }

    @Override
    public int getPotency(int ascension) {return 20 + (3 * getPotionLevel());}

    @Override
    public void updatePowerTip() {
        this.potency = this.getPotency();
        this.description = "#pTestMod NL " + oldPotionStrings.DESCRIPTIONS[0] + this.potency + oldPotionStrings.DESCRIPTIONS[1];

        this.description = this.description + DESCRIPTIONS[0];
        //upgrade level updates before the name.
        if(getPotionLevel() == 1)
            this.name = this.name + "+" + getPotionLevel();
        else
            this.name = this.name.split("\\+")[0] + "+" + getPotionLevel();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }
}
