import processing.core.PApplet;

public class Player extends GameObject
{
    protected Talisman weapon;
    public static int lives;
    public static int bombs;
    private boolean isDead;
    private boolean isInvulnerable;
    private int wait;

    public Player(PApplet applet)
    {
        super(applet);
        x = 224;
        y = applet.height - 64;
        dx = 0;
        dy = 0;

        // Hit boxes are usually 4x4 to 6x6
        w = 5;
        h = 5;
        speed = (float)2.25;

        currentFrame = 0;
        weapon = new Talisman(applet);
        lives = 2;
        bombs = 3;

        isDead = false;
        isInvulnerable = false;

        wait = 0;
    }

    @Override
    public void show()
    {
        applet.image(Main.charOneImages[currentFrame], x - 14, y - 14);
        // Collision detection is done with upper left corner
        if (Main.shiftKey) {
            applet.stroke(255);
            applet.fill(0, 0, 255);
            applet.rect(x, y, w, h);
        }

        if (Talisman.level >= 11) {
            applet.image(Main.balls, Main.player.getX() - 30, Main.player.getY() + 4);
            applet.image(Main.balls, Main.player.getX() + 18, Main.player.getY() + 4);
        }
    }

    @Override
    public void act()
    {
        dx = 0;
        dy = 0;

        // Half of the character can be "outside"
        if (Main.upKey && y > 16) {
            dy = -speed;
        }
        if (Main.downKey && y < 384) {
            dy = speed;
        }
        if (Main.leftKey && x > 32) {
            dx = -speed;
            currentFrame = 1;
        }
        if (Main.rightKey && x < 416) {
            dx = speed;
            currentFrame = 2;
        }

        if (!Main.upKey && !Main.downKey) {
            dy *= 0.95;
        }
        if (!Main.leftKey && !Main.rightKey) {
            dx *= 0.95;
            currentFrame = 0;
        }

        if (Main.shiftKey) {
            speed = (float)1.5;
        }
        else {
            speed = 3;
        }

        x += dx;
        y += dy;

        // Temporary shot
        if (Main.zKey) {
            weapon.shoot();
            if (!Main.select.isPlaying()) {
                Main.select.loop();
                Main.select.amp((float)0.75);
                Main.select.rate(3);
            }
        }
        else {
            Main.select.stop();
        }
        weapon.charge();

        if (Main.xKey && bombs > 0) {
            isInvulnerable = true;
        }

        if (Main.time % 2 == 0) {
            checkCollisions();
        }

        if (isInvulnerable) {
            wait++;
        }

        if (wait == 180) {
            isInvulnerable = false;
            wait = 0;
        }
    }

    public void checkCollisions()
    {
        int i = 0;
        while (i < Main.engine.size()) {
            GameObject object = Main.engine.get(i);
            if (object instanceof Drop) {
                Drop d = (Drop) object;
                if (Tools.rectRect(x - 14, y - 14, 32, 48, d.getX(), d.getY(), d.getW(), d.getH())) {
                    Main.click.play();
                    if (d instanceof PowerItem) {
                        PowerItem p = (PowerItem) d;
                        if (p.isLarge()) {
                            Talisman.level += 10;
                        }
                        else {
                            Talisman.level++;
                        }
                    }
                    object.hp--;
                }
            }
            else if (object instanceof Bullet) {
                // Enemy projectiles
                Bullet b = (Bullet) object;
                if (!b.isFriendly() && Tools.rectRect(x, y, 5, 5, object.getX(), object.getY(), object.getW(), object.getH()) && !isInvulnerable) {
                    die();
                }
            }
            else if (object instanceof Enemy) {
                // Collision detection is with 16x16
                if (Tools.rectRect(x, y, 5, 5, object.getX() + 8, object.getY() + 8, object.getW() / 2, object.getH() / 2) && !isInvulnerable) {
                    die();
                }
            }
            i++;
        }
    }

    public void die()
    {
        // Temporary death animation
        Main.engine.add(new DeathParticle(applet, x, y, 20, 0));
        Main.engine.add(new DeathParticle(applet, x, y, -20, 0));
        Main.engine.add(new DeathParticle(applet, x, y, 0, 20));
        Main.engine.add(new DeathParticle(applet, x, y, 0, -20));

        Main.engine.add(new PowerItem(applet, x - 55, y + 50, false, false));
        Main.engine.add(new PowerItem(applet, x - 25, y + 30, false, false));
        Main.engine.add(new PowerItem(applet, x, y + 10, false, true));
        Main.engine.add(new PowerItem(applet, x + 30, y + 30, false, false));
        Main.engine.add(new PowerItem(applet, x + 60, y + 50, false, false));
        Main.death.play();
        lives--;
        x = 224;
        y = applet.height - 64;

        isInvulnerable = true;
    }

    @Override
    public boolean hasDied()
    {
        return lives < 0;
    }
}
