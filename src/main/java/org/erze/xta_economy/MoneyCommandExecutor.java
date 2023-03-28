package org.erze.xta_economy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MoneyCommandExecutor implements CommandExecutor {

    private final EconomyManager economyManager;

    public MoneyCommandExecutor(EconomyManager economyManager) {
        this.economyManager = economyManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;
        double balance = economyManager.getBalance(player);
        player.sendMessage(ChatColor.GREEN + "Your balance: " + ChatColor.GOLD + balance);
        return true;
    }
}