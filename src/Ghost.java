import processing.core.PApplet;

public class Ghost extends Enemy
{
    private int patternType;

    public Ghost(PApplet applet, float xPos, float yPos, boolean isLeft, int patternType)
    {
        super(applet, xPos, yPos);
        dy = (float)1.5;
        hp = 1;
        // 32x32
        w = 32;
        h = 32;

        this.isLeft = isLeft;
        if (isLeft) {
            frame = 0;
            dx = (float)1.5;
        }
        else {
            frame = 4;
            dx = (float)-1.5;
        }

        this.patternType = patternType;
    }

    public void show()
    {
        applet.image(Main.ghosts[frame], x, y);
        if (Main.time % 15 == 0) {
            frame++;
            if (isLeft && frame > 3) {
                frame = 0;
            }
            else if (!isLeft && frame > 7) {
                frame = 4;
            }
        }

        //applet.fill(0, 0, 255);
        //applet.rect(x + 8, y + 8, w / 2, h / 2);
    }

    public void act()
    {
        // Curvy pattern
        if (patternType == 0)
        {
            if (y < 180) {
                y += dy;
            }
            else {
                y += 0.2;
                x += dx;
            }
        }
        else if (patternType == 1) {
            y += dy;
        }

        if (Main.time % 2 == 0) {
            checkCollisions();
        }
    }
}
