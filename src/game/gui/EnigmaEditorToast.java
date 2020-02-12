package game.gui;

import api.libgdx.ui.Toast;
import api.utils.Observer;
import api.utils.annotations.ConvenienceMethod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import common.enigmas.Enigma;
import common.entities.GameObject;
import data.config.Config;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.ActionsManager;
import editor.bar.edition.ActionsManagerType;

/**
 * Toast d'Enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 05/02/2020
 * @since 6.0 05/02/2020
 */
public class EnigmaEditorToast extends Toast implements Observer<ActionsManager> {

	private static final float BASE_FADE_IN_DELAY = 0.3f, BASE_FADE_OUT_DELAY = 0.5f, DELAY = 1f;

	/**
	 * Crée le toast de l'éditor
	 */
	public EnigmaEditorToast() {
		super(Config.JSON_PATH, Config.SKIN_PATH, "");

		//this.setBackground("default-select-selection"); // <-- cyan/bleu
		this.setBackground("default-round-down");

		//attributes
		this.fadeIn = BASE_FADE_IN_DELAY;
		this.fadeOut = BASE_FADE_OUT_DELAY;
		this.delay = DELAY;

		ActionsManager instance = ActionsManager.getInstance();
		instance.addObserver(this);

		//top aligned
		this.setPosition(
				(Gdx.graphics.getWidth() - CategoriesMenu.WIDTH) / 2f
						+ CategoriesMenu.WIDTH,
				Gdx.graphics.getHeight() / 2f + Gdx.graphics.getHeight() / 2f * 0.7f + HEIGHT / 2, Align.center);
		this.setVisible(true);
		this.hide(true);
	}

	/**
	 * Update du toast selon action faite
	 *
	 * @param object observateur des actions
	 * @since 6.0
	 */
	@Override
	@ConvenienceMethod
	public void update(ActionsManager object) {
		ActionsManager.LastAction lastAction = object.getLastAction();
		//on affiche pas les "add" soit ajout et remove
		if (lastAction == null || lastAction.type.equals(ActionsManagerType.ADD)) return;
		String text = lastAction.type.toString();
		if (text.length() > 0) {
			text += ": ";
		}
		ActionTypes type = lastAction.last.getType();
		text += type.toString() + " ";

		//ajoute l'entité
		if (type.equals(ActionTypes.ADD_ENTITY) || type.equals(ActionTypes.REMOVE_ENTITY)
				|| type.equals(ActionTypes.ADD_SUB_ENTITY) || type.equals(ActionTypes.REMOVE_SUB_ENTITY)
		) {
			text += "[" + ((GameObject) lastAction.last.getActor()).getReadableName() + "]";
		}
		if (type.equals(ActionTypes.ADD_ENIGMA) || type.equals(ActionTypes.REMOVE_ENIGMA)) {
			text += "[" + ((Enigma) lastAction.last.getActor()).getTitle() + "]";
		}

		this.update(text);
	}
}
