package PlantCard;

import Game.GamePlay;
import Plant.CherryBomb;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class CherryBombCard extends PlantCard {
    // Constructor
    public CherryBombCard(int X, int Y, JLayeredPane layeredPane) {
        super(X, Y, layeredPane);
        PlantCardImage = new ImageIcon(getClass().getResource("/Image/PlantCardImage/CherryBombCard.png"));
        setIcon(PlantCardImage);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int cardX = getX() + e.getX();
        int cardY = getY() + e.getY();
        int grassX = 260;
        int grassY = 90;
        int grassWidth = 80 * 9;
        int grassHeight = 100 * 5;

        if (cardX > grassX && cardY > grassY && cardX < grassX + grassWidth && cardY < grassY + grassHeight) {
            GamePlay gamePlay = (GamePlay) layeredPane;
            if (!gamePlay.getGrass(cardX, cardY).isAssigned() && gamePlay.getSunLabel().getCurrentSun() >= 150) {
                gamePlay.getGrass(cardX, cardY).setAssignedPlant(new CherryBomb((GamePlay) layeredPane));
            }
        }
        setLocation(X, Y);
    }
}
