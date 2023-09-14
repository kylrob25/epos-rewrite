package me.krob.epos.storage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.krob.epos.config.JsonConfigBuilder;
import me.krob.epos.storage.user.User;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDB implements Runnable {
    private final MongoDBConfig config;

    private MongoCollection<User> userCollection;

    public MongoDB() {
        JsonConfigBuilder<MongoDBConfig> builder = new JsonConfigBuilder<>(MongoDBConfig.class, "./mongodb.json");
        builder.makeParent();
        config = builder.loadFile();
    }

    public void run() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        try (MongoClient client = MongoClients.create(config.getConnectionString())) {
            MongoDatabase database = client.getDatabase(config.getDatabase()).withCodecRegistry(pojoCodecRegistry);

            userCollection = database.getCollection(config.getUserCollection(), User.class);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
