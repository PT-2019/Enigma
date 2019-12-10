package game.screen.test;

/**
 * All maps constant that are used in this game
 *
 * @author Quentin Ramsamy--Ageorges
 * @version 1.0 03 december 2019
 */
public enum MapsNameUtils {
	//layers
	TILE_COLLISION_LAYER("Collision"),
	NPC_LAYER("Npc"),

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