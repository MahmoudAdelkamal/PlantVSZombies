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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import javafx.scene.paint.Color;
public class Character
{
    private Animations animation;
    private Texture texture;
    private Sprite sprite;
    public Character(String path,int Rows,int columns,float x,float y,float FrameTime)
    {
       texture=new Texture(path);
       animation=new Animations(path,Rows,columns,FrameTime);
       sprite=new Sprite(texture,texture.getWidth(),texture.getHeight(),0,0);
       sprite.setPosition(x,y);
    }
    public Animation DrawPlant()
    {
        return animation.GetAnimation();
    }
    public void update(float x,float y)
    {
        sprite.setPosition(x,y);
    }
    public float Getx()
    {
        return sprite.getX();
    }
    public float Gety()
    {
        return sprite.getY();
    }
}
