package editor.bar;

import api.utils.Utility;
import common.hud.EnigmaButton;
import common.hud.EnigmaPanel;
import common.hud.EnigmaWindow;
import common.hud.ui.EnigmaButtonUI;
import common.hud.ui.EnigmaJComboBoxUI;
import data.NeedToBeTranslated;
import data.config.Config;
import data.config.EnigmaUIValues;
import editor.popup.listeners.ZoomListener;
import game.EnigmaGame;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Barre d'outils de l'application enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 08/02/2020
 * @since 6.0 08/02/2020
 */
public class EnigmaOutilBar extends EnigmaPanel {

	private static final Color COLOR = Color.decode("#bfbfbf");
	private static final String FIT_BUTTON = "Zoom fit", IN_GAME_BUTTON = "Zoom in-game";

	public EnigmaOutilBar(EnigmaWindow window) {
		//couleur de la barre
		this.getComponentUI().setAllBackgrounds(COLOR, COLOR, COLOR);

		//Création du style des boutons de la barre
		EnigmaButtonUI ui = new EnigmaButtonUI();
		ui.setHoveredShowedBorders(EnigmaUIValues.ALL_BORDERS_SHOWED);
		ui.setSelectedHoveredShowedBorders(EnigmaUIValues.ALL_BORDERS_SHOWED);
		ui.setShowedBorders(EnigmaUIValues.ALL_BORDERS_SHOWED);
		ui.setAllBorders(COLOR, EnigmaUIValues.ENIGMA_BUTTON_HOVERED_BORDER, EnigmaUIValues.ENIGMA_BUTTON_PRESSED_BORDER);
		ui.setAllBackgrounds(COLOR, COLOR, COLOR);

		//Ajout des boutons
		this.generateButtons(ui, window);

		//zoom
		this.generateZoom();
	}

	/**
	 * Création des boutons
	 *
	 * @param ui     style
	 * @param window fenêtre
	 * @since 6.0
	 */
	private void generateButtons(EnigmaButtonUI ui, EnigmaWindow window) {
		for (Outil o : Outil.values()) {
			EnigmaButton a = new EnigmaButton();
			//survol
			a.setToolTipText(o.name);
			//icône
			a.setIcon(o.icon);
			//ui
			a.setUI(ui);
			//listener
			Class<? extends ActionListener> c = o.actionListener;
			if (c != null) a.addActionListener((ActionListener) Utility.instance(c, window, a));
			//ajout à la barre
			this.add(a);
			//met un pipe si besoin
			if (o.glue) {
				EnigmaButton sep = new EnigmaButton();
				//fait une copie du style
				EnigmaButtonUI ui2 = (EnigmaButtonUI) ui.duplicate();
				//changement du style pour mettre celui du pipe
				ui2.setAllShowedBorders(EnigmaUIValues.ALL_BORDER_HIDDEN);
				sep.setUI(ui2);
				sep.setIcon(Outil.SEPARATOR);
				//ajout à la barre
				this.add(sep);
			}
		}
	}

	/**
	 * La partie liée au zoom de la barre
	 *
	 * @since 6.0
	 */
	private void generateZoom() {
		//création des composants
		JComboBox<String> zoom = new JComboBox<>(Config.ZOOM_VALUES);
		zoom.setSelectedIndex(Config.ZOOM_VALUES_BASE_INDEX);

		JRadioButton fit = new JRadioButton(FIT_BUTTON);
		fit.setToolTipText(NeedToBeTranslated.ZOOM_FIT);

		JRadioButton inGame = new JRadioButton(IN_GAME_BUTTON);
		inGame.setToolTipText(NeedToBeTranslated.ZOOM_IN_GAME);

		//Création styles
		EnigmaJComboBoxUI.createUI(zoom);
		fit.setBackground(COLOR);
		fit.setFont(EnigmaUIValues.ENIGMA_FONT);
		fit.setForeground(Color.BLACK);
		inGame.setBackground(COLOR);
		inGame.setFont(EnigmaUIValues.ENIGMA_FONT);
		inGame.setForeground(Color.BLACK);

		//ButtonGroup
		ButtonGroup group = new ButtonGroup();
		group.add(fit);
		group.add(inGame);

		//observateurs
		zoom.addItemListener(new SelectZoomListener(group));
		ZoomLevel zoomLevel = new ZoomLevel();
		fit.addItemListener(zoomLevel);
		inGame.addItemListener(zoomLevel);

		//ajouts au panneau
		this.add(zoom);
		this.add(fit);
		this.add(inGame);
	}

	/**
	 * Listener de la liste de sélection de la valeur du zoom
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 09/02/2020
	 * @since 6.0 09/02/2020
	 */
	private static final class SelectZoomListener implements ItemListener {

		private final ButtonGroup group;

		SelectZoomListener(ButtonGroup group) {
			this.group = group;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				this.group.clearSelection();
				String command = ((String) e.getItem());
				//Crée l'action listener
				ZoomListener zoomListener = new ZoomListener(EnigmaGame.getCurrentScreen().getMap());
				zoomListener.actionPerformed(new ActionEvent(this, -1, command));
			}
		}
	}

	/**
	 * Listener de la liste de sélection de la valeur du zoom
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 * @version 6.0 09/02/2020
	 * @since 6.0 09/02/2020
	 */
	private static final class ZoomLevel implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String text = ((JRadioButton) e.getItem()).getText();
				String command;
				if (text.equals(FIT_BUTTON)) {
					command = NeedToBeTranslated.ZOOM_FIT;
				} else if (text.equals(IN_GAME_BUTTON)) {
					command = NeedToBeTranslated.ZOOM_IN_GAME;
				} else {
					return;
				}
				ZoomListener zoomListener = new ZoomListener(EnigmaGame.getCurrentScreen().getMap());
				zoomListener.actionPerformed(new ActionEvent(this, -1, command));
			}
		}
	}
}
