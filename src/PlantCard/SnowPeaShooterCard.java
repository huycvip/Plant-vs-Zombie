package PlantCard;

import Game.GamePlay;
import Plant.SnowPeaShooter;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class SnowPeaShooterCard extends PlantCard {

    public SnowPeaShooterCard(int X, int Y, JLayeredPane layeredPane) {
        super(X, Y, layeredPane);
        PlantCardImage = new ImageIcon(getClass().getResource("/Image/PlantCardImage/SnowPeaCard.png"));
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
            if (!gamePlay.getGrass(cardX, cardY).isAssigned() && gamePlay.getSunLabel().getCurrentSun() >= 175) {
                gamePlay.getGrass(cardX, cardY).setAssignedPlant(new SnowPeaShooter((GamePlay) layeredPane));
            }
        }
        setLocation(X, Y);
    }
}
