package GameObjects;
import Utils.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
public class Chomper extends Plant implements Attackable
{
    private boolean isAttacking;
    private float eatingTime;
    private Animations NormalAnimation;
    private Animations AttackingAnimation;
    public Chomper(float x, float y)
    {
        super(x, y);
        isAttacking = false;
        HealthPoints = 5;
        eatingTime = 0;
        NormalAnimation = new Animations(Constants.NormalChomperPath,Constants.NormalChomperRows,Constants.NormalChomperColumns,0.1f);
        AttackingAnimation = new Animations(Constants.AttackingChomperPath,Constants.AttackingChomperRows,Constants.AttackingChomperColumns,0.1f);
        animation = NormalAnimation;
        setRectangle();
    }
    @Override
    public void setRectangle()
    {
       rectangle= new Rectangle(this.x+20,this.y,70,63);
    }
    public void update(float elapsed)
    {
        if(isAttacking &&  animation==NormalAnimation)
        {
            animation = AttackingAnimation;
            eatingTime = elapsed;
        }  
        if(isAttacking && animation.isAnimationFinished(elapsed-eatingTime))
        {
            isAttacking = false;
            animation = NormalAnimation;
            eatingTime = 0;
        }
    }
    @Override
    public void draw(SpriteBatch batch, float elapsed)
    {
        if(isAttacking)
             batch.draw((TextureRegion)getAnimation().getKeyFrame(elapsed-eatingTime,false),x,y);
        else
             batch.draw((TextureRegion)getAnimation().getKeyFrame(elapsed,true),x,y);
    }
    @Override
    public void Attack(float elapsed, Creature c)
    {
        Zombie zombie = (Zombie)c;
        if(zombie.isTouched(GetRectangle()) )
        {
            isAttacking = true;
            zombie.BeEaten();
        }
    }    
}
