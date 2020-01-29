package editor.view.listeners.available;

import api.entity.GameObject;
import api.enums.AvailablePopUpOption;
import editor.hud.EnigmaPanel;
import editor.view.cases.CasePopUp;
import editor.view.listeners.AvailableOptionRunnable;
import editor.view.listeners.available.view.AbstractPopUpView;

/**
 * Ouvre un menu permettant de cacher l'un des deux salles
 * des objets de type passage
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class HidePassageRoom implements AvailableOptionRunnable {

	/**
	 * Parent
	 */
	private final CasePopUp parent;

	/**
	 * Ouvre un menu permettant de cacher l'un des deux salles
	 * des objets de type passage
	 *
	 * @param parent parent
	 */
	public HidePassageRoom(CasePopUp parent){
		this.parent = parent;
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.SET_PASSAGE_ROOM_HIDDEN;
	}

	@Override
	public void run() {
		/* PASSAGE :
		private JCheckBox hideRigth = new JCheckBox("Cacher room à droite");
		private JCheckBox hideLeft = new JCheckBox("Cacher room à gauche");
		private JPanel passage = new JPanel();
		passage.setLayout(new GridLayout(2, 1));
		if (isPassage//true) {
			passage.add(hideLeft);
			passage.add(hideRigth);
			extra.add(passage);
		}
		passage.remove(hideLeft);
		passage.remove(hideRigth);
		*/
	}

	@Override
	public void run(AbstractPopUpView view, EnigmaPanel panel, GameObject object) {

	}
}

