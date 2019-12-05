package editor.map;

import editor.entity.interfaces.IDInterface;
import editor.enums.Direction;

import java.util.HashMap;

/**
 * Une pièce, c'est un tableau de cases, et elle a 4 murs.
 * @version 2.0
 */
public class Room implements IDInterface {

	/** sa taille */
	private final int col, row;

	/** on a un mur dans chaque direction */
	private final HashMap<Direction, Wall> walls;

	/** les cases de la pièce */
	private final Case[] cases;

	/**
	 * ID
	 */
	private int id;

	/**
	 * Crée une pièce
	 */
	public Room(){//TODO: include a way to render walls
		this.col = 8;
		this.row = 11;

		this.cases = new Case[this.row*this.col];

		for (int i = 0; i < this.cases.length ; i++) {
			this.cases[i] = new Case();
			this.cases[i].setWalkable(true);
		}

		this.walls = new HashMap<>();
	}

	/**
	 * Crée une pièce
	 */
	public Room(int id){//TODO: include a way to render walls
		this.col = 8;
		this.row = 11;

		this.cases = new Case[this.row*this.col];

		for (int i = 0; i < this.cases.length ; i++) {
			this.cases[i] = new Case();
			this.cases[i].setWalkable(true);
		}

		this.walls = new HashMap<>();
		this.id = id;
	}

	/**
	 * Return le nombre de colonnes
	 * @return le nombre de colonnes
	 */
	public int getCol() { return this.col; }

	/**
	 * Return le nombre de lignes
	 * @return le nombre de lignes
	 */
	public int getRow() { return this.row; }

	/**
	 * Return une case de la pièce
	 * @param col sa colonne
	 * @param row sa ligne
	 * @return une case de la pièce
	 */
	public Case getCase(int col, int row) {
		return this.cases[row*this.col+col];
	}

	/**
	 * Obtenir l'ID
	 * @return L'ID, -1 si pas initialisé
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Définir l'ID
	 * @param id ID
	 */
	public void setID(int id) {
		this.id = id;
	}
}
