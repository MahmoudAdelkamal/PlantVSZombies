package GameObjects;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
public class Card  
{
    private float x;
    private float y;
    private float width;
    private float height;
    private Texture texture;
    private final int price;
    private Plant plant;
    public Card(float x,float y,float height,float width,String TexturePath,int price,Plant plant)
    {
        this.x=x;
        this.y=y;
        this.width = width;
        this.height = height;
        this.texture=new Texture(TexturePath);
        this.price = price;
        this.plant = plant;
    }
    public int getPrice()
    {
        return price;
    }
    public float getX() 
    {
        return x;
    }
    public float getY() 
    {
        return y;
    }
    public float getWidth()
    {
        return width;
    }
    public float getHeight()
    {
        return height;
    }
    public Texture getTexture()
    {
        return texture;
    }
    public boolean isTouched(int x,int height,int y)
    {
        Vector2 tmp = new Vector2(x,height-y);
        Rectangle textureBounds = new Rectangle(this.x,this.y,105,67);
        return(textureBounds.contains(tmp.x,tmp.y));
    }
    public Plant generatePlant()
    {
        if(plant instanceof Repeater)
            return new Repeater(0,0);
        else if(plant instanceof PeaShooter)
            return new PeaShooter(0,0);
        else if(plant instanceof SunFlower)
            return new SunFlower(0,0);
        else if(plant instanceof WallNut)
            return new WallNut(0,0);
        else if(plant instanceof CherryBomb)
            return new CherryBomb(0,0);
        else if(plant instanceof Chomper)
            return new Chomper(0,0);
        String name=plant.getClass().getName();
        System.out.println(name);
        return null ;
    }
}