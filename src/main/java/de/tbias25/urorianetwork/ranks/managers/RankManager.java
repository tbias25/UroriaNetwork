package de.tbias25.urorianetwork.ranks.managers;

import com.google.gson.Gson;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.client.model.Filters;
import de.tbias25.urorianetwork.UroriaNetwork;
import de.tbias25.urorianetwork.core.managers.MongoDBManager;
import de.tbias25.urorianetwork.ranks.models.Rank;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class RankManager {
    private UroriaNetwork plugin;
    private Gson gson;
    private MongoDBManager mongoDBManager;
    private MongoCollection collection;
    private Map<String, Rank> ranks;

    public RankManager(UroriaNetwork plugin) {
        this.plugin = plugin;
        gson = new Gson();
        mongoDBManager = plugin.getMongoDBManager();
        collection = mongoDBManager.getDatabase().getCollection("ranks");
        ranks = new HashMap<String, Rank>();
    }

    public void loadRanks() {
        collection.find().into(new ArrayList<Document>(), (SingleResultCallback<List<Document>>) (documents, throwable) -> {
            for (Document document : documents) {
                Rank rank = gson.fromJson(document.toJson(), Rank.class);
                if (!ranks.containsKey(rank.getName())) {
                    ranks.put(rank.getName(), rank);
                }
            }
        });
    }

    public void createRank(String name, String prefix, String suffix, String chatColor, Consumer<Rank> consumer) {
        Rank rank = new Rank(name, prefix, suffix, chatColor);
        Document document = gson.fromJson(gson.toJson(rank), Document.class);
        collection.insertOne(document, (SingleResultCallback<Void>) (unused, throwable) -> {
            ranks.put(rank.getName(), rank);
            consumer.accept(rank);
        });
    }

    public void updateRank(String name, Consumer<Rank> consumer) {
        Rank rank = ranks.get(name);
        ranks.remove(name);
        Document document = gson.fromJson(gson.toJson(rank), Document.class);
        collection.replaceOne(Filters.eq("name", name), document, (SingleResultCallback) (updateResult, throwable) -> {
            ranks.put(rank.getName(), rank);
            consumer.accept(rank);
        });
    }

    public boolean rankExists(String name) {
        return ranks.containsKey(name);
    }

    public Rank getRank(String name) {
        return ranks.get(name);
    }

    public Map<String, Rank> getRankss() {
        return ranks;
    }
}
