package editor.map;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Fait des tests sur la map
 */
public class MapTestMain {

	/**
	 * Test si la structure de la map marche
	 */
	@Test
	public void testMapStructureWork(){
		Map map = new Map(30,20);
		Room room1 = new Room();
		Room room2 = new Room();

		map.addRoom(1,3, room1);

		map.addRoom(11,2, room2);

		System.out.println(map.toString());
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(30*32,20*32));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//on met a gauche le nom de la sous-texture et a droite son fichier

		Map map = new Map(30,20);
		Room room1 = new Room();
		Room room2 = new Room();

		map.addRoom(1,3, room1);

		map.addRoom(11,2, room2);

		frame.add(map.render(),BorderLayout.CENTER);
		frame.setVisible(true);
	}

}