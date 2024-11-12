package com.ayman.plugins.MoreAdvancedAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.BLUE.toString() + ChatColor.BOLD + "Tool Menu")
            && e.getCurrentItem() != null) { //The second check makes sure they're clicking on an item
            //Check what item the user picks by checking what slot they picked

            e.setCancelled(true); //Doesn't let the click go through when a player clicks on something

            Player player = (Player) e.getWhoClicked();
            switch (e.getRawSlot()) {
                case 0: //Close
                    break;
                case 20: //Teleport
                    //Gets a random player
                    Random random = new Random();
                    Player target =  (Player) Bukkit.getOnlinePlayers().toArray()[random.nextInt(Bukkit.getOnlinePlayers().size())];
                    player.teleport(target);
                    player.sendMessage(ChatColor.RED + "You teleported to " + target.getName() + "!");
                    break;
                case 22: //Kill Yourself
                    player.setHealth(0);
                    player.sendMessage(ChatColor.RED + "You killed yourself!");
                    break;
                case 24: //Clear Inventory
                    player.closeInventory();
                    player.getInventory().clear();
                    player.sendMessage(ChatColor.RED + "You cleared your inventory!");
                    return; //Already closing inventory because we don't want to clear the GUI as well
                default:
                    return;
            }


            player.closeInventory();
        }
    }
}
