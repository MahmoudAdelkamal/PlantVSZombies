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
import java.util.Random;

public class MainScreen implements Screen
{
    private Texture sunflower;
    private Texture peashooter;
    private MyGdxGame game;
    private int wave=1;
    private float elapsed;
    boolean settingNewPlant=false;
    private ArrayList<Plant> plants;
    private ArrayList<Zombie> zombies;
    private Plant  temporaryPlant;
    private Random rand = new Random();
    public static final int rowPosition[] = {56, 171, 290, 430, 545}, columnPosition[] = {400, 500, 596, 693, 781, 880, 972, 1067, 1161};
    public MainScreen(MyGdxGame game) 
    {
        this.game = game;
        plants=new ArrayList<Plant>();
        zombies= new ArrayList<Zombie>();
        sunflower = new Texture("sunflower.png");
        peashooter = new Texture("peashooterCard.png");
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
                Zombie newZombie= new Zombie(1200,rowPosition[i],1);
                zombies.add(newZombie);
            }
        }
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(game.img, 0, 0, 1254, 756);
        game.batch.draw(sunflower, 30, 600);
        game.batch.draw(peashooter, 30, 500, sunflower.getWidth(), sunflower.getHeight());
        HandleCollision();
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
            settingNewPlant = !settingNewPlant;
        }
        if (settingNewPlant) {
            temporaryPlant= new Plant(columnPosition[0], rowPosition[0]);
            temporaryPlant.update(Gdx.input.getX(),756-Gdx.input.getY());
            temporaryPlant.update(columnPosition[temporaryPlant.GetXindex()],rowPosition[temporaryPlant.GetYindex()]);
            game.batch.setColor(Color.GRAY);
            game.batch.draw((TextureRegion) temporaryPlant.Draw().getKeyFrame(elapsed, true), temporaryPlant.Getx(), temporaryPlant.Gety());
            game.batch.setColor(Color.WHITE);

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
            {
                plants.add(temporaryPlant);
            }
        }
        for (GameObject p:plants){
            game.batch.draw((TextureRegion) p.Draw().getKeyFrame(elapsed, true), p.Getx(), p.Gety());
        }

        game.batch.end();
    }
    // Handling collision between zombies and peashooters
    private void HandleCollision()
    {
        for (Zombie z:zombies)
        {
            z.update(z.Getx() - 0.5f, z.Gety());
            Iterator<Plant>it=plants.iterator();
            while(it.hasNext())
            {
                Plant p=it.next();
                if(p.GetXindex()==z.GetXindex() && p.Getx()-25==z.Getx() && p.GetYindex()==z.GetYindex())
                {
                      if(z.GetCollisionTime()==-1)
                      {
                          z.SetCollisionTime(elapsed);
                          z.SetState(false,true);
                          z.UpdateAnimation();
                      }
                      p.colliding(elapsed);
                      if (p.IsDead())
                      {
                         it.remove();
                         z.SetCollisionTime(-1);
                         z.SetState(true,false);
                         z.UpdateAnimation();
                      }
                }
            }
            game.batch.draw((TextureRegion) z.Draw().getKeyFrame(elapsed, true), z.Getx(), z.Gety());
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
