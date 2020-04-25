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

public class MainScreen implements Screen
{
    private Texture sunflower;
    private Texture peashooter;
    private Sun star;
    private MyGdxGame game;
    private int wave = 1;
    private float elapsed;
    private ArrayList<Plant> plants;
    private ArrayList<Zombie> zombies;
    private ArrayList<LawnMower> Mowers;
    private Plant temporaryPlant;
    private Random rand = new Random(), randint=new Random();
    public static final int rowPosition[] = {56, 171, 290, 430, 545}, columnPosition[] = {400, 500, 596, 693, 781, 880, 972, 1067, 1161};

    public MainScreen(MyGdxGame game) {
        this.game = game;
        temporaryPlant=null;
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

        // Setting Plants
        SetNewPlant();

        // Handle touching a falling Star
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector2 tmp = new Vector2(Gdx.input.getX(), 756 - Gdx.input.getY());
            Rectangle textureBounds = new Rectangle(star.Getx(), star.Gety(), star.getTexture().getWidth(), star.getTexture().getHeight());
            if (textureBounds.contains(tmp.x, tmp.y)) {
                // you are touching the star Update the score now because there is on score var now
                star.update(columnPosition[randint.nextInt(8)], 1250);
            }
        }
        if (star.Gety() < 0)
            star.update(columnPosition[randint.nextInt(8)], 1250);



        HandleCollision(plants);
        HandleCollision(Mowers);

        for (Iterator<LawnMower> it=Mowers.iterator();it.hasNext();) {
            LawnMower mower=it.next();
            if (mower.isSetToDestroy())
                it.remove();
            mower.move();
            mower.Draw(game.batch, elapsed, mower.Getx(), mower.Gety());
        }

        for (Zombie z:zombies) {
            z.update(z.Getx() - z.getSpeed() , z.Gety());
            game.batch.draw((TextureRegion) z.Draw().getKeyFrame(elapsed, true), z.Getx(), z.Gety());
        }
        for (GameObject p:plants)
        {
            game.batch.draw((TextureRegion) p.Draw().getKeyFrame(elapsed, true), p.Getx(), p.Gety());
            if (p instanceof PeaShooter) {
                Iterator<Bullet> bulletIterator=((PeaShooter) p).getBullet().iterator();
                while (bulletIterator.hasNext())
                {
                    Bullet bu=bulletIterator.next();
                    game.batch.draw((TextureRegion) bu.Draw().getKeyFrame(elapsed, true), bu.Getx(), bu.Gety());
                    bu.move();
                    if (bu.Getx() > 1200)
                        bulletIterator.remove();
                }
            }
        }

        game.batch.end();
    }

    // Handling collision between zombies and peashooters
    private void HandleCollision(ArrayList object)
    {
        Iterator<Zombie> ZombieIterator=zombies.iterator();
        while(ZombieIterator.hasNext())
        {
            GameObject z=ZombieIterator.next();
            Iterator<GameObject> ObjectIterator=object.iterator();

            if (plants.isEmpty() || (!object.isEmpty() && object.get(0) instanceof Plant) )
                z.setColliding(false);

            while(ObjectIterator.hasNext())
            {
                GameObject gameObject=ObjectIterator.next();

                if (gameObject.GetYindex()==z.GetYindex()){

                    if (gameObject instanceof PeaShooter) {
                        PeaShooting((Zombie) z,(PeaShooter) gameObject);
                    }

                    if(gameObject.GetXindex()==z.GetXindex())
                    {
                        gameObject.setColliding(true);
                        gameObject.collide(elapsed);
                        if (gameObject instanceof LawnMower)
                            z.setHealthPoints(0);
                        else
                        {
                            z.setColliding(true);
                            z.collide(elapsed);
                            if (gameObject.IsDead()) {
                                ObjectIterator.remove();
                                z.SetCollisionTime(0);
                                z.setColliding(false);
                            }
                        }
                    }
                    if (z.IsDead())
                    {
                        ZombieIterator.remove();
                        gameObject.SetCollisionTime(0);
                        gameObject.setColliding(false);
                    }
                }

            }
        }
    }

    private void PeaShooting(Zombie zombie , PeaShooter peaShooter ){
        peaShooter.shoot(elapsed);
        Iterator<Bullet> bulletIterator=peaShooter.getBullet().iterator();
        while (bulletIterator.hasNext())
        {
            Bullet bu=bulletIterator.next();
            if (zombie.Getx() - bu.Getx() <1)
            {
                bulletIterator.remove();
                zombie.isHit();
            }
        }
    }

    private void LoadZombies() {
        for (int i = 0; i < 5; i++) {
            Zombie newZombie = new Zombie(1200, rowPosition[i], 0.8f + (0.4f - 0.2f) * rand.nextFloat());
            zombies.add(newZombie);

        }
    }

    private void LoadMowers() {
        for (int i = 0; i < 5; i++) {
            Mowers.add(new LawnMower(Constants.x, rowPosition[i]));
        }
    }

    private void SetNewPlant()
    {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
           if (temporaryPlant==null)
                temporaryPlant=new PeaShooter(0,0);
            else
                temporaryPlant=null;
        }
        if (temporaryPlant!=null)
        {
            temporaryPlant.update(Gdx.input.getX(),756-Gdx.input.getY());
            temporaryPlant.update(columnPosition[temporaryPlant.GetXindex()],rowPosition[temporaryPlant.GetYindex()]);
        //    System.out.println(temporaryPlant.GetXindex()+"   "+temporaryPlant.GetYindex());
            game.batch.setColor(Color.GRAY);
            game.batch.draw((TextureRegion) temporaryPlant.Draw().getKeyFrame(elapsed, true), temporaryPlant.Getx(), temporaryPlant.Gety());
            game.batch.setColor(Color.WHITE);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                plants.add(temporaryPlant);
                temporaryPlant=null;
            }
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