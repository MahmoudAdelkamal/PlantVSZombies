package com.mygdx.game;
import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LawnMower extends GameObject 
{
    private boolean IsSetToDestroy;
    private boolean MoveAble;
    public LawnMower(float x, float y)
    {
        super(x,y);
        MoveAble=false;
        IsSetToDestroy=false;
        animation = new Animations(Constants.StaticLawnMowerPath,Constants.StaticLawnMowerRows,Constants.StaticLawnMowerColumns,0.1f);
    }
    public void CheckZombies(ArrayList<Zombie>zombies)
    {
        Iterator<Zombie>it=zombies.iterator();
        while(it.hasNext())
        {
           Zombie zombie = it.next();
           if(this.GetXindex()==zombie.GetXindex() && this.GetYindex()==zombie.GetYindex())
           {
               UpdateAnimation();
               MoveAble=true;
               break;
           }
        }
    }
    public void Attack(ArrayList<Zombie>zombies)
    {
        Iterator<Zombie>it=zombies.iterator();
        while(it.hasNext())
        {
           Zombie zombie = it.next();
           if(this.GetXindex()==zombie.GetXindex() && this.GetYindex()==zombie.GetYindex())
           {
               it.remove();
           }
        }
    }
    private void UpdateAnimation()
    {
       animation = new Animations(Constants.MovingLawnMowerPath,Constants.MovingLawnMowerRows,Constants.MovingLawnMowerColumns,0.1f);     
    }
    public void move()
    {
         update(x+5,y);
         if(x>1250)
             IsSetToDestroy=true;
    }
    public boolean isSetToDestroy()
    {
        return IsSetToDestroy;
    }
    public boolean CanMove()
    {
        return MoveAble;
    }
    public void Draw(SpriteBatch batch,float elapsed,float x,float y)
    {
        batch.draw((TextureRegion)Draw().getKeyFrame(elapsed,true),x,y);
    }
    @Override
    public void colliding(float elapsed) 
    {
        
    }
}
