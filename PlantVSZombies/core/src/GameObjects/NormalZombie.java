package GameObjects;
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
    public void setRectangle()
    {
        rectangle= new Rectangle(this.x+52,this.y,69,111);
    }
}
