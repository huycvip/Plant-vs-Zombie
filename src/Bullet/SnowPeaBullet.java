package Bullet;

import Game.GamePlay;
import Game.Sound;
import Game.ZombieController;
import Zombie.Zombie;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;


public class SnowPeaBullet extends Bullet{


    public SnowPeaBullet(int x , int y , GamePlay gamePlay){
        super(x, y ,gamePlay);
        this.setIcon(new ImageIcon(this.getClass().getResource("/Image/BulletImage/SnowBullet.png")));
    }


    public void collisionDetection() {
        ZombieController zombieController = gamePlay.getZombieController();
        Iterator iterator = zombieController.getZombieArrayList(row).iterator();
        while (iterator.hasNext()) {
            Zombie zombie = (Zombie) iterator.next();
            if ((zombie.getLabelX() - this.labelX) < 5 && zombie.life > 0 && (zombie.getLabelX() - this.labelX) > -5) {
                Sound sound = new Sound(this.getClass().getResource("/Music/tap.wav"),false);
                sound.start();
                zombie.life -= 1;
                zombie.slow();
                gamePlay.remove(this);
                isLive = false;
                advanceTimer.stop();
                collisionTimer.stop();
                System.gc();
            }
        }
    }
}


