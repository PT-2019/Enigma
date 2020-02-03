package data.config;

import api.Application;
import api.utils.AsciiColor;
import api.utils.annotations.NeedPatch;
import com.badlogic.gdx.Gdx;
import common.language.Language;

/**
 * Fichier des configurations de l'application
 *
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
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
	 * Données de la map sauvegardées dans le logiciel tant que pas exportées
	 */
	public static final String MAP_DATA_FOLDER = "assets/files/data/";

	/**
	 * Données des parties locales sauvegardées dans le logiciel tant que pas exportées
	 */
	public static final String GAME_DATA_FOLDER = "assets/files/game/";

	/**
	 * Données de l'utilisateur
	 */
	public static final String USER_DATA_FILE_FOLDER = "assets/files/user/user.tmx";

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
	public static final AsciiColor ERROR_COLOR = AsciiColor.ORANGE;

	/**
	 * Extension des maps
	 */
	public static final String MAP_EXTENSION = ".tmx";

	/**
	 * Extension des mapData et gameData
	 */
	public static final String DATA_EXTENSION = ".tmx";

	/**
	 * Extension des énigmes
	 */
	public static final String ENIGMA_EXTENSION = ".json";

	/**
	 * Extension des exports
	 */
	public static final String EXPORT_EXTENSION = ".enigma";
}
