package game.entity.item;

import api.entity.interfaces.AbstractGameObject;

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

}
