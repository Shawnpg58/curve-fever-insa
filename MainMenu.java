import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel
{
    public static final int SELECT_PLAYERS = 0;
    public static final int TWO_PLAYERS = 1;
    public static final int THREE_PLAYERS = 2;

    CurveFever window;

    private JButton startGameButton;
    

    public MainMenu(CurveFever window)
    {
        setLayout(new FlowLayout());
        this.window = window;
        startGameButton = new JButton("Start");
        startGameButton.addActionListener(new StartButtonListener(this, SELECT_PLAYERS));
        add(startGameButton);
    }

    public void selectPlayers()
    {
        removeAll();
        repaint();

        JButton twoPlayerButton = new JButton("Two Players");
        twoPlayerButton.addActionListener(new StartButtonListener(this, TWO_PLAYERS));

        JButton threePlayerButton = new JButton("Three Players");
        threePlayerButton.addActionListener(new StartButtonListener(this, THREE_PLAYERS));

        add(twoPlayerButton);
        add(threePlayerButton);
        revalidate();
    }

    public void startGame(int playerCount)
    {
        window.startGame(playerCount);
    }
}