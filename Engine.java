import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.time.Clock;
import java.util.*;

public class Engine extends JPanel
{
    ArrayList<Player> playerList;
    //TODO remove this
    KeyStroke playerOneLeftKey = KeyStroke.getKeyStroke("A");
    //
    int numberOfPlayers;
    long lastTimeMillis;
    boolean isPlaying = true;

    public Engine(int playerCount)
    {
        numberOfPlayers = playerCount;
        lastTimeMillis = System.currentTimeMillis();
        setBackground(Color.BLACK);
        playerList = new ArrayList<Player>();
        //placeholder
        Color[] colors = {Color.RED, Color.GREEN};

        for(int i = 0; i <= numberOfPlayers; i++)
        {
            playerList.add(new Player((float)((i+1) * 100), (float)((i+1) * 100), colors[i]));
            add(playerList.get(i));
        }
        //placeholder

        //keybindings (not too clean since it's hard coded variables)
        //TODO replace with a add input function
        //player 1
        int playerID = 0;
        InputMap inputMap = getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        
        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "playerOneLeftDown");
        getActionMap().put("playerOneLeftDown", new turnAction(playerList.get(playerID), playerList.get(playerID).LEFT_DOWN));
        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "playerOneRightDown");
        getActionMap().put("playerOneRightDown", new turnAction(playerList.get(playerID), playerList.get(playerID).RIGHT_DOWN));
        inputMap.put(KeyStroke.getKeyStroke("released A"), "playerOneLeftUp");
        getActionMap().put("playerOneLeftUp", new turnAction(playerList.get(playerID), playerList.get(playerID).LEFT_UP));
        inputMap.put(KeyStroke.getKeyStroke("released D"), "playerOneRightUp");
        getActionMap().put("playerOneRightUp", new turnAction(playerList.get(playerID), playerList.get(playerID).RIGHT_UP));
        
        
        


        //player 2

        //player 3
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(Player i : playerList)
        {
            i.paint(g);
        }
        repaint();
    }
    
    public void update(float dt)
    {
        for(int i = 0; i < playerList.size(); i++)
        {
            Player p = playerList.get(i);
            p.update(dt);
            if(checkCollision(p))
            {
                p.kill();
            }

            if(!p.isAlive())
            {
                playerList.remove(i);
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
        boolean result = false;
        if(player.getWall().size() < 4)
        {
            return false;
        }

        ArrayList<Point> playerWall = player.getWall();
        Point playerPoint1 = playerWall.get(playerWall.size()-1);
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
        /*
        String[] playerKeys = {"A", "D", "left", "right", "H", "K"};
        InputMap inputMap = getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);

        inputMap.put(KeyStroke.getKeyStroke("pressed A"), "playerOneLeftDown");
        getActionMap().put("playerOneLeftDown", new turnAction(playerList.get(playerID), playerList.get(playerID).LEFT_DOWN));
        inputMap.put(KeyStroke.getKeyStroke("pressed D"), "playerOneRightDown");
        getActionMap().put("playerOneRightDown", new turnAction(playerList.get(playerID), playerList.get(playerID).RIGHT_DOWN));
        inputMap.put(KeyStroke.getKeyStroke("released A"), "playerOneLeftUp");
        getActionMap().put("playerOneLeftUp", new turnAction(playerList.get(playerID), playerList.get(playerID).LEFT_UP));
        inputMap.put(KeyStroke.getKeyStroke("released D"), "playerOneRightUp");
        getActionMap().put("playerOneRightUp", new turnAction(playerList.get(playerID), playerList.get(playerID).RIGHT_UP));
        
        */
    }
}