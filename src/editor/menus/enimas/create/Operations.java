package editor.menus.enimas.create;

import api.utils.Observer;
import common.entities.GameObject;
import common.entities.players.NPC;
import common.entities.special.MusicEditor;
import common.entities.special.Room;
import common.entities.types.Living;
import common.entities.types.Lockable;
import common.entities.types.NeedContainer;
import data.EditorState;
import data.NeedToBeTranslated;
import editor.EditorLauncher;
import editor.menus.SelectionsModes;
import editor.popup.listeners.CaseListener;
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
	GIVE(NeedToBeTranslated.GIVE_DESC, NeedToBeTranslated.ITEMS_ONLY, SelectionsModes.MENU_AND_POPUP),
	SUMMON(NeedToBeTranslated.SUMMON_DESC, NeedToBeTranslated.LIVING_ONLY, SelectionsModes.MENU),
	SHOW_ROOM(NeedToBeTranslated.SHOW_ROOM_DESC, NeedToBeTranslated.ROOM_ONLY, SelectionsModes.MAP),
	HIDE_ROOM(NeedToBeTranslated.HIDE_ROOM_DESC, NeedToBeTranslated.ROOM_ONLY, SelectionsModes.MAP),
	UNLOCK(NeedToBeTranslated.UNLOCK_DESC, NeedToBeTranslated.LOCKABLE_ONLY, SelectionsModes.MAP),
	SOUND(NeedToBeTranslated.LAUNCH_SOUND, NeedToBeTranslated.NO_SOUND, SelectionsModes.MUSIC),
	MAIN_MUSIC(NeedToBeTranslated.CHANGE_MAIN_MUSIC, NeedToBeTranslated.NO_MUSIC, SelectionsModes.MUSIC),
	END_GAME(NeedToBeTranslated.END_GAME, "", SelectionsModes.NONE),
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
	 * Opérations
	 *
	 * @param value    description
	 * @param restrict message si la condition n'est pas respectée
	 * @param menuDrag mode de sélection de l'entité si besoin
	 */
	Operations(String value, String restrict, SelectionsModes menuDrag) {
		this(value, restrict, menuDrag, true);
	}

	/**
	 * Opérations
	 *
	 * @param value     description
	 * @param restrict  message si la condition n'est pas respectée
	 * @param menuDrag  mode de sélection de l'entité si besoin
	 * @param available true si condition disponible
	 */
	Operations(String value, String restrict, SelectionsModes menuDrag, boolean available) {
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
	 * @param operations l'opération
	 */
	public static void unlock(@Nullable Operations operations) {
		if (operations != null) {
			operations.unlock();
		} else if (Operations.locked != null) {
			Operations.locked.unlock();
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
		Operations.unlock(Operations.locked);//unlock de la condition actuelle
		Operations.locked = operations;
		Operations.locked.lock(observer);
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

		//débloque la sélection depuis la map
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
	 * Retourne true si le gameObject respecte les conditions de l'opération
	 *
	 * @param object gameObject
	 * @return true si le gameObject respecte les conditions de l'opération
	 */
	public boolean isValid(GameObject object) {
		//cette méthode n'est pas géniale, on a écrit à la main les conditions
		if (this.equals(Operations.GIVE)) {
			return (object instanceof NeedContainer);
		} else if (this.equals(Operations.SUMMON)) {
			if (object instanceof Living) {
				if (object instanceof NPC) return !((NPC) object).isHero();
				return true;
			}
		} else if (this.equals(Operations.UNLOCK)) {
			return (object instanceof Lockable);
		} else if (this.equals(Operations.SHOW_ROOM)) {
			return (object instanceof Room);
		} else if (this.equals(Operations.HIDE_ROOM)) {
			return (object instanceof Room);
		} else if (this.equals(Operations.SOUND)) {
			return object instanceof MusicEditor;
		} else if (this.equals(Operations.MAIN_MUSIC)) {
			return object instanceof MusicEditor;
		}

		return false;
	}
}
