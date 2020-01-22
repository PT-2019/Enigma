package starter;

import api.enums.AnsiiColor;
import api.utils.annotations.NeedPatch;
import editor.utils.lang.Language;

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
	 * @since 5.0
	 */
	public static final String MAP_FOLDER_SAV = "../../map/";

	/**
	 * Map sauvegardés par exemple dans les doc de l'utilisateur
	 * Elles forment UN SEUL Fichier
	 */
	@NeedPatch
	public static final String MAP_FOLDER_USER = MAP_FOLDER;

	/**
	 * Langue par défaut
	 * @since 5.1
	 */
	public static final Language DEFAULT = Language.FRENCH;

	/**
	 * Couleur des élements affichés dans la console DEBUG
	 * @since 5.1
	 */
	public static final AnsiiColor DEBUG_COLOR = AnsiiColor.YELLOW;
}
