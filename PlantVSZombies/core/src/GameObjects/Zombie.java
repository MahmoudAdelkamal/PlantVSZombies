package GameObjects;

public abstract class Zombie extends Creature implements Attackable {
    protected float speed;
    protected Animations WalkingAnimation;
    protected Animations EatingAnimation;

    public Zombie(float x, float y, float speed) {
        super(x, y);
        this.speed = speed;
    }

    @Override
    public void update(float x, float y) {
        if (!isColliding())
            super.update(x, y);
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

    @Override
    public void collide(float elapsed) {
        if (CollisionTime == 0) {
            SetCollisionTime(elapsed);
            setCollisionState(true);
        }
    }

    @Override
    public void setCollisionState(boolean isColliding) {
        super.setCollisionState(isColliding);
        UpdateAnimation();
    }

    @Override
    public void Attack(float elapsed, Creature c) {
        Plant plant = (Plant) c;
        if (isTouched(plant.GetRectangle()) && GetYindex() == plant.GetYindex()) {
            collide(elapsed);
            plant.setCollisionState(true);
            plant.collide(elapsed);
            if (plant.IsDead()) {
                setCollisionState(false);
                SetCollisionTime(0);
            }
            if (IsDead()) {
                plant.SetCollisionTime(0);
                plant.setCollisionState(false);
            }
        }
    }
}