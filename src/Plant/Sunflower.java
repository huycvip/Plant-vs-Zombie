package Plant;

import Game.GamePlay;
import Game.Sun;

import javax.swing.*;
import javax.swing.Timer;

public class Sunflower extends Plant {
    Timer sunProduceTimer;

    public Sunflower(GamePlay gamePlay) {
        super(70, 80, 50, gamePlay);
        life = 5;
        plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/Sunflower.gif")).getImage();
        sunProduceTimer = new Timer(15000, (e) -> produceSun());
        sunProduceTimer.start();
    }

    public void produceSun() {
        new Sun(getX() - 30, getY() + 20, gamePlay).getDropTimer().stop();
    }
}
