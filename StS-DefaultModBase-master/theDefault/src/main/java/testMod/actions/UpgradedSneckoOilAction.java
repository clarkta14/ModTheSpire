package testMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

public class UpgradedSneckoOilAction extends AbstractGameAction {
    private AbstractPlayer p;

    public UpgradedSneckoOilAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    // basically the same as RandomizeHandCostAction, but it is maxed at 2 instead of 3.
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            Iterator var1 = this.p.hand.group.iterator();

            while (var1.hasNext()) {
                AbstractCard card = (AbstractCard) var1.next();
                if (card.cost >= 0) {
                    int newCost = AbstractDungeon.cardRandomRng.random(2);
                    if (card.cost != newCost) {
                        card.cost = newCost;
                        card.costForTurn = card.cost;
                        card.isCostModified = true;
                    }
                }
            }

            this.isDone = true;
        } else {
            this.tickDuration();
        }

    }
}
