package com.ayman.project.private_message_system;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

//  /reply <message>

public class ReplyCommand implements CommandExecutor {

    private com.ayman.project.private_message_system.PluginTesting pluginTesting;

    public ReplyCommand(PluginTesting pluginTesting) {
        this.pluginTesting = pluginTesting;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (args.length >= 1) {
                if (pluginTesting.getRecentMessages().containsKey(player.getUniqueId())) {
                    UUID uuid = pluginTesting.getRecentMessages().get(player.getUniqueId());
                    if (Bukkit.getPlayer(uuid) != null) { // Online
                        Player target = Bukkit.getPlayer(uuid);

                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }

                        player.sendMessage(ChatColor.YELLOW + "You -> " + ChatColor.BLUE + target.getName() + ": " + ChatColor.WHITE + builder);
                        target.sendMessage(ChatColor.BLUE + player.getName() + ChatColor.YELLOW + " -> You: " + ChatColor.WHITE + builder);
                    } else { // Offline
                        player.sendMessage(ChatColor.RED + "The person you messaged has gone offline!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You haven't messaged anyone yet!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Use /reply <message>.");
            }
        }

        return false;
    }

}
