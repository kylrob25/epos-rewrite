package me.krob;

import me.krob.epos.Application;

public class Main {
    public static void main(String[] args) {
        new Thread(new Application()).start();
    }
}
