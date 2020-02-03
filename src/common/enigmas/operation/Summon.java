package common.enigmas.operation;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import common.entities.Entity;
import common.entities.players.Player;
import common.map.AbstractMap;
import common.map.MapTestScreenCell;
import common.save.enigmas.EnigmaAttributes;
import game.EnigmaGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Fait apparaître une entité sur une case donnée
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @see Operation
 * @since 2.0
 */
public class Summon extends Operation {

	/**
	 * Case où doit apparaître l'entité
	 */
	private MapTestScreenCell spawn;

	/**
	 * @param e     Entité concernée par l'opération
	 * @param spawn Case où doit apparaître l'entité
	 */
	public Summon(Entity e, MapTestScreenCell spawn) {
		super(e);
		this.spawn = spawn;
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public Summon(Map<String, Object> attributes) {
		super(attributes);
		float spawnX = (float) -1.0;
		float spawnY = (float) -1.0;
		String spawnLayer = "null";

		ArrayList<String> attr = new ArrayList<>();
		attr.add(EnigmaAttributes.SPAWN_X);
		attr.add(EnigmaAttributes.SPAWN_Y);
		attr.add(EnigmaAttributes.SPAWN_LAYER);

		for (String a : attr) {
			if (!attributes.containsKey(a))
				throw new IllegalArgumentException("Attribut \"" + a + "\" abscent");

			Object get = attributes.get(a);

			switch (a) {
				case EnigmaAttributes.SPAWN_X:
					spawnX = Float.parseFloat((String) get);
					break;
				case EnigmaAttributes.SPAWN_Y:
					spawnY = Float.parseFloat((String) get);
					break;
				case EnigmaAttributes.SPAWN_LAYER:
					spawnLayer = (String) get;
					break;
			}
		}

		if (spawnX != -1.0 && spawnY != -1.0 && !spawnLayer.equals("null")) {
			AbstractMap map = EnigmaGame.getCurrentScreen().getMap();

			TiledMapTileLayer sLayer = (TiledMapTileLayer) map.getTiledMap().getLayers().get(spawnLayer);
			this.spawn = (MapTestScreenCell) sLayer.getCell((int) spawnX, (int) spawnY);
		}
	}

	/**
	 * Effectue l'action
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 */
	@Override
	@Deprecated
	public void doOperation(Player p) {
		this.run(p);
	}

	/**
	 * Effectue l'action
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 */
	@Override
	public void run(Player p) {
		this.spawn.setEntity(this.entity);
	}

	/**
	 * Obtenir la cellule d'apparition
	 *
	 * @return Cellule de d'apparition
	 */
	public MapTestScreenCell getSpawn() {
		return this.spawn;
	}

	/**
	 * Définir la cellule d'apparition
	 *
	 * @param spawn Cellule d'apparition
	 */
	public void setSpawn(MapTestScreenCell spawn) {
		this.spawn = spawn;
	}


	/**
	 * Obtenir un EnumMap de l'objet avec ses attributs et leur état
	 *
	 * @return EnumMap de l'objet
	 * @see EnigmaAttributes
	 */
	@Override
	public HashMap<String, Object> objectToMap() {
		HashMap<String, Object> object = new HashMap<>();
		object.put(EnigmaAttributes.PATH, this.getClass().getName());
		object.put(EnigmaAttributes.ENTITY, this.entity.getID() + "");
		TiledMapTileLayer layer = this.spawn.getLayer();
		int x, y, index = this.spawn.getIndex();
		x = index % layer.getWidth();
		y = index / layer.getWidth();
		object.put(EnigmaAttributes.SPAWN_X, x + "");
		object.put(EnigmaAttributes.SPAWN_Y, y + "");
		object.put(EnigmaAttributes.SPAWN_LAYER, layer.getName() + "");
		return object;
	}

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return "[Summon]";
	}

	/**
	 * Version texte longue de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toLongString() {
		return "[Summon  : entity = " + this.entity + ", spawn = " + this.spawn + "]";
	}
}