import processing.core.PApplet;

public class Talisman extends Gun
{
    private boolean reachedMax;

    public Talisman(PApplet applet)
    {
        super(applet);
        threshold = 8;
        cooldown = 0;
        level = 0;
        reachedMax = false;
    }

    public void shoot()
    {
        float x = Main.player.getX();
        float y = Main.player.getY();

//        if (cooldown == threshold) {
//            if (level < 11) {
//                Main.engine.add(new Hanafuda(applet, x, y - 14, false));
//            }
//            // Has tracking (frames 5 & 6)
//            else if (level < 15) {
//                Main.engine.add(new Hanafuda(applet, x - 10, y - 14, false));
//                Main.engine.add(new Hanafuda(applet, x + 10, y - 14, false));
//            }
//            else if (level < 31) {
//                Main.engine.add(new Hanafuda(applet, false));
//                Main.engine.add(new Hanafuda(applet, x - 10, y, -2, false));
//                Main.engine.add(new Hanafuda(applet, x + 10, y, 2, false));
//            }
//            else if (level < 47) {
//
//            }
//            else if (level < 71) {
//
//            }
//            else if (level < 95) {
//
//            }
//            else if (level == 127) {
//                reachedMax = true;
//            }
//
//            if (level != 127) {
//                reachedMax = false;
//            }
        if (cooldown == threshold) {
            if (level < 25) {
                Main.engine.add(new Hanafuda(applet, x, y - 14, false));
            }
            // Has tracking (frames 5 & 6)
            else if (level < 50) {
                Main.engine.add(new Hanafuda(applet, x - 10, y - 14, false));
                Main.engine.add(new Hanafuda(applet, x + 10, y - 14, false));
            }
            else if (level < 75) {
                Main.engine.add(new Hanafuda(applet, false));
                Main.engine.add(new Hanafuda(applet, x - 10, y, -2, false));
                Main.engine.add(new Hanafuda(applet, x + 10, y, 2, false));
            }
            else {
                Main.engine.add(new Hanafuda(applet, false));
                Main.engine.add(new Hanafuda(applet, x - 8, y, (float)-1.5, false));
                Main.engine.add(new Hanafuda(applet, x + 8, y,  (float)1.5, false));
                Main.engine.add(new Hanafuda(applet, x - 16, y, -3, false));
                Main.engine.add(new Hanafuda(applet, x + 16, y, 3, false));
            }
            cooldown = 0;
        }
    }
}
