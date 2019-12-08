package desktopLauncher;

import editor.EditorLauncher;
import editor.utils.Button;
import editor.utils.Window;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Lance la version pc de l'application
 */
public class DesktopLauncher implements Runnable {

    //cette méthode contient le code de lancement de l'éditeur
    //après chargement de la libgdx par SwingUtilities.invokeLater(Runnable)
    @Override
    public void run() {

        Window window = new Window("Enigma");
        window.setSize(Window.HALF_SCREEN_SIZE);
        window.setLocation(Window.CENTER);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowManager(window));

        JPanel background = new JPanel();
        background.setOpaque(true);
        background.setBackground(Color.DARK_GRAY);
        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        int inset = 50;

        JLabel title = new JLabel("ENIGMA");
        title.setOpaque(true);
        title.setBackground(new Color(100, 100 ,100));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setMaximumSize(title.getSize());
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.insets = new Insets(inset / 2, inset / 2, inset / 2, inset / 2);
        background.add(title, gbc);

        Button button = new Button("Editer une map");
        button.addActionListener(new LauncherManagement(new EditorLauncher()));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(inset, inset, inset, inset / 4);
        background.add(button, gbc);

        button = new Button("Jouer");
        button.addActionListener(new LauncherManagement(new EditorLauncher()));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(inset, inset, inset, inset / 4);
        background.add(button, gbc);

        window.add(background);

        window.setVisible(true);
    }

    /**
     * Lance la version pc de l'application
     * @param args aucun
     */
    public static void main(String[] args) {
        //appelle après initialisation de la libgdx, l'éditeur
        SwingUtilities.invokeLater(new DesktopLauncher());
    }
}
