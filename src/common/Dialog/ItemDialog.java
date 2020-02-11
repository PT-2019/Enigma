package common.Dialog;

import common.enigmas.Enigma;
import common.entities.GameObject;
import common.entities.items.Chest;
import common.entities.items.Door;
import common.entities.items.Library;
import common.entities.items.Switch;
import common.language.EnigmaField;
import common.language.GameFields;
import common.language.GameLanguage;

/**
 * Classe temporaire pour noter tout les petits dialogue avec les objets
 */
public class ItemDialog {


    /**
     * Renvoie le texte associé à la classe
     */
    public static String getText(GameObject object){
        if (object instanceof Chest)
            if (((Chest) object).isLocked())
                return GameLanguage.gl.get(GameFields.CHEST) +" " + GameLanguage.gl.get(EnigmaField.LOCK);
            else
                return GameLanguage.gl.get(GameFields.CHEST) +" " + GameLanguage.gl.get(EnigmaField.UNLOCK);
        else if (object instanceof Door)
            return GameLanguage.gl.get(GameFields.DOOR) +" " + GameLanguage.gl.get(EnigmaField.UNLOCK);
        else if(object instanceof Switch)
            if (((Switch) object).isActivated())
                return GameLanguage.gl.get(GameFields.SWITCH) +" " + GameLanguage.gl.get(EnigmaField.ACTIVATED);
            else
                return GameLanguage.gl.get(GameFields.SWITCH) +" " + GameLanguage.gl.get(EnigmaField.DESACTIVATED);
        else if(object instanceof Library)
            if (((Library) object).isLocked())
                return GameLanguage.gl.get(GameFields.LIBRARY) +" " + GameLanguage.gl.get(EnigmaField.LOCK);
            //todo afficher l'inventaire de la librairy

         return null;
    }

}
