package de.tbias25.urorianetwork.ranks.commands;

import de.tbias25.urorianetwork.UroriaNetwork;
import de.tbias25.urorianetwork.core.managers.UserManager;
import de.tbias25.urorianetwork.ranks.managers.RankManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RankCommand implements TabExecutor {
    private UroriaNetwork plugin;
    private RankManager rankManager;
    private UserManager userManager;

    public RankCommand(UroriaNetwork plugin) {
        this.plugin = plugin;
        rankManager = plugin.getRankManager();
        userManager = plugin.getUserManager();
        plugin.getCommand("rank").setExecutor(this);
        plugin.getCommand("rank").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if ((args.length == 1) && (args[0].equalsIgnoreCase("create"))) {
            rankManager.createRank(args[1], args[2], args[3], args[4], (rank -> {
                sender.sendMessage(plugin.getPrefix() + "§7The Rank §3" + rank.getName() + "§7 was created.");
            }));
            return true;
        }
        sender.sendMessage(plugin.getPrefix() + "§3/rank create <name> <prefix> <suffix> <chatcolor>");
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
