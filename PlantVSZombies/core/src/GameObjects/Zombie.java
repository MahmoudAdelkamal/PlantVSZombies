package GameObjects;

public abstract class Zombie extends Creature implements Attackable {
    protected float speed;
    protected Animations WalkingAnimation;
    protected Animations EatingAnimation;
    protected boolean isColliding;
    protected float CollisionTime;

    public Zombie(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;
    }
    public boolean isColliding() {
        return isColliding;
    }

    @Override
    public void update(float x, float y) {
        if (!isColliding())
            super.update(x, y);
    }

    public void SetCollisionTime(float CollisionTime) {
        this.CollisionTime = CollisionTime;
    }

    public void BeEaten() {
        HealthPoints = 0;
    }

    public void isHit() {
        HealthPoints--;
    }

    public void Die() {
        HealthPoints = 0;
    }

    public void UpdateAnimation() {
        if (!isColliding())
            animation = WalkingAnimation;
        else
            animation = EatingAnimation;
    }

    public float getSpeed() {
        return speed;
    }

    public void collide (float elapsed,Plant plant) {
        setCollisionState(true);
        if (CollisionTime == 0) {
            SetCollisionTime(elapsed);
        }
        else if (elapsed - CollisionTime>=0.25f)
        {
            plant.HealthPoints--;
            CollisionTime=elapsed;
        }

    }

    public void setCollisionState(boolean isColliding) {
        this.isColliding=isColliding;
        UpdateAnimation();
    }

    @Override
    public void Attack(float elapsed, Creature c) {
        Plant plant = (Plant) c;
        if (isTouched(plant.GetRectangle()) && GetYindex() == plant.GetYindex()) {
            collide(elapsed,plant);

            if (plant.IsDead()) {
                setCollisionState(false);
                SetCollisionTime(0);
            }

        }
    }
}