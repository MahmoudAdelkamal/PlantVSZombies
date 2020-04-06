package com.mygdx.game;

public class Plant extends GameObject {

    public Plant(String path, int Rows, int columns, float x, float y, float FrameTime) 
    {
        super(path, Rows, columns, x, y, FrameTime);
    }
    @Override
    public void colliding(float elapsed) 
    {
        if(CollisionTime==-1)
            CollisionTime=elapsed;
        else if(elapsed-GetCollisionTime()>=1)
            HealthPoints--;
    }
    public boolean IsDead()
    {
        return(HealthPoints==0);
    }
}
