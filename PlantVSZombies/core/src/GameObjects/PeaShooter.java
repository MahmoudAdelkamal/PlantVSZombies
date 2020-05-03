package GameObjects;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class PeaShooter extends Plant
{
    private ArrayList<Bullet> bullet;
    private float shootTime;
    public PeaShooter(float x, float y)
    {
        super(x,y);
        animation=new Animations(Constants.PeaShooterSheetPath,Constants.PeaShooterSheetRows,Constants.PeaShooterSheetColumns,0.1f);
        bullet= new ArrayList<Bullet>();
        shootTime=0;
        setRectangle();
    }
    public void shoot(float elapsed)
    {
        if (shootTime==0)
            shootTime=elapsed;
        else if (elapsed-shootTime>1.5f)
        {
            bullet.add(new Bullet(x+35,y+40,5));
            shootTime=0;
        }
    }
    public ArrayList<Bullet>getBullet()
    {
        return bullet;
    }
    public void setRectangle()
    {
        rectangle= new Rectangle(this.x,this.y,48,63);
    }
}
