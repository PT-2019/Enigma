package common.map.model;

import common.entities.types.IDInterface;

/**
 * Le mur d'une pièce
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @see Room
 * @since 2.0
 */
@Deprecated
public class Wall implements IDInterface {

	/**
	 * la pièce que si charge de l'afficher
	 */
	private Room printer;
	/**
	 * les cases qui le compose
	 */
	private Case[] cases;

	/**
	 * ID
	 */
	private int id;

	/**
	 * Crée un mur
	 *
	 * @param printer la pièce que si charge de l'afficher
	 * @param wall    les cases qui le compose
	 */
	public Wall(Room printer, Case[] wall) {
		this.printer = printer;
		this.cases = wall;
	}

	/**
	 * Crée un mur
	 *
	 * @param printer la pièce que si charge de l'afficher
	 * @param wall    les cases qui le compose
	 * @param id      ID
	 */
	public Wall(Room printer, Case[] wall, int id) {
		this(printer, wall);
		this.id = id;
	}

	/**
	 * Obtenir l'ID
	 *
	 * @return L'ID, -1 si pas initialisé
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Définir l'ID
	 *
	 * @param id ID
	 */
	public void setID(int id) {
		this.id = id;
	}

}