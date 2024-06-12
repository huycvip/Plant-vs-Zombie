package Plant;

import Bullet.PeaBullet;
import Game.GamePlay;

import javax.swing.*;

public class Peashooter extends Plant {
    Timer shootTimer;
    Boolean isShooting;

    public Peashooter(GamePlay gamePlay) {
        super(68, 68, 100, gamePlay);
        life = 5;
        plantImage = new ImageIcon(this.getClass().getResource("/Image/PlantImage/Peashooter.gif")).getImage();
        shootTimer = new Timer(3000, (ActionEvent) -> shoot());
        shootTimer.start();
        isShooting = false;
    }

    protected void shoot() {
        if (isShooting) {
            new PeaBullet(this.getX() + 10, this.getY(), super.gamePlay);
        }
    }

    public void setIsShooting(Boolean b) {
        isShooting = b;
    }
}
