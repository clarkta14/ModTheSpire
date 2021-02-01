package testMod.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class FibonacciPotion extends CustomPotion implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(FibonacciPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 11235813;

    private int firstInt = 0;
    private int secondInt = 1;

    public FibonacciPotion() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main DefaultMod.java
        super(NAME, POTION_ID, AbstractPotion.PotionRarity.COMMON, AbstractPotion.PotionSize.M, PotionColor.ENERGY);

        // Do you throw this potion at an enemy or do you just consume it.
        isThrown = false;

        initializeData();
    }

    @Override
    public void initializeData() {
        tips.clear();
        tips.add(new PowerTip());

        this.tips.get(0).header = NAME;

        if(getPotionLevel() > 0) {
            if(getPotionLevel() == maxPotionLevel) {
                this.tips.get(0).header += "+MAX";
            } else {
                this.tips.get(0).header += "+" + getPotionLevel();
            }
        }

        this.tips.get(0).body = DESCRIPTIONS[0] + getPotency() + DESCRIPTIONS[1] + maxPotionLevel;


        this.name = this.tips.get(0).header;
        this.description = this.tips.get(0).body;
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, getPotency()), AbstractGameAction.AttackEffect.LIGHTNING));
        }
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascension) {
        return firstInt + secondInt;
    }

    @Override
    public boolean upgradePotion()
    {
        if(canUpgradePotion()) {
            potionLevel += 1;
            while(potionLevel >= (secondInt + firstInt)) {
                int temp = secondInt;
                secondInt = secondInt + firstInt;
                firstInt = temp;
            }
            initializeData();
            return true;
        }
        return false;
    }

    @Override
    public int getPotionLevel() {
        return potionLevel;
    }

    @Override
    public AbstractPotion makeCopy() {
        return UpgradablePotionFactory.makeUpgradablePotionFromSimpleClassName(this.getClass().getSimpleName(), getPotionLevel());
    }

    @Override
    public boolean canUpgradePotion() {
        return getPotionLevel() < maxPotionLevel;
    }
}
