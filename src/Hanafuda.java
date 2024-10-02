import processing.core.PApplet;

public class Hanafuda extends Bullet
{
    private boolean isSeeking;

    public Hanafuda(PApplet applet, boolean isSeeking)
    {
        super(applet);
        this.isSeeking = isSeeking;

        h = 8;
        w = 8;

        if (isSeeking) {
            currentFrame = 4;
        }
        else {
            currentFrame = 0;
        }
    }

    public Hanafuda(PApplet applet, float x, float y, boolean isSeeking)
    {
        super(applet);
        this.x = x;
        this.y = y;
        this.isSeeking = isSeeking;

        h = 8;
        w = 8;

        if (isSeeking) {
            currentFrame = 4;
        }
        else {
            currentFrame = 0;
        }
    }

    public Hanafuda(PApplet applet, float x, float y, float offset, boolean isSeeking)
    {
        super(applet);
        this.x = x;
        this.y = y;
        this.isSeeking = isSeeking;

        this.dx = offset;

        h = 4;
        w = 4;

        if (isSeeking) {
            currentFrame = 4;
        }
        else {
            currentFrame = 0;
        }
    }

    @Override
    public void show()
    {
        applet.image(Main.bullets[currentFrame], x - 4, y + 4);
    }

    @Override
    public void act()
    {
        if (!isSeeking)
        {
            x += dx;
            y += dy;

            if (Main.time % 5 == 0 && !(Main.time % 10 == 0))
            {
                currentFrame = 0;
            }
            else if (Main.time % 10 == 0)
            {
                currentFrame = 1;
            }
        }
    }
}
