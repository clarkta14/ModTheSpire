package testMod.potions;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.SmokeBomb;

public class SmokeBombUpgradable extends SmokeBomb implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(SmokeBombUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString(SmokeBomb.POTION_ID);

    public static final String NAME = oldPotionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 25;

    public SmokeBombUpgradable() {
        super();
        initializeData();
    }

    @Override
    public void initializeData() {
        // SmokeBomb initializeData was not implemented for some reason. So it is done from scratch here.
        this.tips.clear();
        this.tips.add(new PowerTip(NAME, oldPotionStrings.DESCRIPTIONS[0]));

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

        if (getPotionLevel() >= maxPotionLevel) {
            AbstractDungeon.actionManager.addToBottom(new ObtainPotionAction(
                    UpgradablePotionFactory.makeUpgradablePotionFromSimpleClassName(SmokeGrenadeUpgradable.class.getSimpleName())));
        }
    }

    //TODO: add a degradePotion method for all potions. That way you can
    //have -3 potions that don't work until you upgrade them to 0 at least.
    //cards can give those sorts of potions as well.
    //TODO: add a Cauldron variant that upgrades your least upgraded potion that isn't maxed.

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