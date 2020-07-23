package GameObjects;


public abstract class Creature extends GameObject {

    protected int HealthPoints;

    public Creature(float x, float y) {
        super(x, y);
    }
    public void isHit() {
        HealthPoints--;
    }



    public boolean IsDead() {
        return (HealthPoints <= 0);
    }


}
