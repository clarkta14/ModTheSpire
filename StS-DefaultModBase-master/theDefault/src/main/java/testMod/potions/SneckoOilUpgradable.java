package testMod.potions;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.SneckoOil;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import testMod.actions.UpgradedSneckoOilAction;

public class SneckoOilUpgradable extends SneckoOil implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(SneckoOilUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString(SneckoOil.POTION_ID);

    public static final String NAME = oldPotionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 15;

    public SneckoOilUpgradable() {
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
        if (getPotionLevel() >= maxPotionLevel) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
                this.addToBot(new DrawCardAction(AbstractDungeon.player, getPotency()));
                this.addToBot(new UpgradedSneckoOilAction());
            }
        } else {
            super.use(target);
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
    public AbstractPotion makeCopy() {
        return UpgradablePotionFactory.makeUpgradablePotionFromSimpleClassName(this.getClass().getSimpleName(), getPotionLevel());
    }

    @Override
    public int getPotionLevel() {
        return potionLevel;
    }
}