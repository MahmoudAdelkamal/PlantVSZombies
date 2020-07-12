package GameObjects;
import Screens.GameLevel;
import Utils.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
public class PoleVaultingZombie extends Zombie
{
    private Animations WalkingAnimation_WithPole_;
    private Animations JumpingAnimation;
    // 0 not jumped :: 1 jumping :: 2 jumped
    protected int state = 0;
    public PoleVaultingZombie(float x, float y, float speed)
    {
        super(x, y, speed);
        HealthPoints = 4;
        WalkingAnimation_WithPole_ = 
        JumpingAnimation = new Animations(Constants.JumpingPoleVaultingZombiePath ,Constants.JumpingPoleVaultingZombieRows ,Constants.JumpingPoleVaultingZombieColumns,0.2f);

        WalkingAnimation = new Animations(Constants.WalkingPoleVaultingZombie_WithoutPole_Path,Constants.WalkingPoleVaultingZombie_WithoutPole_Rows,Constants.WalkingPoleVaultingZombie_WithoutPole_Columns,0.1f);
        EatingAnimation = new Animations(Constants.EatingPoleVaultingZombiePath ,Constants.EatingPoleVaultingZombieRows,Constants.EatingPoleVaultingZombieColumns ,0.1f);
        setRectangle();
    }
    @Override
    public void UpdateAnimation()
    {
        // not jumping
        if (state==0)
        {
            animation = new Animations(Constants.WalkingPoleVaultingZombie_WithPole_Path ,Constants.WalkingPoleVaultingZombie_WithPole_Rows,Constants.WalkingPoleVaultingZombie_WithPole_Columns ,0.1f);
        }
        else if (state==1)
        {
            
        }
        else {
            super.UpdateAnimation();
        }
    }
    @Override
    public void setRectangle()
    {
        rectangle= new Rectangle(this.x+52,this.y,69,111);
    }
    @Override
    public void Attack(float elapsed, Creature c) {

        if (state==0)
        {
            Plant plant = (Plant)(c);
            if(isTouched(plant.GetRectangle()) && GetYindex() == plant.GetYindex())
            {
                setCollisionState(true);
                collide(elapsed);
                // jumping
                animation= new Animations(Constants.JumpingPoleVaultingZombiePath ,Constants.JumpingPoleVaultingZombieRows ,Constants.JumpingPoleVaultingZombieColumns,0.1f);
                speed+=1.8;
                state=1;
            }
        }
        else if (state==1)
        {
            if(animation.isAnimationFinished(elapsed-CollisionTime))
            {
                setCollisionState(false);
                SetCollisionTime(0);
                speed-=1.5;
                state=2;
            }
        }
        else
            super.Attack(elapsed,c);
    }
    public int getState()
    {
        return state;
    }

    @Override
    public void draw(SpriteBatch batch, float elapsed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
