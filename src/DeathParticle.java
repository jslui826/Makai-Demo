import processing.core.PApplet;

public class DeathParticle extends Particle
{
    private float dx;
    private float dy;

    public DeathParticle(PApplet applet, float x, float y, float dx, float dy)
    {
        super(applet, x, y);
        this.dx = dx;
        this.dy = dy;
    }

    public void show()
    {
        applet.image(Main.deathParticle, x, y);
    }

    public void act()
    {
        x += dx;
        y += dy;
    }

    public boolean hasDied()
    {
        return y > 16 + 368 || y < -100 || x > 32 + 384 || x < 32;
    }
}
