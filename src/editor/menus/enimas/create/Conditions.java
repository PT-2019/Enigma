package editor.menus.enimas.create;

import api.utils.Observer;
import common.entities.Consumable;
import common.entities.GameObject;
import common.entities.types.Activatable;
import editor.menus.SelectionsModes;
import game.dnd.DragAndDropBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static data.NeedToBeTranslated.*;

/**
 * Conditions disponibles
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 26/01/2020
 * @since 5.0 26/01/2020
 */
public enum Conditions {
	ACTIVATED(ACTIVATED_DESC, ACTIVATED_RES, null, SelectionsModes.MAP),
	ANSWER(ANSWER_DESC, ANSWER_RES, null, SelectionsModes.NONE),
	HAVE_IN_HANDS(HAVE_IN_HANDS_DESC, HAVE_IN_HANDS_RES, null, SelectionsModes.MAP_AND_POPUP),
	HAVE_IN_INVENTORY(HAVE_IN_INVENTORY_DESC, HAVE_IN_INVENTORY_RES, null, SelectionsModes.MAP_AND_POPUP),
	;

	/**
	 * La condition qui verrouille actuellement l'état
	 */
	private static Conditions locked = null;

	/**
	 * Description
	 */
	public final String value;

	/**
	 * Le nom d'une classe dont la méthode run crée.
	 * <p>
	 * Non utilisé et non fonctionnel.
	 * <p>
	 * La classe possèderait un constructeur qui prends une EnigmaView et
	 * un OperéationListener.
	 *
	 * @see editor.menus.enimas.create.listeners.ConditionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public final Class<? extends Runnable> initClass;
	/**
	 * ignore
	 */
	public final String tooltip;
	/**
	 * Message si la condition n'est pas respectée
	 */
	public final String restrict;
	/**
	 * Possible de sélectionner depuis le menu
	 */
	public final SelectionsModes menuDrag;

	Conditions(String value, String restrict, Class<? extends Runnable> initClass, SelectionsModes menuDrag) {
		this.value = value;
		this.initClass = initClass;
		this.tooltip = "";
		this.restrict = restrict;
		this.menuDrag = menuDrag;
	}

	/**
	 * Dévérouille la condition actuelle. L'éditor est débloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 *
	 * @param conditions condition
	 */
	public static void unlock(@Nullable Conditions conditions) {
		if (conditions != null) {
			conditions.unlock();
		}
	}

	/**
	 * Vérouille la condition actuelle. L'éditor est bloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 *
	 * @param conditions la condition
	 * @param observer   observeur
	 */
	public static void lock(@NotNull Conditions conditions, Observer<GameObject> observer) {
		unlock(locked);
		locked = conditions;
		locked.lock(observer);
	}

	/**
	 * Verrouille la condition actuelle. L'éditor est bloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 *
	 * @param observer observeur
	 */
	private void lock(Observer<GameObject> observer) {
		if (menuDrag.contains(SelectionsModes.MENU)) DragAndDropBuilder.setForPopup(observer);
	}

	/**
	 * Dévérouille la condition actuelle. L'éditor est débloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 */
	private void unlock() {
		if (menuDrag.contains(SelectionsModes.MENU)) DragAndDropBuilder.setForPopup(null);
	}

	/**
	 * Retourne true si le gameObject respecte les conditions de la conditions
	 *
	 * @param object gameObject
	 * @return true si le gameObject respecte les conditions de la conditions
	 */
	public boolean isValid(GameObject object) {
		//cette méthode n'est pas géniale, on a écrit à la main les conditions
		if (this.equals(ACTIVATED)) {
			return object instanceof Activatable;
		} else if (this.equals(ANSWER)) {
			return false;
		} else if (this.equals(HAVE_IN_HANDS)) {
			return object instanceof Consumable;
		} else if (this.equals(HAVE_IN_INVENTORY)) {
			return object instanceof Consumable;
		}
		return false;
	}
}