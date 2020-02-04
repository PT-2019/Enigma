package common;

import api.Application;
import api.ui.base.WindowSize;
import api.utils.annotations.NeedPatch;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import common.hud.EnigmaButton;
import common.hud.EnigmaLabel;
import common.hud.EnigmaPanel;
import common.hud.EnigmaWindow;
import common.hud.ui.EnigmaLabelUI;
import common.language.GameLanguage;
import common.language.HUDFields;
import common.utils.Logger;
import data.config.EnigmaUIValues;
import editor.EditorLauncher;
import editor.EditorScreen;
import game.EnigmaGameLauncher;

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
	 * La fenêtre du lancer
	 *
	 * @since 4.1
	 */
	private EnigmaWindow window;

	/**
	 * Tentative de patch des erreurs de la libgdx
	 * Lancement et fermeture
	 */
	@NeedPatch
	private static EnigmaWindow c;

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
			if (c != null) {
				c.close();
				c = null;
			}
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
		if (RUNNING_APP != null) {
			try {
				RUNNING_APP.stop();
			} catch (Exception e) {
				Logger.printError("DesktopLauncher","Fermeture de la libgdx ratée (last app).");
			}
		}

		try {
			launcher.window.dispose();
		} catch (Exception e){
			Logger.printError("DesktopLauncher","Fermeture de la libgdx ratée (last window).");
		}

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
		final int inset = 50;

		//window
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

		/*
			|---------|   |---------|
			|   PLAY  |   |         |
			|---------|   |         |
					      |   IMG   |
			|---------|   |         |
			|  EDITOR |   |         |
			|---------|   |---------|
		 */

		//panneau pour le contenu du lanceur
		EnigmaPanel content = new EnigmaPanel();
		content.setLayout(new GridLayout(1, 2));

		EnigmaPanel background = this.window.getContentSpace();
		background.getComponentUI().setAllBorders(EnigmaUIValues.ENIGMA_COMBOBOX_BORDER);
		background.setLayout(new CardLayout());
		background.add(content, BorderLayout.CENTER);

		//Cette chose charge la libgdx au lancement du jeu
		//on ne peut pas directement l'attacher a this.window
		//ULTRA CHIANT
		c = new EnigmaWindow();
		c.setIfAskBeforeClosing(false);
		if (!System.getProperty("os.name").equals("Linux")) background.add(new EditorScreen(c, false));
		else c = null;

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