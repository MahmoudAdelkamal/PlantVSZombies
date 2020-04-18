package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class MainScreen implements Screen {
    private Texture sunflower;
    private Texture peashooter;
    private Sun star;
    private MyGdxGame game;
    private int wave = 1;
    private float elapsed;
    boolean settingNewPlant = false;
    private ArrayList<Plant> plants;
    private ArrayList<Zombie> zombies;
    private ArrayList<LawnMower> Mowers;
    private Plant temporaryPlant;
    private Random rand = new Random(), randint=new Random();
    public static final int rowPosition[] = {56, 171, 290, 430, 545}, columnPosition[] = {400, 500, 596, 693, 781, 880, 972, 1067, 1161};

    public MainScreen(MyGdxGame game) {
        this.game = game;
        plants = new ArrayList<Plant>();
        zombies = new ArrayList<Zombie>();
        Mowers = new ArrayList<LawnMower>();
        sunflower = new Texture("sunflower.png");
        peashooter = new Texture("peashooterCard.png");
        star = new Sun(columnPosition[2], 1250);
        star.setTexture(new Texture("star.png"));
        elapsed = 0;
        LoadMowers();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        elapsed += delta;
        if (elapsed >= wave * 10) {
            wave++;
            LoadZombies();
        }
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(game.img, 0, 0, 1254, 756);
        game.batch.draw(sunflower, 30, 600);
        game.batch.draw(peashooter, 30, 500, sunflower.getWidth(), sunflower.getHeight());
        game.batch.draw(star.getTexture(), star.Getx(), star.Gety());
        star.update(star.Getx(), star.Gety() - 0.5f);
        HandleSun();
        HandleCollision();
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            settingNewPlant = !settingNewPlant;
        }
        for (int i = 0; i < Mowers.size(); i++) {
            Mowers.get(i).Draw(game.batch, elapsed, Mowers.get(i).Getx(), Mowers.get(i).Gety());
        }
        HandleMowers();
        if (settingNewPlant) {
            temporaryPlant = new Plant(columnPosition[0], rowPosition[0]);
            temporaryPlant.update(Gdx.input.getX(), 756 - Gdx.input.getY());
            temporaryPlant.update(columnPosition[temporaryPlant.GetXindex()], rowPosition[temporaryPlant.GetYindex()]);
            game.batch.setColor(Color.GRAY);
            game.batch.draw((TextureRegion) temporaryPlant.Draw().getKeyFrame(elapsed, true), temporaryPlant.Getx(), temporaryPlant.Gety());
            game.batch.setColor(Color.WHITE);

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                plants.add(temporaryPlant);
            }
        }
        for (GameObject p : plants) {
            game.batch.draw((TextureRegion) p.Draw().getKeyFrame(elapsed, true), p.Getx(), p.Gety());
        }

        game.batch.end();
    }

    // Handling collision between zombies and peashooters
    private void HandleCollision() {
        for (Zombie z : zombies) {
            z.update(z.Getx() - z.getSpeed(), z.Gety());
            Iterator<Plant> it = plants.iterator();
            while (it.hasNext()) {
                Plant p = it.next();
                if (p.GetXindex() == z.GetXindex() && p.GetYindex() == z.GetYindex()) {
                    if (z.GetCollisionTime() == -1) {
                        z.SetCollisionTime(elapsed);
                        z.SetState(false, true);
                        z.UpdateAnimation();
                    }
                    p.colliding(elapsed);
                    if (p.IsDead()) {
                        it.remove();
                        z.SetCollisionTime(-1);
                        z.SetState(true, false);
                        z.UpdateAnimation();
                    }
                }
            }
            game.batch.draw((TextureRegion) z.Draw().getKeyFrame(elapsed, true), z.Getx(), z.Gety());
        }
    }

    private void LoadZombies() {
        for (int i = 0; i < 5; i++) {
            Zombie newZombie = new Zombie(1200, rowPosition[i], 0.6f + (0.4f - 0.2f) * rand.nextFloat());
            zombies.add(newZombie);

        }
    }

    private void LoadMowers() {
        for (int i = 0; i < 5; i++) {
            Mowers.add(new LawnMower(Constants.x, rowPosition[i]));
        }
    }
    private void HandleSun()
    {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) 
        {
            if(star.IsTouched(Gdx.input.getX(),Gdx.graphics.getHeight(),Gdx.input.getY()))
                star.update(columnPosition[randint.nextInt(8)], 1250);
        }
        if(star.Gety()<0)
            star.update(columnPosition[randint.nextInt(8)], 1250);
    }
    public void HandleMowers() {
        for (int i = 0; i < Mowers.size(); i++) {
            Mowers.get(i).CheckZombies(zombies);
        }
        for (int i = 0; i < Mowers.size(); i++) {
            if (Mowers.get(i).CanMove()) {
                Mowers.get(i).Attack(zombies);
                Mowers.get(i).move();
            }
        }
        DeleteMowers();
    }
    private void DeleteMowers() {
        Iterator<LawnMower> it = Mowers.iterator();
        while (it.hasNext()) {
            LawnMower mower = it.next();
            if (mower.isSetToDestroy())
                it.remove();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


}