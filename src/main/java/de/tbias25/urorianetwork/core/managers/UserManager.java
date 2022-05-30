package de.tbias25.urorianetwork.core.managers;

import com.google.gson.Gson;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.model.Filters;
import de.tbias25.urorianetwork.UroriaNetwork;
import de.tbias25.urorianetwork.core.models.User;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class UserManager {

    private UroriaNetwork plugin;
    private Gson gson;
    private MongoDBManager mongoDBManager;
    private MongoCollection collection;
    private Map<String, User> users;

    public UserManager(UroriaNetwork plugin) {
        this.plugin = plugin;
        gson = new Gson();
        mongoDBManager = plugin.getMongoDBManager();
        collection = mongoDBManager.getDatabase().getCollection("users");
        users = new HashMap<String, User>();
    }

    public void loadUsers() {
        collection.find().into(new ArrayList<Document>(), (SingleResultCallback<List<Document>>) (documents, throwable) -> {
            for (Document document : documents) {
                User user = gson.fromJson(document.toJson(), User.class);
                if (!users.containsKey(user.getDisplayName())) {
                    users.put(user.getDisplayName(), user);
                }
            }
        });
    }

    public void createUser(String uniqiueId, String displayName, Consumer<User> consumer) {
        User user = new User(uniqiueId, displayName);
        Document document = gson.fromJson(gson.toJson(user), Document.class);
        collection.insertOne(document, (SingleResultCallback<Void>) (unused, throwable) -> {
            users.put(user.getDisplayName(), user);
            consumer.accept(user);
        });
    }

    public void updateUser(String displayName, Consumer<User> consumer) {
        User user = users.get(displayName);
        users.remove(displayName);
        Document document = gson.fromJson(gson.toJson(user), Document.class);
        collection.replaceOne(Filters.eq("displayName", displayName), document, (SingleResultCallback) (updateResult, throwable) -> {
            users.put(user.getDisplayName(), user);
            consumer.accept(user);
        });
    }

    public boolean userExists(String displayName) {
        return users.containsKey(displayName);
    }

    public User getUser(String displayName) {
        return users.get(displayName);
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
