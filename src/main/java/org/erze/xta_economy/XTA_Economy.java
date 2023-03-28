package org.erze.xta_economy;

import org.bukkit.plugin.java.JavaPlugin;

public final class XTA_Economy extends JavaPlugin {

    private EconomyManager economyManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        economyManager = new EconomyManager(configManager);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(economyManager, configManager), this);

        getCommand("eco").setExecutor(new EcoCommandExecutor(economyManager));
        getCommand("money").setExecutor(new MoneyCommandExecutor(economyManager));
        getCommand("wallet").setExecutor(new MoneyCommandExecutor(economyManager));
    }

    @Override
    public void onDisable() {
        configManager.savePlayerData(economyManager.getPlayerBalances());
    }
}