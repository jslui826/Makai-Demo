import processing.core.PApplet;

public class Enemy extends GameObject
{
    protected int frame;
    protected boolean isLeft;
    protected boolean isPlaying;

    public Enemy(PApplet applet, float xPos, float yPos)
    {
        super(applet);
        x = xPos;
        y = yPos;
        dx = 0;
        dy = 3;
        hp = 5;
        w = 32;
        h = 32;

        isPlaying = false;
    }

    public void show()
    {
        applet.fill(255, 0, 0);
        applet.rect(x, y, w, h);
    }

    public void act()
    {
        x += dx;
        y += dy;

        if (Main.time % 2 == 0) {
            checkCollisions();
        }
    }

    public void checkCollisions()
    {
        int i = 0;
        while (i < Main.engine.size()) {
            GameObject object = Main.engine.get(i);
            if (object instanceof Bullet) {
                Bullet b = (Bullet) object;
                if (b.isFriendly() && Tools.rectRect(x, y, w, h, b.getX(), b.getY(), b.getW(), b.getH())) {
                    hp--;
                    b.hp--;
                    for (int j = 0; j < 5; j++) {
                        Main.engine.add(new Particle(applet, b.getX(), b.getY()));
                    }

                    if (hasDied()) {
                        if (Math.random() < 0.6)
                        {
                            double chance = Math.random();
                            if (chance < 0.5)
                            {
                                double bigChance = Math.random();
                                Main.engine.add(new PowerItem(applet, x, y, true, bigChance < 0.01));
                            }
                            else
                            {
                                Main.engine.add(new PointItem(applet, x, y, true));
                            }
                        }
                    }
                }
            }
            i++;
        }
    }

    public boolean hasDied()
    {
        if (hp == 0 && !isPlaying) {
            // Audio dies with this
//            Main.boom.amp((float)0.75);
//            Main.boom.play();
            isPlaying = true;
        }
        return y > applet.height|| y < -200 || x < 0 || x > 32 + 384 || hp <= 0;
    }
}
