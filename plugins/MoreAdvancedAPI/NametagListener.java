package com.ayman.plugins.MoreAdvancedAPI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NametagListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        NametagManager.setNametags(e.getPlayer());
        NametagManager.newTag(e.getPlayer());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        NametagManager.removeTag(e.getPlayer());

    }

}
