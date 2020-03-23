package com.mygdx.game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
public class Animations
{
    private String TexturePath;
    private int RowFrames;
    private int columnFrames;
    private float FrameTime;
    private Texture texture;
    private Animation animation;
    private TextureRegion[]animatedFrames;
    private TextureRegion[][]tmpFrames;
    public Animations(String TexturePath,int RowFrames,int columnFrames,float FrameTime)
    {
        texture=new Texture(TexturePath);
        tmpFrames=TextureRegion.split(texture,(int)texture.getWidth()/columnFrames,(int)texture.getHeight()/RowFrames);
        animatedFrames=new TextureRegion[RowFrames*columnFrames];
        this.RowFrames=RowFrames;
        this.columnFrames=columnFrames;
        this.FrameTime=FrameTime;
        CreateAnimation();
    }
    private void CreateAnimation()
    {
        int index=0;
        for(int i=0;i<RowFrames;i++)
        {
            for(int j=0;j<columnFrames;j++)
            {
                animatedFrames[index++]=tmpFrames[i][j];
            }
        }
        animation=new Animation(FrameTime,animatedFrames);
    }
    public Animation GetAnimation()
    {
        return animation;
    }
}
