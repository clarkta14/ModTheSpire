package testMod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import testMod.DefaultMod;
import testMod.potions.UpgradablePotion;
import testMod.util.TextureLoader;

import java.util.ArrayList;
import java.util.Random;

import static testMod.DefaultMod.makePowerPath;

//Gain 1 dex for the turn for each card played.

public class CauldronPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = DefaultMod.makeID(CauldronPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public CauldronPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }


    @Override
    public void atStartOfTurn() { // At the start of your turn
        ArrayList<AbstractPotion> affectedPotions = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            ArrayList<UpgradablePotion> potions = getUpgradablePotionsOnPlayer();

            if (potions.size() == 0) {
                logger.info("Cauldron trigger: no upgradable potions.");
                break;
            }

            Random random = new Random();
            UpgradablePotion selectedPotion = potions.get(random.nextInt(potions.size()));
            selectedPotion.upgradePotion();
            affectedPotions.add((AbstractPotion) selectedPotion);
            logger.info("Cauldron trigger: upgraded " + selectedPotion.getClass().getSimpleName());
        }

        for (AbstractPotion potionToFlash: affectedPotions) {
            potionToFlash.flash();
        }

        //TODO: Cauldron should apply at the end of combat as well.
        //TODO: perhaps this only activates if you deal damage this turn. That would prevent the desire to sit and not attack an easy enemy.
        //TODO: perhaps there should be a relic that determines how much cauldron
        //you can apply. Like max 5. upgrade to max 10 and so on.
        //TODO: perhaps there should be a relic that allows you to point where the
        //Cauldron goes. The drawback is that if you have more cauldron than the max,
        //then it is wasted unlike with random where all Cauldron is applied.
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }

    public ArrayList<UpgradablePotion> getUpgradablePotionsOnPlayer() {
        ArrayList<AbstractPotion> potions = AbstractDungeon.player.potions;

        ArrayList<UpgradablePotion> toReturn = new ArrayList<>();
        for(AbstractPotion potion: potions) {
            if (potion instanceof UpgradablePotion) {
                logger.info("Cauldron trigger: " + potion.name + " is an UpgradablePotion");
                UpgradablePotion bl = (UpgradablePotion) potion;
                if(bl.canUpgradePotion()) {
                    toReturn.add(bl);
                } else {
                    logger.info("Cauldron trigger: " + potion.name + " Cannot be upgraded further");
                }
            } else {
                logger.info("Cauldron trigger: " + potion.getClass().getSimpleName() + " is not an UpgradablePotion");
            }
        }

        return toReturn;
    }


    // Update the description when you apply this power. (i.e. add or remove an "s" in keyword(s))
    @Override
    public void updateDescription() {
        if(amount == 1) {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3] + DESCRIPTIONS[1];
        }
    }


    @Override
    public AbstractPower makeCopy() {
        return new CauldronPower(owner, source, amount);
    }
}
