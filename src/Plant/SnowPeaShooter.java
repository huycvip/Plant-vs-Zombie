package Plant;

import Bullet.SnowPeaBullet;
import Game.GamePlay;
import javax.swing.ImageIcon;

public class SnowPeaShooter extends Peashooter {

    public SnowPeaShooter(GamePlay gamePlay) {
        super(gamePlay);
        setSize(70, 70);
        plantImage = new ImageIcon(getClass().getResource("/Image/PlantImage/SnowPea.gif")).getImage();
    }

    @Override
    protected void shoot() {
        if (isShooting) {
            new SnowPeaBullet(getX() + 40, getY(), gamePlay);
        }
    }
}
