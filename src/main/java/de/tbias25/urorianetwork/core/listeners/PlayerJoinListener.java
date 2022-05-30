package de.tbias25.urorianetwork.core.listeners;

import de.tbias25.urorianetwork.UroriaNetwork;
import de.tbias25.urorianetwork.core.managers.UserManager;
import de.tbias25.urorianetwork.core.models.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private UroriaNetwork plugin;
    private UserManager userManager;

    public PlayerJoinListener(UroriaNetwork plugin) {
        this.plugin = plugin;
        userManager = plugin.getUserManager();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!userManager.userExists(player.getDisplayName())) {
            userManager.createUser(player.getUniqueId().toString(), player.getDisplayName(), (user -> {
            }));
            event.setJoinMessage("§7§m->->->->->-<§r §3§lWelcome §7§l" + player.getDisplayName() + "§r §7§m>-<-<-<-<-<-");
            return;
        }
        event.setJoinMessage("§7[§a+§7] §3" + player.getDisplayName());
    }
}
