package editor.menus.enimas.create;

import api.utils.Observer;
import common.entities.GameObject;
import common.entities.players.NPC;
import common.entities.types.Living;
import common.entities.types.Lockable;
import common.entities.types.NeedContainer;
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
	GIVE("Donne un object à l'utilisateur", "Objects uniquement (livre...).", null, SelectionsModes.MENU_AND_POPUP),
	SUMMON("Invoque une entité", "Seulement des personnages, pas de héros.", null, SelectionsModes.MAP_AND_MENU),
	UNLOCK("Dévérouille un object", "Seulement un object \"Décors\" fermable.", null, SelectionsModes.MAP),
	;

	/**
	 * La condition qui verrouille actuellement l'état
	 */
	private static Operations locked = null;
	public final String value;
	/**
	 * Le nom d'une classe dont la méthode run crée.
	 * <p>
	 * Non utilisé et non fonctionnel.
	 * <p>
	 * La classe possèderait un constructeur qui prends une EnigmaView et
	 * un OperéationListener.
	 *
	 * @see editor.menus.enimas.create.listeners.OperationListener#actionPerformed(java.awt.event.ActionEvent)
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

	Operations(String value, String restrict, Class<? extends Runnable> initClass, SelectionsModes menuDrag) {
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
	 * @param operations l'opération
	 */
	public static void unlock(@Nullable Operations operations) {
		if (operations != null) {
			operations.unlock();
		}
	}

	/**
	 * Vérouille la condition actuelle. L'éditor est bloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 * <p>
	 * Auto unlock
	 *
	 * @param operations l'opération
	 * @param observer   observeur
	 */
	public static void lock(@NotNull Operations operations, Observer<GameObject> observer) {
		unlock(locked);
		locked = operations;
		locked.lock(observer);
	}

	/**
	 * Vérouille la condition actuelle. L'éditor est bloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 *
	 * @param observer observeur
	 */
	private void lock(Observer<GameObject> observer) {
		if (menuDrag.contains(SelectionsModes.MENU)) DragAndDropBuilder.setForPopup(observer);
		//if(menuDrag.contains(SelectionsModes.MAP)) EnigmaView.setAvailable(observer);
	}

	/**
	 * Dévérouille la condition actuelle. L'éditor est débloqué.
	 * Par exemple le drag and drop depuis le menu peu être désactivé.
	 */
	private void unlock() {
		if (menuDrag.contains(SelectionsModes.MENU)) DragAndDropBuilder.setForPopup(null);
		//if(menuDrag.contains(SelectionsModes.MAP)) EnigmaView.setAvailable(null);
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
		}
		return false;
	}
}
