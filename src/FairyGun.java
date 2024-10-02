import processing.core.PApplet;

public class FairyGun extends Gun
{
    protected Fairy fairy;

    public FairyGun(PApplet applet, Fairy fairy)
    {
        super(applet);
        this.applet = applet;
        threshold = 60;

        this.fairy = fairy;
    }

    public void shoot()
    {
        if (cooldown == threshold)
        {
            Main.engine.add(new CircleBullet(applet, fairy.getX(), fairy.getY(), 0, false));
            cooldown = 0;
        }
    }
}
