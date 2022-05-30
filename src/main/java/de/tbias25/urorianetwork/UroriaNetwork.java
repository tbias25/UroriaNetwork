package de.tbias25.urorianetwork;

import de.tbias25.urorianetwork.core.listeners.PlayerJoinListener;
import de.tbias25.urorianetwork.core.listeners.PlayerQuitListener;
import de.tbias25.urorianetwork.core.managers.MongoDBManager;
import de.tbias25.urorianetwork.core.managers.UserManager;
import de.tbias25.urorianetwork.ranks.commands.RankCommand;
import de.tbias25.urorianetwork.ranks.managers.RankManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UroriaNetwork extends JavaPlugin {

    private String prefix;

    private MongoDBManager mongoDBManager;
    private UserManager userManager;
    private RankManager rankManager;

    private PlayerJoinListener playerJoinListener;
    private PlayerQuitListener playerQuitListener;

    private RankCommand rankCommand;

    @Override
    public void onEnable() {
        prefix = "§3§lUroria§b§lNetwork §7| §r";
        mongoDBManager = new MongoDBManager();
        mongoDBManager.connect("cluster0.dbbmpfu.mongodb.net", "root", "2001abc");
        userManager = new UserManager(this);
        userManager.loadUsers();
        rankManager = new RankManager(this);
        rankManager.loadRanks();

        playerJoinListener = new PlayerJoinListener(this);
        playerQuitListener = new PlayerQuitListener(this);

        rankCommand = new RankCommand(this);
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

    public RankManager getRankManager() {
        return rankManager;
    }

    public PlayerJoinListener getPlayerJoinListener() {
        return playerJoinListener;
    }

    public PlayerQuitListener getPlayerQuitListener() {
        return playerQuitListener;
    }
}
