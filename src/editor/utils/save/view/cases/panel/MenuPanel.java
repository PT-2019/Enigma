package editor.utils.save.view.cases.panel;

import editor.utils.save.view.listeners.ListenerMenu;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Cette classe représente un Menu lors de la création d'enigme
 */
public class MenuPanel extends JPanel {

    public MenuPanel(String title,String helpText){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JButton prev = new JButton("Retour");
        JTextArea titleView = new JTextArea(title);
        titleView.setEditable(false);
        JLabel help = new JLabel(new ImageIcon("assets/hud/help.png"));
        help.addMouseListener(new ListenerMenu(helpText));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        this.add(prev,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        this.add(titleView,gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        this.add(help,gbc);
    }
}
