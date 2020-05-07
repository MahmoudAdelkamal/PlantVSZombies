package Utils;
import GameObjects.*;
import Screens.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
public class LevelManager
{
    private float elapsed;
    private int SunScore = 50;
    private ArrayList<Zombie>zombies;
    private ArrayList<Plant>plants;
    private ArrayList<Sun>stars;
    private ArrayList<LawnMower>Mowers;
    private boolean IsPlanted[][];
    private PlantsvsZombies game;
    public static final LevelManager instance = new LevelManager();
    private LevelManager()
    {
        
    }
    public void SetLevel(GameLevel level)
    {
        instance.zombies = level.GetZombies();
        instance.plants = level.GetPlants();
        instance.stars = level.GetStars();
        instance.Mowers = level.GetMowers();
        instance.game = level.GetGame();
        instance.elapsed = level.GetElapsedTime();
        instance.IsPlanted = level.GetPlantedIndices();
        CheckZombies();
        CheckPlants();
        CheckStars();
        CheckMowers();
    }
    // Detect collision between zombies and other objects
    private void CheckZombiesCollision(ArrayList object)
    {
        Iterator<Zombie> ZombieIterator = zombies.iterator();
        while(ZombieIterator.hasNext())
        {
            Zombie zombie = ZombieIterator.next(); 
            Iterator<GameObject> ObjectIterator = object.iterator();
            if (plants.isEmpty() || (!object.isEmpty() && object.get(0) instanceof Plant))
                zombie.setColliding(false);
            while(ObjectIterator.hasNext())
            {
                 GameObject gameObject = ObjectIterator.next();
                 if(gameObject instanceof PeaShooter && gameObject.GetYindex() == zombie.GetYindex())
                     ((PeaShooter)(gameObject)).Shoot(elapsed,zombie);
                 if(gameObject instanceof LawnMower && zombie.isTouched(gameObject.GetRectangle()) &&gameObject.GetYindex() == zombie.GetYindex())
                 {
                     ((LawnMower)(gameObject)).Activate();
                      zombie.Die();
                 }
                 if(gameObject instanceof Plant && zombie.isTouched(gameObject.GetRectangle()) && gameObject.GetYindex() == zombie.GetYindex())
                 {
                     ((Plant)(gameObject)).setColliding(true);
                     ((Plant)(gameObject)).collide(elapsed);
                      zombie.setColliding(true);
                      zombie.collide(elapsed);
                      if(((Plant)(gameObject)).IsDead())
                      {
                           ObjectIterator.remove();
                           IsPlanted[gameObject.GetXindex()][gameObject.GetYindex()] = false;
                           zombie.SetCollisionTime(0);
                           zombie.setColliding(false);
                      }
                 }       
                 if(zombie.IsDead())
                 {
                     ZombieIterator.remove();
                     if(gameObject instanceof Plant)
                     {
                        ((Plant)(gameObject)).SetCollisionTime(0);
                        ((Plant)(gameObject)).setColliding(false);
                     }
                     break;
                 }
            }
        }
    }
    private void CheckBullets(PeaShooter peaShooter)
    {
        Iterator<Bullet>bulletIterator = peaShooter.getBullet().iterator();
        while(bulletIterator.hasNext()) 
        {
             Bullet bullet = bulletIterator.next();
             bullet.setRectangle();
             bullet.move();
             if(bullet.Getx() > Gdx.graphics.getWidth())
                bulletIterator.remove();
        }
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
        for(Plant p:plants)
        {
            p.setRectangle();
            if (p instanceof PeaShooter)
                CheckBullets(((PeaShooter)(p)));
            else if(p instanceof SunFlower)
                CheckStars(((SunFlower)(p)));
            else if(p instanceof WallNut)
                ((WallNut)(p)).update();
        }
    }
    private void CheckZombies()
    {
        for(Zombie zombie:zombies)
        {
            zombie.update(zombie.Getx() - zombie.getSpeed(), zombie.Gety());
            if(zombie.Getx() <= 265)
                game.Gameover();
            zombie.setRectangle();
        }
        CheckZombiesCollision(plants);
        CheckZombiesCollision(Mowers);
    }
    private void CheckMowers() 
    {
        Iterator<LawnMower> it = Mowers.iterator();
        while(it.hasNext())
        {
            LawnMower mower = it.next();
            if(mower.isSetToDestroy())
                it.remove();
            mower.move();
            mower.setRectangle();
        }
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
                instance.SunScore+=25;
                it.remove();
            }
        }
    }
    public void SetScore(int Score)
    {
        instance.SunScore = Score;
    }
    public int GetScore()
    {
        return instance.SunScore;
    }
}
