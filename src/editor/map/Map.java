package editor.map;

import editor.enums.Layer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Une map, c'est un tableau de cases. Certaines sont des pièces.
 * @see Room
 * @see Case
 */
public class Map {

	/** sa taille */
	private final int col,  row;

	/**
	 * Map, chaque point est le le coin supérieur gauche de la map ou
	 * a été ajouté la pièce
	 */
	private HashMap<Point,Room> rooms;

	/** la map **/
	private Case[] cases;

	/**
	 * Crée une map
	 * @param col son nombre de colonnes
	 * @param row son nombre de lignes
	 */
	public Map(int col, int row){
		this.col = col;
		this.row = row;
		this.cases = new Case[col*row];
		this.rooms = new HashMap<>();
	}

	/**
	 * Ajoute une pièce dans la map
	 * @param col colonne, le coin supérieur gauche de la map ou l'ajouter
	 * @param row ligne, le coin supérieur gauche de la map ou l'ajouter
	 * @param room la pièce à ajouter
	 * @throws IllegalArgumentException si la colonne ou la ligne est invalide
	 */
	public void addRoom(int col, int row, Room room){//todo : affecter les cases sans render
		int w = room.getCol(), h = room.getRow();

		//check if the position is correct
		if(w + col > this.col)
			throw new IllegalArgumentException("You cannot put a room here! (col + width) > mapWidth.");
		if(h + row > this.row)
			throw new IllegalArgumentException("You cannot put a room here! (row + height) > mapHeight.");

		this.rooms.put(new Point(col,row),room);//save top-left pos of the room
	}

	/**
	 * Affiche la map
	 */
	public JPanel render(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints bagConstraints = new GridBagConstraints();
		panel.setBackground(Color.BLACK);

		//put all room's values into map array
		for (java.util.Map.Entry<Point,Room> room :this.rooms.entrySet()) {
			int w = room.getValue().getCol(), h = room.getValue().getRow();
			for (int i = room.getKey().y, ir = 0; ir < h ; i++, ir++) {
				for (int j = room.getKey().x, jr = 0; jr < w ; j++, jr++) {
					this.cases[i*this.col+j] = room.getValue().getCase(jr,ir);
				}
			}
		}

		for (int i = 0; i < this.row ; i++) {
			for (int j = 0; j < this.col ; j++) {
				//TODO: find a way to print all layers
				Case aCase = this.cases[i*this.col+j];
				bagConstraints.gridx = j;
				bagConstraints.gridy = i;
				if(aCase != null) {
					panel.add(new A(aCase), bagConstraints);
				}
			}
		}
		return panel;
	}

	private static final class A extends JPanel{
		A(Case aCase){
			this.setOpaque(false);
			JLayeredPane pane = new JLayeredPane();
			/*pane.setPreferredSize(new Dimension(300, 310));
			pane.setBorder(BorderFactory.createTitledBorder(
					"Move the Mouse to Move Duke"));*/
			pane.add(new JLabel(new ImageIcon(aCase.getEntity(Layer.FLOOR1).getTexture().getImage())), 0);
			pane.add(new JLabel(new ImageIcon(aCase.getEntity(Layer.DECORATIONS1).getTexture().getImage())), 1);
			pane.add(new JLabel(new ImageIcon(aCase.getEntity(Layer.DECORATIONS2).getTexture().getImage())), 2);
			this.add(pane);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Map (");

		sb.append(this.col);
		sb.append("*");
		sb.append(this.row);
		sb.append(")\n");

		//put all room's values into map array
		for (java.util.Map.Entry<Point,Room> room :this.rooms.entrySet()) {
			int w = room.getValue().getCol(), h = room.getValue().getRow();
			for (int i = room.getKey().y, ir = 0; ir < h ; i++, ir++) {
				for (int j = room.getKey().x, jr = 0; jr < w ; j++, jr++) {
					this.cases[i*this.col+j] = room.getValue().getCase(jr,ir);
				}
			}
		}

		//print all case into a string builder
		for (int i = 0; i < this.row ; i++) {
			for (int j = 0; j < this.col ; j++) {
				//TODO: find a way to print all layers
				Case aCase = this.cases[i*this.col+j];
				if(aCase != null)
					sb.append(aCase);
				else
					sb.append("x");
				sb.append("|");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public int getCol() { return this.col; }

	public int getRow() {
		return this.row;
	}

	public Case[] getCases() { return this.cases; }

}
