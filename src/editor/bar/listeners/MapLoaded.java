package editor.bar.listeners;

import api.utils.Observer;
import api.utils.Subject;

import java.util.ArrayList;

/**
 * Une classe qui préviens ceux qui ont
 * besoin d'une map, qu'une map a étée chargée/est disponible.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 12/02/2020
 * @since 6.0 12/02/2020
 */
public class MapLoaded implements Subject<MapLoaded> {

	private ArrayList<Observer<MapLoaded>> subjects;
	private boolean mapLoaded;

	private static MapLoaded instance = null;

	/**
	 * Une classe qui préviens ceux qui ont
	 * besoin d'une map, qu'une map a étée chargée/est disponible.
	 * @return l'instance unique
	 */
	public static MapLoaded getInstance() {
		if(MapLoaded.instance == null){
			MapLoaded.instance = new MapLoaded();
		}
		return MapLoaded.instance;
	}

	private MapLoaded() {
		this.subjects = new ArrayList<>();
		this.mapLoaded = false;
	}

	@Override
	public void addObserver(Observer<MapLoaded> obs) {
		this.subjects.add(obs);
	}

	@Override
	public void removeObserver(Observer<MapLoaded> obs) {
		this.subjects.remove(obs);
	}

	@Override
	public void updateObserver(MapLoaded loaded) {
		for (Observer<MapLoaded> obs: this.subjects){
			obs.update(loaded);
		}
	}

	/**
	 * Définit si la map est chargée ou non
	 * @param mapLoaded true si chargée
	 */
	public void setMapLoaded(boolean mapLoaded) {
		this.mapLoaded = mapLoaded;
		this.updateObserver(this);
	}

	/**
	 * Retourne si la map est chargée ou non
	 * @return true si chargée
	 */
	public boolean isMapLoaded() {
		return this.mapLoaded;
	}
}
