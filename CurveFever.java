import javax.swing.*;
import java.awt.*;

public class CurveFever extends JFrame
{
    JButton startGameButton;
    MainMenu menu;
    Engine engine;
    Timer gameLoopTimer;
    final int TIMER_LOOP_DURATION = 16;
    final int WINDOW_WIDTH = 640;
    final int WINDOW_HEIGHT = 480;

    public CurveFever()
    {
        super("Curve Fever");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        menu = new MainMenu(this);
        add(menu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {   
        new CurveFever();
    }

    public void startGame(int players)
    {
        remove(menu);
        repaint();

        engine = new Engine(players, WINDOW_WIDTH, WINDOW_HEIGHT);
        add(engine);
        revalidate();
        
        new Timer(TIMER_LOOP_DURATION, new gameLoopListener(this)).start();
    }
}