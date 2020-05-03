package GameObjects;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animations
{
    private int rowFrames;
    private int columnFrames;
    private float FrameTime;
    private Texture texture;
    private Animation animation;
    private TextureRegion[]animatedFrames;
    private TextureRegion[][]temporaryFrames;
    public Animations(String TexturePath,int RowFrames,int columnFrames,float FrameTime)
    {
        texture = new Texture(TexturePath);
        temporaryFrames = TextureRegion.split(texture,(int)texture.getWidth()/columnFrames,(int)texture.getHeight()/RowFrames);
        animatedFrames = new TextureRegion[RowFrames*columnFrames];
        this.rowFrames = RowFrames;
        this.columnFrames = columnFrames;
        this.FrameTime = FrameTime;
        CreateAnimation();
    }
    private void CreateAnimation()
    {
        int index=0;
        for(int i = 0; i< rowFrames; i++)
        {
            for(int j=0;j<columnFrames;j++)
            {
                animatedFrames[index++]=temporaryFrames[i][j];
            }
        }
        animation=new Animation(FrameTime,animatedFrames);
    }
    public Animation GetAnimation()
    {
        return animation;
    }
    public int getFrameWidth()
    {
        return texture.getWidth()/columnFrames;
    }
}
