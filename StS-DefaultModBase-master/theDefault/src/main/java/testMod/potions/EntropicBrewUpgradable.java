package testMod.potions;

import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.EntropicBrew;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.ObtainPotionEffect;

public class EntropicBrewUpgradable extends EntropicBrew implements UpgradablePotion {
    public static final String POTION_ID = testMod.DefaultMod.makeID(EntropicBrewUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString("EntropicBrew");

    public static final String NAME = oldPotionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 25;

    public EntropicBrewUpgradable() {
        super();
        initializeData();
    }

    @Override
    public void initializeData() {
        // this.name is not reverted by initializeData after an upgrade has been applied.
        this.name = NAME;

        // initializeData is not overridden in EntropicBrew
        this.potency = this.getPotency();
        this.description = oldPotionStrings.DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));

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
        int i;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for(i = 0; i < AbstractDungeon.player.potionSlots; ++i) {
                this.addToBot(new ObtainPotionAction(
                        UpgradablePotionFactory.makeRandomUpgradablePotion((int) Math.floor(getPotionLevel() / 5))));
            }
        } else if (AbstractDungeon.player.hasRelic("Sozu")) {
            AbstractDungeon.player.getRelic("Sozu").flash();
        } else {
            for(i = 0; i < AbstractDungeon.player.potionSlots; ++i) {
                AbstractDungeon.effectsQueue.add(new ObtainPotionEffect(
                        UpgradablePotionFactory.makeRandomUpgradablePotion((int) Math.floor(getPotionLevel() / 5))));
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
    public int getPotionLevel() {
        return potionLevel;
    }
}
