package api.entity.interfaces;

import editor.enigma.Enigma;
import editor.entity.player.Player;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractItem extends AbstractGameObject implements Item {

	/**
	 * Enigmes données à l'objet
	 *
	 * @since 4.0
	 */
	protected ArrayList<Enigma> enigmas;

	//constructeur par default
	protected AbstractItem(){
		this(-1);
	}

	protected AbstractItem(int id){
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
