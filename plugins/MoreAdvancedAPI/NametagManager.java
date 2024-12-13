package com.ayman.plugins.MoreAdvancedAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagManager {

    //Nametags work through scoreboards, and players are assigned to teams

    public static void setNametags(Player player) {    // create scoreboard & teams
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        //For each loop going through enum class, creating all the teams in the scoreboard
        for (Rank rank : Rank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.getOrderSymbol() + rank.name()); //rank.getOrderSymbol() will make the teams sort alphabetically based on orderSymbol
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', rank.getDisplay())); //Translates & symbols to color codes
        }

    }

    public static void newTag(Player player) {    // assign player to their team
        Rank rank = Rank.OWNER;
        for (Player target : Bukkit.getOnlinePlayers()) {
            //Adding them to their respective team based on the player
            target.getScoreboard().getTeam(rank.getOrderSymbol() + rank.name()).addEntry(player.getName());
        }
    }

    public static void removeTag(Player player) {     // remove player form all scoreboards
        for (Player target : Bukkit.getOnlinePlayers()) {
            //looping through everybodys scoreboard, and removing them from the team
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName()); //To stop memory leaks
        }
    }
}
