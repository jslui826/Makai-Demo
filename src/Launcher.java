import processing.core.PApplet;

public class Launcher extends GameObject
{
    private float timer;
    private float angle;

    private int numAdded;

    public Launcher(PApplet applet)
    {
        super(applet);
        angle = 0;
        x = 208;
        y = -100;
        dx = 0;
        dy = 0;

        numAdded = 0;
    }

    public void show()
    {
        applet.fill(255, 0, 0);
        applet.ellipse(x, y, 50, 50);
    }

    public void act()
    {
        timer++;

        if (timer < 100) {

        }
        else if (timer < 300) {
            shiftedLine(32 + 64, 7,true, 0);
        }
        else if (timer == 300) {
            //resetWave();
        }
        else if (timer < 500) {
            shiftedLine(32 + 288, 7, false, 0);
        }
        else if (timer < 700) {
            shiftedLine(32 + 64, (float)7 / 4, true, 0);
            shiftedLine(32 + 288, (float)7 / 4, false, 0);

            shiftedLine(32 + 96, (float)7 / 4, true, 1);
            shiftedLine(32 + 320, (float)7 / 4, false, 1);
        }
        else if (timer > 800 && timer < 850) {
            reflectedFairies(32 + 192, 0);
        }
        else if (timer < 950) {
            reflectedFairiesV2(32 + 192, 0);
        }
        else if (timer > 1100 && timer < 1250) {
            reflectedFairies(32 + 192, 1);
        }
        else if (timer > 1300 && timer < 1800) {
            randomSpawn();
        }
        else if (timer > 2300 && timer < 2600) {
            gatewayFairies(32 + 96, 2);
            gatewayFairies(384 - 96 + 32, 2);
        }
        else if (timer == 2700) {
            Main.engine.add(new Boss(applet, 32 + 192, 16 + 64));
        }
    }

    public void shiftedLine(float xPos, float offset, boolean left, int type)
    {
        x = xPos;
        if (timer % 28 == 0) {
            Main.engine.add(new Ghost(applet, x + offset * (numAdded % 7), y, left, type));
            numAdded++;
        }
    }

    public void reflectedFairies(float xPos, int type)
    {
        x = xPos;
        if (timer % 28 == 0) {
            Main.engine.add(new Fairy(applet, x, y, true, type));
            Main.engine.add(new Fairy(applet, x, y, false, type));
        }
    }

    public void reflectedFairiesV2(float xPos, int type)
    {
        x = xPos;
        if (timer % 28 == 0) {
            Main.engine.add(new Fairy(applet, x, y, 3,  true, type));
            Main.engine.add(new Fairy(applet, x, y, -3, false, type));
        }
    }

    public void gatewayFairies(float xPos, int type)
    {
        x = xPos;
        if (timer % 28 == 0) {
            Main.engine.add(new Fairy(applet, 32 + 96, 76, 5, false, type));
            Main.engine.add(new Fairy(applet, 384 - 96 + 32, 76, -5, true, type));
        }
    }

    public void randomSpawn()
    {
        if (timer % 32 == 0) {
            Main.engine.add(new RandomGhost(applet, x, y));
            Main.engine.add(new RandomGhost(applet, x, y));
        }
    }

    // Maybe use for patterns?
    public void sCurve()
    {
        x = (float)(Math.sin(angle) * (384 / 2) + 384 / 2 + 32);
        angle += Math.PI / 90;
        if (timer % 10 == 0) {
            Main.engine.add(new Enemy(applet, x, y));
        }
    }

    // Temporary helper to utilize shiftedLine (needs to reset multiplier each time)
    public void resetWave()
    {
        numAdded = 0;
    }
}
