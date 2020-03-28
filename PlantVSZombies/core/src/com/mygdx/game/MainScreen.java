package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Iterator;

public class MainScreen implements Screen {
    private Texture sunflower;
    private Texture peashooter;
    private MyGdxGame game;
    private int wave=1;
    private float elapsed;
    boolean settingNewPlant=false;
    ArrayList<Plant> plants;
    ArrayList<Zombie> zombies;
    private Plant  temporaryPlant;

    public static final int rowPosition[] = {56, 171, 290, 430, 545}, columnPosition[] = {400, 500, 596, 693, 781, 880, 972, 1067, 1161};

    public MainScreen(MyGdxGame game) {
        this.game = game;
        plants=new ArrayList<>();
        zombies= new ArrayList<>();
        sunflower = new Texture("sunflower.png");
        peashooter = new Texture("PeashooterCard.png");
        elapsed = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render (float delta) {
        elapsed += delta;
        if (elapsed>=wave*10){
            wave++;
            for (int i=0;i<5;i++){
                Zombie newZombie= new Zombie("ConeZombie.png", 17, 3, 1200, rowPosition[i], 0.05f,3);
                zombies.add(newZombie);
            }

        }
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(game.img, 0, 0, 1254, 756);
        game.batch.draw(sunflower, 30, 600);
        game.batch.draw(peashooter, 30, 500, sunflower.getWidth(), sunflower.getHeight());
        for (Zombie z:zombies) {
            z.update(z.Getx() - 0.5f, z.Gety());
            Iterator<Plant> it=plants.iterator();
            while (it.hasNext()){
                Plant p=it.next();
                if ( p.GetXindex()==z.GetXindex() && p.GetYindex()==z.GetYindex() ){
                    if (z.getCollision_time()==-1)
                        z.setCollision_time(elapsed);
                    p.colliding(elapsed);
                    if (p.is_dead())
                    {
                        it.remove();
                        z.setCollision_time(-1);
                    }

                }
            }

            game.batch.draw((TextureRegion) z.Draw().getKeyFrame(elapsed, true), z.Getx(), z.Gety());
        }


        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            settingNewPlant = !settingNewPlant;
        }
        if (settingNewPlant) {
            temporaryPlant= new Plant("PeaSpriteSheet.png", 4, 6, columnPosition[0], rowPosition[0], 0.1f);
            temporaryPlant.update(Gdx.input.getX(),756-Gdx.input.getY());
            temporaryPlant.update(columnPosition[temporaryPlant.GetXindex()],rowPosition[temporaryPlant.GetYindex()]);
            game.batch.setColor(Color.GRAY);
            game.batch.draw((TextureRegion) temporaryPlant.Draw().getKeyFrame(elapsed, true), temporaryPlant.Getx(), temporaryPlant.Gety());
            game.batch.setColor(Color.WHITE);

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                plants.add(temporaryPlant);
            }

        }
        for (GameObject p:plants){
            game.batch.draw((TextureRegion) p.Draw().getKeyFrame(elapsed, true), p.Getx(), p.Gety());
        }

        game.batch.end();
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
