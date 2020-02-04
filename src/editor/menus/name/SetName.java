package editor.menus.name;

import common.entities.GameObject;
import common.hud.EnigmaButton;
import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.Drawable;
import editor.menus.EnigmaWindowListener;
import editor.popup.cases.CasePopUp;

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
public class SetName implements AvailableOptionRunnable {

	/**
	 * Définit si la case est accessible ou non
	 */
	private static final String NAME = "Changer nom";

	private final CasePopUp parent;
	private final EnigmaButton input;

	public SetName(CasePopUp parent) {
		this.parent = parent;
		this.input = new EnigmaButton(NAME);
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.SET_NAME;
	}

	@Override
	public void run() {
		this.parent.getPanel().add(this.input);
		this.input.addActionListener((e) -> {
			this.parent.setVisible(false);
			AddNameView addNameView = new AddNameView(this.parent, null);
			addNameView.setVisible(true);
			addNameView.addWindowListener(new EnigmaWindowListener(this.parent));
		});
	}

	@Override
	public void run(AbstractPopUpView view, Drawable panel, GameObject object) {
		panel.getDrawable().add(this.input);
		this.input.addActionListener((e) -> {
			this.parent.setVisible(false);
			AddNameView addNameView = new AddNameView(this.parent, object);
			addNameView.setVisible(true);
			addNameView.addWindowListener(new EnigmaWindowListener(this.parent));
		});
	}
}
