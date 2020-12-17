package testMod.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.BlockPotion;

public class BlockPotionUpgradable extends BlockPotion implements UpgradablePotion{
    public static final String POTION_ID = testMod.DefaultMod.makeID(BlockPotionUpgradable.class.getSimpleName());

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    private static final PotionStrings oldPotionStrings = CardCrawlGame.languagePack.getPotionString("Block Potion");

    public static final String NAME = oldPotionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    private int potionLevel = 0;
    private static int maxPotionLevel = 8;

    public BlockPotionUpgradable() {
        super();
        initializeData();
    }

    public BlockPotionUpgradable(int potionLevel) {
        super();

        int levelToSet = 0;
        if(potionLevel > maxPotionLevel)
            levelToSet = maxPotionLevel;
        else if (potionLevel < 0)
            levelToSet = 0;
        else
            levelToSet = potionLevel;

        while (getPotionLevel() < levelToSet) upgradePotion();

        initializeData();
    }

    @Override
    public void initializeData() {
        // this.name is not reverted by initializeData after an upgrade has been applied.
        this.name = NAME;
        super.initializeData();

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

    // This is your potency.
    @Override
    public int getPotency(final int ascension) {
        return 12 + potionLevel;
    }

    @Override
    public int getPotionLevel() {
        return potionLevel;
    }
}
