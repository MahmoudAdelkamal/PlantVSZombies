package com.mygdx.game;
import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LawnMower extends GameObject
{
    private boolean IsSetToDestroy;
    private boolean MoveAble;
    public LawnMower(float x, float y)
    {
        super(x,y);
        MoveAble=false;
        IsSetToDestroy=false;
        animation = new Animations(Constants.StaticLawnMowerPath,Constants.StaticLawnMowerRows,Constants.StaticLawnMowerColumns,0.1f);
    }
    private void UpdateAnimation()
    {
       animation = new Animations(Constants.MovingLawnMowerPath,Constants.MovingLawnMowerRows,Constants.MovingLawnMowerColumns,0.1f);
    }
    public void move()
    {
        if(MoveAble)
        {
            update(x+5,y);
            UpdateAnimation();
            if(x>1250)
                IsSetToDestroy=true;
        }
    }
    public boolean isSetToDestroy()
    {
        return IsSetToDestroy;
    }
    public void Draw(SpriteBatch batch,float elapsed,float x,float y)
    {
        batch.draw((TextureRegion)Draw().getKeyFrame(elapsed,true),x,y);
    }
    @Override
    public void collide(float elapsed)
    {
        MoveAble=true;
    }
}
