import processing.core.PApplet;

public abstract class GameObject
{
    protected int currentFrame;
    protected float x, y, dx, dy, w, h, r, hp, speed;
    protected PApplet applet;

    public GameObject(PApplet applet)
    {
        this.applet = applet;
    }

    public void show()
    {

    }

    public void act()
    {

    }

    public boolean hasDied()
    {
        return false;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getW()
    {
        return w;
    }

    public float getH()
    {
        return h;
    }

    public float getDX()
    {
        return dx;
    }

    public float getDY()
    {
        return dy;
    }

    public float getHP()
    {
        return hp;
    }
}
