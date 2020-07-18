package GameObjects;
import Utils.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
public class LawnMower extends GameObject implements Attackable
{
    private boolean MoveAble;
    private Animations NormalAnimation;
    private Animations MovingAnimation;
    public LawnMower(float x, float y)
    {
        super(x,y);
        MoveAble=false;
        NormalAnimation = new Animations(Constants.StaticLawnMowerPath,Constants.StaticLawnMowerRows,Constants.StaticLawnMowerColumns,0.1f); 
        MovingAnimation = new Animations(Constants.MovingLawnMowerPath,Constants.MovingLawnMowerRows,Constants.MovingLawnMowerColumns,0.1f);
        animation = NormalAnimation;
        setRectangle();
    }
    private void UpdateAnimation()
    {
       if(animation==NormalAnimation)
          animation = MovingAnimation; 
    }
    public void move()
    {
        if(MoveAble)
        {
            update(x+5,y);
            UpdateAnimation();
        }
    }
    public void Activate()
    {
        MoveAble = true;
    }
    @Override
    public void setRectangle()
    {
        rectangle= new Rectangle(this.x,this.y,80,63);
    }
    @Override
    public void Attack(float elapsed,Creature c)
    {
        Zombie zombie = (Zombie)(c);
        if(zombie.isTouched(this.GetRectangle()) && GetYindex()== zombie.GetYindex())
        {
             Activate();
             zombie.Die();
        }
    }
    @Override
    public void draw(SpriteBatch batch, float elapsed)
    {
        batch.draw((TextureRegion)(getAnimation().getKeyFrame(elapsed, true)),x,y);
    }
}
