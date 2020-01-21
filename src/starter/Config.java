package starter;

import api.utils.annotations.NeedPatch;

/**
 * Fichier des configurations de l'application
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 5.0
 * @since 4.0
 */
public final class Config {

	/**
	 * Map sauvegardé dans le logiciel tant que pas exportée.
	 * Elles sont composées de deux fichiers : .tmx (map) et .json (énigmes)
	 *
	 * @see #MAP_FOLDER_SAV
	 */
	public static final String MAP_FOLDER = "assets/files/map/";

	/**
	 * Le chemin relatif des fichiers sauvegardés avec les ressources
	 */
	public static final String MAP_FOLDER_SAV = "../../map/";

	/**
	 * Map sauvegardés par exemple dans les doc de l'utilisateur
	 * Elles forment UN SEUL Fichier
	 */
	@NeedPatch
	public static final String MAP_FOLDER_USER = MAP_FOLDER;
}
