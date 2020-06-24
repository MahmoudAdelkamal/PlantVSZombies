package GameObjects;

import Utils.Constants;

public class Repeater extends PeaShooter 
{
    public Repeater(float x, float y) 
    {
        super(x,y);
        animation = new Animations(Constants.RepeaterSheetPath,Constants.RepeaterSheetRows,Constants.RepeaterSheetColumns,0.06f);
    }  
    @Override
    public void AddBullet(float elapsed)
    {
        if (shootTime==0)
            shootTime=elapsed;
        else if (elapsed-shootTime>1.5f)
        {
            bullets.add(new Bullet(x+35,y+40,9));
            bullets.add(new Bullet(x+35,y+40,7));
            shootTime=0;
        }
    }
}
