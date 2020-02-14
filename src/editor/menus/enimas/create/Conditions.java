package editor.menus.enimas.create;

import api.utils.Observer;
import common.entities.Consumable;
import common.entities.GameObject;
import common.entities.special.Room;
import common.entities.types.Activatable;
import data.EditorState;
import data.NeedToBeTranslated;
import editor.EditorLauncher;
import common.entities.types.Content;
import editor.menus.SelectionsModes;
import editor.popup.listeners.CaseListener;
import game.dnd.DragAndDropBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
	ACTIVATED(NeedToBeTranslated.ACTIVATED_DESC, NeedToBeTranslated.ACTIVATED_RES, SelectionsModes.MAP),
	ANSWER(NeedToBeTranslated.ANSWER_DESC, NeedToBeTranslated.NO_ANSWER, SelectionsModes.ANSWER),
	ROOM_DISCOVERED(NeedToBeTranslated.ROOM_DISCOVERED_DESC, NeedToBeTranslated.ROOM_ONLY, SelectionsModes.MAP),
	ROOM_UNDISCOVERED(NeedToBeTranslated.ROOM_UNDISCOVERED_DESC, NeedToBeTranslated.ROOM_ONLY, SelectionsModes.MAP),
	HAVE_IN_HANDS(NeedToBeTranslated.HAVE_IN_HANDS_DESC, NeedToBeTranslated.ITEMS_ONLY, SelectionsModes.MAP_AND_POPUP),
	HAVE_IN_INVENTORY(NeedToBeTranslated.HAVE_IN_INVENTORY_DESC, NeedToBeTranslated.ITEMS_ONLY, SelectionsModes.MAP_AND_POPUP),
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
	 * true si condition disponible
	 */
	public final boolean available;

	/**
	 * tooltip
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

	/**
	 * Conditions
	 * @param value description
	 * @param restrict message si la condition n'est pas respectée
	 * @param menuDrag mode de sélection de l'entité si besoin
	 */
	Conditions(String value, String restrict, SelectionsModes menuDrag) {
		this(value, restrict, menuDrag, true);
	}

	/**
	 * Conditions
	 * @param value description
	 * @param restrict message si la condition n'est pas respectée
	 * @param menuDrag mode de sélection de l'entité si besoin
	 * @param available true si condition disponible
	 */
	Conditions(String value, String restrict, SelectionsModes menuDrag, boolean available) {
		this.value = value;
		this.available = available;
		this.tooltip = value;
		this.restrict = restrict;
		this.menuDrag = menuDrag;
	}

	/**
	 * Déverrouille la condition actuelle. L'éditor est débloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 *
	 * @param conditions une condition ou null pour la condition actuelle
	 */
	public static void unlock(@Nullable Conditions conditions) {
		if (conditions != null) {
			conditions.unlock();
		} else if(Conditions.locked != null){
			Conditions.locked.unlock();
		}
	}

	/**
	 * Verrouille la condition actuelle. L'éditor est bloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 *
	 * @param conditions la condition
	 * @param observer   observer
	 */
	public static void lock(@NotNull Conditions conditions, Observer<GameObject> observer) {
		Conditions.unlock(Conditions.locked);//unlock de l'ancienne condition
		Conditions.locked = conditions;
		Conditions.locked.lock(observer);
	}

	/**
	 * Verrouille la condition actuelle. L'éditor est bloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 *
	 * @param observer observer
	 */
	private void lock(Observer<GameObject> observer) {
		//capture du drag and drop
		if (this.menuDrag.contains(SelectionsModes.MENU)) {
			DragAndDropBuilder.setForPopup(observer);
		}

		//désactivation de la sélection depuis la map
		if (!this.menuDrag.contains(SelectionsModes.MAP)) {
			if (EditorLauncher.containsState(EditorState.NORMAL)) {
				EditorLauncher.clearStates();
			}
			EditorLauncher.addState(EditorState.SPECIAL_POPUP_DISABLED);
		} else {//activation
			if (EditorLauncher.containsState(EditorState.NORMAL)) {
				EditorLauncher.clearStates();
			}
			EditorLauncher.addState(EditorState.SPECIAL_POPUP_ENABLED);
		}

		//active container
		if (this.menuDrag.contains(SelectionsModes.POPUP)) {
			if (EditorLauncher.containsState(EditorState.NORMAL)) {
				EditorLauncher.clearStates();
			}
			EditorLauncher.addState(EditorState.SPECIAL_POPUP_CONTENT);
		}
	}

	/**
	 * Déverrouille la condition actuelle. L'éditor est débloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 */
	private void unlock() {
		//débloque la capture du drag and drop
		if (this.menuDrag.contains(SelectionsModes.MENU)) {
			DragAndDropBuilder.setForPopup(null);
		}

		//débloque la sélection de la map
		if (!this.menuDrag.contains(SelectionsModes.MAP)) {
			EditorLauncher.removeState(EditorState.SPECIAL_POPUP_DISABLED);
		} else {
			EditorLauncher.removeState(EditorState.SPECIAL_POPUP_ENABLED);
		}

		//ferme le pop
		CaseListener.close(CaseListener.ClosePopup.SECONDARY);

		//désactive container
		if (this.menuDrag.contains(SelectionsModes.POPUP)) {
			EditorLauncher.removeState(EditorState.SPECIAL_POPUP_CONTENT);
		}

	}

	/**
	 * Retourne true si le gameObject respecte les conditions de la conditions
	 *
	 * @param object gameObject
	 * @return true si le gameObject respecte les conditions de la conditions
	 */
	public boolean isValid(GameObject object) {
		//cette méthode n'est pas géniale, on a écrit à la main les conditions
		if (this.equals(Conditions.ACTIVATED)) {
			return object instanceof Activatable;
		} else if (this.equals(Conditions.ANSWER)) {
			return object instanceof Content;
		} else if (this.equals(Conditions.HAVE_IN_HANDS)) {
			return object instanceof Consumable;
		} else if (this.equals(Conditions.HAVE_IN_INVENTORY)) {
			return object instanceof Consumable;
		} else if (this.equals(Conditions.ROOM_DISCOVERED)) {
			return object instanceof Room;
		} else if (this.equals(Conditions.ROOM_UNDISCOVERED)) {
			return object instanceof Room;
		}

		return false;
	}
}