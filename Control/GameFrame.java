package Control;

import javax.swing.*;

public class GameFrame extends JFrame {
    
    GamePanel panel;

    GameFrame(){

        panel = new GamePanel();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false); 
        this.setIconImage(new ImageIcon("Image/GUI/pvz_logo.png").getImage());
        this.setTitle("Plants Vs Zombies"); 
        
        this.add(panel); 
        this.pack();

        this.setLocationRelativeTo(null);
        this.setVisible(true); 
        this.setLayout(null);
    }

}