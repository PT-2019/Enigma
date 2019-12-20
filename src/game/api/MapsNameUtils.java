package game.api;

/**
 * Toutes les constantes pouvant être utilisés par la map de l'éditeur
 * et reconnues par la map libgdx.
 *
 * @version 1.0 03 december 2019
 * @see game.entity.MapLibgdx
 * @since 03 december 2019
 */
public enum MapsNameUtils {
	//specials
	BOTTOM("bot"),
	AREA("area"),
	SEPARATOR("::"),
	COLLISION("collision"),

	//teleport
	TO("to"),
	INSIDE("inside"),
	FACED("faced"),

	//message
	CONTENT("content"),

	//map properties
	TILE_WIDTH_P("tilewidth"),
	TILE_HEIGHT_P("tileheight"),
	WIDTH_P("width"),
	HEIGHT_P("height"),

	//position value
	X("x"),
	Y("y"),
	;

	public final String value;

	MapsNameUtils(String value) {
		this.value = value;
	}
}
