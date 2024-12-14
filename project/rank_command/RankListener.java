package com.ayman.project.rank_command;

import com.ayman.test.PluginTesting;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

public class RankListener implements Listener {

    private PluginTesting pluginTesting;

    public RankListener(PluginTesting pluginTesting) {
        this.pluginTesting = pluginTesting;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        if (!player.hasPlayedBefore()) { //If player has never played before, sets their rank
            pluginTesting.getRankManager().setRank(player.getUniqueId(), Rank.GUEST, true);
        }

        //Must do this after setting initial rank to guest or it will cause an error
        pluginTesting.getNametagManager().setNametags(player);
        pluginTesting.getNametagManager().newTag(player);


        PermissionAttachment attachment;
        if (pluginTesting.getRankManager().getPerms().containsKey(player.getUniqueId())) { //If they player is in the hasmap
            attachment = pluginTesting.getRankManager().getPerms().get(player.getUniqueId());
        } else {
            attachment = player.addAttachment(pluginTesting);
            pluginTesting.getRankManager().getPerms().put(player.getUniqueId(), attachment);
        }

        //Add all of the permissions of current rank
        for (String perm : pluginTesting.getRankManager().getRank(player.getUniqueId()).getPermissions()) {
            attachment.setPermission(perm, true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        pluginTesting.getNametagManager().removeTag(e.getPlayer());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) { //When anyone types in chat

        e.setCancelled(true);

        Player player = e.getPlayer();

        //Will showcase the player's rank in chat every time they message
        Bukkit.broadcastMessage(pluginTesting.getRankManager().getRank(player.getUniqueId()).getDisplay() + " " + ChatColor.WHITE + player.getName() + ": " + ChatColor.GRAY + e.getMessage());

    }
}
