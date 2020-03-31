import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;

public class Engine extends JPanel
{
    ArrayList<Player> playerList;
    int numberOfPlayers;
    long lastTimeMillis;
    boolean isPlaying = true;
    int WINDOW_WIDTH;
    int WINDOW_HEIGHT;
    JPanel gameWorld;
    JPanel scoreBoard;
    

    public Engine(int playerCount, int width, int height)
    {
        setLayout(new BorderLayout());

        WINDOW_HEIGHT = height;
        WINDOW_WIDTH = width;
        numberOfPlayers = playerCount;

        lastTimeMillis = System.currentTimeMillis();

        playerList = new ArrayList<Player>();
        Color[] colors = {Color.RED, Color.GREEN, Color.PINK};

        for(int i = 0; i <= numberOfPlayers; i++)
        {
            //TODO random position
            playerList.add(new Player((float)((i+1) * 100), (float)((i+1) * 100), colors[i]));
        }

        gameWorld = new gameWorld(playerList);
        gameWorld.setSize(WINDOW_WIDTH*3/4, WINDOW_HEIGHT);
        gameWorld.setLocation(WINDOW_WIDTH/4, 0);
        gameWorld.setBackground(Color.BLACK);
        
        scoreBoard = new JPanel();
        scoreBoard.setSize(WINDOW_WIDTH/4, WINDOW_HEIGHT);
        scoreBoard.setLocation(0, 0);
        scoreBoard.setBackground(Color.GREEN);

        add(gameWorld);
        add(scoreBoard);
        
        addInput();
    }
    
    public void update(float dt)
    {
        for(Player p : playerList)
        {
            if (p.isAlive()) 
            {
                p.update(dt);
                if (checkCollision(p)) 
                {
                    p.kill();
                }
            }
        }
    }

    public void run()
    {
        float dt = (float) (System.currentTimeMillis() - lastTimeMillis);
        lastTimeMillis = System.currentTimeMillis();
        update(dt);
    }

    public boolean checkCollision(Player player)
    {
        //TODO add case for oout of bounds
        if(player.getXpos() > gameWorld.getWidth() || player.getYpos() > gameWorld.getHeight() || player.getXpos() < 0 || player.getYpos() < 0)
        {
            return true;
        }

        boolean result = false;
        if(player.getWall().size() < 4)
        {
            return false;
        }

        ArrayList<Point> playerWall = player.getWall();
        Point playerPoint1 = playerWall.get(playerWall.size() -1 );
        Point playerPoint2 = playerWall.get(playerWall.size() -2 );

        for (Player i : playerList)
        {
            ArrayList<Point> walls = i.getWall();
            int max;
            //max is used to know what part of the array we should check
            if (player.equals(i)) 
            { // dont let the player collide with its current line
                max = 3;
            } 
            else // we make lines with the next point so size-1 is required
            {
                max = 1;
            }
            for (int j = 0; j < walls.size() - max; j++) {
                Point p1 = walls.get(j);
                Point p2 = walls.get(j + 1);
                // check if result is true at least once
                result = areIntersecting(playerPoint1, playerPoint2, p1, p2) || result;
            }
        }

        return result;

    }

    private boolean areIntersecting(Point playerPoint1, Point playerPoint2, Point p1, Point p2) 
    {
        if (p1.isHole()) { return false; } //no way you'll collide with a hole (holes are when starting point is hole)

        return Line2D.linesIntersect(playerPoint1.getX(), playerPoint1.getY(), playerPoint2.getX(), playerPoint2.getY(),
         p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    private void addInput()
    {
        //TODO finish this function
        
        String[] playerKeys = {"A", "D", "LEFT", "RIGHT", "H", "K"};
        InputMap inputMap = getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);

        for(int playerID = 0; playerID < playerList.size(); playerID++)
        {
            inputMap.put(KeyStroke.getKeyStroke("pressed " + playerKeys[(playerID*2)]), "playerLeftDown" + playerID);
            getActionMap().put("playerLeftDown" + playerID,
                    new turnAction(playerList.get(playerID), playerList.get(playerID).LEFT_DOWN));

            inputMap.put(KeyStroke.getKeyStroke("released " + playerKeys[(playerID*2)]), "playerLeftUp" + playerID);
            getActionMap().put("playerLeftUp" + playerID,
                    new turnAction(playerList.get(playerID), playerList.get(playerID).LEFT_UP));

            inputMap.put(KeyStroke.getKeyStroke("pressed " + playerKeys[(playerID*2)+1]), "playerRightDown" + playerID);
            getActionMap().put("playerRightDown" + playerID,
                    new turnAction(playerList.get(playerID), playerList.get(playerID).RIGHT_DOWN));

            inputMap.put(KeyStroke.getKeyStroke("released " + playerKeys[(playerID*2)+1]), "playerRightUp" + playerID);
            getActionMap().put("playerRightUp" + playerID,
                    new turnAction(playerList.get(playerID), playerList.get(playerID).RIGHT_UP));

        }
    }
}