package api.utils;

import api.entity.GameObject;

public interface Subject {

	void addObserveur(Observer obs);

	void removeObserveur(Observer obs);

	void updateObserveur(GameObject object);
}
