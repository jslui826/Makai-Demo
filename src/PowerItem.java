import processing.core.PApplet;

public class PowerItem extends Drop
{
    private boolean isBig;

    public PowerItem(PApplet applet, float x, float y, boolean isRandom, boolean isBig)
    {
        super(applet, x, y, isRandom);
        this.isBig = isBig;
    }

    public void show()
    {
        if (isBig) {
            applet.image(Main.drops[3], x, y);
        }
        else {
            applet.image(Main.drops[0], x, y);
        }
    }

    public boolean isLarge()
    {
        return isBig;
    }
}
