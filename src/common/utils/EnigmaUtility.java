package common.utils;

import api.utils.Utility;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import common.entities.GameObject;
import common.map.MapTestScreenCell;
import data.TypeEntity;
import data.config.Config;

import java.util.ArrayList;

/**
 * Des méthodes utiles pour Enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.1
 * @since 6.0
 */
public final class EnigmaUtility {

	/**
	 * Obtenir le nom de toutes les maps en local
	 * Les noms sont issus des fichiers tmx présents
	 *
	 * @return Le nom des maps
	 * @since 6.0
	 */
	public static ArrayList<String> getAllMapNames() {
		return Utility.getAllFiles(Config.MAP_FOLDER, new String[]{Config.MAP_EXTENSION}, true);
	}

	/**
	 * Obtenir le nom de toutes les parties en local
	 * Les noms sont issus des fichiers tmx présents
	 *
	 * @return Le nom des parties
	 * @since 6.0
	 */
	public static ArrayList<String> getAllGameName() {
		return Utility.getAllFiles(Config.GAME_DATA_FOLDER, new String[]{Config.DATA_EXTENSION}, true);
	}

	/**
	 * Obtenir le nom de toutes les musiques en local
	 * Les noms sont issus des fichiers tmx présents
	 *
	 * @return Le nom des maps
	 * @since 6.0
	 */
	public static ArrayList<String> getAllMusicName() {
		return Utility.getAllFiles(Config.MUSIC_FOLDER, Config.MUSIC_EXTENSIONS, true);
	}

	/**
	 * Obtenir le nom de toutes les maps en local
	 * Les noms sont issus des fichiers tmx présents
	 *
	 * @return Le nom des maps
	 * @since 6.0
	 */
	public static ArrayList<String> getAllSoundName() {
		return Utility.getAllFiles(Config.SOUND_FOLDER, Config.SOUND_EXTENSIONS, true);
	}

	/**
	 * Retourne la cellule contenant l'entité la plus intéressante
	 *
	 * @param cell cellule dont on veut l'entité la plus intéressante
	 * @param map  la map
	 * @return la cellule contenant l'entité la plus intéressante
	 * @since 5.0
	 */
	public static MapTestScreenCell getRelevantEntity(MapTestScreenCell cell, TiledMap map) {
		MapTestScreenCell relevant = null;
		final int LAYER_COUNT = 4;

		//on parcours tout les layers à la recherche d'une entité
		// pour afficher le panneau avec l'entité en premier
		GameObject entity = cell.getEntity();
		if (entity == null) {
			MapLayers layers = map.getLayers();
			MapTestScreenCell tmp;

			for (int i = 0; i < LAYER_COUNT; i++) {
				TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(i);
				tmp = (MapTestScreenCell) layer.getCell(cell.getIndex() % layer.getWidth(),
						cell.getIndex() / layer.getWidth());
				if (tmp.getEntity() != null) {
					relevant = tmp;
				}
			}
			//si ya déjà une entité mais c'est une pièce
		} else if (entity.getImplements().get(TypeEntity.CONTAINER_MANAGER)) {
			//on regarde si on a quelque chose de mieux
			MapLayers layers = map.getLayers();
			MapTestScreenCell tmp;

			for (int i = 0; i < LAYER_COUNT; i++) {
				TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(i);
				tmp = (MapTestScreenCell) layer.getCell(cell.getIndex() % layer.getWidth(),
						cell.getIndex() / layer.getWidth());
				if (tmp.getEntity() != null && !tmp.getEntity().getImplements().get(TypeEntity.CONTAINER_MANAGER)) {
					relevant = tmp;
				}
			}
		}

		if (relevant == null) {
			relevant = cell;
		}

		return relevant;
	}

	/**
	 * Retourne la position d'une cellule (x,y) depuis son indice
	 *
	 * @param c une cellule
	 * @return la position d'une cellule (x,y) depuis son indice
	 * @since 6.1
	 */
	public static Vector2 getPosFromCellIndex(MapTestScreenCell c) {
		Vector2 pos = new Vector2();
		TiledMapTileLayer layer = c.getLayer();
		pos.y = c.getIndex() / layer.getWidth();
		pos.x = c.getIndex() % layer.getWidth();
		return pos;
	}
}
