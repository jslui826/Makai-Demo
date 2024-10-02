import processing.core.PApplet;

public class FairyShotGun extends FairyGun
{
    public FairyShotGun(PApplet applet, Fairy fairy)
    {
        super(applet, fairy);
        threshold = 120;
    }

    public void shoot()
    {
        if (cooldown == threshold)
        {
            Main.engine.add(new CircleBullet(applet, fairy.getX(), fairy.getY(), (float)-1.5, false));
            Main.engine.add(new CircleBullet(applet, fairy.getX(), fairy.getY(), 0, true));
            Main.engine.add(new CircleBullet(applet, fairy.getX(), fairy.getY(), (float)1.5, false));
            Main.bleep.play();
            cooldown = 0;
        }
    }
}
