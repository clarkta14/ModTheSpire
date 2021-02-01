package testMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import testMod.DefaultMod;
import testMod.util.TextureLoader;

import static testMod.DefaultMod.makeRelicOutlinePath;
import static testMod.DefaultMod.makeRelicPath;

public class CopyCatalyst extends CustomRelic implements ClickableRelic { // You must implement things you want to use from StSlib
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * StSLib for Clickable Relics
     *
     * Usable once per turn. Right click: Evoke your rightmost orb.
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID(CopyCatalyst.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("default_clickable_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("default_clickable_relic.png"));

    private boolean usedThisCombat = false; // You can also have a relic be only usable once per combat. Check out Hubris for more examples, including other StSlib things.
    private boolean isPlayerTurn = false; // We should make sure the relic is only activateable during our turn, not the enemies'.

    private static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());

    public CopyCatalyst() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);

        tips.clear();
        tips.add(new PowerTip(name, description));
    }


    @Override
    public void onRightClick() {// On right click
        AbstractPlayer player = AbstractDungeon.player;
        if (!isObtained || usedThisCombat || !isPlayerTurn) {
            // If it has been used this turn, the player doesn't actually have the relic (i.e. it's on display in the shop room), or it's the enemy's turn
            return; // Don't do anything.
        }

        logger.info("CopyCatalyst> player.potionSlots: " + player.potionSlots + " player.potions.size(): " + player.potions.size());

        if (!player.hasPotion(PotionSlot.POTION_ID)) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "Potion belt full", 2.0f, 2.0f));
            return;
        }

        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) { // Only if you're in combat
            AbstractPotion potionToCopy = player.getRandomPotion();

            if(potionToCopy != null) {
                AbstractDungeon.actionManager.addToBottom(new ObtainPotionAction(potionToCopy.makeCopy()));
                usedThisCombat = true; // Set relic as "Used this combat"
                flash(); // Flash
                stopPulse(); // And stop the pulsing animation (which is started in atPreBattle() below)
            } else
                AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "No potion to Copy", 4.0f, 2.0f));
        }
    }

    @Override
    public void atPreBattle() {
        usedThisCombat = false; // Make sure usedThisTurn is set to false at the start of each combat.
        beginLongPulse();     // Pulse while the player can click on it.
    }

    public void atTurnStart() {
//        usedThisTurn = false;  // Resets the used this turn. You can remove this to use a relic only once per combat rather than per turn.
        isPlayerTurn = true; // It's our turn!
        if(!usedThisCombat) {
            beginLongPulse(); // Pulse while the player can click on it.
        }
    }

    @Override
    public void onPlayerEndTurn() {
        isPlayerTurn = false; // Not our turn now.
        stopPulse();
    }


    @Override
    public void onVictory() {
        stopPulse(); // Don't keep pulsing past the victory screen/outside of combat.
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
