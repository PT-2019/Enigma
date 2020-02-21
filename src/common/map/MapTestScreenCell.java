package common.map;

import api.utils.Utility;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import common.entities.GameObject;
import data.Layer;
import game.EnigmaGame;
import game.screens.TestScreen;

/**
 * Une cellule de la map libgdx
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 3.0 14 décembre 2019
 */
public class MapTestScreenCell extends TiledMapTileLayer.Cell {

	/**
	 * Niveau ou se trouve l'entité
	 */
	private final TiledMapTileLayer layer;

	/**
	 * Index de la case parmi les cases de l'entité
	 */
	private final int index;

	/**
	 * L'entité contenue dans la case
	 * <p>
	 * this.entity.getClassName renvoi par exemple editor.entity.item.Chest
	 */
	private GameObject entity;

	/**
	 * Crée une cellule de la map
	 *
	 * @param layer Niveau ou se trouve l'entité
	 * @param index Index de la case parmi les cases de l'entité
	 */
	public MapTestScreenCell(TiledMapTileLayer layer, int index) {
		this.layer = layer;
		this.index = index;
		this.entity = null;
	}

	public GameObject getEntity() {
		return entity;
	}

	/**
	 * Défini l'entité contenue dans la case
	 *
	 * @param entity l'entité contenue dans la case
	 */
	public void setEntity(GameObject entity) {
		this.entity = entity;
	}

	public int getIndex() {
		return index;
	}

	/**
	 * Retourne le niveau Libgdx
	 *
	 * @return le niveau libgdx
	 */
	public TiledMapTileLayer getLayer() {
		return this.layer;
	}

	/**
	 * Retourne le nom du niveau
	 *
	 * @return le nom du niveau
	 */
	public Layer getLayerName() {
		return Utility.stringToEnum(this.layer.getName(), Layer.values());
	}

	/**
	 * Vide la case
	 *
	 * @return message de suppression
	 */
	public String removeEntity() {
		return ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap().removeEntity(entity);
	}
}

