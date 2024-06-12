package Bullet;

import Game.GamePlay;

import javax.swing.*;

public class PeaBullet extends Bullet{


    public PeaBullet(int x , int y , GamePlay g){
        super(x, y ,g);
        this.setIcon(new ImageIcon(this.getClass().getResource("/Image/BulletImage/PeaBullet.png")));
    }
}
