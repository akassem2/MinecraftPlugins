package com.ayman.project.private_message_system;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//  /message <player> <message>

public class MessageCommand implements CommandExecutor {

    private com.ayman.project.private_message_system.PluginTesting pluginTesting;

    public MessageCommand(PluginTesting pluginTesting) {
        this.pluginTesting = pluginTesting;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }

                    player.sendMessage(ChatColor.YELLOW + "You -> " + ChatColor.BLUE + target.getName() + ": " + ChatColor.WHITE + builder);
                    target.sendMessage(ChatColor.BLUE + player.getName() + ChatColor.YELLOW + " -> You: " + ChatColor.WHITE + builder);

                    pluginTesting.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.RED + "This player was not found!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Use /message <player> <message>.");
            }
        }

        return false;
    }
}
