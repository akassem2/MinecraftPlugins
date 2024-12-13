package com.ayman.plugins.MoreAdvancedAPI;

import com.ayman.test.PluginTesting;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class PermsCommand implements CommandExecutor {

    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>(); //PermissionAttachements controls perms of a player

    private PluginTesting pluginTesting; //Live instance of main class

    public PermsCommand(PluginTesting pluginTesting) {
        this.pluginTesting = pluginTesting;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            PermissionAttachment attachment;

            if (!perms.containsKey(player.getUniqueId())) { //This doesn't keep after a reload
                attachment = player.addAttachment(pluginTesting);
                perms.put(player.getUniqueId(), attachment);
            } else {
                attachment = perms.get(player.getUniqueId());
            }

            if (player.hasPermission("worldedit.help")) {
                attachment.unsetPermission("worldedit.help");
                player.sendMessage("Remove perms!");
            } else {
                attachment.setPermission("worldedit.help", true);
                player.sendMessage("Added perms!");
            }
        }


        return false;
    }
}
