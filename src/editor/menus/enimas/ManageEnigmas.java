package editor.menus.enimas;

import common.entities.GameObject;
import common.hud.EnigmaButton;
import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.Drawable;
import editor.menus.enimas.create.listeners.PopButtonListener;
import editor.popup.cases.CasePopUp;

/**
 * Permet de gérer les énigmes
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class ManageEnigmas implements AvailableOptionRunnable {

	private static final String ENIGMAS = "Gérer les énigmes";
	private final CasePopUp parent;

	/**
	 * Bouton gérer les énigmes
	 */
	private EnigmaButton eng;

	public ManageEnigmas(CasePopUp parent) {
		this.parent = parent;
		this.eng = new EnigmaButton(ENIGMAS);
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.MANAGE_ENIGMAS;
	}

	@Override
	public void run() {
		this.parent.getPanel().add(this.eng);
		this.eng.addActionListener(new PopButtonListener(this.parent));
	}

	@Override
	public void run(AbstractPopUpView view, Drawable panel, GameObject object) {
		panel.getDrawable().add(this.eng);
		this.eng.addActionListener(new PopButtonListener(this.parent, view, object));
	}
}
