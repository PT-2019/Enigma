package editor.screens.menus.edition;

import api.utils.Observer;
import api.utils.Subject;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Conserve l'historique des actions effectuées.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 5.0 29/01/2020
 * @since 5.0 29/01/2020
 */
public class ActionsManager implements Subject<ActionsManager> {
	private static ActionsManager instance = new ActionsManager();

	public Stack<EditorAction> undo, redo;
	private ArrayList<Observer<ActionsManager>> obs;

	public static ActionsManager getInstance() {
		if(instance == null){
			instance = new ActionsManager();
		}
		return instance;
	}

	private ActionsManager() {
		this.undo = new Stack<>();
		this.redo = new Stack<>();
		this.obs = new ArrayList<>();
	}

	/**
	 * Annule une action, puis renvoi s'il est encore possible d'annuler une autre action.
	 * @return s'il est encore possible d'annuler une autre action après.
	 */
	public boolean undo(){
		if(this.undo.isEmpty()) return true;//TODO: false

		//ajoute aux redo et on lance l'action normale
		this.redo.push(this.undo.pop()).doAction();

		return true;
	}

	/**
	 * Annule une annulation d'une action action, puis renvoi s'il est encore possible d'annuler une autre action.
	 * @return s'il est encore possible d'annuler une autre action après.
	 */
	public boolean redo(){
		if(this.redo.isEmpty()) return true;

		//ajoute aux redo et on lance l'action normale
		this.undo.push(this.redo.pop()).undoAction();

		return true;
	}

	public void add(EditorAction action){
		this.undo.add(action);
	}

	/**
	 * Retourne si redo est disponible
	 * @return true si redo est disponible
	 */
	public boolean isRedoAvailable(){
		return this.redo.size() > 0;
	}

	/**
	 * Retourne si undo est disponible
	 * @return true si undo est disponible
	 */
	public boolean isUndoAvailable(){
		return this.undo.size() > 0;
	}

	@Override
	public void addObserveur(Observer<ActionsManager> obs) {
		this.obs.add(obs);
	}

	@Override
	public void removeObserveur(Observer<ActionsManager> obs) {
		this.obs.remove(obs);
	}

	@Override
	public void updateObserveur(ActionsManager object) {
		for (Observer<ActionsManager> obs: this.obs){
			obs.update(object);
		}
	}
}
