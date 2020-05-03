package GameObjects;

public abstract class Plant extends GameObject
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
        else if(elapsed-GetCollisionTime()>=1)
            setHealthPoints(getHealthPoints() - 1);
    }
}
