package Game;

import javax.swing.*;

public class SunLabel extends JLabel {

    int total;

    public SunLabel(JLayeredPane layeredPane) {
        total = 100;
        setVisible(true);
        setText(Integer.toString(total));
        setSize(100, 100);
        setLocation(28, 25);
        layeredPane.add(this);
        layeredPane.setLayer(this, 3);
    }

    public int getCurrentSun() {
        return total;
    }

    public void addCurrentSun(int x) {
        total += x;
        setText(Integer.toString(total));
    }
}
