import processing.core.PApplet;

public class Fairy extends Enemy
{
    private FairyGun fairyGun;
    private boolean hasFired;
    private int shotFrames;
    private int type;
    private int wait;

    public Fairy(PApplet applet, float xPos, float yPos, boolean isLeft, int type)
    {
        super(applet, xPos, yPos);
        this.isLeft = isLeft;
        if (isLeft) {
            frame = 6;
            dx = -1;
        }
        else {
            frame = 4;
            dx = 1;
        }

        dy = (float)3;
        hp = 8;
        w = 32;
        h = 32;

        if (type == 0)
        {
            fairyGun = new FairyGun(applet, this);
        }
        else if (type == 1) {
            fairyGun = new FairyShotGun(applet, this);
        }
        this.type = type;

        hasFired = false;
        wait = 0;
        shotFrames = 0;
    }

    public Fairy(PApplet applet, float xPos, float yPos, float dx, boolean isLeft, int type)
    {
        super(applet, xPos, yPos);
        this.isLeft = isLeft;
        if (isLeft) {
            frame = 6;
        }
        else {
            frame = 4;
        }

        this.dx = dx;
        dy = (float)3;
        hp = 8;
        w = 32;
        h = 32;

        if (type == 0)
        {
            fairyGun = new FairyGun(applet, this);
        }
        else if (type == 1) {
            fairyGun = new FairyShotGun(applet, this);
        }
        else if (type == 2) {
            fairyGun = new FairySpreadGun(applet, this);
            frame = 8;
        }
        this.type = type;

        hasFired = false;
        shotFrames = 0;
    }

    public void show()
    {
        if (type == 0 || type == 1)
        {
            applet.image(Main.blueFairies[frame], x, y);
            if (Main.time % 15 == 0)
            {
                frame++;
                if (y < 40 && !isLeft && frame > 5)
                {
                    frame = 4;
                }
                else if (y < 40 && isLeft && frame > 7)
                {
                    frame = 6;
                }
                else if (y < 60 && frame > 3)
                {
                    frame = 0;
                }
                else if (y > 60 && frame > 11)
                {
                    frame = 8;
                }
            }

            if (hasFired && fairyGun instanceof FairyShotGun)
            {
                applet.image(Main.circleBullets[shotFrames], x, y);
                if (Main.time % 15 == 0 && shotFrames < 3)
                {
                    shotFrames++;
                }
            }
        }
        else if (type == 2) {
            applet.image(Main.blueFairies[frame], x, y);
            if (Main.time % 15 == 0) {
                frame++;
                if (!hasFired && frame > 11)
                {
                    frame = 8;
                }
                else if (hasFired && isLeft && frame > 7) {
                    frame = 6;
                }
                else if (hasFired && !isLeft && frame > 5) {
                    frame = 4;
                }
            }
        }
    }


    public void act()
    {
        if (type == 0 || type == 1)
        {
            if (y < 40)
            {
                y += dy;
                x += dx;
            }
            else if (y < 60)
            {
                y += dy;
            }

            if (y > 60)
            {
                x += dx;
                fairyGun.shoot();
                hasFired = true;
                if (shotFrames == 3)
                {
                    hasFired = false;
                }

                if (type == 1) {
                    y += (float)0.5;
                }

                // Makes shotgun fairies stationary
//                if (type == 1 && !hasFired) {
//                    x -= dx;
//                }
            }
        }
        else if (type == 2) {
            wait++;
            if (wait > 50 && !hasFired) {
                fairyGun.shoot();
                hasFired = true;
            }

            if (hasFired) {
                y += dy;
                x += dx;
                if (y % 20 == 0) {
                    fairyGun.shoot();
                }
            }
        }

        fairyGun.charge();
        checkCollisions();
    }
}
