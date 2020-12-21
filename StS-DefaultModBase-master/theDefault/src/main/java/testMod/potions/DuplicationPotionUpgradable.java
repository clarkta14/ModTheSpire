package testMod.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.DuplicationPotion;

public class DuplicationPotionUpgradable extends DuplicationPotion implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(DuplicationPotionUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString("DuplicationPotion");

    public static final String NAME = oldPotionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 10;

    public DuplicationPotionUpgradable() {
        super();
        initializeData();
    }

    @Override
    public void initializeData() {
        // this.name is not reverted by initializeData after an upgrade has been applied.
        this.name = NAME;
        super.initializeData();

        String upgradeDesc = potionStrings.DESCRIPTIONS[0];

        if (AbstractDungeon.player != null && AbstractDungeon.player.hasRelic("SacredBark")) {
            upgradeDesc = upgradeDesc.replace('2','4');
            if(getPotionLevel() == maxPotionLevel)
                this.tips.get(0).body = this.tips.get(0).body.replace('2','4');
        } else {
            if(getPotionLevel() == maxPotionLevel)
                this.tips.get(0).body = oldPotionStrings.DESCRIPTIONS[1];
        }

        this.tips.get(0).body += (upgradeDesc + maxPotionLevel);

        if(getPotionLevel() > 0) {
            if(getPotionLevel() == maxPotionLevel) {
                this.tips.get(0).header += "+MAX";
            } else {
                this.tips.get(0).header += "+" + getPotionLevel();
            }
        }

        this.name = this.tips.get(0).header;
        this.description = this.tips.get(0).body;
    }

    @Override
    public void use(AbstractCreature target) {
        if(getPotionLevel() == maxPotionLevel) {
            super.use(target);
            super.use(target);
        } else
            super.use(target);
    }
    @Override
    public boolean upgradePotion() {
        if(canUpgradePotion()) {
            potionLevel += 1;
            initializeData();
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

    @Override
    public int getPotionLevel() {
        return potionLevel;
    }
}
