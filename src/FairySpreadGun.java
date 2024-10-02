import processing.core.PApplet;

public class FairySpreadGun extends FairyGun
{
    public FairySpreadGun(PApplet applet, Fairy fairy)
    {
        super(applet, fairy);
        threshold = 60;
    }

    public void shoot()
    {
        if (cooldown == threshold)
        {
            Main.engine.add(new CircleBullet(applet, fairy.getX() - 6, fairy.getY(), (float)-1.5, (float)1.5, false));
            Main.engine.add(new CircleBullet(applet, fairy.getX() - 6, fairy.getY(), (float)1.5,  (float)1.5, false));
            Main.engine.add(new CircleBullet(applet, fairy.getX() - 6, fairy.getY(), (float)1.5, (float)-1.5, false));
            Main.engine.add(new CircleBullet(applet, fairy.getX() - 6, fairy.getY(), (float)-1.5, (float)-1.5, false));

            Main.engine.add(new CircleBullet(applet, fairy.getX() + 6, fairy.getY(), (float)-1.5, (float)1.5, false));
            Main.engine.add(new CircleBullet(applet, fairy.getX() + 6, fairy.getY(), (float)1.5,  (float)1.5, false));
            Main.engine.add(new CircleBullet(applet, fairy.getX() + 6, fairy.getY(), (float)1.5, (float)-1.5, false));
            Main.engine.add(new CircleBullet(applet, fairy.getX() + 6, fairy.getY(), (float)-1.5, (float)-1.5, false));
            cooldown = 0;
        }
    }
}
