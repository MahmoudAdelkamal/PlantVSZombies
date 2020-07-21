package GameObjects;

import Screens.GameLevel;
import Utils.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
        JumpingAnimation = new Animations(Constants.JumpingPoleVaultingZombiePath ,Constants.JumpingPoleVaultingZombieRows ,Constants.JumpingPoleVaultingZombieColumns,0.1f);

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
        else if (state==2)
        {
            super.UpdateAnimation();
        }
    }

    @Override
    public void setRectangle()
    {
        if (state==0)
            rectangle= new Rectangle(this.x+184,this.y,72,111);
        else
            rectangle= new Rectangle(this.x+184,this.y,72,111);

    }

    @Override
    public void draw(SpriteBatch batch, float elapsed) {
        if (state==1)
            batch.draw((TextureRegion)getAnimation().getKeyFrame(elapsed-CollisionTime,false),x,y);
        else
            batch.draw((TextureRegion)getAnimation().getKeyFrame(elapsed,true),x,y);
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
                animation= JumpingAnimation;
                speed+=1.2f;
            }
        }
        else if (state==1)
        {
            if (animation.isAnimationFinished(elapsed-CollisionTime))
            {
                state=2;
                speed-=1.2f;
                setCollisionState(false);
                SetCollisionTime(0);
            }
        }
        else
            super.Attack(elapsed,c);
    }

    @Override
    public void BeEaten() {
        if (state==2)
            super.BeEaten();
    }
}