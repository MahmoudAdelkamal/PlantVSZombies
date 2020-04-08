package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
public abstract class GameObject
{
    protected Animations animation;
    protected float x;
    protected float y;
    protected int HealthPoints;
    protected float CollisionTime;
    public GameObject(float x,float y)
    {
            HealthPoints=5;
            CollisionTime=-1;
            this.x=x;
            this.y=y;
    }
    public Animation Draw()
    {
        return animation.GetAnimation();
    }
    public void update(float x,float y)
    {
        this.x=x;
        this.y=y;
    }
    public float Getx()
    {
        return x;
    }
    public float Gety()
    {
        return y;
    }
    public void SetCollisionTime(float CollisionTime)
    {
        this.CollisionTime=CollisionTime;
    }
    public float GetCollisionTime()
    {
        return CollisionTime;
    }
    public void SetHealthPoints(int HealthPoints)
    {
        this.HealthPoints = HealthPoints;
    }
    public int GetHealthPoints()
    {
        return HealthPoints;
    }
    public abstract void colliding(float elapsed);
    public int GetXindex()
    {
        int index=0;
        while (index < 8 && MainScreen.columnPosition[index + 1] < x)
            index++;
        return index;
    }
    public int GetYindex()
    {
        int index=0;
        while (index < 4 && MainScreen.rowPosition[index + 1] <= y)
            index++;
        return index;
    }
}
