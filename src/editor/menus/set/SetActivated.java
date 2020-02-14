package editor.menus.set;

import common.entities.GameObject;
import common.entities.types.Activatable;
import common.hud.ui.EnigmaJCheckBoxUI;
import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.Drawable;
import editor.popup.cases.CasePopUp;

import javax.swing.JCheckBox;
import java.awt.event.ItemEvent;

/**
 * Permet de changer la propriété d'accès (collision)
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public class SetActivated implements AvailableOptionRunnable {

	/**
	 * Définit si l'object est activé ou non
	 */
	private static final String VALUE = "Activé";

	private final CasePopUp parent;

	public SetActivated(CasePopUp parent) {
		this.parent = parent;
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.SET_ACTIVATED;
	}

	@Override
	public void run() {
		JCheckBox activated = new JCheckBox(VALUE);
		activated.setUI(EnigmaJCheckBoxUI.createUI(activated));
		Activatable activatable = (Activatable) parent.getCell().getEntity();

		//si ya une case, alors on coche "Activé"
		activated.setSelected(activatable.isActivated());

		//applique les changements
		activated.addItemListener((e) -> activatable.setActivated(e.getStateChange() == ItemEvent.SELECTED));

		this.parent.getPanel().add(activated);
	}

	@Override
	public void run(AbstractPopUpView view, Drawable panel, GameObject object) {
		JCheckBox activated = new JCheckBox();
		activated.setUI(EnigmaJCheckBoxUI.createUI(activated));
		Activatable activatable = (Activatable) object;

		//si ya une case, alors on coche "bloquante"
		activated.setSelected(activatable.isActivated());

		//applique les changements
		activated.addItemListener((e) -> activatable.setActivated(e.getStateChange() == ItemEvent.SELECTED));

		panel.getDrawable().add(activated);
	}
}
