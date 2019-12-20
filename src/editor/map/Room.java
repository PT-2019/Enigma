package editor.map;

import editor.datas.Direction;
import editor.datas.Layer;
import editor.entity.interfaces.IDInterface;
import editor.textures.Texture;

import javax.swing.*;
import java.util.HashMap;

/**
 * Une pièce, c'est un tableau de cases, et elle a 4 murs.
 *
 * @version 2.0
 */
public class Room implements IDInterface {

	/**
	 * sa taille
	 */
	private final int col, row;

	/**
	 * on a un mur dans chaque direction
	 */
	private final HashMap<Direction, Wall> walls;

	/**
	 * les cases de la pièce
	 */
	private final Case[] cases;

	/**
	 * ID
	 */
	private int id;

	/**
	 * Crée un pièce
	 */
	public Room() {//TODO: include a way to render walls
		this.col = 8;
		this.row = 11;

		this.cases = new Case[this.row * this.col];

		for (int i = 0; i < this.cases.length; i++) {
			this.cases[i] = new Case();
		}

		//fill
		//pour les tests
		ImageIcon img = new ImageIcon("assets/monsters/019.png");

		for (int i = 0; i < this.cases.length; i++) {
			this.cases[i] = new Case();
			this.cases[i].setWalkable(true);
			this.cases[i].setEntity(Layer.DECORATIONS1, new Texture(5, img.getImage()));
			this.cases[i].setEntity(Layer.DECORATIONS2, new Texture(5, img.getImage()));
			this.cases[i].setEntity(Layer.FLOOR1, new Texture(5, img.getImage()));
			this.cases[i].setEntity(Layer.FLOOR2, new Texture(5, img.getImage()));
		}

		//end

		this.walls = new HashMap<>();
	}

	public Room(int id) {
		this();
		this.id = id;
	}

	public Room(int col, int row, Case[] roomcase) {
		this.col = col;
		this.row = row;
		this.cases = roomcase;

		for (int i = 0; i < this.cases.length; i++) {
			if (this.cases[i] == null)
				this.cases[i] = new Case();
		}

		this.walls = new HashMap<>();
	}

	/**
	 * Return le nombre de colonnes
	 *
	 * @return le nombre de colonnes
	 */
	public int getCol() {
		return this.col;
	}

	/**
	 * Return le nombre de lignes
	 *
	 * @return le nombre de lignes
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * Return une case de la pièce
	 *
	 * @param col sa colonne
	 * @param row sa ligne
	 * @return une case de la pièce
	 */
	public Case getCase(int col, int row) {
		return this.cases[row * this.col + col];
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
