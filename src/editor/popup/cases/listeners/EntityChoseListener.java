package editor.popup.cases.listeners;

import api.utils.Observer;
import api.utils.Subject;
import common.entities.GameObject;

import java.util.ArrayList;

/**
 * Observer d'entités
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0 30/01/2020
 * @since 5.0 30/01/2020
 */
public class EntityChoseListener implements Subject<GameObject> {

	private ArrayList<Observer<GameObject>> obsList;

	public EntityChoseListener() {
		obsList = new ArrayList<>();
	}

	@Override
	public void addObserveur(Observer<GameObject> obs) {
		obsList.add(obs);
	}

	@Override
	public void removeObserveur(Observer<GameObject> obs) {
		obsList.remove(obs);
	}

	@Override
	public void updateObserveur(GameObject object) {
		for (Observer<GameObject> obs : obsList) {
			obs.update(object);
		}
	}
}
