import processing.core.PApplet;

public class Particle extends GameObject
{
    public Particle(PApplet applet, float x, float y)
    {
        super(applet);
        this.x = x;
        this.y = y;
        dx = (float)(Math.random() * 11) - 5;
        dy = (float)(Math.random() * 11) - 5;
        hp = (float)(Math.random() * 156) + 100;
    }

    public void show()
    {
        applet.fill(0, 0, 255, hp);
        applet.stroke(255);
        applet.rect(x, y, 2, 2);
    }

    public void act()
    {
        x += dx;
        y += dy;

        hp -= 2;
    }

    public boolean hasDied()
    {
        return hp <= 0 || y > applet.height || y < 0 || x < 0 || x > applet.width;
    }
}
