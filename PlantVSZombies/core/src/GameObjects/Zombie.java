package GameObjects;
public abstract class Zombie extends Creature
{
    protected float speed;
    protected Animations WalkingAnimation;
    protected Animations EatingAnimation;
    public Zombie(float x, float y, float speed)
    {
        super(x,y);
        this.speed = speed;
    }
    @Override
    public void update(float x, float y) 
    {
        if(!isColliding())
            super.update(x,y);
    }
    public void isHit()
    {
       HealthPoints--;
    }
    public void Die()
    {
        HealthPoints = 0;
    }
    public void UpdateAnimation()
    {
        if(!isColliding())
            animation = WalkingAnimation;
        else
            animation= EatingAnimation;
    }
    public float getSpeed()
    {
        return speed;
    }
    @Override
    public void collide(float elapsed)
    {
        if(CollisionTime==0)
        {
            SetCollisionTime(elapsed);
            setColliding(true);
        }
    }
     @Override
    public void setColliding(boolean colliding)
    {
        super.setColliding(colliding);
        UpdateAnimation();
    }
}