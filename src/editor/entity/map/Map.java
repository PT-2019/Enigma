package editor.entity.map;

import api.entity.interfaces.IDInterface;

import java.awt.Point;
import java.util.HashMap;

/**
 * Une map, c'est un tableau de cases. Certaines sont des pièces.
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @see Room
 * @see Case
 * @since 2.0
 */
public class Map implements IDInterface {

	/**
	 * sa taille
	 */
	private final int col, row;

	/**
	 * Map, chaque point est le le coin supérieur gauche de la map ou
	 * a été ajouté la pièce
	 */
	private HashMap<Point, Room> rooms;

	/**
	 * la map
	 **/
	private Case[] cases;

	/**
	 * ID
	 */
	private int id;

	/**
	 * Crée une map
	 *
	 * @param col son nombre de colonnes
	 * @param row son nombre de lignes
	 */
	public Map(int col, int row) {
		this.col = col;
		this.row = row;
		this.cases = new Case[col * row];
		this.rooms = new HashMap<>();
	}

	/**
	 * Crée une map
	 *
	 * @param col son nombre de colonnes
	 * @param row son nombre de lignes
	 * @param id  ID
	 */
	public Map(int col, int row, int id) {
		this(col, row);
		this.id = id;
	}

	/**
	 * Ajoute une pièce dans la map
	 *
	 * @param col  colonne, le coin supérieur gauche de la map ou l'ajouter
	 * @param row  ligne, le coin supérieur gauche de la map ou l'ajouter
	 * @param room la pièce à ajouter
	 * @throws IllegalArgumentException si la colonne ou la ligne est invalide
	 */
	public void addRoom(int col, int row, Room room) {//todo : affecter les cases sans render
		int w = room.getCol(), h = room.getRow();

		//check if the position is correct
		if (w + col > this.col)
			throw new IllegalArgumentException("You cannot put a room here! (col + width) > mapWidth.");
		if (h + row > this.row)
			throw new IllegalArgumentException("You cannot put a room here! (row + height) > mapHeight.");

		this.rooms.put(new Point(col, row), room);//save top-left pos of the room
	}

	/**
	 * Affiche la map dans la console
	 */
	public void render() {
		StringBuilder sb = new StringBuilder("Map (");

		sb.append(this.col);
		sb.append("*");
		sb.append(this.row);
		sb.append(")\n");

		//put all room's values into map array
		for (java.util.Map.Entry<Point, Room> room : this.rooms.entrySet()) {
			int w = room.getValue().getCol(), h = room.getValue().getRow();
			for (int i = room.getKey().y, ir = 0; ir < h; i++, ir++) {
				for (int j = room.getKey().x, jr = 0; jr < w; j++, jr++) {
					this.cases[i * this.col + j] = room.getValue().getCase(jr, ir);
				}
			}
		}

		//print all case into a string builder
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				//TODO: find a way to print all layers
				Case aCase = this.cases[i * this.col + j];
				if (aCase != null)
					sb.append(aCase);
				else
					sb.append("x");
				sb.append("|");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Map (");

		sb.append(this.col);
		sb.append("*");
		sb.append(this.row);
		sb.append(")\n");

		//put all room's values into map array
		for (java.util.Map.Entry<Point, Room> room : this.rooms.entrySet()) {
			int w = room.getValue().getCol(), h = room.getValue().getRow();
			for (int i = room.getKey().y, ir = 0; ir < h; i++, ir++) {
				for (int j = room.getKey().x, jr = 0; jr < w; j++, jr++) {
					this.cases[i * this.col + j] = room.getValue().getCase(jr, ir);
				}
			}
		}

		//print all case into a string builder
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				//TODO: find a way to print all layers
				Case aCase = this.cases[i * this.col + j];
				if (aCase != null)
					sb.append(aCase);
				else
					sb.append("x");
				sb.append("|");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public int getCol() {
		return this.col;
	}

	public int getRow() {
		return this.row;
	}

	public HashMap<Point, Room> getRooms() {
		return rooms;
	}

	public void setRooms(HashMap<Point, Room> rooms) {
		this.rooms = rooms;
	}

	public Case[] getCases() {
		return cases;
	}

	public void setCases(Case[] cases) {
		this.cases = cases;
	}


	public Case getCase(int i) {
		return cases[i];
	}

	public void setCase(int indice, Case c) {
		cases[indice] = c;
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
