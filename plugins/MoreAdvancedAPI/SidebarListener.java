package com.ayman.plugins.MoreAdvancedAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;


public class SidebarListener implements Listener {

    private HashMap<UUID, Integer> mobsSlain = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("testboard", "dummy"); //always use dummy, it's tradition
        obj.setDisplaySlot(DisplaySlot.SIDEBAR); //Using the sidebar part of the scoreboard
        obj.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Test Board!"); //Title of scoreboard, the two chat colors add two characters to the name size

        //Every line of the score board have to be unique! Else it will give a null pointer exception
        Score website = obj.getScore(ChatColor.YELLOW + "www.absoluteKILLER.com"); //Will stay static even in a dynamic board
        website.setScore(1); //Goes up to 15 lines

        Score space = obj.getScore(" "); //Add another space to make it unique, like "  ", "    ", or use a color code with the empty string
        space.setScore(2);

        //Dynamic scoreboard onward
        Team mobsSlain = board.registerNewTeam("mobsSlain");
        mobsSlain.addEntry(ChatColor.BOLD.toString());
        //Prefix: what goes before a value,   Suffix: what goes after a value
        // Mobs Slain: 5
        // Mobs Slain: = Prefix
        // 5 = Suffix

        mobsSlain.setPrefix(ChatColor.BLUE + "Mobs slain: ");
        mobsSlain.setSuffix(ChatColor.YELLOW + "0");
        obj.getScore(ChatColor.BOLD.toString()).setScore(3); //Will be at line 3


        player.setScoreboard(board);

        this.mobsSlain.put(player.getUniqueId(), 0);

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {

        if (e.getEntity().getKiller() instanceof Player) {
            Player player = e.getEntity().getKiller();

            //Counter for the player
            int amount = mobsSlain.get(player.getUniqueId());
            amount++;

            mobsSlain.put(player.getUniqueId(), amount);
            //To override new value onto scoreboard
            player.getScoreboard().getTeam("mobsSlain").setSuffix(ChatColor.YELLOW.toString() + amount); //Use the team name made earlier
        }
    }

}
