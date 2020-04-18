package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Sun extends GameObject
{
    public Sun(float x, float y)
    {
        super(x, y);
    }
    public boolean IsTouched(int x,int height,int y)
    {
        Vector2 tmp = new Vector2(x,height-y);
        Rectangle textureBounds = new Rectangle(this.Getx(),this.Gety(),this.getTexture().getWidth(),this.getTexture().getHeight());
        return(textureBounds.contains(tmp.x,tmp.y));
    }
    @Override
    public void colliding(float elapsed)
    {

    }
}
