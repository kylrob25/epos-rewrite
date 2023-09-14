package me.krob.epos.storage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import me.krob.epos.config.JsonConfigBuilder;
import me.krob.epos.storage.user.User;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDB implements Runnable {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();
    private static final User DEFAULT_USER = new User("default", "user", 9999);

    private final MongoDBConfig config;

    private MongoCollection<User> userCollection;

    public MongoDB() {
        JsonConfigBuilder<MongoDBConfig> builder = new JsonConfigBuilder<>(MongoDBConfig.class, "./mongodb.json");
        builder.makeParent();
        config = builder.loadFile();
    }

    public void run() {
        EXECUTOR_SERVICE.submit(() -> {
            CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

            try {
                MongoClient client = MongoClients.create(config.getConnectionString());
                MongoDatabase database = client.getDatabase(config.getDatabase()).withCodecRegistry(pojoCodecRegistry);

                userCollection = database.getCollection(config.getUserCollection(), User.class);

                User defaultUser = userCollection.find(Filters.eq("pin", 9999)).first();
                if (defaultUser == null) userCollection.insertOne(DEFAULT_USER);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public CompletableFuture<User> getUserByPin(long pin) {
        return CompletableFuture.supplyAsync(() -> userCollection.find(Filters.eq("pin", pin)).first(), EXECUTOR_SERVICE);
    }
}
