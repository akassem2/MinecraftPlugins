package com.ayman.plugins.MoreAdvancedAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            Inventory inv = Bukkit.createInventory(player, 45, ChatColor.BLUE.toString() + ChatColor.BOLD + "Tool Menu"); //45 slots big, meaning there's 45 / 9 = 5 rows and 9 col

            // Creating the meta information for the ender pearl in the GUI
            // TELEPORT
            ItemStack teleport = new ItemStack(Material.ENDER_PEARL);
            ItemMeta teleportMeta = teleport.getItemMeta();
            teleportMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Teleport");
            teleportMeta.setLore(Arrays.asList(ChatColor.GRAY + "Teleport to random player!"));
            teleport.setItemMeta(teleportMeta);

            // The Top Left spot starts at 0, and counts from left to right. Sets slot 20 to the ender pearl
            inv.setItem(20, teleport);


            //KILL YOURSELF
            ItemStack kys = new ItemStack(Material.IRON_SWORD);
            ItemMeta kysMeta = kys.getItemMeta();
            kysMeta.setDisplayName(ChatColor.DARK_RED + "Kill Yourself");
            kysMeta.setLore(Arrays.asList(ChatColor.GRAY + "Kills you."));
            kys.setItemMeta(kysMeta);

            inv.setItem(22, kys);

            //CLEAR INVENTORY
            ItemStack clear = new ItemStack(Material.BUCKET);
            ItemMeta clearMeta = clear.getItemMeta();
            clearMeta.setDisplayName(ChatColor.GOLD + "Clear inventory");
            clearMeta.setLore(Arrays.asList(ChatColor.GRAY + "Clears your inventory."));
            clear.setItemMeta(clearMeta);

            inv.setItem(24, clear);


            //ClOSE BUTTON
            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta closeMeta = close.getItemMeta();
            closeMeta.setDisplayName(ChatColor.RED + "Close!");
            close.setItemMeta(closeMeta);

            inv.setItem(0, close);

            //FRAME
            ItemStack frame = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
            ItemMeta frameMeta = frame.getItemMeta();
            frameMeta.setDisplayName(" ");
            frame.setItemMeta(frameMeta);

            //Will set the item in each of those slots to the frame
            for (int i : new int[]{1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44}) {
                inv.setItem(i, frame);
            }

            player.openInventory(inv);

        }

        return false;
    }
}
