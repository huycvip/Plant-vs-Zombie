package Zombie;

import javax.swing.*;

public class NormalZombie extends Zombie {

    public NormalZombie(int LabelX, int LabelY, JLayeredPane layeredPane) {
        super(LabelX, LabelY, 83, 123, layeredPane);
        super.life = 10;
        zombieNormalAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/ZombieAdvanceImage.gif")).getImage();
        zombieNormalAttackImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/ZombieAttackImage.gif")).getImage();
        zombieFrozenAdvanceImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/frozenZombieAdvanceImage.gif")).getImage();
        zombieFrozenAttackImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/frozenZombieAttackImage.gif")).getImage();
        zombieDeadImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/ZombieDeadImage.gif")).getImage();
        zombieHeadImage = new ImageIcon(this.getClass().getResource("/Image/ZombieImage/NormalZombieImage/ZombieHead.gif")).getImage();
    }
}
