public class Point
{
    private float xpos;
    private float ypos;
    //is used to determine if we should consider a hole in the wall (used for gameplay and draw)
    private boolean hole;

    Point(float xpos, float ypos, boolean hole)
    {
        this.xpos = xpos;
        this.ypos = ypos;
        this.hole = hole;
    }

    public float getX() { return xpos; }
    public float getY() { return ypos; }
    public boolean isHole() { return hole; }
}