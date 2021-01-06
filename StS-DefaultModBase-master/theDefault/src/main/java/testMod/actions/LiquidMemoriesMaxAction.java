package testMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PotionStrings;
import testMod.potions.LiquidMemoriesUpgradable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class LiquidMemoriesMaxAction extends AbstractGameAction {
    private final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(LiquidMemoriesUpgradable.POTION_ID);
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;
    private boolean optional;
    private int newCost;
    private boolean setCost;

    /**
     * Basically the same as a BetterDiscardPileToHandAction, except it upgrades the choices from the discard
     * pile for the current combat. It also has less options in its constructors, making it less verstile than
     * BetterDiscardPileToHandAction, but work specifically for this max potion.
     * @param numberOfCards Number of cards moving from discard pile to hand. Same as LiquidMemories current potency.
     */
    public LiquidMemoriesMaxAction(int numberOfCards) {
        this.newCost = 0;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.optional = false;
        this.setCost = true;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            if (!this.player.discardPile.isEmpty() && this.numberOfCards > 0) {
                if (this.player.discardPile.size() <= this.numberOfCards) {
                    ArrayList<AbstractCard> cardsToMove = new ArrayList();
                    Iterator var5 = this.player.discardPile.group.iterator();

                    AbstractCard c;
                    while(var5.hasNext()) {
                        c = (AbstractCard)var5.next();
                        cardsToMove.add(c);
                    }

                    var5 = cardsToMove.iterator();

                    while(var5.hasNext()) {
                        c = (AbstractCard)var5.next();
                        if (this.player.hand.size() < 10) {
                            this.player.hand.addToHand(c);
                            if (this.setCost) {
                                c.setCostForTurn(this.newCost);
                                if(c.canUpgrade())
                                    c.upgrade(); // apparently this only upgrades for this combat... perhaps that is due
                            }                    // to the fact that it came from discard pile and not player.masterDeck

                            this.player.discardPile.removeCard(c);
                        }

                        c.lighten(false);
                        c.applyPowers();
                    }

                    this.isDone = true;
                } else {
                    if (this.numberOfCards == 1)
                        AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards,
                                TEXT[0] + potionStrings.DESCRIPTIONS[1], false);
                    else
                        AbstractDungeon.gridSelectScreen.open(this.player.discardPile, this.numberOfCards,
                                TEXT[1] + this.numberOfCards + TEXT[2] + potionStrings.DESCRIPTIONS[1], false);

                    this.tickDuration();
                }
            } else {
                this.isDone = true;
            }
        } else {
            Iterator var1;
            AbstractCard c;
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                var1 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    if (this.player.hand.size() < 10) {
                        this.player.hand.addToHand(c);
                        if (this.setCost) {
                            c.setCostForTurn(this.newCost);
                            if(c.canUpgrade())
                                c.upgrade();
                        }

                        this.player.discardPile.removeCard(c);
                    }

                    c.lighten(false);
                    c.unhover();
                    c.applyPowers();
                }

                for(var1 = this.player.discardPile.group.iterator(); var1.hasNext(); c.target_y = 0.0F) {
                    c = (AbstractCard)var1.next();
                    c.unhover();
                    c.target_x = (float) CardGroup.DISCARD_PILE_X;
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }

            this.tickDuration();
            if (this.isDone) {
                var1 = this.player.hand.group.iterator();

                while(var1.hasNext()) {
                    c = (AbstractCard)var1.next();
                    c.applyPowers();
                }
            }

        }
    }

    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;
    }
}

