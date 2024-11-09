package com.ayman.plugins;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BookCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta meta = (BookMeta) book.getItemMeta();
            meta.setTitle(ChatColor.RED + "My Epic Book!");
            meta.setAuthor(ChatColor.YELLOW + "Zen");
            meta.addPage(ChatColor.BLUE + "Welcome to the world." +
                    ChatColor.GREEN + "\nFreedom is here." +
                    "\nBut this is the point of no return..." +
                    ChatColor.BLACK + "\nYou cannot go back.");
            book.setItemMeta(meta);

            player.getInventory().addItem(book);
        }

        return false;
    }
}
