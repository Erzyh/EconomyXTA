package org.erze.xta_economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EcoCommandExecutor implements CommandExecutor {

    private final EconomyManager economyManager;

    public EcoCommandExecutor(EconomyManager economyManager) {
        this.economyManager = economyManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Command Helper: /eco <add|sub|set|reset> <player> <amount>");
            return true;
        }

        String action = args[0];
        String playerName = args[1];
        Player target = Bukkit.getPlayerExact(playerName);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        double amount;
        try {
            amount = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Invalid amount.");
            return true;
        }

        switch (action.toLowerCase()) {
            case "add":
                if (!sender.hasPermission("erze.eco.add")) {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                    return true;
                }
                economyManager.addBalance(target, amount);
                sender.sendMessage(ChatColor.GREEN + "Added " + amount + " to " + target.getName() + "'s balance.");
                break;
            case "sub":
                if (!sender.hasPermission("erze.eco.sub")) {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                    return true;
                }
                economyManager.subtractBalance(target, amount);
                sender.sendMessage(ChatColor.GREEN + "Subtracted " + amount + " from " + target.getName() + "'s balance.");
                break;
            case "set":
                if (!sender.hasPermission("erze.eco.set")) {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                    return true;
                }
                economyManager.setBalance(target, amount);
                sender.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s balance to " + amount + ".");
                break;
            case "reset":
                if (!sender.hasPermission("erze.eco.reset")) {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                    return true;
                }
                economyManager.resetBalance(target);
                sender.sendMessage(ChatColor.GREEN + "Reset " + target.getName() + "'s balance.");
                break;
            default:
                sender.sendMessage(ChatColor.RED + "Usage: /eco <add|sub|set|reset> <player> <amount>");
        }
        return true;
    }
}
