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
import com.megacrit.cardcrawl.relics.SacredBark;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import basemod.abstracts.CustomPotion;

public class BottledLightning extends CustomPotion implements UpgradablePotion{

    public static final String POTION_ID = testMod.DefaultMod.makeID(BottledLightning.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);

    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public BottledLightning() {
        // The bottle shape and inside is determined by potion size and color. The actual colors are the main DefaultMod.java
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.M, PotionColor.SMOKE);

        // Potency is the damage/magic number equivalent of potions.
        potency = getPotency();

        // Do you throw this potion at an enemy or do you just consume it.
        isThrown = false;

        // Initialize the on-hover name + description
        description = DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];
        tips.add(new PowerTip(name, description));
    }

    @Override
    public void use(AbstractCreature target) {
        target = AbstractDungeon.player;
        // If you are in combat, gain strength and the "lose strength at the end of your turn" power, equal to the potency of this potion.
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            AbstractDungeon.actionManager.addToBottom( // 2.Damage all enemies
                    new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, potency), AbstractGameAction.AttackEffect.LIGHTNING));
//                    new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(evokeAmount, true, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
            // The damage matrix is how orb damage all enemies actions have to be assigned. For regular cards that do damage to everyone, check out cleave or whirlwind - they are a bit simpler.

        }
    }

    @Override
    public AbstractPotion makeCopy() {
        return new BottledLightning();
    }

    // This is your potency.
    @Override
    public int getPotency(final int potency) {
        return 20;
    }

    @Override
    public boolean upgradePotion()
    {
        if(canUpgradePotion()) {
            potency += 2;
            updatePowerTip();
            return true;
        }
        return false;
    }

    private void updatePowerTip() {
        description = "#pTestMod NL " + DESCRIPTIONS[0] + potency + DESCRIPTIONS[1];

        //upgrade level updates before the name.
        if(getPotionLevel() == 1)
            name = name + "+" + getPotionLevel();
        else
            name = name.split("\\+")[0] + "+" + getPotionLevel();

        tips.clear();
        tips.add(new PowerTip(name, description));
    }

    @Override
    public int getPotionLevel() {
        return (potency - 20) / 2;
    }

    @Override
    public boolean canUpgradePotion() {
        return getPotionLevel() < 10;
    }
}
