package testMod.potions;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AttackPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;
import testMod.powers.CauldronPower;

public class AttackPotionUpgradable extends AttackPotion implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(AttackPotionUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString("Attack Potion");

    public static final String NAME = oldPotionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 10;

    public AttackPotionUpgradable() {
        super();
    }

    @Override
    public void initializeData() {
        // this.name is not reverted by initializeData after an upgrade has been applied.
        this.name = "Attack Potion";
        super.initializeData();

        // initializeData clears tips THEN adds the main PowerTip THEN the Stance PowerTip.
        this.tips.get(0).body += (potionStrings.DESCRIPTIONS[0] + maxPotionLevel);
    }

    @Override
    public void use(AbstractCreature target) {
        AbstractCreature p = AbstractDungeon.player;

        // Regular use if potion is not maxed.
        // Regular use if player already has MaterRealityPower since cards will
        // already be upgraded.
        if (getPotionLevel() < maxPotionLevel || p.hasPower("MasterRealityPower")) {
            super.use(target);
        } else {
            //TODO: this works, but it seems jank. Namely, it doesn't show the cards as upgraded.
            AbstractPower MRP = new MasterRealityPower(p);
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(p, p, MRP));

            super.use(target);

            AbstractDungeon.actionManager.addToBottom(
                    new RemoveSpecificPowerAction(p, p, MRP));
        }
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
        if(getPotionLevel() < maxPotionLevel) {
            return true;
        }
        return false;
    }

    // This is your potency.
    @Override
    public int getPotency(final int ascension) {
        return 1;
    }

    @Override
    public int getPotionLevel() {
        return potionLevel;
    }

    //TODO: Remove this method and put it into initializeData. Or else
    //Sacredbark will not work correctly.
    @Override
    public void updatePowerTip() {
        initializeData();

        if(getPotionLevel() > 0) {
            this.tips.get(0).header += "+" + getPotionLevel();
        }

        //TODO: Perhaps add MAX to the name when it is maxed out

        //the base game does this upon initializeData()... but I do not know where.
        //this.tips.get(0).body = "#pTestMod NL " + this.tips.get(0).body;

        this.name = this.tips.get(0).header;
        this.description = this.tips.get(0).body;
    }
}
