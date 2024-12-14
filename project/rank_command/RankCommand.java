package com.ayman.project.rank_command;

import com.ayman.test.PluginTesting;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    //              /rank <player> <rank>

    private PluginTesting pluginTesting;

    public RankCommand(PluginTesting pluginTesting) {
        this.pluginTesting = pluginTesting;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.isOp()) {
                if (args.length == 2) {
                    if (Bukkit.getOfflinePlayer(args[0]) != null) { //We want to be able to change ranks of online players, and have joined before

                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                        for (Rank rank : Rank.values()) {
                            if (rank.name().equalsIgnoreCase(args[1])) {
                                pluginTesting.getRankManager().setRank(target.getUniqueId(), rank, false); //Sets the rank when player has had rank before

                                if (target.getName().equalsIgnoreCase(player.getName())) { //If you use the command on yourself
                                    player.sendMessage(ChatColor.GREEN + "You changed your rank to " + rank.getDisplay() + ChatColor.GREEN + ".");
                                } else { //If you use it on another player
                                    player.sendMessage(ChatColor.GREEN + "You changed " + target.getName() + "'s rank to " + rank.getDisplay() + ChatColor.GREEN + ".");
                                    if (target.isOnline()) {
                                        target.getPlayer().sendMessage(ChatColor.GREEN + player.getName() + "set your rank to " + rank.getDisplay() + ChatColor.GREEN + ".");
                                    }
                                }

                                return false; //Stops all code execution
                            }
                        }

                        player.sendMessage(ChatColor.RED + "You did not specify a valid rank! Options are Guest, Member, Admin, Owner.");
                    } else {
                        player.sendMessage(ChatColor.RED + "This user has never joined the server before!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid usage! Please use /rank <player> <rank>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You must be OP to use this command!");
            }


        }

        return false;
    }
}
