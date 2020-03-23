package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.holidaystudios.tools.GifDecoder;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture frontyard;
	Sprite fy;
	public final static int  VirtualWidth=1260;
	public final static int  VirtualHeight=700;
	ConeZombie[] coneZombies=new ConeZombie[5000];
	Random rand = new Random();


	@Override
	public void create () {
		batch = new SpriteBatch();
		frontyard = new Texture("Frontyard.png");
		fy = new Sprite(frontyard);
		for(int i=0;i<5;i++)
		{
			coneZombies[i]=new ConeZombie((float)(0.2+rand.nextFloat()%.5),1,rand.nextInt(4)+1);

		}
	}

	@Override
	public void render () {

		batch.begin();
		fy.setSize(VirtualWidth,VirtualHeight);
		fy.draw(batch);
		for(int i=0;i<5;i++)
		{
			coneZombies[i].zombieRender(batch);

		}


		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		frontyard.dispose();
	}
}
