package common.save;

import common.map.MapObjects;
import common.utils.Logger;
import api.utils.annotations.ConvenienceMethod;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import common.enigmas.Enigma;
import common.entities.GameObject;
import common.entities.types.EnigmaContainer;
import common.map.MapTestScreen;
import common.map.model.Map;
import common.save.enigmas.EnigmaJsonReader;
import common.save.enigmas.EnigmaJsonWriter;
import common.utils.textures.TextureProxy;
import data.config.Config;
import game.EnigmaGame;
import game.screens.TestScreen;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Une classe qui génère une map vide
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 23/12/2019
 * @since 4.0 23/12/2019
 */
public class EmptyMapGenerator {

	/**
	 * Génère une map vide
	 *
	 * @param path chemin du fichier a créer
	 * @param col  nombre de colonnes
	 * @param row  nombre de lignes
	 * @since 4.0
	 */
	@ConvenienceMethod
	public static void generate(String path, int col, int row) {
	/*
		Extrait de la map source

		<tileset name="dungeon1"><image width="128" height="96"/></tileset>
		<tileset name="pipo"><image width="128" height="3984"/></tileset>
		<tileset name="collision"><image width="128" height="128"/></tileset>
		<tileset name="3"><image width="1280" height="1280"/></tileset>
	 */
		TextureProxy proxy = new TextureProxy();
		//toutes ces constantes magiques sont enregistrés depuis la "map source" à la main, pas top...
		proxy.addTexture(16, "assets/map/tiles/ground/dungeon1.png", 8, 1, 48);
		proxy.addTexture(16, "assets/map/tiles/pipo.png", 8, 49, 1992 + 49);
		proxy.addTexture(16, "assets/map/collision.png", 8, 2041, 2041 + 64);
		proxy.addTexture(16, "assets/map/Player.png", 80, 2105, 2105 + 6400);

		SaveMap.saveMap(path, new Map(col, row), proxy.getTextures(), Config.MAP_FOLDER_SAV);
	}

	/**
	 * Sauvegarde du jeu (.tmx et .json séparés)
	 *
	 * @param path     chemin du fichier sans l'extension
	 * @param game     map tiled
	 * @param entities liste des entités de la map
	 */
	public static void save(String path, TiledMap game, MapObjects entities) {
		//----------------- fichier .tmx -----------------
		SaveMap save = new SaveMap(game, entities);
		save.saveMap(path + ".tmx", Config.MAP_FOLDER_SAV);

		// ------------ fichier .json ------------------

		//Récupère les énigmes
		ArrayList<Enigma> enigmas = new ArrayList<>();
		for (GameObject entity : entities.getAllObjectsByClass(GameObject.class)) {
			if (entity instanceof EnigmaContainer) {
				System.out.println(entity);
				Iterator<Enigma> enigmasRaw = ((EnigmaContainer) entity).getAllEnigmas();
				while (enigmasRaw.hasNext()) {
					//associe aux énigmes l'id de l'entité
					Enigma e = enigmasRaw.next();
					e.setID(entity.getID());
					enigmas.add(e);
				}
			}
		}

		//Sauvegarde
		try {
			EnigmaJsonWriter.writeEnigmas(path + ".json", enigmas);
		} catch (IOException e) {
			//TODO: do something
		}
	}


	/**
	 * Recharge le jeu depuis une sauvegarde
	 *
	 * @param path chemin sauvegarde
	 */
	public static void load(String path) {
		//------------ charge .tmx ------------
		((TestScreen) EnigmaGame.getCurrentScreen()).setMap(path);

		//------------ charge .json ------------
		MapTestScreen map = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
		MapObjects entities = map.getEntities();

		ArrayList<Enigma> enigmas;
		ArrayList<Enigma> copy = new ArrayList<>();
		path = path.substring(0, path.length() - 4) + ".json"; //retire l'extension tmx
		Logger.printDebug("EmptyMapGenerator#load", path);
		int id;
		try {
			enigmas = EnigmaJsonReader.readEnigmas(path);
			for (GameObject o : entities.getAllObjectsByClass(GameObject.class)) {
				if (!(o instanceof EnigmaContainer)) continue;
				//récupère les énigmes de cet object
				for (Enigma en : enigmas) {
					id = en.getID();
					if (id != -1 && id == o.getID()) {
						copy.add(en);
					}
				}
				//ajoute les énigmes
				for (Enigma en : copy) {
					enigmas.remove(en);
					((EnigmaContainer) o).addEnigma(en);
				}
				copy.clear();
			}
		} catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
				InstantiationException | IllegalAccessException ignore) {
		} catch (IllegalStateException e) {
			System.err.println(e.toString());
		}
	}
}
