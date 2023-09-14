package me.krob.epos.storage;

import lombok.Getter;

@Getter
public class MongoDBConfig {
    private final String
            connectionString = "",
            database = "epos",
            userCollection = "users"
            ;
}
