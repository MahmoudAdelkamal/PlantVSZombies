package GameObjects;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
public class PeaShooter extends Plant
{
    private ArrayList<Bullet>bullets;
    private float shootTime;
    public PeaShooter(float x,float y)
    {
        super(x,y,100);
        animation=new Animations(Constants.PeaShooterSheetPath,Constants.PeaShooterSheetRows,Constants.PeaShooterSheetColumns,0.1f);
        bullets = new ArrayList<Bullet>();
        HealthPoints = 5;
        shootTime=0;
        setRectangle();
    }
    public void AddBullet(float elapsed)
    {
        if (shootTime==0)
            shootTime=elapsed;
        else if (elapsed-shootTime>1.5f)
        {
            bullets.add(new Bullet(x+35,y+40,9));
            shootTime=0;
        }
    }
    public void Shoot(float elapsed,Zombie zombie)
    {   
          AddBullet(elapsed);
          Iterator<Bullet>bulletIterator = bullets.iterator();
          while(bulletIterator.hasNext())
          {
              Bullet bullet = bulletIterator.next();
              if(zombie.isTouched(bullet.GetRectangle()))
              {
                  bulletIterator.remove();
                  zombie.isHit();
              }
          }
    }
    public ArrayList<Bullet>getBullet()
    {
        return bullets;
    }
    public void setRectangle()
    {
        rectangle= new Rectangle(this.x,this.y,48,63);
    }
}