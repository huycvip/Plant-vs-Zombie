package Plant;

import Game.GamePlay;
import Game.Sound;
import Zombie.Zombie;

import javax.swing.*;
import java.util.Iterator;

public class PotatoMine extends Plant {
    Timer stateChangeTimer;
    Timer bombTimer;
    public enum State {
        UNDERGROUND, READY, BOMB, DIE;
    }
    State state;

    public PotatoMine(GamePlay gamePlay) {
        super(36, 30, 25, gamePlay);
        super.px = -20;
        super.py = -23;
        state = State.UNDERGROUND;
        life = 5;
        plantImage = new ImageIcon(getClass().getResource("/Image/PlantImage/PotatoMineNotReady.png")).getImage();
        stateChangeTimer = new Timer(15000, (ActionEvent) -> stateChange());
        stateChangeTimer.start();
        bombTimer = new Timer(500, (ActionEvent) -> bomb());
    }

    public void stateChange() {
        super.px = -1;
        super.py = 0;
        setLocation(getX() - 10, getY() - 10);
        plantImage = new ImageIcon(getClass().getResource("/Image/PlantImage/PotatoMine.gif")).getImage();
        setSize(74, 53);
        repaint();
        state = State.READY;
        stateChangeTimer.stop();
        bombTimer.start();
    }

    public void timerStop(){
        stateChangeTimer.stop();
        bombTimer.stop();
    }
    
    public void bomb() {
        if (state == State.READY) {
            Iterator zombiesIterator = gamePlay.getZombieController().getZombieArrayList((getY() - 90) / 100 + 1).iterator();
            while (zombiesIterator.hasNext()) {
                Zombie zombie = (Zombie) zombiesIterator.next();
                if ((zombie.getLabelX() - getX()) < 35) {
                    Sound bombSound = new Sound(getClass().getResource("/Music/explosion.wav"), false);
                    bombSound.start();
                    setSize(131, 92);
                    plantImage = new ImageIcon(getClass().getResource("/Image/PlantImage/PotatoMine_mashed.gif")).getImage();
                    repaint();
                    zombie.setState(Zombie.State.DEAD);
                    zombiesIterator.remove();
                    gamePlay.remove(zombie);
                    System.gc();
                    state = State.BOMB;
                }
            }
        } else if (state == State.BOMB) {
            setSize(74, 53);
            bombTimer.stop();
            gamePlay.getGrass(getX(), getY()).removeAssignedPlant();
            state = State.DIE;
        } else if (state == State.DIE) {
            bombTimer.stop();
        }
    }

}
