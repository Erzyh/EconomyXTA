package org.erze.xta_economy;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class ConfigManager {

    private final JavaPlugin plugin;
    private FileConfiguration settingsConfig;
    private FileConfiguration playerDataConfig;
    private File playerDataFile;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        createSettingsConfig();
        createPlayerDataConfig();
    }

    private void createSettingsConfig() {
        settingsConfig = plugin.getConfig();
        settingsConfig.addDefault("DefaultMoney", 1000);
        settingsConfig.options().copyDefaults(true);
        plugin.saveConfig();
    }

    private void createPlayerDataConfig() {
        playerDataFile = new File(plugin.getDataFolder(), "playerData.yml");
        if (!playerDataFile.exists()) {
            try {
                playerDataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        playerDataConfig = YamlConfiguration.loadConfiguration(playerDataFile);
    }

    public void savePlayerData(HashMap<UUID, Double> playerBalances) {
        playerBalances.forEach((uuid, balance) -> playerDataConfig.set(uuid.toString(), balance));
        try {
            playerDataConfig.save(playerDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getSettingsConfig() {
        return settingsConfig;
    }

    public FileConfiguration getPlayerDataConfig() {
        return playerDataConfig;
    }
}