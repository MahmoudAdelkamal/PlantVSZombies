package GameObjects;
import com.badlogic.gdx.graphics.Texture;
public class SunFlower extends Plant
{
    private Sun star;
    private boolean GenerateSun;
    private float SunPeriod;
    public SunFlower(float x,float y)
    {
        super(x,y);
        SunPeriod = 0;
        GenerateSun = false;
        star = new Sun(x,y);
        star.setTexture(new Texture(Constants.SunSheetPath));
        animation = new Animations(Constants.SunFlowerSheetPath,Constants.SunFlowerRows,Constants.SunFlowerColumns,0.05f);
    }
    public void UpdateTime()
    {
        SunPeriod++;
        if(SunPeriod > 800)
            GenerateSun = true;
    }
    public void Reset()
    {
        GenerateSun = false;
        SunPeriod = 0;
    }
    public boolean CanProduceSun()
    {
        return GenerateSun;
    }
    public void update(float x,float y)
    {
        super.update(x,y);
        star.update(x+10,y+15);
    }
    public Sun GetSun()
    {
        return star;
    }
}
