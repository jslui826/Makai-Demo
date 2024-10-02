import processing.core.PApplet;

public class CircleBullet extends Bullet
{
    private boolean isLarge;

    public CircleBullet(PApplet applet, float x, float y, float dx, boolean isLarge)
    {
        super(applet);
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.isLarge = isLarge;
        isGood = false;

        dy = (float)2.25;

        if (isLarge) {
            w = 18;
            h = 18;
        }
        else {
            w = 9;
            h = 9;
        }
    }

    public CircleBullet(PApplet applet, float x, float y, float dx, boolean isLarge, boolean randDy)
    {
        super(applet);
        this.x = x;
        this.y = y;
        this.dx = dx;
        if (randDy)
        {
            dy = (float) (Math.random() * 3 - 1.5);
        }
        this.isLarge = isLarge;
        isGood = false;

        if (isLarge) {
            w = 18;
            h = 18;
        }
        else {
            w = 9;
            h = 9;
        }
    }

    public CircleBullet(PApplet applet, float x, float y, float dx, float dy, boolean isLarge)
    {
        super(applet);
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.isLarge = isLarge;
        isGood = false;

        if (isLarge) {
            w = 18;
            h = 18;
        }
        else {
            w = 9;
            h = 9;
        }
    }

    public void show()
    {
        if (!isLarge)
        {
            applet.stroke(0);
            applet.fill(255);
            applet.ellipse(x, y, w, h);
        }
        else {
            applet.image(Main.circleBullets[3], x, y);
        }
    }

    public void act()
    {
        x += dx;
        y += dy;
    }

    public boolean hasDied()
    {
        // Only works for dy direction
        return y > applet.height;
    }

}
