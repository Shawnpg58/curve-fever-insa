import javax.swing.*;
import java.awt.event.*;

public class turnAction extends AbstractAction
{
    private Player player;
    private int turnDirection;

    public turnAction(Player player, int turnDirection)
    {
        this.player = player;
        this.turnDirection = turnDirection;
    }
 
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(turnDirection == player.LEFT_DOWN) { player.pressLeft();}
        if(turnDirection == player.LEFT_UP) { player.releaseLeft();}
        if(turnDirection == player.RIGHT_DOWN) {player.pressRight();}
        if(turnDirection == player.RIGHT_UP) {player.releaseRight();}
    }
}