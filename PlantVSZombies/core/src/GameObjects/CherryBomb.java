package GameObjects;
import Utils.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
public class CherryBomb extends Plant implements Attackable
{
    private Animations bombAnimation;
    private Animations fireAnimation;
    private float bombTime;
    private float fireTime;
    public CherryBomb(float x, float y)
    {
        super(x, y);
        bombAnimation = new Animations(Constants.CherryBombSheet,Constants.CherryBombRows,Constants.CherryBombColumns,0.1f);
        fireAnimation = new Animations(Constants.FireSheet,Constants.FireRows,Constants.FireColumns,0.1f);
        animation = bombAnimation;
        bombTime = 0;
        fireTime = 0;
        HealthPoints = 5;
        setRectangle();
    }
    public void update(float elapsed)
    {
        if(bombTime==0)
            bombTime = elapsed;
        else
        {
            if(animation.isAnimationFinished(elapsed-bombTime) &&  fireTime==0)
            {
                animation = fireAnimation;
                fireTime=elapsed;
            }
            else if(animation.isAnimationFinished(elapsed-fireTime) && fireTime!=0)
            {
                HealthPoints = 0;
            }
        }
    }
    @Override
    public void setRectangle() 
    {
        rectangle = new Rectangle(this.x,this.y,48,63);
    }
    @Override
    public void Attack(float elapsed, Creature c)
    {
       Zombie zombie=(Zombie)c;
       Rectangle ExplosionRectangle = new Rectangle(this.x-130,this.y-100,2*130,3*100);
       if(animation==fireAnimation && ExplosionRectangle.overlaps(zombie.rectangle) /*Math.abs(zombie.GetXindex()-GetXindex())<=1 && Math.abs(zombie.GetYindex()-GetYindex())<=1*/)
            ((Zombie)(c)).Die();
    }    
    @Override
    public void draw(SpriteBatch batch, float elapsed)
    {
        if(fireTime==0)
           batch.draw((TextureRegion)getAnimation().getKeyFrame(elapsed-bombTime,false),x,y);
        else
           batch.draw((TextureRegion)getAnimation().getKeyFrame(elapsed-fireTime,false),x,y);
    }
}
    