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
            CollisionTime=0;
            this.x=x;
            this.y=y;
    }
     public void setRectangle(){

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
    public Texture getTexture()
    {
        return texture;
    }
    public void setTexture(Texture texture) 
    {
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
    public boolean isTouched(Rectangle rectangle2)
    {
        return(rectangle.overlaps(rectangle2));
    }
    public Rectangle GetRectangle()
    {
        return rectangle;
    }
}