package com.mygdx.game.desktop;

import GameObjects.PlantsvsZombies;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1254;
        config.height = 756;
        new LwjglApplication(new PlantsvsZombies(), config);
    }
}
