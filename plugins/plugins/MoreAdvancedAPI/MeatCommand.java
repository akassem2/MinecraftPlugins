package com.ayman.plugins.MoreAdvancedAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

//IS FOR THE COMMAND TAB COMPLETE LESSON
public class MeatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
                if (args[1] != null) {
                    Player target = Bukkit.getPlayer(args[1]);

                    switch (args[0]) {
                        case "Beef":
                            ItemStack beef = new ItemStack(Material.COOKED_BEEF);
                            target.getInventory().addItem(beef);
                            target.sendMessage("You got beef!");
                            break;
                        case "Chicken":
                            ItemStack chicken = new ItemStack(Material.COOKED_CHICKEN);
                            target.getInventory().addItem(chicken);
                            target.sendMessage("You got chicken!");
                            break;
                        case "Mutton":
                            ItemStack mutton = new ItemStack(Material.COOKED_MUTTON);
                            target.getInventory().addItem(mutton);
                            target.sendMessage("You got mutton!");
                            break;
                        case "Pork":
                            ItemStack pork = new ItemStack(Material.COOKED_PORKCHOP);
                            target.getInventory().addItem(pork);
                            target.sendMessage("You got pork!");
                            break;
                        default:
                            player.sendMessage(ChatColor.RED + "Please input a proper food item! Usage /meat <food> <player>");
                            return false;
                    }
                }
        }

        return false;
    }
}
