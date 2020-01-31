package general;

import api.Application;
import api.hud.base.WindowSize;
import data.config.EnigmaUIValues;
import editor.EditorLauncher;
import editor.EditorScreen;
import game.EnigmaGameLauncher;
import general.hud.EnigmaButton;
import general.hud.EnigmaLabel;
import general.hud.EnigmaPanel;
import general.hud.EnigmaWindow;
import general.hud.ui.EnigmaLabelUI;
import general.language.GameLanguage;
import general.language.HUDFields;

import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Lance la version pc de l'application
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 1.0
 */
public class DesktopLauncher implements Runnable {

	private static final String RUNNING = GameLanguage.gl.get(HUDFields.RUNNING);
	private static final String GAME = GameLanguage.gl.get(HUDFields.PLAY);
	private static final String EDITOR = GameLanguage.gl.get(HUDFields.EDITOR);

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

	/**
	 * Une fenêtre temporaire pour lancer l'application
	 */
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
	private static void startApp(Application app) {
		if (RUNNING_APP == null) {
			//ferme l'application au démarrage d'une nouvelle
			if (c != null) {
				c.close();
				c = null;
			}
			RUNNING_APP = app;
			RUNNING_APP.start();
			RUNNING_APP.getWindow().addWindowListener(new AppClosingManager());
			PLAY_BUTTON.setText(RUNNING);
			EDIT_BUTTON.setText(RUNNING);
		}
	}

	/**
	 * Ferme l'application en cours
	 *
	 * @since 4.0
	 */
	private static void closeRunningApp() {
		if (RUNNING_APP != null) {
			RUNNING_APP = null;
			PLAY_BUTTON.setText(GAME);
			EDIT_BUTTON.setText(EDITOR);
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
	 * Définit le cursor de l'application
	 *
	 * @param cursor le cursor
	 */
	public static void setCursor(Cursor cursor) {
		DesktopLauncher.getInstance().getWindow().setCursor(cursor);
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
		this.window.setLocation(EnigmaWindow.CENTER);
		this.window.setWindowBackground(Color.DARK_GRAY);
		this.window.setMinimumSize(WindowSize.HALF_SCREEN_SIZE);
		this.window.setIfAskBeforeClosing(false);

		//boutons
		PLAY_BUTTON = new EnigmaButton(GameLanguage.gl.get(HUDFields.PLAY));
		PLAY_BUTTON.addActionListener(new LauncherManagement(EnigmaGameLauncher.getInstance()));

		EDIT_BUTTON = new EnigmaButton(GameLanguage.gl.get(HUDFields.EDITOR));
		EDIT_BUTTON.addActionListener(new LauncherManagement(EditorLauncher.setEditor(this.window)));

		//Cette chose charge la libgdx au lancement du jeu
		//on ne peut pas directement l'attacher a this.window
		c = new EnigmaWindow();
		c.setIfAskBeforeClosing(false);

		//Card layout pour cacher le chargement de la libgdx
		EnigmaPanel background = this.window.getContentSpace();
		background.getComponentUI().setAllBorders(EnigmaUIValues.ENIGMA_COMBOBOX_BORDER);
		CardLayout cardLayout = new CardLayout();
		background.setLayout(cardLayout);

		EnigmaPanel content = new EnigmaPanel();
		background.add(content);

		//ajoute écran caché du cardLayout un copie de l'éditor
		if (!System.getProperty("os.name").equals(OS)) background.add(new EditorScreen(c, false));
		else c = null;


		//p1.setLayout(new GridBagLayout());
		content.setLayout(new GridLayout(1, 2));

		/*
			|---------|   |---------|
			|   PLAY  |   |         |
			|---------|   |         |
					      |   IMG   |
			|---------|   |         |
			|  EDITOR |   |         |
			|---------|   |---------|
		 */
		final int inset = 50;
		GridBagConstraints gbc = new GridBagConstraints();

		//boutons
		EnigmaPanel left = new EnigmaPanel(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(inset, inset, inset, inset);
		left.add(PLAY_BUTTON, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(inset, inset, inset, inset);
		left.add(EDIT_BUTTON, gbc);

		//img
		EnigmaPanel right = new EnigmaPanel(new BorderLayout());
		right.setBorder(new EmptyBorder(inset, inset, inset, inset));
		EnigmaLabel info = new EnigmaLabel("ENIGMA");
		EnigmaLabelUI infoUI = new EnigmaLabelUI();
		Color infoColor = new Color(100, 100, 100);
		infoUI.setAllBackgrounds(infoColor, infoColor, infoColor);
		infoUI.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		info.setComponentUI(infoUI);
		right.add(info);

		//addToContainer
		content.add(left);
		content.add(right);

		this.window.setVisible(true);
	}

	public EnigmaWindow getWindow() {
		return this.window;
	}


	/**
	 * Lance une application par la lanceur du bureau lors de l'appui
	 * sur un bouton
	 *
	 * @author Louka DOZ
	 * @version 3.0
	 * @since 3.0
	 */
	private static class LauncherManagement implements ActionListener {

		/**
		 * L'application à lancer
		 */
		private Application app;

		/**
		 * Lance une application par la lanceur du bureau lors de l'appui
		 * sur un bouton
		 *
		 * @param app l'application à lancer
		 * @since 3.0
		 */
		LauncherManagement(Application app) {
			this.app = app;
		}

		/**
		 * Lance une application par la lanceur du bureau lors de l'appui
		 * sur un bouton
		 *
		 * @param actionEvent l'évènement de clic sur le bouton
		 * @since 3.0
		 */
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			DesktopLauncher.startApp(this.app);
		}
	}

	/**
	 * Ferme un sous-application en appelant {@link DesktopLauncher#closeRunningApp()}.
	 * <p>
	 * C'est une classe interne car seulement cette classe contrôle l'ajout de ce listener
	 * et elle l'ajoute automatiquement pour éviter les oublis.
	 *
	 * @author Louka DOZ
	 * @version 4.0
	 * @since 3.0
	 */
	private static class AppClosingManager extends WindowAdapter {

		@Override
		public void windowClosed(WindowEvent windowEvent) {
			DesktopLauncher.closeRunningApp();
		}

	}

}