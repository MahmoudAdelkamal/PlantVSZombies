package GameObjects;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
public class Card  
{
    private float x;
    private float y;
    private Texture texture;
    public float getX() 
    {
        return x;
    }
    public float getY() 
    {
        return y;
    }
    public Texture getTexture()
    {
        return texture;
    }
    public Card(float x,float y,String TexturePath)
    {
        this.x=x;
        this.y=y;
        this.texture=new Texture(TexturePath);
    }
    public boolean isTouched(int x,int height,int y)
    {
        Vector2 tmp = new Vector2(x,height-y);
        Rectangle textureBounds = new Rectangle(this.x,this.y,105,67);
        return(textureBounds.contains(tmp.x,tmp.y));
    }
}