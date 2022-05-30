package de.tbias25.urorianetwork.core.managers;

import com.mongodb.ConnectionString;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoDatabase;

public class MongoDBManager {
    private MongoClient client;
    private MongoDatabase database;

    public void connect(String hostname, String username, String password) {
        ConnectionString connectionString = new ConnectionString("mongodb://root:2001abc@ac-ly80e10-shard-00-00.dbbmpfu.mongodb.net:27017,ac-ly80e10-shard-00-01.dbbmpfu.mongodb.net:27017,ac-ly80e10-shard-00-02.dbbmpfu.mongodb.net:27017/?ssl=true&replicaSet=atlas-13bcs4-shard-0&authSource=admin&retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        client = MongoClients.create(settings);
        database = client.getDatabase("database");
    }

    public void disconnect() {
        client.close();
    }

    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
