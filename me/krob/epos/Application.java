package me.krob.epos;

import me.krob.epos.menu.StartMenu;
import me.krob.epos.storage.MongoDB;
import me.krob.epos.storage.user.User;

public class Application implements Runnable {
    private final MongoDB mongoDB;

    public Application() {
        mongoDB = new MongoDB();
        mongoDB.run();

        //mongoDB.getUserByPin(9999).thenApply(User::toString).thenAccept(System.out::println);
    }

    public void run() {
        new StartMenu().setVisible(true);
    }
}
