package GameObjects;
public class ConeZombie extends Zombie
{
    public ConeZombie(float x, float y, float speed)
    {
        super(x,y,speed);
        HealthPoints = 5;
        WalkingAnimation=new Animations(Constants.WalkingConeZombiePath,Constants.WalkingConeZombieRows,Constants.WalkingConeZombieColumns,0.1f);
        EatingAnimation=new Animations(Constants.EatingConeZombiePath,Constants.EatingConeZombieRows,Constants.EatingConeZombieColumns,0.1f);
        animation = WalkingAnimation;
    }
}
