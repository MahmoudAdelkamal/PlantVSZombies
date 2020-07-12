package GameObjects;
import Utils.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
public class Bullet extends GameObject
{
    private final float speed;
    public Bullet(float x,float y,float speed)
    {
        super(x,y);
        this.speed=speed;
        animation=new Animations(Constants.BulletPath,1,1,1e9f);
        setRectangle();
    }
    public void move()
    {
        update(x+speed,y);
    }
    @Override
    public void setRectangle()
    {
        rectangle=new Rectangle(x,y,20,20);
    }
    public float getSpeed()
    {
        return speed;
    }
    @Override
    public void draw(SpriteBatch batch, float elapsed)
    {
        batch.draw((TextureRegion)(getAnimation().getKeyFrame(elapsed, true)),x,y);
    }
}