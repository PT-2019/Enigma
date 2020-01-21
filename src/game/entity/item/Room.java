package game.entity.item;

import api.entity.AbstractGameObject;
import api.enums.TypeEntite;

import java.util.EnumMap;

/**
 * Une pièce
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 4.0 24/12/2019
 * @since 4.0 24/12/2019
 */
public class Room extends AbstractGameObject {

	/**
	 * Crée une pièce
	 *
	 * @since 4.0
	 */
	public Room(){
		super(-1);
	}

	/**
	 * Crée une pièce d'une certaine taille
	 *
	 * @param cols nombre de colonnes
	 * @param rows nombre de lignes
	 *
	 * @since 4.0
	 */
	public Room(int cols, int rows){
		super(-1);
		this.setDimension(cols, rows);
	}

	@Override
	public EnumMap<TypeEntite, Boolean> getImplements() {
		EnumMap<TypeEntite,Boolean> imp = new EnumMap<>(TypeEntite.class);
		imp.put(TypeEntite.item,false);
		imp.put(TypeEntite.lockable,false);
		imp.put(TypeEntite.passage,false);
		return imp;
	}
}
