package common.entities.types;

import api.utils.annotations.ConvenienceClass;
import common.enigmas.Enigma;
import common.entities.Consumable;
import common.entities.players.Player;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Ce classe ne doit pas se retrouver ailleurs que dans un extends
 * <p>
 * Abstraction d'un Consommable.
 * <p>
 * Toutes les méthodes sont implémentées.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.1 25/12/2019
 * @since 4.0 24/12/2019
 */
@ConvenienceClass
public abstract class AbstractConsumable extends AbstractGameObject implements Consumable {

	/**
	 * Enigmes données à l'objet
	 *
	 * @since 4.0
	 */
	protected ArrayList<Enigma> enigmas;
	/**
	 *
	 */
	protected String atlasPath = "assets/files/atlas/uiskin.atlas";
	/**
	 *
 	 */
	protected String atlasName;

	/**
	 * Ce classe ne doit pas se retrouver ailleurs que dans un extends
	 * <p>
	 * Abstraction d'un Consommable.
	 */
	protected AbstractConsumable() {
		this(-1);
	}

	/**
	 * Ce classe ne doit pas se retrouver ailleurs que dans un extends
	 * <p>
	 * Abstraction d'un Consommable.
	 *
	 * @param id id unique de l'object pour les énigmes
	 */
	protected AbstractConsumable(int id) {
		super(id);
		this.enigmas = new ArrayList<Enigma>();
	}

	//Enigmas

	@Override
	public void interactsWith(Player p) {
		for (Enigma e : this.enigmas) {
			if (!e.isKnown()) e.discovered();
			else e.verifyConditions(p);
		}
	}

	@Override
	public void addEnigma(Enigma e) {
		this.enigmas.add(e);
	}

	@Override
	public void removeEnigma(Enigma e) {
		this.enigmas.remove(e);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterator<Enigma> getAllEnigmas() {
		ArrayList<Enigma> e = (ArrayList<Enigma>) this.enigmas.clone();
		return e.iterator();
	}

	/**
	 * Chemin vers l'atlas
	 *
	 * @return Chemin vers l'atlas
	 */
	public String getAtlasPath() {
		return this.atlasPath;
	}

	/**
	 * Nom de la région dans l'atlas
	 *
	 * @return Nom de la région dans l'atlas
	 */
	public String getAtlasRegionName() {
		return this.atlasName;
	}

	/**
	 * Définie les données de l'atlas
	 *
	 * @param atlasPath Chemin vers l'atlas
	 * @param atlasName Nom de la région dans l'atlas
	 */
	public void setAtlas(String atlasPath, String atlasName) {
		this.atlasPath = atlasPath;
		this.atlasName = atlasName;
	}

	//toString

	@Override
	public String toLongString() {
		StringBuilder s = new StringBuilder(this.toString() + ":: enigmas = {");
		int size = this.enigmas.size() - 1;
		int i = 0;
		for (Enigma e : this.enigmas) {
			s.append(e.toLongString());
			if (i < size) s.append(", ");
			i++;
		}
		s.append("}]");
		return s.toString();
	}

	@Override
	public abstract String toString();
}
