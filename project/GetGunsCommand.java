package com.ayman.project;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GetGunsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            ItemStack diamondHoe = new ItemStack(Material.DIAMOND_HOE);
            ItemMeta diamondHoeMeta = (ItemMeta) diamondHoe.getItemMeta();
            diamondHoeMeta.setDisplayName("Snowball Launcher");
            diamondHoe.setItemMeta(diamondHoeMeta);
            player.getInventory().addItem(diamondHoe);

            ItemStack ironHoe = new ItemStack(Material.IRON_HOE);
            ItemMeta ironHoeMeta = (ItemMeta) ironHoe.getItemMeta();
            ironHoeMeta .setDisplayName("Egg Launcher");
            ironHoe.setItemMeta(ironHoeMeta);
            player.getInventory().addItem(ironHoe);

            ItemStack netheriteHoe = new ItemStack(Material.NETHERITE_HOE);
            ItemMeta netheriteHoeMeta = (ItemMeta) netheriteHoe.getItemMeta();
            netheriteHoeMeta .setDisplayName("Egg Launcher");
            netheriteHoe.setItemMeta(netheriteHoeMeta);
            player.getInventory().addItem(netheriteHoe);

            player.sendMessage(ChatColor.GOLD + "You have gotten your guns!");

        }

        return false;
    }
}

//In main method    | In .yml   gungame:
//@EventHandler
//public void onPlayerInteract(PlayerInteractEvent e) {
//    //hasItem() ensures they have an item in there hand
//    if (e.hasItem()) {
//        Player player = e.getPlayer();
//        if (e.getItem().getType().equals(Material.DIAMOND_HOE)) {
//            player.launchProjectile(Snowball.class);
//            player.sendMessage(ChatColor.GREEN + "You have shot the snowball!");
//        } else if (e.getItem().getType().equals(Material.IRON_HOE)) {
//            player.launchProjectile(Egg.class);
//            player.sendMessage(ChatColor.GREEN + "You have shot the egg!");
//        } else if (e.getItem().getType().equals(Material.NETHERITE_HOE)) {
//            player.launchProjectile(Trident.class);
//            player.sendMessage(ChatColor.GREEN + "You have shot the trident!");
//        }
//    }
//}