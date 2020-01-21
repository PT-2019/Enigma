package starter;

import api.Application;
import api.hud.WindowSize;
import api.hud.components.CustomWindow;
import api.utils.LoadGameLibgdxApplication;
import editor.hud.EnigmaButton;
import editor.hud.EnigmaLabel;
import editor.hud.EnigmaPanel;
import editor.hud.EnigmaWindow;
import editor.hud.ui.EnigmaLabelUI;
import editor.screens.EditorScreen;
import editor.utils.lang.GameLanguage;
import editor.utils.lang.fields.HUDFields;
import game.EnigmaGame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Lance la version pc de l'application
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.2
 * @since 1.0
 */
public class DesktopLauncher implements Runnable {

	/**
	 * Bouton de l'application en cours, null si aucune
	 *
	 * @since 4.0
	 */
	private static Application RUNNING_APP;

	/**
	 * Le bouton pour lancer le jeu
	 *
	 * @since 4.0
	 */
	private static EnigmaButton PLAY_BUTTON;

	/**
	 * Le bouton pour lancer l'éditeur
	 *
	 * @since 4.0
	 */
	private static EnigmaButton EDIT_BUTTON;

	/**
	 * l'instance unique du lanceur de la version pc de l'application
	 *
	 * @since 4.1
	 */
	private static DesktopLauncher launcher;
	private static EnigmaWindow c;
	/**
	 * La fenêtre du lancer
	 *
	 * @since 4.1
	 */
	private EnigmaWindow window;

	/**
	 * Crée le lanceur de la version pc de l'application
	 *
	 * @since 4.0
	 */
	private DesktopLauncher() {
		RUNNING_APP = null;
		GameLanguage.init();
		LoadGameLibgdxApplication.setGame(EnigmaGame.getInstance());
		PLAY_BUTTON = new EnigmaButton(GameLanguage.gl.get(HUDFields.PLAY));
		PLAY_BUTTON.addActionListener(new LauncherManagement(EnigmaGameLauncher.getInstance()));

		EDIT_BUTTON = new EnigmaButton(GameLanguage.gl.get(HUDFields.EDITOR));
		EDIT_BUTTON.addActionListener(new LauncherManagement(EditorLauncher.getInstance()));
	}

	/**
	 * Retourne l'instance unique du lanceur de la version pc de l'application
	 *
	 * @return l'instance unique du lanceur de la version pc de l'application
	 * @since 4.1
	 */
	public static DesktopLauncher getInstance() {
		if (DesktopLauncher.launcher == null) {
			DesktopLauncher.launcher = new DesktopLauncher();
		}
		return DesktopLauncher.launcher;
	}

	/**
	 * Lance une application
	 *
	 * @param app l'application a lancer
	 * @since 4.0
	 */
	public static void startApp(Application app) {
		if (RUNNING_APP == null) {
			if (c != null) {
				c.close();
				c = null;
			}
			RUNNING_APP = app;
			RUNNING_APP.start();
			PLAY_BUTTON.setText(GameLanguage.gl.get(HUDFields.RUNNING));
			EDIT_BUTTON.setText(GameLanguage.gl.get(HUDFields.RUNNING));
		}
	}

	/**
	 * Ferme l'application en cours
	 *
	 * @since 4.0
	 */
	public static void closeRunningApp() {
		if (RUNNING_APP != null) {
			RUNNING_APP = null;
			PLAY_BUTTON.setText(GameLanguage.gl.get(HUDFields.PLAY));
			EDIT_BUTTON.setText(GameLanguage.gl.get(HUDFields.EDITOR));
		}
	}

	/**
	 * Ferme toute l'application
	 *
	 * @since 4.2
	 */
	public static void close() {
		//Dernière, on quitte
		if (c != null) {
			c.close();
			c = null;
		}
		if (RUNNING_APP != null) {
			RUNNING_APP.stop();
		}

		launcher.window.dispose();
		System.exit(0);
	}

	/**
	 * Lance le lanceur de la version pc de l'application
	 *
	 * @since 1.0
	 */
	//cette méthode contient le code de lancement de l'éditeur
	//après chargement de la libgdx par SwingUtilities.invokeLater(Runnable)
	@Override
	public void run() {
		final String OS = "Linux";
		this.window = new EnigmaWindow();
		this.window.setSize(WindowSize.HALF_SCREEN_SIZE);
		this.window.setLocation(CustomWindow.CENTER);
		this.window.setWindowBackground(Color.DARK_GRAY);
		this.window.setMinimumSize(WindowSize.HALF_SCREEN_SIZE);
		this.window.setIfAskBeforeClosing(false);

		//Cette chose charge la libgdx au lancement du jeu
		//on ne peut pas directement l'attacher a this.window
		c = new EnigmaWindow();
		c.setIfAskBeforeClosing(false);

		//Card layout pour cacher le chargement de la libgdx
		EnigmaPanel background = this.window.getContentSpace();
		CardLayout cardLayout = new CardLayout();
		background.setLayout(cardLayout);

		EnigmaPanel p1 = new EnigmaPanel();

		background.add(p1);
		if (!System.getProperty("os.name").equals(OS))
			background.add(new EditorScreen(c, false));
		else
			c = null;


		p1.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		final int inset = 50;

		EnigmaLabel info = new EnigmaLabel("ENIGMA");
		EnigmaLabelUI infoUI = new EnigmaLabelUI();
		Color infoColor = new Color(100, 100, 100);
		infoUI.setAllBackgrounds(infoColor, infoColor, infoColor);
		infoUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		info.setComponentUI(infoUI);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 2;
		gbc.weighty = 1;
		gbc.insets = new Insets(inset / 2, inset / 2, inset / 2, inset / 2);
		p1.add(info, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(inset, inset, inset, inset / 4);
		p1.add(PLAY_BUTTON, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(inset, inset, inset, inset / 4);
		p1.add(EDIT_BUTTON, gbc);

		this.window.setVisible(true);
	}

	public EnigmaWindow getWindow() {
		return this.window;
	}
}
