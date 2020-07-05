package GameObjects;

import Screens.GameLevel;
import Utils.Constants;
import com.badlogic.gdx.math.Rectangle;

public class PoleVaultingZombie extends Zombie
{
    protected Animations WalkingAnimation_WithPole_;
    protected Animations JumpingAnimation;
    // 0 not jumped :: 1 jumping :: 2 jumped
    protected int state = 0;
    public PoleVaultingZombie(float x, float y, float speed) {
        super(x, y, speed);
        HealthPoints = 4;
        WalkingAnimation_WithPole_ = new Animations(Constants.WalkingPoleVaultingZombie_WithPole_Path ,Constants.WalkingPoleVaultingZombie_WithPole_Rows,Constants.WalkingPoleVaultingZombie_WithPole_Columns ,0.1f);
        JumpingAnimation = new Animations(Constants.JumpingPoleVaultingZombiePath ,Constants.JumpingPoleVaultingZombieRows ,Constants.JumpingPoleVaultingZombieColumns,0.2f);

        WalkingAnimation = new Animations(Constants.WalkingPoleVaultingZombie_WithoutPole_Path,Constants.WalkingPoleVaultingZombie_WithoutPole_Rows,Constants.WalkingPoleVaultingZombie_WithoutPole_Columns,0.1f);
        EatingAnimation = new Animations(Constants.EatingPoleVaultingZombiePath ,Constants.EatingPoleVaultingZombieRows,Constants.EatingPoleVaultingZombieColumns ,0.1f);
        animation = WalkingAnimation_WithPole_;
        setRectangle();
    }

    @Override
    public void UpdateAnimation() {
        // not jumping
        if (state==0)
        {
            animation = WalkingAnimation_WithPole_;
        }
        else if (state==1)
        {
            //animation = new Animations(Constants.JumpingPoleVaultingZombiePath ,Constants.JumpingPoleVaultingZombieRows ,Constants.JumpingPoleVaultingZombieColumns,0.1f);
            //speed+=0.04f;
        }
        else {
            //speed-=0.04f;
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
            if(this.isTouched(plant.GetRectangle()) && this.GetYindex() == plant.GetYindex())
            {
                this.setCollisionState(true);
                this.collide(elapsed);
                // jumping
                state=1;
                animation= new Animations(Constants.JumpingPoleVaultingZombiePath ,Constants.JumpingPoleVaultingZombieRows ,Constants.JumpingPoleVaultingZombieColumns,0.1f);
                speed+=1.5;
            }
        }
        else if (state==1)
        {
            if (animation.isAnimationFinished(elapsed-CollisionTime))
            {
                state=2;
                speed-=1.5;
                setCollisionState(false);
                SetCollisionTime(0);
            }
        }
        else
            super.Attack(elapsed,c);
    }

    public int getState() {
        return state;
    }
}