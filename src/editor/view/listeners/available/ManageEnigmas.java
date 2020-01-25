package editor.view.listeners.available;

import api.enums.AvailablePopUpOption;
import editor.enigma.create.listeners.PopButtonListener;
import editor.hud.EnigmaButton;
import editor.view.cases.CasePopUp;
import editor.view.listeners.AvailableOptionRunnable;

/**
 * Permet de gérer les énigmes
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class ManageEnigmas implements AvailableOptionRunnable {

	private static final String ENIGMAS = "Gérer les énigmes";

	/**
	 * Affiche la gestion de contenu pour les entités.
	 * /TOD ce fameux menu de gestion
	 * @see api.entity.types.Content
	 */
	private EnigmaButton eng;

	private final CasePopUp parent;

	public ManageEnigmas(CasePopUp parent){
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
}
