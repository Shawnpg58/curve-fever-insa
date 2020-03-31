import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class gameWorld extends JPanel
{
    ArrayList<Player> playerList;
    public gameWorld(ArrayList<Player> playerList)
    {
        this.playerList = playerList;

        for (Player p : playerList)
        {
            add(p);
        }
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
}