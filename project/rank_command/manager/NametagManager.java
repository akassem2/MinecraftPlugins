package com.ayman.project.rank_command.manager;

import com.ayman.test.PluginTesting;
import com.ayman.project.rank_command.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagManager {

    private PluginTesting pluginTesting;

    public NametagManager(PluginTesting pluginTesting) {
        this.pluginTesting = pluginTesting;
    }

    public void setNametags(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard()); //Creates new scoreboard for player

        for (Rank rank : Rank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.name());
            team.setPrefix(rank.getDisplay() + " ");
        }

        for (Player target : Bukkit.getOnlinePlayers()) { //Finds out the rank of other players so the player can see them
            if (player.getUniqueId() != target.getUniqueId()) { //So the player isn't added twice
                player.getScoreboard().getTeam(pluginTesting.getRankManager().getRank(target.getUniqueId()).name()).addEntry(target.getName());
            }
        }
    }

    public void newTag(Player player) { //Putting the player in everyone elses scoreboards

        Rank rank = pluginTesting.getRankManager().getRank(player.getUniqueId());
        for (Player target : Bukkit.getOnlinePlayers()) {

            target.getScoreboard().getTeam(rank.name()).addEntry(player.getName()); //Adds the player to everyones team
        }
    }

    public void removeTag(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }
}
