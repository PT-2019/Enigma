package game.gui;

import api.libgdx.ui.Toast;
import api.utils.Observer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Align;
import editor.bar.edition.ActionsManager;

/**
 * Toast d'Enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 05/02/2020
 * @since 6.0 05/02/2020
 */
public class EnigmaEditorToast extends Toast implements Observer<ActionsManager> {

	//dans fichier config (toutes constantes)
	/**
	 * Json
	 * @since 6.0
	 */
	private static final String JSON_PATH = "assets/files/atlas/uiskin.json";
	/**
	 * Fichier skin
	 * @since 6.0
	 */
	private static final String SKIN_PATH = "assets/files/atlas/uiskin.atlas";

	private static final float BASE_FADE_IN_DELAY = 0.3f, BASE_FADE_OUT_DELAY = 0.5f, DELAY = 1f;

	/**
	 * Crée le toast de l'éditor
	 */
	public EnigmaEditorToast(){
		super(JSON_PATH, SKIN_PATH,"");

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
				Gdx.graphics.getHeight()/2f + Gdx.graphics.getHeight()/2f * 0.7f  + HEIGHT/2, Align.center);
		this.setVisible(true);
		this.hide(true);
	}

	/**
	 * Update du toast selon action faite
	 * @param object observateur des actions
	 * @since 6.0
	 */
	@Override
	public void update(ActionsManager object) {
		ActionsManager.LastAction lastAction = object.getLastAction();
		if(lastAction == null) return;
		String text = lastAction.type.toString();
		if(text.length() > 0){
			text +=": ";
		}
		text += lastAction.last.getType().toString();
		this.update(text);
	}
}
