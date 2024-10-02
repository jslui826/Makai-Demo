import processing.core.PApplet;

public class Drop extends GameObject
{
    public Drop(PApplet applet, float x, float y, boolean isRandom)
    {
        super(applet);
        if (isRandom)
        {
            this.x = x + (float) (Math.random() * 31) - 15; // Creates drop around location
        }
        else {
            this.x = x;
        }
        this.y = y;
        w = 16;
        h = 16;
        hp = 1;
        dy = -12;
    }

    public void show()
    {
        //applet.image(Main.drops[type], x, y);
        applet.fill(255, 0, 0);
        applet.rect(x, y, w, h);
    }

    public void act()
    {
        y += dy;

        if (dy < 0) {
            dy += 1.5;
        }
        else {
            dy = (float)1.75;
        }
    }

    public boolean hasDied()
    {
        return hp <= 0 || y > applet.height;
    }
}
