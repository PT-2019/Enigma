import editor.map.Map;
import editor.map.Room;
import org.junit.Test;

/**
 * Fait des tests sur la map
 */
public class TestMap {

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

		map.render();
	}

}
