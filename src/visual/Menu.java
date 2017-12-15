package visual;

import control.Configuration;
import control.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    private JTextField bigField;
    private Main main;
    private Configuration configuration;

    public Menu(Configuration config) {
        configuration = config;
        main = new Main();
        JButton startButton = new JButton("Start");
        add(startButton);
        setPreferredSize(new Dimension(config.getWidthPS(),
                config.getHeightPS() + config.getPLine()));
        ActionListener actionListener = new TestActionListener();
        startButton.addActionListener(actionListener);
        bigField = new JTextField(20);
        bigField.setToolTipText("Введите количество игроков (больше одного)");
        bigField.setFont(new Font("Dialog", Font.PLAIN, 14));
        bigField.setHorizontalAlignment(JTextField.RIGHT);
        add(bigField);
        JLabel label = new JLabel(bigField.getToolTipText());
        add(label);
    }

    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!bigField.getText().equals("") && Integer.parseInt(bigField.getText()) > 1) {
                main.setChoice(false);
                configuration.setCountOfPlayers(Integer.parseInt(bigField.getText()));
                configuration.setCountOfRounds();
            }
        }
    }
}
