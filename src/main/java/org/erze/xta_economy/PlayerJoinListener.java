package org.erze.xta_economy;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final EconomyManager economyManager;
    private final ConfigManager configManager;

    public PlayerJoinListener(EconomyManager economyManager, ConfigManager configManager) {
        this.economyManager = economyManager;
        this.configManager = configManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!economyManager.getPlayerBalances().containsKey(player.getUniqueId())) {
            double defaultMoney = configManager.getSettingsConfig().getDouble("DefaultMoney");
            economyManager.setBalance(player, defaultMoney);
        }
    }
}
