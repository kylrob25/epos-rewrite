package me.krob.epos;

import me.krob.epos.menu.StartMenu;

public class Application {
    public Application() {
        // Storage


        start();
    }

    public void start() {
        new StartMenu().setVisible(true);
    }
}
