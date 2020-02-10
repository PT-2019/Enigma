package data;

import api.utils.annotations.Temporary;
import data.config.Config;

import java.util.ArrayList;

/**
 * Constantes à traduire et ajouter au language.
 *
 * Date d'ajout : fin du sprint 6.
 */
@Temporary(reason = "flemme, car possibles doublons et changer tout le temps et enums et json et ... clc et ça")
public interface NeedToBeTranslated {

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

	//Conditions

	String ACTIVATED_DESC = "Un object doit être activé";
	String ACTIVATED_RES = "Seulement un objet activable.";

	String ANSWER_DESC = "Demander au joueur une réponse";
	String ANSWER_RES = "";

	String HAVE_IN_HANDS_DESC = "Avoir l'objet dans ses mains";
	String HAVE_IN_HANDS_RES = "Objects uniquement (clef, livre, ...)";

	String HAVE_IN_INVENTORY_DESC = "Avoir l'objet dans l'inventaire";
	String HAVE_IN_INVENTORY_RES = "Objects uniquement (clef, livre, ...)";

	String ROOM_DISCOVERED_DESC = "Salle doit être découverte";
	String ROOM_DISCOVERED_RES = "Salles uniquement";

	String ROOM_UNDISCOVERED_DESC = "Salle ne doit pas être découverte.";
	String ROOM_UNDISCOVERED_RES = "Salles uniquement";

	String HIDE_ROOM = "Cacher une salle";

	//ActionTypes

	String ADD_ENTITY = "Ajout d'une entité";
	String REMOVE_ENTITY = "Suppression d'une entité";
	String ADD_ENIGMA = "Ajoute une énigme";
	String REMOVE_ENIGMA = "Suppression d'une énigme";

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
}
