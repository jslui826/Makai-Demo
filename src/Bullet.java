import processing.core.PApplet;

public class Bullet extends GameObject
{
    protected boolean isGood;

    public Bullet(PApplet applet)
    {
        super(applet);
        x = Main.player.getX();
        y = Main.player.getY();
        dx = 0;
        dy = -15;
        hp = 1;
        w = 5;
        h = 5;
        isGood = true;
    }

    public Bullet(PApplet applet, float x, float y, float dx, float dy, boolean isGood)
    {
        super(applet);
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.isGood = isGood;
    }

    @Override
    public void show()
    {
        applet.fill(8, 192, 255);
        applet.rect(x, y, w, h);
    }

    @Override
    public void act()
    {
        x += dx;
        y += dy;
    }

    public boolean isFriendly()
    {
        return isGood;
    }

    @Override
    public boolean hasDied()
    {
        return y < 0 || hp <= 0;
    }
}
