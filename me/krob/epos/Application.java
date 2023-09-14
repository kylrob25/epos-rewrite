package me.krob.epos;

import me.krob.epos.menu.StartMenu;
import me.krob.epos.storage.MongoDB;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application implements Runnable {
    private static final ExecutorService MONGO_SERVICE = Executors.newSingleThreadExecutor();

    private final MongoDB mongoDB;

    public Application() {
        mongoDB = new MongoDB();
        MONGO_SERVICE.submit(mongoDB);
    }

    public void run() {
        new StartMenu().setVisible(true);
    }
}
