package GameObjects;
public abstract class Plant extends Creature
{
    protected final int Price;
    public Plant(float x, float y,int Price) 
    {
        super(x,y);
        this.Price = Price; 
    }
    public int GetPrice()
    {
        return Price;
    }
    @Override
    public void collide(float elapsed)
    {
        if(CollisionTime==0)
            CollisionTime=elapsed;
        else if(elapsed-GetCollisionTime()>=1)
            HealthPoints--;
    }
}