import java.awt.event.*;

class gameLoopListener implements ActionListener
{
    private CurveFever window;

    public gameLoopListener(CurveFever window)
    {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        window.engine.run();
    }
}