package visual;

import control.Configuration;
import control.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    JTextField bigField;
    Main main;
    Configuration configuration;

    public Menu(Configuration config) {
        configuration = config;
        JButton startButton = new JButton("Start");
        add(startButton);
        setPreferredSize(new Dimension(config.getWidthPS(),
                config.getHeightPS() + config.getPLine()));
        ActionListener actionListener = new TestActionListener();
        startButton.addActionListener(actionListener);
        bigField = new JTextField(25);
        bigField.setToolTipText("Длиное поле");
        bigField.setFont(new Font("Dialog", Font.PLAIN, 14));
        bigField.setHorizontalAlignment(JTextField.RIGHT);
        add(bigField);
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            main.setChoice(false);
            configuration.setCountOfPlayers(Integer.parseInt(bigField.getText()));
            configuration.setCountOfRounds();
        }
    }
}
