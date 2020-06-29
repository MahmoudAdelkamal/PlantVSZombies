package GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Plant extends Creature
{
    public Plant(float x, float y) 
    {
        super(x,y);
    }
    @Override
    public void collide(float elapsed)
    {
        if(CollisionTime==0)
            CollisionTime=elapsed;
        else if(elapsed-GetCollisionTime()>=2)
            HealthPoints--;
    }
}