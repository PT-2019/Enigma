package editor.popup.cases.listeners;

import common.entities.GameObject;
import editor.popup.cases.AbstractPopUp;
import editor.popup.cases.CasePopUp;
import editor.popup.cases.SpecialPopUp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur qui consiste à définir l'objet pour la création d'énigmes
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see CasePopUp
 * @since 4.0
 */
public class SpecialPopListener implements ActionListener {

	/**
	 * Popup à détruire lors de l'activation
	 */
	private AbstractPopUp popDispose;

	/**
	 * Popup qui garde en mémoire l'objet gameObject
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
		EntityChoseListener obs = this.popup.getObserver();
		obs.updateObserver(this.objectChose);
		this.popDispose.dispose();

		if (this.popDispose instanceof SpecialPopUp)
			((SpecialPopUp) this.popDispose).getCaseListener().setPop(null);
	}
}
