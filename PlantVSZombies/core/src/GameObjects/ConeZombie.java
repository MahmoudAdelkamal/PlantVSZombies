package GameObjects;
import Utils.Constants;
import com.badlogic.gdx.math.Rectangle;
public class ConeZombie extends Zombie
{
    public ConeZombie(float x, float y, float speed)
    {
        super(x,y,speed);
        HealthPoints = 10;
        WalkingAnimation=new Animations(Constants.WalkingConeZombiePath,Constants.WalkingConeZombieRows,Constants.WalkingConeZombieColumns,0.1f);
        EatingAnimation=new Animations(Constants.EatingConeZombiePath,Constants.EatingConeZombieRows,Constants.EatingConeZombieColumns,0.1f);
        animation = WalkingAnimation;
        setRectangle();
    }
    @Override
    public void setRectangle()
    {
        rectangle= new Rectangle(this.x+52,this.y,69,111);
    }
}