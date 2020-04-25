package com.mygdx.game;


public class Bullet extends GameObject{
    private float speed;

    public Bullet (float x, float y,float speed) {
        super(x,y);
        this.speed=speed;
        animation=new Animations(Constants.BulletPath,1,1,1e9f);
    }

    public void move()
    {
        update(x+speed,y);
    }
    @Override
    public void collide(float elapsed) {

    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}