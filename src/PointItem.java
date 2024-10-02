import processing.core.PApplet;

public class PointItem extends Drop
{
    public PointItem(PApplet applet, float x, float y, boolean isRandom)
    {
        super(applet, x, y, isRandom);
    }

    public void show()
    {
        applet.image(Main.drops[1], x, y);
    }
}