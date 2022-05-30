package de.tbias25.urorianetwork.ranks.models;

import de.tbias25.urorianetwork.core.models.User;

import java.util.List;

public class Rank {
    private String name, prefix, suffix, chatColor;
    private List<Rank> inheritance;
    private List<String> permissions;
    private List<User> users;

    public Rank(String name, String prefix, String suffix, String chatColor) {
        this.name = name;
        this.prefix = prefix;
        this.suffix = suffix;
        this.chatColor = chatColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getChatColor() {
        return chatColor;
    }

    public void setChatColor(String chatColor) {
        this.chatColor = chatColor;
    }

    public List<Rank> getInheritance() {
        return inheritance;
    }

    public void setInheritance(List<Rank> inheritance) {
        this.inheritance = inheritance;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
