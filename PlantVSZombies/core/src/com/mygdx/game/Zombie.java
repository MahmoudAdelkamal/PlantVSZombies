package com.mygdx.game;

public class Zombie extends GameObject
{
    private float speed;
    private Animations WalkingAnimation;
    private Animations EatingAnimation;
    public Zombie(float x, float y, float speed)
    {
        super(x,y);
        this.speed = speed;
        WalkingAnimation=new Animations(Constants.WalkingConeZombiePath,Constants.WalkingConeZombieRows,Constants.WalkingConeZombieColumns,0.1f);
        EatingAnimation=new Animations(Constants.EatingConeZombiePath,Constants.EatingConeZombieRows,Constants.EatingConeZombieColumns,0.1f);
        animation = WalkingAnimation;
    }
    @Override
    public void update(float x, float y) 
    {
        if (!isColliding())
            super.update(x,y);
    }

    public void isHit()
    {
            setHealthPoints(getHealthPoints() - 1);
    }
    public void UpdateAnimation()
    {
        if(isColliding()==false)
            animation = WalkingAnimation;
        else
            animation= EatingAnimation;
    }
    public float getSpeed()
    {
        return speed;
    }


    @Override
    public void collide(float elapsed)
    {
        if(GetCollisionTime()==0)
        {
            SetCollisionTime(elapsed);
            setColliding(true);
        }
    }

    @Override
    public void setColliding(boolean colliding) {
        super.setColliding(colliding);
        UpdateAnimation();
    }

}