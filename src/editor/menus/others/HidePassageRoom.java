package editor.menus.others;

import common.entities.GameObject;
import common.hud.EnigmaPanel;
import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.popup.cases.CasePopUp;

/**
 * Ouvre un menu permettant de cacher l'un des deux salles
 * des objets de type passage
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
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
	public HidePassageRoom(CasePopUp parent) {
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
