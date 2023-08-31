package me.krob.epos.storage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {
    private MongoCollection<Document> userCollection, productCollection;

    public MongoDB(String connectionString) {
        try (MongoClient client = MongoClients.create(connectionString)) {
            MongoDatabase database = client.getDatabase("epos");

            userCollection = database.getCollection("users");

        } catch (Throwable throwable) {

        }
    }
}
