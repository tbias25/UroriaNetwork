package de.tbias25.urorianetwork.core.models;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String uniqiueId, displayName;
    private int balance, kills, deaths;
    private Map<String, User> friends, friendRequests;

    public User(String uniqiueId, String displayName) {
        this.uniqiueId = uniqiueId;
        this.displayName = displayName;
        this.balance = 0;
        this.kills = 0;
        this.deaths = 0;
        this.friends = new HashMap<String, User>();
        this.friendRequests = new HashMap<String, User>();
    }

    public String getUniqiueId() {
        return uniqiueId;
    }

    public void setUniqiueId(String uniqiueId) {
        this.uniqiueId = uniqiueId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public Map<String, User> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, User> friends) {
        this.friends = friends;
    }

    public Map<String, User> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(Map<String, User> friendRequests) {
        this.friendRequests = friendRequests;
    }
}
