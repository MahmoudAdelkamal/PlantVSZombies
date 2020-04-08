package com.mygdx.game;

public class Plant extends GameObject
{
    public Plant(float x, float y) 
    {
        super(x,y);
        animation=new Animations(Constants.PeaShooterSheetPath,Constants.PeaShooterSheetRows,Constants.PeaShooterSheetColumns,0.1f);
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
