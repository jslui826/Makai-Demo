import processing.core.PApplet;

public class Boss extends Enemy
{
    private boolean isSpawning;
    private int spawnFrame;
    private int circleFrame;
    private int spawnTime;

    private float initialX;
    private float initialY;

    public Boss(PApplet applet, float xPos, float yPos)
    {
        super(applet, xPos, yPos);
        dy = 3;
        dx = 3;

        hp = 150;
        w = 32;
        h = 32;

        frame = 0;
        isSpawning = true;
        spawnFrame = 0;
        circleFrame = 4;
        spawnTime = 0;
        initialX = x;
        initialY = y;
    }

    public void show()
    {
        if (isSpawning) {
            applet.image(Main.circleBG[spawnFrame], x - 16, y - 16);
            if (Main.time % 5 == 0) {
                spawnFrame++;
                if (spawnFrame > 3) {
                    spawnFrame = 0;
                }
            }
        }
        else {
            applet.image(Main.circleBG[circleFrame], x - 16, y - 16);
            if (Main.time % 5 == 0) {
                circleFrame++;
                if (circleFrame > 7) {
                    circleFrame = 4;
                }
            }
        }
        spawnTime++;
        if (spawnTime == 50) {
            isSpawning = false;
        }

        applet.image(Main.redFairy[frame], x, y);
        if (Main.time % 15 == 0) {
            frame++;
            if (frame > 3) {
                frame = 0;
            }
        }
    }

    public void act()
    {
        if (!isSpawning) {
            x += dx;
            y += dy;

            if (x > initialX + 160) {
                dx = (float)-3;
            }
            else if (x < initialX - 160) {
                dx = (float)3;
            }

            if (Main.time % 120 == 0) {
                dy *= -1;
            }
        }

        if (Main.time % 2 == 0) {
            checkCollisions();
        }
    }
}
