package editor.menus.others;

import common.entities.GameObject;
import common.hud.EnigmaButton;
import common.hud.EnigmaPanel;
import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.enimas.listeners.PopButtonListener;
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
	 * Affiche la gestion de contenu pour les entités.
	 * /TOD ce fameux menu de gestion
	 *
	 * @see common.entities.types.Content
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
		this.eng.addActionListener(new PopButtonListener(this.parent, this.parent.getCell(), this.parent.getObserver()));
	}

	@Override
	public void run(AbstractPopUpView view, EnigmaPanel panel, GameObject object) {
		panel.add(this.eng);
		this.eng.addActionListener(new PopButtonListener(this.parent,
				this.parent.getCell(), this.parent.getObserver(), view));
	}
}
