package api.hud.base;

import api.hud.CustomMenuBar;
import api.hud.CustomPanel;
import api.hud.manager.window.Exit;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Une version abstraite d'une fenêtre
 *
 * @author Louka DOZ
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 27/12/2019
 * @since 4.0 27/12/2019
 */
public interface AbstractWindow extends WindowConstants, WindowResizeConstants {

	// content

	/**
	 * Renvoi le panneau qui contient le contenu a la fenêtre
	 *
	 * @return le panneau qui contient le contenu a la fenêtre
	 */
	CustomPanel getContentSpace();

	// util

	/**
	 * Change le listener de fermeture de la fenêtre
	 *
	 * @param listener le nouveau listener de fermeture de la fenêtre
	 * @since 4.2
	 */
	void setExitListener(Exit listener);

	//get and setters

	/**
	 * Chance la taille de la fenêtre
	 *
	 * @param size une nouvelle taille
	 * @see #setSize(WindowSize)
	 * @deprecated since 4.4
	 */
	@Deprecated
	default void setSize(int size) {
	}

	/**
	 * Chance la taille minimum de la fenêtre
	 *
	 * @param size une taille minimum
	 * @see WindowSize
	 * @since 4.5
	 */
	void setMinimumSize(WindowSize size);

	/**
	 * Chance la taille de la fenêtre
	 *
	 * @param size une nouvelle taille
	 * @see WindowSize
	 * @since 4.4
	 */
	void setSize(WindowSize size);

	/**
	 * Définit si on doit demander avant de fermer la fenêtre
	 *
	 * @param askBeforeClosing true si on demander avant de fermer la fenêtre
	 */
	void setIfAskBeforeClosing(boolean askBeforeClosing);

	/**
	 * Retourne si on doit demander avant de fermer la fenêtre
	 *
	 * @return true si on demander avant de fermer la fenêtre
	 */
	boolean willAskBeforeClosing();

	/**
	 * Afficher le bouton "réduire la fenêtre dans la barre des taches"
	 *
	 * @param show true pour afficher
	 */
	void showMinimizeButton(boolean show);

	/**
	 * Afficher le bouton "réduire la fenêtre"
	 *
	 * @param show true pour afficher
	 */
	void showSmallerButton(boolean show);

	/**
	 * Afficher le bouton "fermer"
	 *
	 * @param show true pour afficher
	 */
	void showCloseButton(boolean show);

	/**
	 * Cache tous les boutons
	 *
	 * @see #setMenuButtonsVisible(boolean)
	 * @deprecated since 4.4
	 */
	@Deprecated
	default void hideAllButton() {
		this.showMinimizeButton(false);
		this.showSmallerButton(false);
		this.showCloseButton(false);
	}

	/**
	 * Affiche ou cache les boutons minimise, small et close
	 *
	 * @param show true pour afficher sinon false
	 * @since 4.4
	 */
	void setMenuButtonsVisible(boolean show);

	/**
	 * Affiche tous les boutons
	 *
	 * @see #setMenuButtonsVisible(boolean)
	 * @deprecated since 4.4
	 */
	@Deprecated
	default void showAllButton() {
		this.showMinimizeButton(true);
		this.showSmallerButton(true);
		this.showCloseButton(true);
	}

	/**
	 * Cache la barre de boutons
	 *
	 * @see #setMenuBarVisible(boolean)
	 * @deprecated since 4.4
	 */
	@Deprecated
	default void hideMenuBar() {
	}

	/**
	 * Affiche la barre de boutons
	 *
	 * @see #setMenuBarVisible(boolean)
	 * @deprecated since 4.4
	 */
	@Deprecated
	default void showMenuBar() {
	}

	/**
	 * Affiche ou cache la barre de menus
	 *
	 * @param show true pour afficher
	 */
	void setMenuBarVisible(boolean show);

	/**
	 * true si plein écran
	 *
	 * @return true si plein écran
	 */
	boolean isFullScreen();

	/**
	 * true si re-dimension-able
	 *
	 * @return true si re-dimension-able
	 */
	boolean isResizable();

	/**
	 * true si re-dimension-able
	 *
	 * @param resizable true si re-dimension-able
	 */
	void setResizable(boolean resizable);

	/**
	 * Change taille écran
	 *
	 * @param width  largeur
	 * @param height hauteur
	 */
	void setSize(int width, int height);

	/**
	 * Change taille écran
	 *
	 * @param dimension dimension
	 */
	void setSize(Dimension dimension);

	/**
	 * Définit position fenêtre
	 *
	 * @param location position fenêtre
	 */
	void setLocation(int location);

	/**
	 * Définit barre de menus
	 *
	 * @param menuBar barre de menus
	 */
	void setWindowMenuBar(CustomMenuBar menuBar);

	/**
	 * Définit fond fenêtre
	 *
	 * @param bgColor fond fenêtre
	 */
	void setWindowBackground(Color bgColor);

	/**
	 * Définit fond fenêtre
	 *
	 * @param image image fond fenêtre
	 */
	void setBackground(ImageIcon image);

	/**
	 * Cache bordures
	 */
	void hideBorder();

	/**
	 * Affiche bordures
	 *
	 * @param color      color
	 * @param borderSize taille
	 */
	void showBorder(Color color, int borderSize);

	//compare

	@Deprecated
	default boolean compareWindowSizeWith(int size) {
		throw new UnsupportedOperationException("disabled");
	}

	@Deprecated
	default boolean compareWindowSizeWith(int width, int height) {
		throw new UnsupportedOperationException("disabled");
	}

	@Deprecated
	default boolean compareWindowSizes(Dimension dim1, Dimension dim2) {
		throw new UnsupportedOperationException("disabled");
	}

}
