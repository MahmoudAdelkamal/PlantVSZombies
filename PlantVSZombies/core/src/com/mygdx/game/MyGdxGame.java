package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game  {
	SpriteBatch batch;
	Texture img;
	@Override
	public void create () {
	    batch = new SpriteBatch();
	    img = new Texture("Frontyard.png");
            setScreen(new MainScreen(this));
        }
	@Override
	public void render () {
	    super.render();
	}
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
