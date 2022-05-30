package de.tbias25.urorianetwork.core.listeners;

import de.tbias25.urorianetwork.UroriaNetwork;
import de.tbias25.urorianetwork.core.managers.UserManager;
import de.tbias25.urorianetwork.core.models.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private UroriaNetwork plugin;
    private UserManager userManager;

    public PlayerQuitListener(UroriaNetwork plugin) {
        this.plugin = plugin;
        userManager = plugin.getUserManager();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (userManager.userExists(player.getDisplayName())) {
            userManager.updateUser(player.getDisplayName(), (user -> {
            }));
            event.setQuitMessage("§7[§c-§7] §3" + player.getDisplayName());
        }
    }
}
