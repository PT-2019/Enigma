package data;

import api.utils.annotations.Temporary;

/**
 * Constantes à traduire et ajouter au language.
 * <p>
 * Date d'ajout : fin du sprint 6.
 */
@Temporary(reason = "flemme, car possibles doublons et changer tout le temps et enums et json et ... clc et ça")
public interface NeedToBeTranslated {

	String WITH = "avec";

	//case popup
	String NO_ENTITY = "Aucune entité";

	//addContentView
	String INPUT_CONTENT = "Saisir du contenu";
	String TITLE_CONTENT = "Contenu de l'object";
	String SAVE = "Enregistrer";
	String CONTENT_SAVED = "Contenu enregistré";

	//SetContent

	String DEFINE_CONTENT = "Définir le contenu";

	//CluePanel

	String ADD_CLUE = "Ajouter un indice";
	String TIME_CLUE = "Temps (minutes) avant activation de l'indice :";
	String INPUT_CLUE = "Indice :";
	String SUBMIT = "Valider";

	//ConditionPanel

	String NOT_AVAILABLE_CONDITION = "Condition non disponible";
	String ASK_SELECT = "Veuillez sélectionner un objet";
	String ASK_COND = "Veuillez sélectionner une condition.";
	String ADD_CONDITION = "Ajouter une Condition à l'énigme";
	String INVALID_ENTITY = "Entité Invalide.";

	//Opération Panel

	//String NOT_SELECTED = "Vous n'avez pas encore choisi d'entité";
	String ASK_OP = "Veuillez sélectionner une opération.";
	String NOT_AVAILABLE_OPERATION = "Opération non disponible";
	String ADD_OPERATION = "Ajouter une Opération à l'énigme";

	//Conditions

	String MUSIC = "Jouer un son";
	String CHANGE_MAIN_MUSIC = "Change la musique ambiante";

	String ACTIVATED_DESC = "Un object doit être activé";
	String ACTIVATED_RES = "Seulement un objet actionnable.";

	String ANSWER_DESC = "Demander au joueur une réponse";
	String ANSWER_RES = "";
	String ANSWER = "Taper la réponse";
	String ANSWER_SELECT = "Réponse présente";
	String ANSWER_UNSELECT = "Réponse manquante";

	String HAVE_IN_HANDS_DESC = "Avoir l'objet dans ses mains";
	String ITEMS_ONLY = "Objects uniquement (clef, livre, ...)";

	String HAVE_IN_INVENTORY_DESC = "Avoir l'objet dans l'inventaire";

	String ROOM_DISCOVERED_DESC = "Salle doit être découverte";
	String ROOM_ONLY = "Salles uniquement";

	String ROOM_UNDISCOVERED_DESC = "Salle ne doit pas être découverte.";

	//ManageEnigmasAddView

	String CREATE_ENIGMA = "Créer une énigme";
	String SAVE_ENIGMA = "Sauvegarder énigme";
	String ADD_COND = "Ajouter une condition";
	String ADD_OP = "Ajouter une conséquence";

	String HIDE_ROOM = "Cacher une salle";

	//ActionTypes

	String ADD_ENTITY = "Ajout de";
	String REMOVE_ENTITY = "Suppression de";
	String ADD_ENIGMA = "Ajoute une énigme";
	String REMOVE_ENIGMA = "Suppression d'une énigme";
	String SET_CONTENT = "Changement du contenu";
	String ADD_SUB_ENTITY = ADD_ENTITY;
	String REMOVE_SUB_ENTITY = REMOVE_ENTITY;

	//SetName
	String SET_NAME = "Changer nom";

	//ActionsManagerType

	//String ADDED = "Ajout";
	String UN_DONE = "Undo";
	String RE_DONE = "Redo";

	//AddNameView

	String INPUT_NAME = "Saisir nom";
	String TITLE_NAME = "Nom de l'entité";
	String NAME_SAVED = "Nom enregistré";

	//enigma OptionPane

	String ASK_SELECT_MAP = "Choisissez une map";
	String ASK_SELECT_MUSIC = "Choisissez une musique";
	String ASK_SELECT_SOUND = "Choisissez un son";

	//ImportExport
	String REPLACE_FILE = "Un fichier du même nom existe déjà, le remplacer?";
	String REPLACE_GAME = "Une partie du même nom existe déjà, la remplacer?";
	String REPLACE_MAP = "Une map du même nom existe déjà, la remplacer?";
	String EXPORTATION = "Exportation";
	String IMPORTATION = "Importation";

	//editor.bar.listener
	String NAME_ALREADY_EXIST = "Ce nom existe déjà";
	String CREATE_ERROR = "Erreur lors de la création";

	String CHOOSE_DESTINATION_FOLDER = "Choisissez un dossier de destination";
	String EXPORT_ENDED = "Exportation terminée";
	String EXPORT_ERROR = "Erreur lors de l'exportation";
	String EXPORT_ABANDONED = "Exportation abandonnée";

	String CHOOSE_FILE_TO_IMPORT = "Choisissez le fichier à importer";
	String IMPORT_ENDED = "Importation terminée";
	String IMPORT_ERROR = "Erreur lors de l'importation";
	String IMPORT_ABANDONED = "Importation abandonnée";

	String MAP_NAME = "Nom de la map :";
	String SAVE_ENDED = "Sauvegarde terminée";
	String SAVE_CANCELED = "Sauvegarde annulée";
	String SAVE_FAILED = "Erreur lors de la sauvegarde";

	//game.hmi
	String NAME = "Nom";
	String MAP = "Map";
	String DESCRIPTION = "Description";
	String NB_PLAYERS = "Nombre de joueurs";
	String DURATION = "Durée";
	String MULTI_PLAYERS = "Multijoueurs";
	String YES = "Oui";
	String NO = "Non";
	String NO_NAME = "La partie n'a pas de nom";
	String NO_DURATION = "La partie n'a pas de durée";
	String NO_MAP = "Aucune map séléctionnée";
	String WRONG_DURATION = "La durée n'est pas valide";
	String PLAYER_WAIT = "En attente de joueurs";
	String LEADER_WAIT = "En attente du chef de la partie";
	String LEADER = "Chef";
	String AUTHOR = "Auteur";
	String DELETE = "Supprimer";
	String PLAY = "Jouer";
	String ENTER_IP = "Entrez une adresse IP";
	String YOUR_GAMES = "Vos parties";
	String FIND = "Trouver une partie";
	String JOIN = "Rejoindre";
	String MORE = "Plus";
	String EXPORT = "Exporter";
	String DELETE_ERROR = "Erreur lors de la suppression";
	String DELETE_CONFIRMATION = "Supprimer ?";
	String NOT_ENOUGH_PLAYERS = "Nombre de joueurs insufisant";
	String QUIT_CONFIRMATION = "Quitter?";
	String LAUNCH = "Commencer";
	String QUIT = "Quitter";
	String CREATE = "Créer";
	String IMPORT = "Importer";
	String SOLO_TITLE = "Parties solo";
	String MULTI_TITLE = "Multijoueurs";
	String CREATE_TITLE = "Nouvelle partie";

	//Operations

	String GIVE_DESC = "Donne un object à l'utilisateur";
	String SUMMON_DESC = "Invoque une entité";
	String UNLOCK_DESC = "Déverrouille un object";
	String HIDE_ROOM_DESC = "Cacher une salle";
	String SHOW_ROOM_DESC = "Afficher une salle";
	String LAUNCH_SOUND = "Jouer un son";


	String LOCKABLE_ONLY = "Seulement un object \"Décors\" verrouillable.";
	String LIVING_ONLY = "Seulement des personnages, pas de héros.";
	String MUSIC_ONLY = "Seulement une musique.";

	// DragAndDrop

	String DRAG_AND_DROP_NOT_IN_MAP = "Déplacement raté. En dehors de la map.";
	String DRAG_AND_DROP_FAILED_ZOOM = "Déplacement raté. Veuillez rétablir le zoom.";
	String DRAG_AND_DROP_FAILED_CONTAINER = "Déplacement raté. Ne peut être directement posé.";
	String DRAG_AND_DROP_NOT_SUPPORTED = "Déplacement raté. Action non supportée.";
	String DRAG_AND_DROP_EXIT_FAILED = "Déplacement raté. Une seule sortie par map.";
	String DRAG_AND_DROP_FAILED_MENU = "Déplacement raté. Dans le menu.";
	String DRAG_AND_DROP_ALREADY_TAKEN = "Déplacement raté. Emplacement déjà pris.";
	String DRAG_AND_DROP_NOT_EMPTY_SAVE = "Il y a des entités à cette position.";
	String DRAG_AND_DROP_ENTITY_UNREACHABLE = "bloque.";
	String DELETE_FAILED_IN_ENIGMA_COND = "Impossible. Utilisé dans une condition.";
	String DELETE_FAILED_IN_ENIGMA_OP = "Impossible. Utilisé dans une opération.";
	String DELETE_FAILED_CHILD_ENIGMA_COND = "Impossible. Enfant utilisé dans une condition.";
	String DELETE_FAILED_CHILD_ENIGMA_OP = "Impossible. Enfant utilisé dans une opération.";

	// CaseListener
	String ERASE_FAILED_ZOOM = "Effacement raté. Veuillez rétablir le zoom.";

	//EntityPopMenu

	String ZOOM = "Zoom";
	String ZOOM_FIT = "Voir toute la map";
	String ZOOM_IN_GAME = "Zoom du jeu";
	String ZOOM_BASE = "Zoom de base";

	String SHOW = "Affichage";
	String CASES_BLOCKED = "Cases bloquantes";

	String CURSOR = "Souris";
	String ERASER = "Gomme";
	String BRUSH = "Pinceau";
	String MOVE = "Déplacement";

	// Spécial popup
	String SELECT_ENTITY = "Utiliser cette entité";

	//createListener
	String CANCEL = "Annuler";
	String CREATE_NEW_MAP = "Création d'une nouvelle map.";
	String WIDTH = "Largeur";
	String HEIGHT = "Hauteur";
	String EMPTY = "Vide";

	//ManageEnigmasSeeView
	String CONFIRM = "Confirmer";

	//AddItemAddView
	String ADD_ITEM = "Ajouter un objet";

	//SelectionsModes
	String MENU_ONLY = "menu uniquement";
	String MAP_ONLY = "carte uniquement";
	String POPUP_ONLY = "contenu uniquement";
	String MAP_AND_MENU = "carte et menu";
	String MENU_AND_POPUP = "menu et contenu";
	String MAP_AND_POPUP =	"carte et contenu";
	String MAP_AND_MENU_AND_POPUP = "carte, menu et contenu";
	String NO_MODE = "Spécial";
	String ALL_MODES = "carte, menu et contenu";

	//SupportListener
	String SUPPORT_MESSAGE = "Vous pouvez demander de l'aide sur le forum d'Enigma.";
	String OPEN_LINK_FAILED = "Echec de l'ouverture par l'application.\n";

	//testScreen
	String OPEN_MAP = "Ouvrir une map";
	String NEW_MAP = "Nouvelle map";

	//create
	String INPUT_MAP_NAME = "Nom de la map";

	//special popup
	String CONTENT = "Contenu";

	//addItemListView
	String ITEM_LIST = "Liste des objets";
	String SEE_ITEM = "Voir l'objet sélectionnée";
	String NO_ITEM = "Aucun item.";
}
