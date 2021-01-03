package testMod.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.SmokeBomb;

public class SmokeGrenadeUpgradable extends SmokeBomb implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(SmokeGrenadeUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString(SmokeBomb.POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 0;

    public SmokeGrenadeUpgradable() {
        super();
        initializeData();
    }

    @Override
    public void initializeData() {
        // SmokeBomb initializeData was not implemented for some reason. So it is done from scratch here.
        this.tips.clear();
        this.tips.add(new PowerTip(NAME, oldPotionStrings.DESCRIPTIONS[0]));

        this.tips.get(0).body += (potionStrings.DESCRIPTIONS[0] + maxPotionLevel);

        this.name = this.tips.get(0).header;
        this.description = this.tips.get(0).body;
    }

    @Override
    public boolean upgradePotion() {
        return false;
    }

    @Override
    public boolean canUpgradePotion() {
        return false;
    }

    @Override
    public int getPotionLevel() {
        return potionLevel;
    }
}