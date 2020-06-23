package GameObjects;
public abstract class Creature extends GameObject
{ 
    protected int HealthPoints;
    protected boolean isColliding;
    protected float CollisionTime;
    public Creature(float x, float y)
    {
        super(x, y);
    }
    public boolean isColliding()
    {
        return isColliding;
    }
    public void setCollisionState(boolean isColliding) 
    {
        this.isColliding = isColliding;
    }
    public boolean IsDead()
    {
        return(HealthPoints==0);
    }
    public int getHealthPoints()
    {
        return HealthPoints;
    }
    public void setHealthPoints(int healthPoints)
    {
        HealthPoints = healthPoints;
    }
    public void SetCollisionTime(float CollisionTime)
    {
        this.CollisionTime=CollisionTime;
    }
    public float GetCollisionTime()
    {
        return CollisionTime;
    }
    public abstract void collide(float elapsed);
}
