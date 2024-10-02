public class Tools
{
    //Jeff Thompson // v0.9 // November 2011 // www.jeffreythompson.org
    // Placeholder with rectangle collision detection (canonically player is rectangle, enemies & bullets are ellipses)
    public static boolean rectRect(float r1x, float r1y, float r1w, float r1h, float r2x, float r2y, float r2w, float r2h)
    {
        if (r1x + r1w >= r2x && // r1 right edge past r2 left
            r1x <= r2x + r2w && // r1 left edge past r2 right
            r1y + r1h >= r2y && // r1 top edge past r2 bottom
            r1y <= r2y + r2h) { // r1 bottom edge past r2 top
            return true;
        }
        return false;
    }

    // Circle (entities) and rectangle (player) collision detection
    public static boolean circleRect(float cx, float cy, float radius, float rx, float ry, float rw, float rh)
    {
        float testX = cx;
        float testY = cy;

        if (cx < rx) {           // test left edge
            testX = rx;
        }
        else if (cx > rx + rw) { // right edge
            testX = rx + rw;
        }
        if (cy < ry) {           // top edge
            testY = ry;
        }
        else if (cy > ry + rh) { // bottom edge
            testY = ry + rh;
        }

        float distX = cx - testX;
        float distY = cy - testY;
        float distance = (float)Math.sqrt((distX * distX) + (distY * distY));

        if (distance <= radius) {
            return true;
        }
        return false;
    }
}
