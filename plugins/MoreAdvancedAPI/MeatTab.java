package com.ayman.plugins.MoreAdvancedAPI;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//IS FOR THE COMMAND TAB COMPLETE LESSON
public class MeatTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> results = new ArrayList<>();

        //Using the stringUtil will let this act like a search function, so it will not show options that don't make sense
        if (args.length == 1) {

            return StringUtil.copyPartialMatches(args[0], Arrays.asList("Beef", "Chicken", "Mutton", "Pork"), new ArrayList<>());
        } else if (args.length == 2) {
            List<String> names = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                names.add(player.getName());
            }
            return StringUtil.copyPartialMatches(args[1], names, new ArrayList<>());
        }

        return new ArrayList<>();
    }
}