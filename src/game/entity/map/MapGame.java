package game.entity.map;

import api.entity.actor.GameActor;
import api.utils.Bounds;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MapGame extends AbstractMap {

	public MapGame(String path, float unitScale) {
		//CE CONSTRUCTEUR NE FAIT RIEN
		//c'est uniquement pour que votre constructeur soit de cette forme
		super(path, unitScale);

		//ICI CODE DE CHARGEMENT D'UNE MAP
	}

	public static Vector2 posToIndex(float posX, float posY, MapGame map) {
		//ICI CODER
		//AbstractMap#swingPosToIndex(float posX, float posY, final AbstractMap map) si besoin
		throw new UnsupportedOperationException("non codé");
	}

	public boolean isWalkable(float posX, float posY, GameActor actor) {
		//ICI CODER, gameActor est pas obligé, la classe peut être changé (GameActorAnimation..., PlayerGame)...
		//throw new UnsupportedOperationException("non codé");
		return true;
	}

	//boolean doAction(float posX, float posY, GameActor actor, ???  action)

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
