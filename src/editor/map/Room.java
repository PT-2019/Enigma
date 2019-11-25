package editor.map;

import editor.enums.Direction;

import java.util.HashMap;

/**
 * Une pièce, c'est un tableau de cases, et elle a 4 murs.
 */
public class Room {

	/** sa taille */
	private final int col, row;

	/** on a un mur dans chaque direction */
	private final HashMap<Direction, Wall> walls;

	/** les cases de la pièce */
	private final Case[] cases;

	/**
	 * Crée un pièce
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
}
