package GameObjects;

public class SunCounter{
    private int currency = 0;
    public static float x = 50;
    public static float y = 650;

    public SunCounter(int currency) {
        this.currency = currency;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }
}