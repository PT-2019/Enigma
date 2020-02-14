package editor.bar.listeners;

import api.net.utils.NetUtility;
import api.ui.CustomColors;
import api.utils.Utility;
import common.hud.EnigmaButton;
import common.hud.EnigmaWindow;
import common.hud.ui.EnigmaButtonUI;
import common.language.GameLanguage;
import common.language.HUDFields;
import common.utils.Logger;
import data.NeedToBeTranslated;
import data.config.Config;
import data.config.EnigmaUIValues;
import game.EnigmaGame;
import org.jetbrains.annotations.Nullable;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Observateur du bouton support
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 01/02/2020
 * @since 5.0 01/02/2020
 */
public class SupportListener extends MenuListener {

	/**
	 * Couleur de fond de base d'un JPanel
	 */
	private static final Color EMPTY_COLOR = new JPanel().getBackground();
	private static final Color LINK_COLOR = CustomColors.DODGER_BLUE;

	/**
	 * Padding du contenu
	 */
	private static final int LEFT = 20, RIGHT = 20, TOP = 10, BOT = 10;
	private static final String OPEN_LINK_FAILED = NeedToBeTranslated.OPEN_LINK_FAILED;

	/**
	 * Fenêtre de dialogue
	 */
	private final JDialog dialog;

	/**
	 * Observateur du bouton support
	 *
	 * @param window window
	 * @param parent parent
	 */
	public SupportListener(EnigmaWindow window, @Nullable Component parent) {
		super(window, parent);
		//préparation de la fenêtre
		this.dialog = new JDialog(window);
		//titre
		this.dialog.setTitle(GameLanguage.gl.get(HUDFields.SUPPORT));
		//icône enigma
		this.dialog.setIconImage(new ImageIcon(Config.LOGO).getImage());
		//cachée tant qu'aucun clic
		this.dialog.setVisible(false);
		//si on la ferme, alors on la cache
		this.dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		//si changement, alors on recalcule sa position pour la centrer
		this.dialog.addWindowStateListener(e -> {
			Window win = e.getWindow();
			win.setLocation(Utility.getAlignCenterPosition(win));
		});

		this.dialog.setLayout(new BorderLayout());

		JPanel content = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(TOP, LEFT, BOT, RIGHT);
		content.add(new JLabel(NeedToBeTranslated.SUPPORT_MESSAGE), gbc);
		gbc.gridy = 1;
		EnigmaButton button = new EnigmaButton(Config.GITHUB_LINK);
		button.addActionListener(new OpenLinkListener());
		EnigmaButtonUI ui = button.getComponentUI();
		final boolean[] SHOWED = new boolean[4];//4 bordures
		//bordure bas uniquement affichée
		SHOWED[EnigmaUIValues.BOTTOM_BORDER] = true;
		final boolean[] HIDDEN = EnigmaUIValues.ALL_BORDER_HIDDEN;
		ui.setAllShowedBorders(HIDDEN, SHOWED, HIDDEN);
		ui.setHoveredBorder(LINK_COLOR);
		ui.setAllBackgrounds(EMPTY_COLOR);
		ui.setAllForegrounds(LINK_COLOR);
		ui.setAllSelectedBackgrounds(EMPTY_COLOR);
		ui.setAllSelectedForegrounds(LINK_COLOR);

		content.add(button, gbc);

		this.dialog.add(content, BorderLayout.CENTER);
		//pack
		this.dialog.pack();
		//centré X et Y
		this.dialog.setLocation(Utility.getAlignCenterPosition(this.dialog));
	}

	@Override
	public void actionPerformed(@Nullable ActionEvent e) {
		this.dialog.setVisible(true);
	}

	private class OpenLinkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				NetUtility.openLinkInBrowser(e.getActionCommand());
			} catch (Exception ex) {
				Logger.printError("SupportListener", "OpenLinkFailed");
				EnigmaGame.getCurrentScreen().showToast(OPEN_LINK_FAILED);
			}
		}
	}
}
