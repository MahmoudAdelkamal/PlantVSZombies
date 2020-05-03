package GameObjects;


import com.badlogic.gdx.math.Rectangle;

public class Bullet extends GameObject{
    private float speed;

    public Bullet (float x, float y,float speed) {
        super(x,y);
        this.speed=speed;
        animation=new Animations(Constants.BulletPath,1,1,1e9f);
        setRectangle();
    }

    public void move()
    {
        update(x+speed,y);
    }

    @Override
    void setRectangle() {
        rectangle=new Rectangle(x,y,20,20);
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