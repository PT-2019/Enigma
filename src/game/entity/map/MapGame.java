package game.entity.map;

import api.utils.Bounds;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class MapGame extends AbstractMap {

	public MapGame(String path, float unitScale) {
		//CE CONSTRUCTEUR NE FAIT RIEN
		//c'est uniquement pour que votre constructeur soit de cette forme
		super(path, unitScale);

		//ICI CODE DE CHARGEMENT D'UNE MAP
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		//update map
		//....
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		//dessin de la map
		//....

		super.draw(batch, parentAlpha);//cette ligne dessine les composant ajoutés à la map
	}

	//A COMPLETER TOUS LES GETTERS ET SETTERS... (voir MapTestScreen)

	@Override
	protected Bounds getMapBounds() {
		return null;
	}

	@Override
	protected Rectangle getMapSize() {
		return null;
	}

	@Override
	public void showGrid(boolean show) {

	}

	@Override
	public float getMapHeight() {
		return 0;
	}

	@Override
	public float getMapWidth() {
		return 0;
	}

	@Override
	public float getUnitScale() {
		return 0;
	}

	@Override
	public int getTileWidth() {
		return 0;
	}

	@Override
	public int getTileHeight() {
		return 0;
	}

	@Override
	public TiledMap getTiledMap() {
		return null;
	}

}
