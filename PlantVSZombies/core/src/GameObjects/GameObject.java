package GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;


public abstract class GameObject
{
    protected Animations animation;
    protected Texture texture;
    protected float x;
    protected float y;
    protected int HealthPoints;
    protected boolean isColliding;
    protected float CollisionTime;
    protected Rectangle rectangle;
    public GameObject(float x,float y)
    {
            setColliding(false);
            setHealthPoints(5);
            CollisionTime=0;
            this.x=x;
            this.y=y;
    }
     void setRectangle(){

     };

    public Animation Draw()
    {
        return animation.GetAnimation();
    }
    public void update(float x,float y)
    {
        this.x=x;
        this.y=y;
    }
    public float Getx()
    {
        return x;
    }
    public float Gety()
    {
        return y;
    }
    public void SetCollisionTime(float CollisionTime)
    {
        this.CollisionTime=CollisionTime;
    }
    public float GetCollisionTime()
    {
        return CollisionTime;
    }
    public void SetHealthPoints(int HealthPoints)
    {
        this.setHealthPoints(HealthPoints);
    }
    public int GetHealthPoints()
    {
        return getHealthPoints();
    }
    public abstract void collide(float elapsed);

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int GetXindex()
    {
        int index=0;
        while (index < 8 && Constants.columnPosition[index + 1] < x)
            index++;
        return index;
    }
    public int GetYindex()
    {
        int index=0;
        while (index < 4 && Constants.rowPosition[index + 1] <= y)
            index++;
        return index;
    }
    public boolean isColliding()
    {
        return isColliding;
    }

    public void setColliding(boolean colliding) 
    {
        isColliding = colliding;
    }
    public boolean IsDead()
    {
        return(getHealthPoints() ==0);
    }
    public int getHealthPoints()
    {
        return HealthPoints;
    }
    public void setHealthPoints(int healthPoints)
    {
        HealthPoints = healthPoints;
    }
    public int getFrameWidth()
    {
        return animation.getFrameWidth();
    }
    public boolean isTouched(Rectangle rectangle2)
    {
        return(rectangle.overlaps(rectangle2));
    }

    public Rectangle get_rectangle() {
        return rectangle;
    }
}
