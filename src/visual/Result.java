package visual;

import control.Configuration;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Result extends JPanel {
    private Configuration config;

    public Result(Configuration config) {
        this.config = config;
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(config.getWidthPS(),
                config.getHeightPS() + config.getPLine()));

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        int y = 30;
        int x = 250;

        Map<String, Integer> result = new HashMap<String, Integer>();
        for (int i = 0; i < config.getCountOfPlayers(); i++) {
            result.put(String.valueOf(i), 1);
        }
        for (int i = 0; i < config.getCountOfPlayers(); i++) {
            for (int j = 0; j < config.getCountOfPlayers(); j++) {
                if (i != j) {
                    if (config.getScores(i) < config.getScores(j)) {
                        String key = String.valueOf(i);
                        int old = result.get(key);
                        result.remove(key);
                        result.put(key, old + 1);
                    }
                }
            }
        }

        for (int i = 0; i < config.getCountOfPlayers(); i++) {
            String newGame = "Player " + (i + 1) + ": " + result.get(String.valueOf(i)) + " place (" +
                    config.getScores(i) + " scores)";
            Font font = new Font("Times New Roman", Font.BOLD, 25);
            g.setColor(Color.WHITE);
            g.setFont(font);
            g.drawString(newGame, x, y);
            y += 30;
        }
    }
}
