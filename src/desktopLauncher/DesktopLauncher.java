package desktopLauncher;

import editor.EditorLauncher;
import editor.utils.EnigmaLabel;
import editor.utils.EnigmaPanel;
import editor.utils.Window;
import editor.utils.ui.EnigmaButtonUI;
import editor.utils.EnigmaButton;
import editor.utils.ui.EnigmaLabelUI;

import javax.swing.*;
import java.awt.*;

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
        window.setWindowBackground(Color.DARK_GRAY);

        JPanel background = window.getContentSpace();
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
        EnigmaLabel p = new EnigmaLabel();
        EnigmaLabelUI pui = new EnigmaLabelUI();
        pui.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        pui.setAllBackgrounds(Color.RED,Color.GREEN,Color.BLACK);
        p.setLabelUI(pui);
        background.add(p, gbc);

        EnigmaButton button = new EnigmaButton("Editer une map");
        button.addActionListener(new LauncherManagement(new EditorLauncher()));
        button.setButtonUI(new EnigmaButtonUI());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(inset, inset, inset, inset / 4);
        background.add(button, gbc);

        button = new EnigmaButton("Jouer");
        button.addActionListener(new LauncherManagement(new EditorLauncher()));
        button.setButtonUI(new EnigmaButtonUI());
        gbc.gridy = 2;
        background.add(button, gbc);

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
