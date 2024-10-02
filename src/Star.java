import processing.core.PApplet;

public class Star extends GameObject
{
    public Star(PApplet applet)
    {
        super(applet);
        x = (float)Math.random() * applet.width;
        y = 0;
        dx = 0;
        dy = (float)(Math.random() * 3) + 3;
    }

    public void show()
    {
        applet.fill(255);
        applet.rect(x, y, dy, dy);
    }

    public void act()
    {
        x += dx;
        y += dy;
    }

    public boolean hasDied()
    {
        return y > applet.height;
    }
}
