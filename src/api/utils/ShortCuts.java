package api.utils;

import com.badlogic.gdx.Input;
import org.intellij.lang.annotations.MagicConstant;

import java.util.ArrayList;

/**
 * Classe des raccourcis de l'éditeur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.1
 * @since 6.0
 */
@SuppressWarnings("unused")
public class ShortCuts {

	/**
	 * Liste des keys de l'éditeur
	 *
	 * @since 6.0
	 */
	private ArrayList<Integer> listKeys;

	/**
	 * Touche principale
	 *
	 * @since 6.0
	 */
	private int main;

	/**
	 * boolean pour indiquer si besoin d'un contrôleur
	 *
	 * @since 6.0
	 */
	private boolean needCtrl, needAlt;

	/**
	 * Classe des raccourcis de l'éditeur
	 *
	 * @param main   Touche principale
	 * @param others autre touches
	 * @see Input.Keys
	 */
	public ShortCuts(@MagicConstant(flagsFromClass = Input.Keys.class) int main,
	                 @MagicConstant(flagsFromClass = Input.Keys.class) int... others) {
		//touche principale
		this.main = main;
		//autre keys
		this.listKeys = new ArrayList<>();
		for (int key : others) {
			this.listKeys.add(key);
			if (key == Input.Keys.CONTROL_LEFT) this.needCtrl = true;
			else if (key == Input.Keys.ALT_LEFT) this.needAlt = true;
		}
	}

	/**
	 * Retourne true si besoin du contrôleur alt gauche
	 *
	 * @return true si besoin du contrôleur alt gauche
	 * @since 6.0
	 */
	public boolean needAlt() {
		return this.needAlt;
	}

	/**
	 * Retourne true si besoin du contrôleur control gauche
	 *
	 * @return true si besoin du contrôleur control gauche
	 * @since 6.0
	 */
	public boolean needCtrl() {
		return this.needCtrl;
	}

	/**
	 * Retourne la touche principale
	 *
	 * @return la touche principale
	 * @since 6.0
	 */
	public int getMain() {
		return main;
	}

	/**
	 * Retourne toutes les touches du raccourcis
	 *
	 * @return toutes les touches du raccourcis
	 * @since 6.0
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getListKeys() {
		return (ArrayList<Integer>) this.listKeys.clone();
	}

	/**
	 * Vérifie si les touches sont celles du raccourcis
	 *
	 * @param keycode  touche principale
	 * @param ctrlPush true si contrôle gauche activé
	 * @param altPush  true si alt gauche activé
	 * @return true si c'est le raccourcis
	 * @since 6.1
	 */
	@SuppressWarnings("RedundantIfStatement")
	public boolean check(int keycode, boolean ctrlPush, boolean altPush) {
		if (keycode == this.main) {
			if (needCtrl != ctrlPush) return false;
			if (needAlt != altPush) return false;
			return true;
		}
		return false;
	}
}