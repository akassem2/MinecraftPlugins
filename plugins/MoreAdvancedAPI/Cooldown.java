package com.ayman.plugins.MoreAdvancedAPI;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Cooldown implements CommandExecutor {

    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (!cooldown.asMap().containsKey(player.getUniqueId())) {
                player.sendMessage(ChatColor.GREEN + "Test worked!");
                cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 5000); // adds player to the cooldown cache with a 5 second cooldown
            } else {
                long distance = cooldown.asMap().get(player.getUniqueId()) - System.currentTimeMillis(); //Calculates time left of cooldown
                player.sendMessage(ChatColor.RED + "You must wait " + TimeUnit.MILLISECONDS.toSeconds(distance) + " to use this again.");
            }
        }

        return false;
    }
}
