package editor.menus.enimas.create;

import api.utils.Observer;
import common.entities.GameObject;
import common.entities.players.NPC;
import common.entities.special.Room;
import common.entities.types.Living;
import common.entities.types.Lockable;
import common.entities.types.NeedContainer;
import data.EditorState;
import data.NeedToBeTranslated;
import editor.EditorLauncher;
import editor.menus.SelectionsModes;
import game.dnd.DragAndDropBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Opérations disponibles
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 25/01/2020
 * @since 5.0 25/01/2020
 */
public enum Operations {
	GIVE(NeedToBeTranslated.GIVE_DESC, NeedToBeTranslated.GIVE_RES, SelectionsModes.MENU_AND_POPUP),
	SUMMON(NeedToBeTranslated.SUMMON_DESC, NeedToBeTranslated.SUMMON_RES, SelectionsModes.MAP_AND_MENU),
	SHOW_ROOM(NeedToBeTranslated.SHOW_ROOM_DESC, NeedToBeTranslated.SHOW_ROOM_RES, SelectionsModes.MAP_AND_MENU),
	HIDE_ROOM(NeedToBeTranslated.HIDE_ROOM_DESC, NeedToBeTranslated.HIDE_ROOM_RES, SelectionsModes.MAP_AND_MENU),
	UNLOCK(NeedToBeTranslated.UNLOCK_DESC, NeedToBeTranslated.UNLOCK_RES, SelectionsModes.MAP),
	;

	/**
	 * La condition qui verrouille actuellement l'état
	 */
	private static Operations locked = null;

	/**
	 * Operation
	 */
	public final String value;
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

	Operations(String value, String restrict, SelectionsModes menuDrag) {
		this.value = value;
		this.tooltip = "";
		this.restrict = restrict;
		this.menuDrag = menuDrag;
	}

	/**
	 * Déverrouille la condition actuelle. L'éditor est débloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 *
	 * @param operations l'opération
	 */
	public static void unlock(@Nullable Operations operations) {
		if (operations != null) {
			operations.unlock();
		}
	}

	/**
	 * Verrouille la condition actuelle. L'éditor est bloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 * <p>
	 * Auto unlock
	 *
	 * @param operations l'opération
	 * @param observer   observer
	 */
	public static void lock(@NotNull Operations operations, Observer<GameObject> observer) {
		unlock(locked);
		locked = operations;
		locked.lock(observer);
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

		//débloque la sélection depuis la map
		if (!menuDrag.contains(SelectionsModes.MAP)) {
			EditorLauncher.removeState(EditorState.SPECIAL_POPUP_DISABLED);
		}
	}

	/**
	 * Retourne true si le gameObject respecte les conditions de l'opération
	 *
	 * @param object gameObject
	 * @return true si le gameObject respecte les conditions de l'opération
	 */
	public boolean isValid(GameObject object) {
		//cette méthode n'est pas géniale, on a écrit à la main les conditions
		if (this.equals(GIVE)) {
			return (object instanceof NeedContainer);
		} else if (this.equals(SUMMON)) {
			if (object instanceof Living) {
				if (object instanceof NPC) {
					return !((NPC) object).isHero();
				}
				return true;
			}
		} else if (this.equals(UNLOCK)) {
			return (object instanceof Lockable);
		} else if (this.equals(SHOW_ROOM)) {
			return (object instanceof Room);
		} else if (this.equals(HIDE_ROOM)) {
			return (object instanceof Room);
		}

		return false;
	}
}
