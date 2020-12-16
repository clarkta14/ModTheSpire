package testMod.potions;

public interface UpgradablePotion {
    public boolean upgradePotion();
    public boolean canUpgradePotion();
    public int getPotionLevel();
    public void updatePowerTip();
}
