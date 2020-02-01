package common.save;

import common.map.GameMap;

/**
 * Toutes les constantes pouvant être utilisés par la map de l'éditeur
 * et reconnues par la map libgdx.
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 1.0 03 december 2019
 * @see GameMap
 * @see common.map.MapTestScreen
 * @since 1.0 03 december 2019
 */
public enum MapsNameUtils {
	//specials
	/**
	 * Indique le point maximal ou la caméra peut décendre si on n'est pas inside.
	 * <p>
	 * Si on est inside alors il s'agit du point maximal
	 *
	 * @see #INSIDE
	 */
	BOTTOM("bot"),

	/**
	 * Propriété d'un entité, indique s'il représente une zone ou non
	 */
	AREA("area"),

	/**
	 * Séparateur si besoin
	 */
	SEPARATOR("::"),

	/**
	 * Propriété d'une entité, son tile sera celui de la collision
	 * si aucun, alors tile !=0 sera utilisé
	 */
	COLLISION("collision"),

	//teleport
	/**
	 * Propriété de la téléportation : destination
	 */
	TO("to"),
	/**
	 * Propriété qui indique si on est a l'intérieur ou l'extérieur
	 */
	INSIDE("inside"),
	/**
	 * Indique la direction a facer après téléportation
	 */
	FACED("faced"),

	//message
	/**
	 * Contenu d'une action de type message
	 */
	CONTENT("content"),

	//map properties
	/**
	 * largeur d'une tile
	 */
	TILE_WIDTH_P("tilewidth"),
	/**
	 * hauteur d'une tile
	 */
	TILE_HEIGHT_P("tileheight"),
	/**
	 * largeur de la map
	 */
	WIDTH_P("width"),
	/**
	 * hauteur de la map
	 */
	HEIGHT_P("height"),

	//position value
	/**
	 * propriété des abscisses
	 **/
	X("x"),
	/**
	 * propriété des ordonnées
	 **/
	Y("y"),
	;

	/**
	 * Valeur de la constante
	 */
	public final String value;

	MapsNameUtils(String value) {
		this.value = value;
	}
}
