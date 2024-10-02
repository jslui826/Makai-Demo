import processing.core.PApplet;

public abstract class Gun
{
    protected PApplet applet;
    protected float cooldown, threshold;
    public static int level;

    public Gun(PApplet applet)
    {
        this.applet = applet;
    }

    public void shoot()
    {
        if (cooldown == threshold) {
            Main.engine.add(new Bullet(applet));
            cooldown = 0;
        }
    }

    // For delay
    public void charge()
    {
        if (cooldown < threshold) {
            cooldown++;
        }
    }
}
