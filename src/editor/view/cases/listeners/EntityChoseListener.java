package editor.view.cases.listeners;


import api.entity.GameObject;
import api.utils.Observer;
import api.utils.Subject;

import java.util.ArrayList;

public class EntityChoseListener implements Subject<GameObject>{

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
