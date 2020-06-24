package GameObjects;

import Utils.Constants;
import com.badlogic.gdx.math.Rectangle;

public class PoleVaultingZombie extends Zombie
{
    protected Animations WalkingAnimation_WithPole_;
    protected Animations JumpingAnimation;
    boolean jumped = false;
    public PoleVaultingZombie(float x, float y, float speed) {
        super(x, y, speed);
        HealthPoints = 4;
        WalkingAnimation_WithPole_ = new Animations(Constants.WalkingPoleVaultingZombie_WithPole_Path ,Constants.WalkingPoleVaultingZombie_WithPole_Rows,Constants.WalkingPoleVaultingZombie_WithPole_Columns ,0.1f);
        JumpingAnimation = new Animations(Constants.JumpingPoleVaultingZombiePath ,Constants.JumpingPoleVaultingZombieRows ,Constants.JumpingPoleVaultingZombieColumns,0.1f);

        WalkingAnimation = new Animations(Constants.WalkingPoleVaultingZombie_WithoutPole_Path,Constants.WalkingPoleVaultingZombie_WithoutPole_Rows,Constants.WalkingPoleVaultingZombie_WithoutPole_Columns,0.1f);
        EatingAnimation = new Animations(Constants.EatingPoleVaultingZombiePath ,Constants.EatingPoleVaultingZombieRows,Constants.EatingPoleVaultingZombieColumns ,0.1f);
        animation = WalkingAnimation_WithPole_;
        setRectangle();
    }

    @Override
    public void UpdateAnimation() {
        if (!jumped)
        {
            if(!isColliding())
                animation = WalkingAnimation_WithPole_;
            else
            {
                animation = JumpingAnimation;
                jumped = true;
            }
        }
        else
            super.UpdateAnimation();
    }

    @Override
    public void setRectangle()
    {
        rectangle= new Rectangle(this.x+52,this.y,69,111);
    }

    @Override
    public void Attack(float elapsed, Creature c) {
        if (jumped)
            super.Attack(elapsed, c);
    }
}