package game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import editor.datas.Direction;
import editor.datas.Layer;
import editor.entity.EntityFactory;
import editor.entity.EntitySerializable;
import editor.entity.interfaces.Entity;
import editor.utils.Utility;
import game.ui.Border;
import game.utils.InputListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Iterator;
import java.util.Set;

import static game.api.MapsNameUtils.*;

/**
 * Map de la libgdx
 *
 * @since 2.0 5 décembre 2019
 * @version 3.0 14 décembre 2019
 */
public class MapLibgdx extends Group implements InputListener {

	/**
	 * Dessinateur de la map
	 */
	private OrthogonalTiledMapRenderer map;

	/**
	 * Caméra de la map
	 */
	private OrthographicCamera camera;

	/**
	 * Bordure des cases de la map
	 */
	private Border border;

	/**
	 * info principales
	 */
	private final int tileWidth, tileHeight, width, height;

	/**
	 * Crée une map depuis un fichier tmx
	 *
	 * @param path fichier .tmx
	 */
	public MapLibgdx(@NotNull String path) {
		//load the map
		TiledMap tiledMap = new TmxMapLoader().load(path);
		this.map = new OrthogonalTiledMapRenderer(tiledMap);

		//save needed properties
		MapProperties properties = tiledMap.getProperties();
		this.tileWidth = properties.get(TILE_WIDTH_P.value, Integer.class);
		this.tileHeight = properties.get(TILE_HEIGHT_P.value, Integer.class);
		this.width = properties.get(WIDTH_P.value, Integer.class);
		this.height = properties.get(HEIGHT_P.value, Integer.class);

		this.border = new Border(this.width, this.height, this.tileHeight);

		this.map.getMap().getLayers().get(COLLISION.toString().toUpperCase()).setVisible(false);

		//setup camera
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.position.set(getMapWidth()/2,getMapHeight()/2,0);
		this.camera.update();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		//update caméra
		camera.update();
		//update borders
		border.setProjectionMatrix(camera.combined);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		//Setup camera
		this.map.setView(this.camera);

		//render map
		this.map.render();

		//render borders
		this.border.draw();
	}

	@Override
	public boolean keyDown(int keycode) {

		if(keycode == Input.Keys.LEFT) {
			camera.translate(-32, 0);
			return true;
		}
		if(keycode == Input.Keys.RIGHT){
			camera.translate(32,0);
			return true;
		}
		if(keycode == Input.Keys.UP) {
			camera.translate(0, 32);
			return true;
		}
		if(keycode == Input.Keys.DOWN) {
			camera.translate(0, -32);
			return true;
		}

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)){
			if (amount==1){
				camera.zoom += 0.05;
			}else{
				camera.zoom -= 0.05;
			}
			return true;
		}
		return false;
	}

	/**
	 *
	 * Charge une entité dans la map depuis un point
	 *
	 * @param entity entité a placer
	 * @param location coordonnés x,y ou placer l'entité
	 *
	 * @since 3.0 14 décembre 2019
	 */
	public void load(EntitySerializable entity, Point location) {
		//on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
		for (MapLayer mapLayer: this.map.getMap().getLayers()) {
			//c'est un layer de tiles ?
			if(!(mapLayer instanceof TiledMapTileLayer)) continue;

			TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

			//récupère les tiles de l'entités pour ce niveau
			Array<Float> entities = entity.getTiles(Utility.stringToEnum(tileLayer.getName(), Layer.values()));

			//si pas de tiles a mettre sur ce layer, on passe au suivant
			if(entities == null) continue;

			//TODO: supprimer ses trucs dégeu pour obtenir la taille de la map dans l'espace
			float dCamD = this.getStage().getWidth()/2 - this.getMapWidth() /2;
			float dCamE = this.getStage().getWidth()/2 + this.getMapWidth() /2 - entity.getWidth()*tileWidth;
			float dCamT = this.getStage().getHeight()/2 - this.getMapHeight() / 2;
			float dCamB = this.getStage().getHeight()/2 + this.getMapHeight() / 2 - entity.getHeight()*tileHeight;

			//on regarde si l'entités est dans la map
			Vector2 start = null; //start contient le x, y ou on commence a placer les tiles
			if(location.x >= dCamD && location.x <= dCamE){//Axe x
				if(location.y >= dCamT && location.y <= dCamB){//Axe y
					start = posToIndex(location.x, location.y, this);
				}
			}

			//si pas dans la map
			if(start == null) return;

			//calcul pour placer les tiles depuis x et y
			//sachant que y est inversé, on part de la dernière tile et on remonte
			//pas de problème pour x
			for (int i = (int) start.y -1 , index = 0; i >= (start.y - entity.getHeight()) ; i--) {
				for (int j = (int) start.x; j < start.x + entity.getWidth() && index < entities.size ; j++, index++) {
					MapLibgdxCell c = new MapLibgdxCell();
					c.setTile(this.map.getMap().getTileSets().getTile(MathUtils.ceil(entities.get(index))));
					c.setEntity(entity);
					tileLayer.setCell(j, i, c);
				}
			}
		}
	}

	/**
	 * Retourne la case (indices) dans la map depuis une positon x,y dans l'espace.
	 *
	 * Attention! La position  x,y est considérée comme étant toujours dans la map.
	 *
	 * @param posX position x
	 * @param posY position y
	 * @param map la map
	 *
	 * @return la case (indices) dans la map depuis une positon x,y dans l'espace.
	 *
	 * @since 3.0 14 décembre 2019
	 */
	private static Vector2 posToIndex(float posX, float posY, MapLibgdx map) {
		Vector2 index = new Vector2();

		//convert coordinates to row and column
		//and clamp to the map
		//TODO: supprimer ici aussi mais même calcul que dans load
		float dCamD = map.getStage().getWidth()/2 - map.getMapWidth() /2;
		float dCamT = map.getStage().getHeight()/2 - map.getMapHeight() / 2;

		index.x = MathUtils.round((posX - dCamD) / map.tileWidth);
		index.y = map.height - Math.round((posY - dCamT) / map.tileHeight);

		return index;
	}

	private float getMapHeight() { return height * tileHeight; }

	public float getMapWidth() { return width * tileWidth; }

	public float getUnitScale(){ return this.map.getUnitScale(); }

	public int getTileWidth() { return tileWidth; }

	public int getTileHeight() { return tileHeight; }
}

