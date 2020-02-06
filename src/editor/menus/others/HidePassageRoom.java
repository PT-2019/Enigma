package editor.menus.others;

import common.entities.GameObject;
import common.hud.EnigmaButton;
import data.NeedToBeTranslated;
import editor.menus.AbstractPopUpView;
import editor.menus.AvailableOptionRunnable;
import editor.menus.AvailablePopUpOption;
import editor.menus.Drawable;
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
 *
 * Plus besoin, maintenant si s'agît d'une opération d'une énigme.
 */
@Deprecated
public class HidePassageRoom implements AvailableOptionRunnable {

	private final static String HIDE = NeedToBeTranslated.HIDE_ROOM;

	/**
	 * Parent
	 */
	private final CasePopUp parent;

	private final EnigmaButton hide;

	/**
	 * Ouvre un menu permettant de cacher l'un des deux salles
	 * des objets de type passage
	 *
	 * @param parent parent
	 */
	public HidePassageRoom(CasePopUp parent) {
		this.parent = parent;
		this.hide = new EnigmaButton();
	}

	@Override
	public AvailablePopUpOption getOption() {
		return AvailablePopUpOption.SET_PASSAGE_ROOM_HIDDEN;
	}

	@Override
	public void run() {
		//ajoute le bouton
		this.parent.getPanel().add(this.hide);
		this.hide.addActionListener((e) -> {
		});
	}

	@Override
	public void run(AbstractPopUpView view, Drawable panel, GameObject object) {
		//ajoute le bouton
		panel.getDrawable().add(this.hide);
		this.hide.addActionListener((e) -> {
		});
	}
}

