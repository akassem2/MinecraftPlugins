package com.ayman.plugins.HeartOfSpigotAPI;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.List;

public class BannerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            ItemStack item = new ItemStack(Material.WHITE_BANNER);
            BannerMeta meta = (BannerMeta) item.getItemMeta();

            List<Pattern> patterns = new ArrayList<>();
            patterns.add(new Pattern(DyeColor.RED, PatternType.PIGLIN));
            patterns.add(new Pattern(DyeColor.YELLOW, PatternType.BORDER));

            meta.setPatterns(patterns);
            item.setItemMeta(meta);

            player.getInventory().addItem(item);
        }

        return false;
    }
}
