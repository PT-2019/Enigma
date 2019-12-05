package desktopLauncher;

import editor.EditorLauncher;
import editor.utils.Button;
import editor.utils.Window;

import javax.swing.*;
import java.awt.*;

/**
 * Lance la version pc de l'application
 */
public class DesktopLauncher implements Runnable {

    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    private static final Color ELEMENT_COLOR = new Color(100, 100 ,100);
    private static final Color TEXT_COLOR = Color.WHITE;

    //cette méthode contient le code de lancement de l'éditeur
    //après chargement de la libgdx par SwingUtilities.invokeLater(Runnable)
    @Override
    public void run() {

        Window window = new Window("Enigma");
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowManager(window));

        JPanel background = new JPanel();
        background.setOpaque(true);
        background.setBackground(BACKGROUND_COLOR);
        background.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        int insetW = 400;
        int insetH = 10;

        JLabel title = new JLabel("ENIGMA");
        title.setOpaque(true);
        title.setBackground(ELEMENT_COLOR);
        title.setForeground(TEXT_COLOR);
        title.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 3;
        gbc.weighty = 3;
        gbc.insets = new Insets(insetH, insetH, insetH, insetH);
        background.add(title, gbc);

        Button button = new Button("Editeur de maps");
        button.setBackground(BACKGROUND_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setBorder(Color.WHITE);
        button.addActionListener(new LauncherManagement(new EditorLauncher()));
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(insetH, insetW, insetH, insetW);
        background.add(button, gbc);

        button = new Button("Solo");
        button.setBackground(BACKGROUND_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setBorder(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(insetH, insetW, insetH, insetW);
        background.add(button, gbc);

        button = new Button("Multijoueur");
        button.setBackground(BACKGROUND_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setBorder(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(insetH, insetW, insetH, insetW);
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
