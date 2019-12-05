package editor.map;

import editor.datas.Direction;
import editor.datas.Layer;
import editor.entities.Player;
import editor.entities.interfaces.Entity;
import editor.entities.interfaces.IDInterface;
import editor.io.JsonTextureLoader;
import editor.textures.Texture;

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
	 * Crée un pièce
	 */
	public Room(){//TODO: include a way to render walls
		this.col = 8;
		this.row = 11;

		this.cases = new Case[this.row*this.col];

		for (int i = 0; i < this.cases.length ; i++) {
			this.cases[i] = new Case();
		}

		//todo: remove this (just for tests)
		//Begin
		Entity library = new Entity() {//fake entities
			@Override
			public void interactsWith(Player p) {}

			@Override
			public Texture getTexture() {
				return JsonTextureLoader.getTexture("tile723", "assets/files/atlas/test.atlas");
			}

			@Override
			public void setTexture(Texture t) {}

			@Override
			public void showDialog() {}

			@Override
			public int getID() {return 0;}

			@Override
			public void setID(int id) {}
		};
		//fill
		for (int i = 0; i < this.cases.length ; i++) {
			this.cases[i] = new Case();
			this.cases[i].setWalkable(true);
			this.cases[i].setEntity(Layer.DECORATIONS1, library);
			this.cases[i].setEntity(Layer.DECORATIONS2, library);
			this.cases[i].setEntity(Layer.FLOOR1, library);
			this.cases[i].setEntity(Layer.FLOOR2, library);
		}

		//end

		this.walls = new HashMap<>();
	}

	public Room(int id){
		this();
		this.id = id;
	}

	public Room(int col, int row, Case[] roomcase) {
		this.col = col;
		this.row = row;
		this.cases = roomcase;

		for (int i = 0; i < this.cases.length ; i++) {
			if(this.cases[i] == null)
				this.cases[i] = new Case();
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
