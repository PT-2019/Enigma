package editor.menus.set;

import common.entities.GameObject;
import common.entities.types.Lockable;
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
public class SetLocked implements AvailableOptionRunnable {

	/**
	 * Définit si la case est accessible ou non
	 */
	private static final String VALUE = "Verrouillée";

	private final CasePopUp parent;

	public SetLocked(CasePopUp parent) {
		this.parent = parent;
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.SET_LOCKED;
	}

	@Override
	public void run() {
		JCheckBox locked = new JCheckBox(VALUE);
		locked.setUI(EnigmaJCheckBoxUI.createUI(locked));
		Lockable lockable = (Lockable) parent.getCell().getEntity();

		//si ya une case, alors on coche "Verrouillée"
		locked.setSelected(lockable.isLocked());

		//applique les changements
		locked.addItemListener((e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) lockable.lock();
			else lockable.unlock();
		});

		this.parent.getPanel().add(locked);
	}

	@Override
	public void run(AbstractPopUpView view, Drawable panel, GameObject object) {
		JCheckBox locked = new JCheckBox(VALUE);
		locked.setUI(EnigmaJCheckBoxUI.createUI(locked));
		Lockable lockable = (Lockable) object;

		//si ya une case, alors on coche "Verrouillée"
		locked.setSelected(lockable.isLocked());

		//applique les changements
		locked.addItemListener((e) -> {
			if (e.getStateChange() == ItemEvent.SELECTED) lockable.lock();
			else lockable.unlock();
		});

		panel.getDrawable().add(locked);
	}
}
