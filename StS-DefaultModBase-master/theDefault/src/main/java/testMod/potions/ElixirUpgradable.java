package testMod.potions;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.unique.GamblingChipAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.Elixir;
import com.megacrit.cardcrawl.potions.GamblersBrew;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import testMod.actions.ExhaustThenDrawAnyNumberAction;

import java.util.Iterator;

public class ElixirUpgradable extends Elixir implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(ElixirUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString("ElixirPotion");

    public static final String NAME = oldPotionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 10;

    public ElixirUpgradable() {
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
        if(getPotionLevel() < maxPotionLevel) {
            super.use(target);
        } else {
            if (!AbstractDungeon.player.hand.isEmpty()) {
                this.addToBot(new ExhaustThenDrawAnyNumberAction(AbstractDungeon.player));
            }
        }

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
    public AbstractPotion makeCopy() {
        return UpgradablePotionFactory.makeUpgradablePotionFromSimpleClassName(this.getClass().getSimpleName(), getPotionLevel());
    }

    @Override
    public int getPotionLevel() {
        return potionLevel;
    }
}
