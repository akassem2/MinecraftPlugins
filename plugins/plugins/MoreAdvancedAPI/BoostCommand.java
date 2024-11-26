package com.ayman.plugins.MoreAdvancedAPI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

//https://minecraft.fandom.com/wiki/Attribute Attributes wiki
public class BoostCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                AttributeModifier modifier = new AttributeModifier("generic.attack_damage", 100, AttributeModifier.Operation.ADD_NUMBER);
                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
                item.setItemMeta(meta);
                player.sendMessage(ChatColor.GOLD + "Your item has been blessed with 100 attack damage!");

                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
                player.sendMessage(ChatColor.RED + "Your health has been doubled!");

            }
        }

        return false;
    }
}
