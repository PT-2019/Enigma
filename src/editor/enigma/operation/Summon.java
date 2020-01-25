package editor.enigma.operation;


import api.entity.Entity;
import api.enums.Attributes;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import editor.entity.IDFactory;
import editor.entity.Player;

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
	private TiledMapTileLayer.Cell spawn;

	/**
	 * @param e     Entité concernée par l'opération
	 * @param spawn Case où doit apparaître l'entité
	 */
	public Summon(Entity e, TiledMapTileLayer.Cell spawn) {
		super(e);
		this.spawn = spawn;
	}

	/**
	 * @param attributes Attributs de la classe
	 * @throws IllegalArgumentException Si un attribut est manquant
	 */
	public Summon(Map<String, Object> attributes) {
		super(attributes);
		IDFactory idFactory = IDFactory.getInstance();
		if (attributes.containsKey(Attributes.SPAWN))
			this.spawn = (TiledMapTileLayer.Cell) idFactory.getObject(Integer.parseInt((String) attributes.get(Attributes.SPAWN)));
		else throw new IllegalArgumentException("Attribut \"spawn\" abscent");
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
		object.put(Attributes.SPAWN, this.entity.getID() + "");//TODO: spawn
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
