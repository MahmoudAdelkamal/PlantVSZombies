package GameObjects;
import Utils.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
public class NormalZombie extends Zombie
{    
    public NormalZombie(float x, float y, float speed)
    {
        super(x,y,speed);
        HealthPoints = 5;
        WalkingAnimation=new Animations(Constants.WalkingNormalZombiePath,Constants.WalkingNormalZombieRows,Constants.WalkingNormalZombieColumns,0.1f);
        EatingAnimation=new Animations(Constants.EatingNormalZombiePath,Constants.EatingNormalZombieRows,Constants.EatingNormalZombieColumns,0.1f);
        animation = WalkingAnimation;
        setRectangle();
    }
    @Override
    public void setRectangle()
    {
        rectangle= new Rectangle(this.x+65,this.y,59,111);
    }
    @Override
    public void draw(SpriteBatch batch, float elapsed)
    {
       batch.draw((TextureRegion)(animation.GetAnimation().getKeyFrame(elapsed, true)),x,y);
    }
}