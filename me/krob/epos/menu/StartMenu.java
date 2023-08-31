package me.krob.epos.menu;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JFrame {
    private JPanel mainPanel;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JPasswordField pinField;
    private JButton clearButton;
    private JButton a0Button;
    private JButton enterButton;

    public StartMenu() {
        super("Start Menu");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        pack();

        setContentPane(mainPanel);
    }
}
