package game.entity.map;

import api.entity.GameObject;
import api.entity.actor.GameActor;
import api.hud.components.CustomWindow;
import api.utils.Bounds;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import game.hud.Border;

import java.util.HashMap;

/**
 * Map de la libgdx
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 2.0 5 décembre 2019
 */
public class MapGame extends AbstractMap {
    /**
     * Dimension d'un tile
     */
    private final int tileWidth, tileHeight;
    /**
     * Dessinateur de la map
     */
    private final OrthogonalTiledMapRenderer map;
    /**
     * Caméra de la map
     */
    private final OrthographicCamera camera;

    /**
     * Fenetre parent qui contient la map
     */
    private final CustomWindow window;

    /**
     * Bordure des cases de la map
     */
    private final Border border;
    /**
     * Dimension de la map
     */
    private int mapWidth, mapHeight;
    /**
     * Les limites de la map dans l'espace
     *
     * @since 3.0
     */
    private Bounds mapBounds;

    /**
     * Les entités de la maps
     */
    private HashMap<Vector2, GameObject> added;

    private boolean showGrid;


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
        this.showGrid= show;
	}

	@Override
	public float getMapHeight() {
		return mapHeight;
	}

	@Override
	public float getMapWidth() {
		return mapWidth;
	}

	@Override
	public float getUnitScale() {
		return 0;
	}

	@Override
	public int getTileWidth() {
		return tileWidth;
	}

	@Override
	public int getTileHeight() {
		return tileHeight;
	}

	@Override
	public TiledMap getTiledMap() {
		return this.map.getMap();
	}

}
