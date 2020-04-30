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
    private Card sunflowerCard;
    private Card peashooterCard;
    private MyGdxGame game;
    private int wave = 1;
    private float elapsed;
    private ArrayList<Plant> plants;
    private ArrayList<Zombie> zombies;
    private ArrayList<LawnMower> Mowers;
    private ArrayList<Sun>stars;
    private Plant temporaryPlant;
    private int score = 0 ;
    private Random rand = new Random();
    private Random randint=new Random();
    public static final int rowPosition[] = {56, 171, 290, 430, 545};
    public static final int columnPosition[] = {400, 500, 596, 693, 781, 880, 972, 1067, 1161};
    public MainScreen(MyGdxGame game)
    {
        this.game = game;
        temporaryPlant=null;
        plants = new ArrayList<Plant>();
        zombies = new ArrayList<Zombie>();
        Mowers = new ArrayList<LawnMower>();
        stars = new ArrayList<Sun>();
        sunflowerCard = new Card(30f,600f,"sunflower.png");
        peashooterCard = new Card(30f,500f,"peashooterCard.png");
        elapsed = 0;
        LoadMowers();
    }
    @Override
    public void show()
    {

    }
    @Override
    public void render(float delta)
    {
        elapsed += delta;
        if(elapsed >= wave * 10)
        {
            wave++;
            LoadZombies();
            LoadStars();
        }
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(game.img, 0, 0, 1254, 756);
        game.batch.draw(sunflowerCard.getTexture(), sunflowerCard.getX(), sunflowerCard.getY(),105,67 );
        game.batch.draw(peashooterCard.getTexture(), peashooterCard.getX(), peashooterCard.getY(),105,67);
        SetNewPlant();
        HandleZombies();
        HandlePlants();
        HandleStars();
        HandleMowers();
        game.batch.end();
    }
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

                if (gameObject.GetYindex()==z.GetYindex())
                {
                    if (gameObject instanceof PeaShooter) 
                        PeaShooting((Zombie) z,(PeaShooter) gameObject);
                    if(gameObject.GetXindex()==z.GetXindex())
                    {
                        gameObject.setColliding(true);
                        gameObject.collide(elapsed);
                        if(gameObject instanceof LawnMower)
                            z.setHealthPoints(0);
                        else
                        {
                            z.setColliding(true);
                            z.collide(elapsed);
                            if(gameObject.IsDead())
                            {
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
                        break;
                    }
                }
            }
        }
    }
    private void PeaShooting(Zombie zombie , PeaShooter peaShooter)
    {
        peaShooter.shoot(elapsed);
        Iterator<Bullet>bulletIterator=peaShooter.getBullet().iterator();
        while (bulletIterator.hasNext())
        {
            Bullet bu=bulletIterator.next();
            if(bu.Getx()>=zombie.Getx()+50)
            {
                bulletIterator.remove();
                zombie.isHit();
            }
        }
    }
    private void LoadStars()
    {
        Sun star = new Sun(columnPosition[new Random().nextInt(9)],1250);
        star.setTexture(new Texture("star.png"));
        stars.add(star);
    }
    private void LoadZombies() 
    {
        for (int i = 0; i < 5; i++)
        {
            Zombie newZombie = new Zombie(1200, rowPosition[i], 0.5f + (0.4f - 0.2f) * rand.nextFloat());
            zombies.add(newZombie);
        }
    }
    private void LoadMowers()
    {
        for (int i = 0; i < 5; i++)
        {
            Mowers.add(new LawnMower(Constants.x, rowPosition[i]));
        }
    }
    private void HandleStars()
    {
        for(int i=0;i<stars.size();i++)
        {
            if(stars.get(i).Gety()>=620)
                stars.get(i).update(stars.get(i).Getx(),stars.get(i).Gety()-0.5f);
        }
        Iterator<Sun>it=stars.iterator();
        while(it.hasNext())
        {
            Sun star=it.next();
            game.batch.draw(star.getTexture(),star.Getx(),star.Gety());
            System.out.println(score);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && star.IsTouched(Gdx.input.getX(),Gdx.graphics.getHeight(),Gdx.input.getY()))
            {
                score++;
                it.remove();
            }
        }
    }
    private void HandleMowers()
    {
        Iterator<LawnMower> it=Mowers.iterator();
        while(it.hasNext()) 
        {
            LawnMower mower=it.next();
            if (mower.isSetToDestroy())
                it.remove();
            mower.move();    
            mower.Draw(game.batch, elapsed, mower.Getx(), mower.Gety());
        }
    }
    private void HandleZombies()
    {
        HandleCollision(plants);
        HandleCollision(Mowers);
        for(Zombie z:zombies)
        {
            z.update(z.Getx() - z.getSpeed() , z.Gety());
            game.batch.draw((TextureRegion) z.Draw().getKeyFrame(elapsed, true), z.Getx(), z.Gety());
        }
    }
    private void HandlePlants()
    {
        for(Plant p:plants)
        {
            game.batch.draw((TextureRegion) p.Draw().getKeyFrame(elapsed, true), p.Getx(), p.Gety());
            if(p instanceof PeaShooter)
            {
                Iterator<Bullet> bulletIterator=((PeaShooter) p).getBullet().iterator();
                while (bulletIterator.hasNext())
                {
                    Bullet bu=bulletIterator.next();
                    game.batch.draw((TextureRegion) bu.Draw().getKeyFrame(elapsed, true), bu.Getx(), bu.Gety());
                    bu.move();
                }
            }
        }
    }
    private void SetNewPlant()
    {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (peashooterCard.isTouched(Gdx.input.getX(),Gdx.graphics.getHeight(),Gdx.input.getY())) {
                if (temporaryPlant==null)
                    temporaryPlant=new PeaShooter(0,0);
                else
                    temporaryPlant=null;
            }
        }
        if (temporaryPlant!=null && Gdx.input.getX()>columnPosition[0] && Gdx.input.getY()>rowPosition[0])
        {
            temporaryPlant.update(Gdx.input.getX(),756-Gdx.input.getY());
            temporaryPlant.update(columnPosition[temporaryPlant.GetXindex()],rowPosition[temporaryPlant.GetYindex()]);
            game.batch.setColor(Color.GRAY);
            game.batch.draw((TextureRegion) temporaryPlant.Draw().getKeyFrame(elapsed, true), temporaryPlant.Getx(), temporaryPlant.Gety());
            game.batch.setColor(Color.WHITE);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
            {
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