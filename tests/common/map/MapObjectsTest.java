package common.map;

import com.badlogic.gdx.math.Vector2;
import common.entities.consumable.Key;
import common.entities.items.Chest;
import common.entities.items.Door;
import common.entities.special.Room;
import common.entities.types.EnigmaContainer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test sur MapObject
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0 09/02/2020
 * @since 6.0 09/02/2020
 */
public class MapObjectsTest {

	/**
	 * GetAllObjectsByClassName retourne bien les bon objects.
	 */
	@Test
	public void testGetAllObjectsByClassName(){
		MapObjects objects = new MapObjects();
		Key key = new Key();
		Door door1 = new Door(), door2 = new Door();
		Chest chest = new Chest();
		objects.put(new Vector2(10,10), key);//ok
		objects.put(new Vector2(10,10), door1);//ok
		objects.put(new Vector2(10,10), new Room());
		objects.put(new Vector2(10,10), new Room());
		objects.put(new Vector2(10,10), door2);//ok
		objects.put(new Vector2(10,10), chest);//ok

		ArrayList<EnigmaContainer> all = objects.getAllObjectsByClass(EnigmaContainer.class);

		//attends key, door, door, chest
		assertEquals(4, all.size());
		assertTrue(all.contains(key));
		assertTrue(all.contains(door1));
		assertTrue(all.contains(door2));
		assertTrue(all.contains(chest));
	}

}
