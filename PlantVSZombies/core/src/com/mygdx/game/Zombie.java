package com.mygdx.game;
import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.*;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Zombie extends GameObject
{
    private float speed;
    private boolean IsWalking;
    private boolean IsEating;
    private Animations WalkingAnimation;
    private Animations EatingAnimation;
    public Zombie(float x, float y, float speed)
    {
        super(x,y);
        this.speed = speed;
        IsWalking = true;
        IsEating = false;
        WalkingAnimation=new Animations(Constants.WalkingConeZombiePath,Constants.WalkingConeZombieRows,Constants.WalkingConeZombieColumns,0.1f);
        EatingAnimation=new Animations(Constants.EatingConeZombiePath,Constants.EatingConeZombieRows,Constants.EatingConeZombieColumns,0.1f);
        InitialAnimation();
    }
    @Override
    public void update(float x, float y) 
    {
        if (CollisionTime==-1)
            super.update(x,y);
    }
    public void InitialAnimation()
    {
        animation = WalkingAnimation;
    }
    public void UpdateAnimation()
    {
        if(GetState()==State.Walking)
            animation = WalkingAnimation;
        else if(GetState()==State.Eating)
            animation= EatingAnimation;
    }
    public float getSpeed()
    {
        return speed;
    }
    public void SetState(boolean IsWalking,boolean IsEating)
    {
        this.IsWalking = IsWalking;
        this.IsEating = IsEating;
    }
    private State GetState()
    {
        if(IsEating)
            return State.Eating;
        else 
            return State.Walking;
    }
    private enum State
    {
        Walking,
        Eating,
    }
    @Override
    public void colliding(float elapsed)
    {
          
    }
}