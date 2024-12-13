package com.ayman.plugins.MoreAdvancedAPI;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpawnHologramCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            String[] lines = new String[]{
                    ChatColor.RED + "This is",
                    ChatColor.GREEN + "a",
                    ChatColor.WHITE + "hologram!",
            };


            Location location = player.getLocation();
            for (String line : lines) {
                ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(location.subtract(0, 0.3, 0), EntityType.ARMOR_STAND);
                stand.setInvisible(true);
                stand.setGravity(false);
                stand.setInvulnerable(true);

                stand.setCustomNameVisible(true);
                stand.setCustomName(line);
            }


        }

        return false;
    }
}
