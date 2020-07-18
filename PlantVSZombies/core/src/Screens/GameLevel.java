package Screens;
import Utils.Constants;
import Utils.*;
import GameObjects.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class GameLevel implements Screen 
{
    private Texture SunScore;
    private int score;
    private BitmapFont SunScorefont;
    private PlantsvsZombies game;
    private int wave;
    private float elapsed;
    private Plant PlacedPlant;
    private int LevelNumber;
    private ArrayList<Plant>plants;
    private ArrayList<Zombie>zombies;
    private ArrayList<LawnMower>Mowers;
    private ArrayList<Card>Cards;
    private ArrayList<Sun>stars;
    private static boolean IsPlanted[][];
    public GameLevel(PlantsvsZombies game)
    {
        wave = 1;
        score = 11350;
        this.game = game;
        SunScorefont = new BitmapFont(Gdx.files.internal("Font.fnt"));
        SunScorefont.setColor(Color.WHITE);
        SunScorefont.getData().setScale(2.6f, 3.4f);
        SunScore = new Texture("star.png");
        plants = new ArrayList<Plant>();
        zombies = new ArrayList<Zombie>();
        Mowers = new ArrayList<LawnMower>();
        stars = new ArrayList<Sun>();
        Cards = new ArrayList<Card>();
        IsPlanted = new boolean[10][10];
        elapsed = 0;
        AddMowers();
        AddCards();
    }
    @Override
    public void show() 
    {

    }
    @Override
    public void render(float delta)
    {
        elapsed += delta;
        if(elapsed >= wave*25)
        {
            wave++;
            AddZombies();
            AddStars();
        }
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(game.img, 0, 0, 1254, 756);
        game.batch.draw(SunScore, 10, 640);
        SunScorefont.draw(game.batch, Integer.toString(score),100,700);
        AddPlant();
        CheckZombies();
        CheckMowers();
        CheckPlants();
        CheckStars();
        CheckCards();
        game.batch.end();
    }    
    private void AddCards()
    {
        Cards.add(new Card(30, 550,105,67,"sunflower.png",50,new SunFlower(0,0)));
        Cards.add(new Card(30, 450,105,67,"peashooterCard.png",100,new PeaShooter(0,0)));
        Cards.add(new Card(30,350,105,67,"wallnutCard.png",50,new WallNut(0,0)));
        Cards.add(new Card(30,250,105,67,"Repeater.png",200,new Repeater(0,0)));
        Cards.add(new Card(30,150,105,67,"CherryBombCard.png",150,new CherryBomb(0,0)));
        Cards.add(new Card(10,25,140,105,"card_chomper.png",150,new Chomper(0,0)));
    }
    private void AddStars() 
    {
        Sun star = new Sun(Constants.columnPosition[new Random().nextInt(9)], 1250);
        star.setTexture(new Texture("star.png"));
        stars.add(star);
    }
    private void AddZombies()
    {
        for(int i = 0; i<3; i++)
        {
            NormalZombie newZombie = new NormalZombie(1170, Constants.rowPosition[i], 0.5f + (0.2f)* new Random().nextFloat());
            zombies.add(newZombie);
        }
    }
    private void AddPlant()
    {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
        {
            for(Card card:Cards)
            {
                if(card.isTouched(Gdx.input.getX(), Gdx.graphics.getHeight(), Gdx.input.getY()) && score>=card.getPrice() && PlacedPlant==null)
                {
                    PlacedPlant = card.generatePlant();
                    score-=card.getPrice();
                }
            }
        }
        if(PlacedPlant != null && Gdx.input.getX() > Constants.columnPosition[0] && Gdx.input.getY() > Constants.rowPosition[0])
        {
            PlacedPlant.update(Gdx.input.getX(), 756 - Gdx.input.getY());
            PlacedPlant.update(Constants.columnPosition[PlacedPlant.GetXindex()], Constants.rowPosition[PlacedPlant.GetYindex()]);
            game.batch.setColor(Color.GRAY);
            game.batch.draw((TextureRegion) PlacedPlant.getAnimation().getKeyFrame(elapsed, true), PlacedPlant.Getx(), PlacedPlant.Gety());
            game.batch.setColor(Color.WHITE);
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !IsPlanted[PlacedPlant.GetXindex()][PlacedPlant.GetYindex()]) 
            {
                plants.add(PlacedPlant);
                IsPlanted[PlacedPlant.GetXindex()][PlacedPlant.GetYindex()] = true;
                PlacedPlant = null;
            }
        }
    }
    private void AddMowers()
    {
        for (int i = 0; i < 5; i++)
        {
            Mowers.add(new LawnMower(Constants.x, Constants.rowPosition[i]));
        }
    }
    private void CheckZombies()
    {
        Iterator<Zombie> ZombieIterator = zombies.iterator();
        while(ZombieIterator.hasNext())
        {
            Zombie zombie = ZombieIterator.next();
            if(zombie.IsDead())
                ZombieIterator.remove();
            if(zombie.Getx() <= 265)
                game.Gameover();
            zombie.update(zombie.Getx() - zombie.getSpeed(), zombie.Gety());
            zombie.setRectangle();
            if(!plants.isEmpty())
              zombie.setCollisionState(false);
            for(Plant plant:plants)
                zombie.Attack(elapsed,plant);
        }
        for(Zombie zombie:zombies)
          game.batch.draw((TextureRegion) zombie.getAnimation().getKeyFrame(elapsed, true), zombie.Getx(), zombie.Gety());
    }
    private void CheckStars(SunFlower sunflower)
    {
        if(!stars.contains(sunflower.GetSun()))
            sunflower.UpdateTime();
        if(sunflower.CanProduceSun() && !stars.contains(sunflower.GetSun()))
        {
            stars.add(sunflower.GetSun());
            sunflower.ResetSun();
        }
    }
    private void CheckPlants() 
    {
        Iterator<Plant>it=plants.iterator();
        while(it.hasNext())
        {
            Plant p = it.next();
            if(p.IsDead())
            {
                IsPlanted[p.GetXindex()][p.GetYindex()] = false;
                it.remove();
            }
            p.setRectangle();
            if(p instanceof PeaShooter)
            {
                ((PeaShooter)(p)).CheckBullets();
                 for(Zombie zombie:zombies)
                 {
                     ((PeaShooter)(p)).Attack(elapsed,zombie);
                 }  
            }
            else if(p instanceof SunFlower)
                CheckStars(((SunFlower)(p)));
            else if(p instanceof WallNut)
                ((WallNut)(p)).update();
            else if(p instanceof CherryBomb)
            {
                ((CherryBomb)(p)).update(elapsed);
                for(Zombie zombie:zombies)
                {
                  ((CherryBomb)(p)).Attack(elapsed,zombie);
                }
            }
            else if(p instanceof Chomper)
            {
                ((Chomper)(p)).update(elapsed);
                for(Zombie zombie:zombies)
                {
                  ((Chomper)(p)).Attack(elapsed,zombie);
                }
            }
        }
        for(Plant plant:plants)
        {
           plant.draw(game.batch,elapsed);
        }
    }
    private void CheckMowers() 
    {
        Iterator<LawnMower> it = Mowers.iterator();
        while(it.hasNext())
        {
            LawnMower mower = it.next();
            mower.setRectangle();
            if(mower.Getx()>=1250)
                it.remove();
            for(Zombie zombie:zombies)
                mower.Attack(elapsed,zombie);
            mower.move();
        }
        for(LawnMower mower:Mowers)
           mower.draw(game.batch,elapsed);
    }
    private void CheckStars()
    {
        for(int i=0;i<stars.size();i++)
        {
            if(stars.get(i).Gety() >= 620)
                stars.get(i).update(stars.get(i).Getx(), stars.get(i).Gety() - 0.5f);
        }
        Iterator<Sun> it = stars.iterator();
        while (it.hasNext())
        {
            Sun star = it.next();
            if((Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && star.IsTouched(Gdx.input.getX(), Gdx.graphics.getHeight(), Gdx.input.getY())))
            {
                score+=25;
                it.remove();
            }
        }
        for(Sun star:stars)
          game.batch.draw(star.getTexture(), star.Getx(), star.Gety());
    }
    private void CheckCards()
    {
        for(Card card:Cards)
        {
            game.batch.draw(card.getTexture(),card.getX(),card.getY(),card.getHeight(),card.getWidth());
        }
    }
    public static void SetPlantedIndex(int x,int y,boolean planted)
    {
        IsPlanted[x][y] = planted;
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