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
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameLevel implements Screen 
{
    private Texture SunScore;
    private BitmapFont SunScorefont;
    private Card sunflowerCard;
    private Card peashooterCard;
    private Card WallNutCard;
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
    private boolean IsPlanted[][];
    public GameLevel(PlantsvsZombies game)
    {
        wave = 1;
        this.game = game;
        SunScorefont = new BitmapFont(Gdx.files.internal("Font.fnt"));
        SunScorefont.setColor(Color.WHITE);
        SunScorefont.getData().setScale(2.6f, 3.4f);
        SunScore = new Texture("star.png");
        plants = new ArrayList<Plant>();
        zombies = new ArrayList<Zombie>();
        Mowers = new ArrayList<LawnMower>();
        stars = new ArrayList<Sun>();
        IsPlanted = new boolean[10][10];
        sunflowerCard = new Card(30, 550,"sunflower.png");
        peashooterCard = new Card(30, 450,"peashooterCard.png");
        WallNutCard = new Card(30,350,"wallnutCard.png");
        AddMowers();
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
        LevelManager.instance.SetLevel(this);
        SunScorefont.draw(game.batch, Integer.toString(LevelManager.instance.GetScore()),100,700);
        AddNewPlant();
        DrawPlants();
        DrawMowers();
        DrawStars();
        DrawZombies();
        DrawCards();
        game.batch.end();
    }    
    private void AddStars() 
    {
        Sun star = new Sun(Constants.columnPosition[new Random().nextInt(9)], 1250);
        star.setTexture(new Texture("star.png"));
        stars.add(star);
    }
    private void AddZombies()
    {
        for(int i = 1; i < 4; i++)
        {
            NormalZombie newZombie = new NormalZombie(1170, Constants.rowPosition[i], 0.2f + (0.2f)* new Random().nextFloat());
            zombies.add(newZombie);
        }
    }
    private void AddNewPlant()
    {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
        {
            if (peashooterCard.isTouched(Gdx.input.getX(), Gdx.graphics.getHeight(), Gdx.input.getY()) && LevelManager.instance.GetScore() >= 100)
                PlacedPlant = new PeaShooter(0, 0);
            if (sunflowerCard.isTouched(Gdx.input.getX(), Gdx.graphics.getHeight(), Gdx.input.getY()) && LevelManager.instance.GetScore() >= 50) 
                PlacedPlant = new SunFlower(0, 0);
            if(WallNutCard.isTouched(Gdx.input.getX(), Gdx.graphics.getHeight(), Gdx.input.getY()) && LevelManager.instance.GetScore() >= 50)
                PlacedPlant = new WallNut(0,0);
        }
        if(PlacedPlant != null && Gdx.input.getX() > Constants.columnPosition[0] && Gdx.input.getY() > Constants.rowPosition[0])
        {
            PlacedPlant.update(Gdx.input.getX(), 756 - Gdx.input.getY());
            PlacedPlant.update(Constants.columnPosition[PlacedPlant.GetXindex()], Constants.rowPosition[PlacedPlant.GetYindex()]);
            game.batch.setColor(Color.GRAY);
            game.batch.draw((TextureRegion) PlacedPlant.Draw().getKeyFrame(elapsed, true), PlacedPlant.Getx(), PlacedPlant.Gety());
            game.batch.setColor(Color.WHITE);
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && !IsPlanted[PlacedPlant.GetXindex()][PlacedPlant.GetYindex()]) 
            {
                LevelManager.instance.SetScore(LevelManager.instance.GetScore()-PlacedPlant.GetPrice());
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
    public ArrayList<Zombie>GetZombies()
    {
        return zombies;
    }
    public ArrayList<Plant>GetPlants()
    {
        return plants;
    }
    public ArrayList<LawnMower>GetMowers()
    {
        return Mowers;
    }
    public ArrayList<Card>GetCards()
    {
        return Cards;
    }
    public ArrayList<Sun>GetStars()
    {
        return stars;
    }
    public float GetElapsedTime()
    {
        return elapsed;
    }
    public boolean[][]GetPlantedIndices()
    {
        return IsPlanted;
    }
    public PlantsvsZombies GetGame()
    {
        return game;
    }
    private void DrawMowers()
    {
        for(LawnMower mower:Mowers)
           mower.Draw(game.batch, elapsed, mower.Getx(), mower.Gety());
    }
    private void DrawPlants()
    {
        for(Plant plant:plants)
        {
           game.batch.draw((TextureRegion) plant.Draw().getKeyFrame(elapsed, true), plant.Getx(), plant.Gety());
           if(plant instanceof PeaShooter)
           {
               ArrayList<Bullet>bullets=((PeaShooter)(plant)).getBullet();
               for(Bullet bullet:bullets)
                   game.batch.draw((TextureRegion) bullet.Draw().getKeyFrame(elapsed, true), bullet.Getx(), bullet.Gety());   
           }
        }
    }
    private void DrawZombies()
    {
        for(Zombie zombie:zombies)
          game.batch.draw((TextureRegion) zombie.Draw().getKeyFrame(elapsed, true), zombie.Getx(), zombie.Gety());
    }
    private void DrawStars()
    {
        for(Sun star:stars)
          game.batch.draw(star.getTexture(), star.Getx(), star.Gety());
    }
    private void DrawCards()
    {
        game.batch.draw(sunflowerCard.getTexture(), sunflowerCard.getX(), sunflowerCard.getY(), 105, 67);
        game.batch.draw(peashooterCard.getTexture(), peashooterCard.getX(), peashooterCard.getY(), 105, 67);
        game.batch.draw(WallNutCard.getTexture(),WallNutCard.getX(),WallNutCard.getY(),105,67);
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