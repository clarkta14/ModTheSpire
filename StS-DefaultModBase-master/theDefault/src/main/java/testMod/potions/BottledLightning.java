package testMod.potions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.Ambrosia;
import com.megacrit.cardcrawl.relics.SacredBark;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.abstracts.CustomPotion;

public class BottledLightning extends CustomPotion implements UpgradablePotion{

    public static final String POTION_ID = testMod.DefaultMod.makeID(BottledLightning.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 10;

    public BottledLightning() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main DefaultMod.java
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionColor.SMOKE);

        // Do you throw this potion at an enemy or do you just consume it.
        isThrown = false;

        // Initialize the on-hover name + description
        description = DESCRIPTIONS[0] + getPotency() + DESCRIPTIONS[1] + maxPotionLevel;
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature target) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, getPotency()), AbstractGameAction.AttackEffect.LIGHTNING));
        }
    }

    @Override
    public AbstractPotion makeCopy() {
        return new BottledLightning();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 20 + (2 * getPotionLevel());
    }

    @Override
    public boolean upgradePotion()
    {
        if(canUpgradePotion()) {
            potionLevel += 1;
            updatePowerTip();
            return true;
        }
        return false;
    }

    @Override
    public void updatePowerTip() {
        description = "#pTestMod NL " + DESCRIPTIONS[0] + getPotency() + DESCRIPTIONS[1] + maxPotionLevel;

        //upgrade level updates before the name.
        //TODO: Use a contains +, in case in the future you can upgrade multiple times at once.
        if(getPotionLevel() == 1)
            name = name + "+" + getPotionLevel();
        else
            name = name.split("\\+")[0] + "+" + getPotionLevel();

        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public int getPotionLevel() {
        return potionLevel;
    }

    @Override
    public boolean canUpgradePotion() {
        return getPotionLevel() < maxPotionLevel;
    }
}
