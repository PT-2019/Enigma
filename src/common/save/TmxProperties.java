package common.save;

public interface TmxProperties {

	//propriétés de base

	String TMX_ID = "id";
	String TMX_X = "x";
	//obligé de faire ce truc sale y2 car y renvoi truc bizarres y=789 renvoi y=0...
	String TMX_Y = "y2";
	String TMX_WIDTH = "width";
	String TMX_HEIGHT = "height";
	String TMX_VALUE = "value";
	String TMX_NAME = "name";

	/**
	 * nom de la propriété
	 **/
	String TMX_PROP_ENTITY = "entity";

	/**
	 * nom propriété entité : classe
	 */
	String TMX_PROP_ENTITY_CLASS = "className";

}
