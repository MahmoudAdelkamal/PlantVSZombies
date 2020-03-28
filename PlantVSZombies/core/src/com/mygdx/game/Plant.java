package com.mygdx.game;

public class Plant extends GameObject {


    public Plant(String path, int Rows, int columns, float x, float y, float FrameTime) {
        super(path, Rows, columns, x, y, FrameTime);
    }

    @Override
    public void colliding(float elapsed) {
        if (collision_time==-1)
            collision_time=elapsed;
        else if (elapsed-getCollision_time()>=1)
            hp--;
    }
    public boolean is_dead(){
        return (hp==0);
    }
}
