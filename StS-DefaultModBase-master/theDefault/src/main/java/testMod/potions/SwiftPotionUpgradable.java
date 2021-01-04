package testMod.potions;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.SwiftPotion;
import testMod.powers.SwiftPotionMaxPower;

public class SwiftPotionUpgradable extends SwiftPotion implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(SwiftPotionUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString(SwiftPotion.POTION_ID);

    public static final String NAME = oldPotionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 15;

    public SwiftPotionUpgradable() {
        super();
        initializeData();
    }

    @Override
    public void initializeData() {
        // this.name is not reverted by initializeData after an upgrade has been applied.
        this.name = NAME;
        super.initializeData();

        // initializeData clears tips THEN adds the main PowerTip THEN the Stance PowerTip.
        this.tips.get(0).body += (potionStrings.DESCRIPTIONS[0] + maxPotionLevel);

        if (getPotionLevel() > 0) {
            if (getPotionLevel() == maxPotionLevel) {
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
        super.use(target);

        if(getPotionLevel() >= maxPotionLevel) {
            AbstractCreature p = AbstractDungeon.player;
            SwiftPotionMaxPower SPMP = new SwiftPotionMaxPower(p, 1);
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, SPMP));
        }
    }

    @Override
    public int getPotency(int ascensionLevel) {
        //the @MAX: overrides the Upgrade: property. Meaning instead of adding 1 at max, it only applies @MAX.
        if (getPotionLevel() >= maxPotionLevel) {
            return 5;
        } else {
            return 3 + (int) Math.floor(getPotionLevel() / 5);
        }
    }

    @Override
    public boolean upgradePotion() {
        if (canUpgradePotion()) {
            potionLevel += 1;
            initializeData();
            return true;
        }
        return false;
    }

    @Override
    public boolean canUpgradePotion() {
        if (getPotionLevel() < maxPotionLevel) {
            return true;
        }
        return false;
    }

    @Override
    public int getPotionLevel() {
        return potionLevel;
    }
}