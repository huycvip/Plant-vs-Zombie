package Game;

import javax.swing.*;
import java.util.Random;

public class SunProducer {
    Timer sunProduceTimer;
    JLayeredPane layeredPane;
    Random xRandom;

    public SunProducer(JLayeredPane layeredPane) {
        this.layeredPane = layeredPane;
        sunProduceTimer = new Timer(10000, (ActionEvent) -> produceSun());
        xRandom = new Random();
        sunProduceTimer.start();
    }

    public void produceSun() {
        int x = xRandom.nextInt(1000);
        if (x < 260)
            x += 260;
        new Sun(x, 0, layeredPane);
    }
}
