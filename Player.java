import java.awt.*;
import java.awt.geom.*;
import java.lang.Math.*;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Component
{
    private int playerID;
    private static int createdPlayers;

    //should be floats?
    private float xpos;
    private float ypos;
    private float turnSpeed = 0.2f;
    private float currentAngle = 0.f;
    private float linearSpeed = 0.1f;
    private int score;
    private float lineWidth = 8.f;
    private Color playerColor;
    private boolean isRightPressed = false;
    private boolean isLeftPressed = false;
    private boolean alive;

    //constants used for the turnAction to make input more readable
    public final int RIGHT_DOWN = 0;
    public final int LEFT_DOWN = 1;
    public final int RIGHT_UP = 2;
    public final int LEFT_UP = 3;

    private ArrayList<Point> wall;
    private boolean makingHole = false;

    //TODO constructor with position and color
    public Player()
    {
        alive = true;
        //debug constructor
        playerID = createdPlayers;
        createdPlayers++;
        wall = new ArrayList<Point>();
        //placeholder position, create position through constructor random 

        currentAngle = 180;
        playerColor = Color.RED;
        xpos = 100;
        ypos = 100;
        wall.add(new Point(xpos, ypos, true));
    }

    public Player(float xpos, float ypos, Color color)
    { //proper constructor
        alive = true;
        playerID = createdPlayers;
        createdPlayers++;
        wall = new ArrayList<Point>();

        currentAngle =(float)(new Random().nextInt(359));

        this.xpos = xpos;
        this.ypos = ypos;
        this.playerColor = color;

        wall.add(new Point(xpos, ypos, false));
    }

    //updates the position of a player based on the time since last frame dt
    public void update(float dt)
    {
        wall.add(new Point(xpos, ypos, makingHole));

        if (isRightPressed^isLeftPressed) //go straight if player is pressing both keys
        {
            if(isRightPressed)
            {
                currentAngle += turnSpeed * dt;
            }
            else if (isLeftPressed)
            {
                currentAngle -= turnSpeed * dt;
            }
        }
        xpos +=  dt * linearSpeed * Math.cos(Math.toRadians(currentAngle));
        ypos += dt * linearSpeed * Math.sin(Math.toRadians(currentAngle));
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(playerColor);
        g2.fill(new Ellipse2D.Float(xpos, ypos, lineWidth, lineWidth));

        g2.setStroke(new BasicStroke(lineWidth));

        for (int i = 0; i < wall.size()-1; i++)
        {
            Point currentPoint = wall.get(i);
            Point nextPoint = wall.get(i+1);
            if(!wall.get(i).isHole())
            {
                g2.draw(new Line2D.Float(currentPoint.getX() + lineWidth/2, currentPoint.getY() + lineWidth/2, nextPoint.getX() + lineWidth/2, nextPoint.getY() + lineWidth/2));
            }
        } 
    }

public int getPlayerID() {return playerID;}
public ArrayList<Point> getWall() { return wall;}

public void pressRight() {isRightPressed = true;}
public void releaseRight() {isRightPressed = false;}
public void pressLeft() {isLeftPressed = true;}
public void releaseLeft() {isLeftPressed = false;}

public boolean equals(Player p) {return playerID == p.getPlayerID();}
public boolean isAlive() {return alive;}
public void kill() 
{
    alive = false;
    makingHole = true;
}

}