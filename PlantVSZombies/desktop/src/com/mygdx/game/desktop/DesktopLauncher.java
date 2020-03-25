package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher 
{
	public static void main (String[] arg)
        {
        // De el Main
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width=1254;
                config.height=756;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
