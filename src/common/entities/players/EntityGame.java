package common.entities.players;

/**
 * Interface qui pour toute les entités du jeu
 */
public interface EntityGame {

	void setJson(String json, String key);

	String getJson();

	String getKey();
}
