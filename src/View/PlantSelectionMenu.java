package View;

import javax.swing.*;
import java.awt.*;

public class PlantSelectionMenu extends JPanel {
    public PlantSelectionMenu(GamePanel gamePanel) {
        setLayout(new FlowLayout());

        // Create buttons for each plant type
        JButton peashooterButton = new JButton("Peashooter");
        peashooterButton.addActionListener(e -> gamePanel.setCurrentPlant("Peashooter"));
        add(peashooterButton);

        JButton wallnutButton = new JButton("Wall-Nut");
        wallnutButton.addActionListener(e -> gamePanel.setCurrentPlant("Wall-nut"));
        add(wallnutButton);

        JButton sunflowerButton = new JButton("Sunflower");
        sunflowerButton.addActionListener(e -> gamePanel.setCurrentPlant("Sunflower"));
        add(sunflowerButton);
    }
}