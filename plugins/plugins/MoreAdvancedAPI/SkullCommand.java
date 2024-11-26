package com.ayman.plugins.MoreAdvancedAPI;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

//Website to get heads https://freshcoal.com/maincollection
public class SkullCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            // Gives players their own head
//            ItemStack is = new ItemStack(Material.PLAYER_HEAD);
//            SkullMeta meta = (SkullMeta) is.getItemMeta();
//            meta.setOwningPlayer(player);
//            is.setItemMeta(meta);
//
//            player.getInventory().addItem(is);

            ItemStack is = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) is.getItemMeta();

            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            //Player head value string always start with an ey
            profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjcxZWJjMTFiZGQxNTE0MTBkYTcwZDkzMTI1OWM0ZTk2OTUyOGU2ZjU4ODllOWM0YmIyZGQ3NjNiOWVhZmQifX19"));
            Field field;

            try {
                field = meta.getClass().getDeclaredField("profile"); //getting the game profile
                field.setAccessible(true);
                field.set(meta, profile);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }

            is.setItemMeta(meta);
            player.getInventory().addItem(is);

        }

        return false;
    }
}
