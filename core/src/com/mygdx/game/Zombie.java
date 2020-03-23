package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.sun.javafx.geom.Vec2d;

public abstract class Zombie {
    protected float speed;
    protected int attackPower;
    protected float  x;
    protected float y;

    protected Animation<TextureRegion> animation;
    protected float elapsed;
    protected int row;


    public Zombie(float speed, int attackPower,int row) {
        this.speed = speed;
        this.attackPower = attackPower;
        this.row = row;
        x=MyGdxGame.VirtualWidth;
        y=MyGdxGame.VirtualHeight/6 * row-20;

    }

    public void zombieMove(){
        x-=speed;
    }

    public void zombieRender(SpriteBatch SB)
    {
        zombieMove();
        elapsed += (Gdx.graphics.getDeltaTime())*2;
        SB.draw(animation.getKeyFrame(elapsed),(int)x , (int)y);



    }














    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


}
