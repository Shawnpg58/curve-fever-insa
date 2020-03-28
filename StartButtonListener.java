import java.awt.event.*;

public class StartButtonListener implements ActionListener
{
    private MainMenu menu;
    int function;

    StartButtonListener(MainMenu menu, int function)
    {
        this.menu = menu;
        this.function = function;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(function == 0)
        {
            menu.selectPlayers();
        }
        else
        {
            menu.startGame(function);
        }
    }
}