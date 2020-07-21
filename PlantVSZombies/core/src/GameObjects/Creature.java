package GameObjects;


public abstract class Creature extends GameObject {
    public int getHealthPoints() {
        return HealthPoints;
    }

    protected int HealthPoints;

    public Creature(float x, float y) {
        super(x, y);
    }



    public boolean IsDead() {
        return (HealthPoints <= 0);
    }


}
