package data.config;

import api.Application;
import api.utils.AsciiColor;
import api.utils.annotations.NeedPatch;
import common.language.Language;

/**
 * Fichier des configurations de l'application
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.1
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
	 * Le chemin pour trouver le fichier DTD depuis {@link #MAP_FOLDER}
	 *
	 * @since 5.1
	 */
	public static final String DTD_FOLDER_RELATIVE_TO_MAP = "../../assets/map/mapDtd.dtd";

	/**
	 * Le chemin relatif des fichiers sauvegardés avec les ressources
	 *
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
	 *
	 * @since 5.1
	 */
	public static final Language DEFAULT = Language.FRENCH;

	/**
	 * Chemin du logo
	 *
	 * @since 5.2
	 */
	public static final String LOGO = "assets/logo.jpg";

	/**
	 * Nombre maximum de undo
	 * Reset a chaque sauvegarde
	 * @since 6.0
	 */
	public static final int MAX_OF_UNDO = 20;

	/**
	 * Nombre maximum de redo
	 * Reset a chaque sauvegarde
	 * @since 6.0
	 */
	public static final int MAX_OF_REDO = 20;

	/**
	 * Le type de logs qui doivent être affichés
	 *
	 * @see Application#LOG_DEBUG
	 * @see Application#LOG_ERROR
	 * @see Application#LOG_INFO
	 * @see Application#LOG_NONE
	 *
	 * @since 6.0
	 */
	public static final int APPLICATION_LOG = Application.LOG_DEBUG|Application.LOG_ERROR;

	/**
	 * Couleur des éléments affichés en mode DEBUG
	 *
	 * @since 5.1
	 */
	public static final AsciiColor DEBUG_COLOR = AsciiColor.YELLOW;

	/**
	 * Couleur des éléments affichés en mode ERROR
	 *
	 * @since 6.0
	 */
	public static final AsciiColor ERROR_COLOR = AsciiColor.BRIGHT_MAGENTA;

	/**
	 * Couleur des éléments affichés en mode DEBUG_ALL
	 *
	 * @since 6.1
	 */
	public static final AsciiColor DEBUG_ALL_COLOR = AsciiColor.CYAN;
}
