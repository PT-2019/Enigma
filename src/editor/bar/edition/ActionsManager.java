package editor.bar.edition;

import api.utils.Observer;
import api.utils.Subject;
import api.utils.annotations.ConvenienceMethod;
import data.config.Config;

import java.util.ArrayList;

/**
 * Conserve l'historique des actions effectuées.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.2
 * @since 5.0 29/01/2020
 */
public class ActionsManager implements Subject<ActionsManager> {

	/**
	 * Instance unique
	 *
	 * @since 5.0
	 */
	private static ActionsManager instance;
	private volatile boolean isDoingAction;
	/**
	 * Liste des actions
	 *
	 * @since 5.0
	 */
	private ArrayList<EditorAction> undo, redo;
	/**
	 * Personnes qui souhaitent êtres notifiés d'un changement
	 * dans l'historique.
	 *
	 * @since 5.1
	 */
	private ArrayList<Observer<ActionsManager>> obs;
	/**
	 * Dernière action effectuée
	 */
	private LastAction last;

	/**
	 * Crée un historique des actions effectuées
	 *
	 * @since 5.0
	 */
	private ActionsManager() {
		this.undo = new ArrayList<>(Config.MAX_OF_UNDO);
		this.redo = new ArrayList<>(Config.MAX_OF_REDO);
		this.obs = new ArrayList<>();
		this.isDoingAction = false;
		this.last = null;
	}

	/**
	 * Historique des actions effectuées
	 *
	 * @return instance de l'Historique des actions effectuées
	 * @since 5.0
	 */
	public static ActionsManager getInstance() {
		if (instance == null) instance = new ActionsManager();
		return instance;
	}

	/**
	 * Vide le manager
	 *
	 * @since 6.1
	 */
	@ConvenienceMethod
	public static void reset() {
		getInstance().clear();
	}

	/**
	 * Annule une action, puis renvoi s'il est encore possible d'annuler une autre action.
	 *
	 * @return s'il est encore possible d'annuler une autre action après.
	 * @since 5.0
	 */
	public boolean undo() {
		if (this.undo.isEmpty()) return false;

		this.isDoingAction = true;

		EditorAction editorAction = this.undo.get(this.undo.size() - 1);
		editorAction.undoAction(); //annule l'action de la dernière ajoutée

		this.last = new LastAction(editorAction, ActionsManagerType.UNDO);

		this.isDoingAction = false;

		this.undo.remove(editorAction);

		this.redo.add(editorAction); //stocke l'annulée

		this.updateObserver(this);

		return true;
	}

	/**
	 * Annule une annulation d'une action action, puis renvoi s'il est encore possible d'annuler une autre action.
	 *
	 * @return s'il est encore possible d'annuler une autre action après.
	 * @since 5.0
	 */
	public boolean redo() {
		if (this.redo.isEmpty()) return false;

		this.isDoingAction = true;

		//on rétablit l'action annulée
		EditorAction editorAction = this.redo.get(this.redo.size() - 1);
		editorAction.doAction();

		this.last = new LastAction(editorAction, ActionsManagerType.REDO);

		this.isDoingAction = false;

		//on l'ajoute aux undo
		this.undo.add(editorAction);
		this.redo.remove(editorAction);

		this.updateObserver(this);

		return true;
	}

	/**
	 * Ajoute une action
	 *
	 * @param action une action
	 * @return true si ajouté sinon false
	 * @since 5.0
	 */
	public boolean add(EditorAction action) {
		if (this.isDoingAction) return false;

		if (this.undo.size() >= Config.MAX_OF_UNDO - 1)
			this.undo.remove(0); //remove first

		//possible d'undo notre nouvelle action
		this.undo.add(action);

		//plus possible de redo car branche changée.
		this.redo.clear();

		//save comme étant la dernière
		this.last = new LastAction(action, ActionsManagerType.ADD);

		this.updateObserver(this);

		return true;
	}

	/**
	 * Vide toutes les actions redo/undo possibles
	 *
	 * @since 6.0
	 */
	public void clear() {
		for (EditorAction action : this.undo) {
			action.clear();
		}
		for (EditorAction action : this.redo) {
			action.clear();
		}
		this.undo.clear();
		this.redo.clear();
		this.last = null;
		this.updateObserver(this);
	}

	/**
	 * Retourne si redo est disponible
	 *
	 * @return true si redo est disponible
	 * @since 5.0
	 */
	public boolean isRedoAvailable() {
		return this.redo.size() > 0;
	}

	/**
	 * Retourne si undo est disponible
	 *
	 * @return true si undo est disponible
	 * @since 5.0
	 */
	public boolean isUndoAvailable() {
		return this.undo.size() > 0;
	}

	/**
	 * Ajout d'une personne qui souhaite êtres notifiée d'un changement
	 * dans l'historique.
	 *
	 * @since 5.1
	 */
	@Override
	public void addObserver(Observer<ActionsManager> obs) {
		this.obs.add(obs);
	}

	/**
	 * Retire d'une personne de celles notifiées d'un changement
	 * dans l'historique.
	 *
	 * @since 5.1
	 */
	@Override
	public void removeObserver(Observer<ActionsManager> obs) {
		this.obs.remove(obs);
	}

	/**
	 * Appels des personnes car il y a eu un changement dans l'historique.
	 *
	 * @since 5.1
	 */
	@Override
	public void updateObserver(ActionsManager object) {
		for (Observer<ActionsManager> obs : this.obs) {
			obs.update(object);
		}
	}

	/**
	 * Retourne la dernière action effectuée
	 *
	 * @return la dernière action effectuée
	 * @since 6.2
	 */
	public LastAction getLastAction() {
		return this.last;
	}

	/**
	 * Dernière action faite sur la map
	 */
	public static final class LastAction {
		public final ActionsManagerType type;
		public final EditorAction last;

		private LastAction(EditorAction last, ActionsManagerType type) {
			this.type = type;
			this.last = last;
		}
	}
}
