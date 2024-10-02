import processing.core.PApplet;

public class RandomGhost extends Enemy
{
    private boolean isSpawning;
    private int spawnFrame;
    private int spawnTime;

    private float initialX;

    public RandomGhost(PApplet applet, float xPos, float yPos)
    {
        super(applet, xPos, yPos);
        x = (int)(Math.random() * 384) + 32;
        y = (int)(Math.random() * 100) + 16;

        initialX = x;

        isLeft = Math.random() > 0.5;
        hp = 1;
        dy = 1;
        dx = 2;
        w = 32;
        h = 32;

        if (isLeft) {
            frame = 0;
        }
        else {
            frame = 4;
        }

        isSpawning = true;
        spawnFrame = 0;
        spawnTime = 0;
    }

    public void show()
    {
        spawnTime++;

        if (isSpawning) {
            applet.image(Main.spawnIns[spawnFrame], x, y);
            if (Main.time % 5 == 0) {
                spawnFrame++;
                if (spawnFrame > 3) {
                    spawnFrame = 0;
                }
            }
        }
        else {
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
        }

        if (spawnTime > 300) {
            isSpawning = false;
        }
    }

    public void act()
    {
        if (!isSpawning)
        {
            x += dx;
            y += dy;

            if (x > initialX + 20) {
                dx = (float)-0.75;
            }
            else if (x < initialX - 20) {
                dx = (float)0.75;
            }

            if (Main.time % 2 == 0)
            {
                checkCollisions();
            }
        }
    }
}
