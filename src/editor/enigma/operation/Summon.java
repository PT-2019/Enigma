package editor.enigma.operation;


import api.entity.Entity;
import api.enums.Attributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.entity.Player;
import game.entity.map.MapGame;
import game.entity.map.MapTestScreen;
import game.entity.map.MapTestScreenCell;

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
		this.spawn = new MapTestScreenCell(new TiledMapTileLayer(10,10,0,0),0);
		this.spawn.setTile(new TiledMapTile() {
			@Override
			public int getId() {
				return 0;
			}

			@Override
			public void setId(int i) {

			}

			@Override
			public BlendMode getBlendMode() {
				return null;
			}

			@Override
			public void setBlendMode(BlendMode blendMode) {

			}

			@Override
			public TextureRegion getTextureRegion() {
				return null;
			}

			@Override
			public void setTextureRegion(TextureRegion textureRegion) {

			}

			@Override
			public float getOffsetX() {
				return 0;
			}

			@Override
			public void setOffsetX(float v) {
			}

			@Override
			public float getOffsetY() {
				return 0;
			}

			@Override
			public void setOffsetY(float v) {

			}

			@Override
			public MapProperties getProperties() {
				return null;
			}
		});
		float spawnX;
		float spawnY;
		String spawnLayer;

		ArrayList<String> attr = new ArrayList<>();
		attr.add(Attributes.SPAWN_X);
		attr.add(Attributes.SPAWN_Y);
		attr.add(Attributes.SPAWN_LAYER);

		for(String a : attr){
			if(!attributes.containsKey(a))
				throw new IllegalArgumentException("Attribut \"" + a + "\" abscent");

			Object get = attributes.get(a);

			switch(a){
				case Attributes.SPAWN_X:
					spawnX = Float.parseFloat((String) get);
					break;
				case Attributes.SPAWN_Y:
					spawnY = Float.parseFloat((String) get);
					break;
				case Attributes.SPAWN_LAYER:
					spawnLayer = (String) get;
					break;
			}
		}
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
		object.put(Attributes.SPAWN_LAYER, layer.getName() + "");
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