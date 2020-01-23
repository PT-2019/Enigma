package editor.view.cases.listeners;


import api.entity.GameObject;
import api.utils.Observer;
import api.utils.Subject;

import java.util.ArrayList;

public class EntityChoseListener implements Subject {

	private ArrayList<Observer> obsList;

	public EntityChoseListener() {
		obsList = new ArrayList<>();
	}

	@Override
	public void addObserveur(Observer obs) {
		obsList.add(obs);
	}

	@Override
	public void removeObserveur(Observer obs) {
		obsList.remove(obs);
	}

	@Override
	public void updateObserveur(GameObject object) {
		for (Observer obs : obsList) {
			obs.update(object);
		}
	}
}
