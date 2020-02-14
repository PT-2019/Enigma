package api.ui;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Visualisateur de progession
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 06 fevrier 2020
 * @since 6.0
 */
public class CustomProgressPopup extends Thread {
	/**
	 * Affiche les valeur
	 */
	public final static int VALUE_TYPE = 0;
	/**
	 * Affiche un pourcentage (arrondi)
	 */
	public final static int PERCENT_TYPE = 1;
	/**
	 * Ne fait rien à l'atteinte du 100%
	 */
	public final static boolean DO_NOTHING_ON_MAX_VALUE_REACHED = false;
	/**
	 * Ferme la fenêtre à l'atteinte du 100%
	 */
	public final static boolean END_ON_MAX_VALUE_REACHED = true;
	/**
	 * Valeur actuelle
	 */
	private int value;
	/**
	 * Valeur à atteindre
	 */
	private int maxValue;
	/**
	 * Pourcentage d'avancement
	 */
	private int percent;
	/**
	 * Type
	 */
	private int type;
	/**
	 * Action lors du 100%
	 */
	private boolean action;
	/**
	 * Fenêtre
	 */
	private CustomWindow window;
	/**
	 * Elément d'affichage de la progression
	 */
	private CustomLabel progress;

	/**
	 * @param message           Message
	 * @param startingValue     Valeur de départ
	 * @param maxValue          Valeur à atteindre
	 * @param type              Type
	 * @param window            Fenêtre
	 * @param messageComponent  Composant qui va contenir le message
	 * @param progressComponent Composant qui va contenir la progression
	 * @throws IllegalArgumentException Si la valeur de départ est supérieure à la valeur maximale
	 */
	public CustomProgressPopup(String message, int startingValue, int maxValue, int type,
	                           CustomWindow window, CustomLabel messageComponent, CustomLabel progressComponent) {
		if (startingValue > maxValue)
			throw new IllegalArgumentException("Valeur de départ plus grande que " + maxValue);

		this.value = startingValue;
		this.maxValue = maxValue;
		this.type = type;
		this.action = DO_NOTHING_ON_MAX_VALUE_REACHED;

		this.window = window;
		this.window.getContentSpace().setLayout(new GridLayout(2, 1));
		messageComponent.setText(message);
		this.window.getContentSpace().add(messageComponent);
		this.window.setExtendedState(JFrame.NORMAL);
		this.window.setMinimumSize(new Dimension(250, 100));
		this.window.setSize(new Dimension(250, 100));
		this.window.setLocation(CustomWindow.CENTER);
		this.window.setResizable(false);
		this.window.setUndecorated(true);
		this.window.setAlwaysOnTop(true);
		this.window.setIfAskBeforeClosing(false);

		this.progress = progressComponent;
		this.window.getContentSpace().add(this.progress);
		this.update(this.value);
	}

	@Override
	public void run() {
		this.window.setVisible(true);
	}

	/**
	 * Commence l'affichage de la progession
	 */
	public void begin() {
		this.start();
	}

	/**
	 * Fini l'affichage de la progession
	 */
	public void end() {
		this.window.dispose();
	}

	/**
	 * Met à jour la progession
	 *
	 * @param updatedValue Nouvelle valeur
	 * @throws IllegalArgumentException Si la nouvelle valeur est supérieure à la valeur maximale
	 */
	public void update(int updatedValue) {
		if (updatedValue > this.maxValue)
			throw new IllegalArgumentException("Valeur " + updatedValue + " plus grande que " + this.maxValue);
		else {
			this.value = updatedValue;
			double d = (double) this.value / maxValue;
			d = d * 100;
			this.percent = (int) d;
			this.updateUI();
		}

		if (this.value == this.maxValue && this.action)
			this.end();
	}

	/**
	 * Met à jour l'affichage
	 */
	private void updateUI() {
		String message = "";

		switch (this.type) {
			case VALUE_TYPE:
				message = this.value + "/" + this.maxValue;
				break;
			case PERCENT_TYPE:
				message = this.percent + "%";
				break;
		}

		this.progress.setText(message);
	}

	/**
	 * Obtenir l'action lors de l'atteinte du 100%
	 *
	 * @return action
	 */
	public boolean getActionOnMaxValueReached() {
		return this.action;
	}

	/**
	 * Défini l'action lors de l'atteinte du 100%
	 *
	 * @param action Action
	 */
	public void setActionOnMaxValueReached(boolean action) {
		this.action = action;
	}

	/**
	 * obtenir la fenêtre
	 *
	 * @return Fenêtre
	 */
	public CustomWindow getWindow() {
		return window;
	}

	/**
	 * Obtenir la valeur actuelle
	 *
	 * @return Valeur actuelle
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Obtenir la valeur maximale
	 *
	 * @return Valeur maximale
	 */
	public int getMaxValue() {
		return maxValue;
	}

	/**
	 * Obtenir le pourcentage (arrondi)
	 *
	 * @return Pourcentage
	 */
	public int getPercent() {
		return percent;
	}

	/**
	 * Obtenir le type
	 *
	 * @return Type
	 */
	public int getType() {
		return type;
	}
}
