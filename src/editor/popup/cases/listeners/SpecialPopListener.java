package editor.popup.cases.listeners;

import editor.menus.enimas.view.EnigmaView;
import editor.popup.cases.AbstractPopUp;
import editor.popup.cases.CasePopUp;
import editor.popup.cases.SpecialPopUp;
import general.entities.GameObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlleur qui consiste à définir l'objet pour la création d'énigmes
 *
 * @see CasePopUp
 */
public class SpecialPopListener implements ActionListener {

	/**
	 * Popup à détruire lors de l'activation
	 */
	private AbstractPopUp popDispose;

	/**
	 * Popup qui garde en mémoire l'objet gameobject
	 */
	private CasePopUp popup;

	/**
	 * Objet choisi par l'utilisateur
	 */
	private GameObject objectChose;

	public SpecialPopListener(AbstractPopUp popDispose, CasePopUp popup, GameObject chose) {
		this.popDispose = popDispose;
		this.popup = popup;
		this.objectChose = chose;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		EntityChoseListener obs = popup.getObserver();
		obs.updateObserveur(objectChose);
		popDispose.dispose();
		EnigmaView.setAvailable(null);

		if (popDispose instanceof SpecialPopUp)
			((SpecialPopUp) popDispose).getCaseListener().setPop(null);
	}
}
