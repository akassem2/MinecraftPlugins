package com.ayman.project.rank_command.manager;

import com.ayman.test.PluginTesting;
import com.ayman.project.rank_command.Rank;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {

    private PluginTesting pluginTesting;

    private File file;
    private YamlConfiguration config;

    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>(); //This is how we'll deal with permissions


    public RankManager(PluginTesting pluginTesting) {

        this.pluginTesting = pluginTesting;

        if (!pluginTesting.getDataFolder().exists()) { //If plugin folder doesn't exist
            pluginTesting.getDataFolder().mkdir();
        }

        file = new File(pluginTesting.getDataFolder(), "ranks.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file); //YamlConfiguration is like a wrapper for the file class
    }

    public void setRank(UUID uuid, Rank rank, boolean firstJoin) {

        //Functionality to change permissions, Used with the RankCommand
        //Have to check if player whose been on server before and has had a rank
        if (Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin) {
            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment attachment;
            if (perms.containsKey(uuid)) { //If they player is in the hasmap
                attachment = perms.get(uuid);
            } else {
                attachment = player.addAttachment(pluginTesting);
                perms.put(uuid, attachment);
            }

            //Get rid of current permissions
            for (String perm : getRank(uuid).getPermissions()) {
                if (player.hasPermission(perm)) {
                    attachment.unsetPermission(perm);
                }
            }

            //Set new permissions for the rank
            for (String perm : rank.getPermissions()) {
                attachment.setPermission(perm, true);
            }
        }


        config.set(uuid.toString(), rank.name());
        try {
            config.save(file); //Saves the file
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Updates the rank on the nametag
        if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
            Player player = Bukkit.getPlayer(uuid);
            pluginTesting.getNametagManager().removeTag(player);
            pluginTesting.getNametagManager().newTag(player);
        }

    }

    public Rank getRank(UUID uuid) {
        return Rank.valueOf(config.getString(uuid.toString()));
    }

    public HashMap<UUID, PermissionAttachment> getPerms() { return perms; }
}
