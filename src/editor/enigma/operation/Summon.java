package editor.enigma.operation;


import api.entity.Entity;
import api.enums.Attributes;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.entity.IDFactory;
import editor.entity.Player;
import game.entity.map.MapTestScreen;
import game.entity.map.MapTestScreenCell;

import java.util.HashMap;
import java.util.Map;

/**
 * Fait apparaître une entité sur une case donnée
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.2
 * @see editor.enigma.operation.Operation
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
		this.spawn = new MapTestScreenCell(new TiledMapTileLayer(0,0,0,0),0);
		float spawnX;
		float spawnY;
		String spawnLayer;
		if (attributes.containsKey(Attributes.SPAWN_X))
			spawnX = Float.parseFloat((String) attributes.get(Attributes.SPAWN_X));
		else throw new IllegalArgumentException("Attribut \"spawnX\" abscent");
		if (attributes.containsKey(Attributes.SPAWN_Y))
			spawnY = Float.parseFloat((String) attributes.get(Attributes.SPAWN_Y));
		else throw new IllegalArgumentException("Attribut \"spawnY\" abscent");
		if (attributes.containsKey(Attributes.LAYER))
			spawnLayer = (String) attributes.get(Attributes.LAYER);
		else throw new IllegalArgumentException("Attribut \"layer\" abscent");
	}

	/**
	 * Effectue l'action
	 *
	 * @param p Joueur ayant mené à l'appel de cette méthode
	 */
	@Override
	public void doOperation(Player p) {
		//faire apparaitre this.entity sur this.spawn
	}

	/**
	 * Obtenir la cellule d'apparition
	 * @return Cellule de d'apparition
	 */
	public MapTestScreenCell getSpawn(){
		return this.spawn;
	}

	/**
	 * Définir la cellule d'apparition
	 * @param spawn Cellule d'apparition
	 */
	public void setSpawn(MapTestScreenCell spawn){
		this.spawn = spawn;
	}


	/**
	 * Obtenir un EnumMap de l'objet avec ses attributs et leur état
	 *
	 * @return EnumMap de l'objet
	 * @see api.enums.Attributes
	 */
	@Override
	public HashMap<String, Object> objectToMap() {
		HashMap<String, Object> object = new HashMap<>();
		object.put(Attributes.PATH, this.getClass().getName());
		object.put(Attributes.ENTITY, this.entity.getID() + "");
		TiledMapTileLayer layer = this.spawn.getLayer();
		int x, y, index = this.spawn.getIndex();
		x = index%layer.getWidth();
		y = index/layer.getWidth();
		object.put(Attributes.SPAWN_X, x + "");
		object.put(Attributes.SPAWN_Y, y + "");
		object.put(Attributes.LAYER, layer.getName() + "");
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