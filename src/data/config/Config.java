package data.config;

import api.Application;
import api.utils.AsciiColor;
import api.utils.annotations.NeedPatch;
import common.language.Language;

/**
 * Fichier des configurations de l'application
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.4
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
	 * Elles forment UN SEUL Fichier.
	 * <p>
	 * Cette valeur peut être changée dans les préférences.
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
	 *
	 * @since 6.0
	 */
	public static final int MAX_OF_UNDO = 35;

	/**
	 * Nombre maximum de redo
	 * Reset a chaque sauvegarde
	 *
	 * @since 6.0
	 */
	public static final int MAX_OF_REDO = 35;

	/**
	 * Le type de logs qui doivent être affichés
	 *
	 * @see Application#LOG_DEBUG
	 * @see Application#LOG_ERROR
	 * @see Application#LOG_INFO
	 * @see Application#LOG_NONE
	 * @since 6.0
	 */
	public static final int APPLICATION_LOG = Application.LOG_DEBUG | Application.LOG_ERROR;

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
	/**
	 * Extension des maps
	 *
	 * @since 6.2
	 */
	public static final String MAP_EXTENSION = ".tmx";
	/**
	 * Extension des mapData et gameData
	 *
	 * @since 6.2
	 */
	public static final String DATA_EXTENSION = ".tmx";
	/**
	 * Extension des énigmes
	 *
	 * @since 6.2
	 */
	public static final String ENIGMA_EXTENSION = ".json";
	/**
	 * Extension des exports
	 *
	 * @since 6.2
	 */
	public static final String EXPORT_EXTENSION = ".enigma";
	/**
	 * Ratio d'affichage pour afficher 100 tiles dans un écran
	 *
	 * @since 6.3
	 */
	public static final float ZOOM_RATIO_BY_HEIGHT = 1.84f, RATIO_HEIGHT = 881, RATIO_UNIT = 100;
	/**
	 * Valeur du zoom de base
	 *
	 * @since 6.3
	 */
	public static final float BASE_ZOOM_VALUE = 1.0f;
	/**
	 * Valeur de changement du zoom
	 *
	 * @since 6.3
	 */
	public static final float ZOOM_CHANGE_VALUE = 0.05f;
	/**
	 * Valeur du déplacement de la caméra
	 *
	 * @since 6.3
	 */
	public static final int CAMERA_OFFSET = 32;
	/**
	 * Valeur du zoom en jeu
	 *
	 * @since 6.3
	 */
	public static final float IN_GAME_ZOOM_VALUE = BASE_ZOOM_VALUE / 2f;
	/**
	 * Les valeurs du zoom
	 *
	 * @since 6.3
	 */
	public static final String[] ZOOM_VALUES = new String[]{"25%", "50%", "100%", "125%", "150%", "175%", "200%"};
	/**
	 * Valeur de base dans le tableau des valeurs du zoom
	 *
	 * @since 6.3
	 */
	public static final int ZOOM_VALUES_BASE_INDEX = 2;
	/**
	 * Taille max des map que l'on peut créer (inclus)
	 *
	 * @since 6.3
	 */
	public static final int MAP_MAX_WIDTH = 300, MAP_MAX_HEIGHT = 300;

	/**
	 * Lien vers le github du projet
	 *
	 * @since 6.4
	 */
	public static final String GITHUB_LINK = "https://github.com/PT-2019/Enigma";

	/**
	 * Taux de rafraîchissement d'images par seconde
	 *
	 * @since 6.3
	 */
	private static final int FPS_RATE = 45;

	/**
	 * Json
	 *
	 * @since 6.0
	 */
	public static final String JSON_PATH = "assets/files/atlas/uiskin.json";

	/**
	 * Fichier skin
	 *
	 * @since 6.0
	 */
	public static final String SKIN_PATH = "assets/files/atlas/uiskin.atlas";
}
