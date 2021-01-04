package testMod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import testMod.DefaultMod;

public class SwiftPotionMaxPower extends AbstractPower {

    public static final String POWER_ID = DefaultMod.makeID(SwiftPotionMaxPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;;

    public SwiftPotionMaxPower(AbstractCreature owner, int drawAmount) {
        this.name = NAME;
        this.ID = "SwiftPotionMaxPower";

        this.owner = owner;
        this.amount = drawAmount;
        this.updateDescription();
        this.loadRegion("carddraw");
        this.priority = 20;
    }

    public void updateDescription() {
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }

    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        this.addToBot(new DrawCardAction(this.owner, this.amount));
    }
}
