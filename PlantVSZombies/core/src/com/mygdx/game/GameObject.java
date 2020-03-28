package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
public abstract class GameObject {
    protected Animations animation;
    protected Texture texture;
    protected float x;
    protected float y;
    protected int hp=5;
    protected float collision_time=-1;

    public GameObject(String path,int Rows,int columns,float x,float y,float FrameTime) {
       texture=new Texture(path);
       animation=new Animations(path,Rows,columns,FrameTime);
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
    public void setCollision_time(float collision_time) {
        this.collision_time = collision_time;
    }

    public float getCollision_time() {
        return collision_time;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public abstract void colliding(float elapsed);

    public int GetXindex(){
        int index=0;
        while (index < 8 && MainScreen.columnPosition[index + 1] < x)
            index++;
        return index;
    }
    public int GetYindex(){
        int index=0;
        while (index < 4 && MainScreen.rowPosition[index + 1] <= y)
            index++;
        return index;
    }
}
