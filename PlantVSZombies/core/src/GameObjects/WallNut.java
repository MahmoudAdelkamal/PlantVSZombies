package GameObjects;
import Utils.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
public class WallNut extends Plant
{
    private Animations HalfLifeAnimation;
    private Animations FullLifeAnimation;
    public WallNut(float x,float y)
    {
        super(x,y);
        FullLifeAnimation = new Animations(Constants.FullWallNutSheet,Constants.FullWallNutRows,Constants.FullWallNutColumns,0.1f);
        HalfLifeAnimation = new Animations(Constants.HalfWallNutSheet,Constants.HalfWallNutRows,Constants.HalfWallNutColumns,0.1f);
        animation = FullLifeAnimation;
        HealthPoints = 200;
        setRectangle();
    }
    public void update()
    {
        if(HealthPoints>=130)
            animation = FullLifeAnimation;
        else
            animation = HalfLifeAnimation;
    }
    @Override
    public void setRectangle()
    {
       rectangle= new Rectangle(this.x,this.y,48,63);
    }
    @Override
    public void draw(SpriteBatch batch, float elapsed)
    {
        batch.draw((TextureRegion)getAnimation().getKeyFrame(elapsed,true),x,y);
    }
}
