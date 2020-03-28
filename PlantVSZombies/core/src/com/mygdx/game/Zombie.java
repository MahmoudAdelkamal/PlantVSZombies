package com.mygdx.game;

public class Zombie extends GameObject {
    private float speed;

    public Zombie(String path, int Rows, int columns, float x, float y, float FrameTime, float speed) {
        super(path, Rows, columns, x, y, FrameTime);
        this.speed = speed;
    }

    @Override
    public void update(float x, float y) {
        if (collision_time==-1)
        super.update(x, y);
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public void colliding(float elapsed) {

    }
}