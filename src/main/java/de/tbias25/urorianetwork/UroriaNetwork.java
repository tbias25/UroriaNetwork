package de.tbias25.urorianetwork;

import de.tbias25.urorianetwork.core.listeners.PlayerJoinListener;
import de.tbias25.urorianetwork.core.listeners.PlayerQuitListener;
import de.tbias25.urorianetwork.core.managers.MongoDBManager;
import de.tbias25.urorianetwork.core.managers.UserManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UroriaNetwork extends JavaPlugin {

    private String prefix;

    private MongoDBManager mongoDBManager;
    private UserManager userManager;

    private PlayerJoinListener playerJoinListener;
    private PlayerQuitListener playerQuitListener;

    @Override
    public void onEnable() {
        prefix = "§3§lUroria§b§lNetwork §7| §r";
        mongoDBManager = new MongoDBManager();
        mongoDBManager.connect("cluster0.dbbmpfu.mongodb.net", "root", "2001abc");
        userManager = new UserManager(this);
        userManager.loadUsers();

        playerJoinListener = new PlayerJoinListener(this);
        playerQuitListener = new PlayerQuitListener(this);
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onDisable() {
        mongoDBManager.disconnect();
    }

    public String getPrefix() {
        return prefix;
    }

    public MongoDBManager getMongoDBManager() {
        return mongoDBManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public PlayerJoinListener getPlayerJoinListener() {
        return playerJoinListener;
    }

    public PlayerQuitListener getPlayerQuitListener() {
        return playerQuitListener;
    }
}
