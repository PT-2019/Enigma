package editor.entity.view;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import editor.entity.map.Room;

import java.awt.Point;
import java.util.Map;

/**
 * TODO: comment RoomView and write Readme.md in editor.entity.view
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 4.0
 */
public class RoomView extends ShapeRenderer {

	private boolean visible;

	private Map<Point, Room> room;

	private int tile;

	private int mapHeight;

	private int mapWidth;


	public RoomView(Map<Point, Room> r, int t, int mapHeight, int mapWidth) {
		visible = false;
		room = r;
		tile = t;
		this.mapHeight = mapHeight * tile;
		this.mapWidth = mapWidth;
	}

	public void draw() {
		if (visible) {
			this.begin(ShapeType.Filled);
			this.setColor(0, 50, 246, 0);
			for (Map.Entry<Point, Room> r : room.entrySet()) {
				Room tmp = r.getValue();
				int y = (mapHeight - tmp.getRow() * tile) - (int) r.getKey().getY() * tile;

				this.rect((int) r.getKey().getX() * tile, y, tmp.getCol() * tile, tmp.getRow() * tile);
			}
			this.end();
		}
	}

	public void setVisible(boolean b) {
		visible = b;
	}

	public int getTile() {
		return tile;
	}

	public int getHeightSize() {
		return mapHeight;
	}

	public int getMapWidth() {
		return mapWidth;
	}
}
