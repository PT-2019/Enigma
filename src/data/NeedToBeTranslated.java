package data;

import api.utils.annotations.Temporary;

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

	String HIDE_ROOM = "Cacher une salle";
}
