package api.hud.components;

import api.utils.annotations.ConvenienceClass;

/**
 * Class pratique pour passer le style d'une application dans un object.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 28/12/2019
 * @since 4.0 28/12/2019
 */
@ConvenienceClass
public interface OptionPaneStyle {

	/**
	 * Retourne le style de base d'un bouton de l'option bane
	 *
	 * @param text contenu du bouton
	 * @return le bouton auquel le style a été appliqué
	 */
	CustomButton getButtonStyle(String text);

	/**
	 * Retourne le style de base d'un champ de saisie de l'option bane
	 *
	 * @return le champ de saisie auquel le style a été appliqué
	 */
	CustomTextArea getTextAreaStyle();

	/**
	 * Retourne la fenêtre de l'option pane
	 *
	 * @return la fenêtre de l'option pane
	 */
	CustomAlert getWindow();

	/**
	 * Retourne le style d'un panneau de base de l'option pane
	 *
	 * @return le style d'un panneau de base de l'option pane
	 */
	CustomPanel getPanelStyle();

	/**
	 * Retourne le style d'un label de base de l'option pane
	 *
	 * @param content contenu du label
	 * @return le style d'un label de base de l'option pane
	 */
	CustomLabel getLabelStyle(String content);

}
