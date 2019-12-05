package editor.io;

import editor.entity.interfaces.IDInterface;

import java.util.ArrayList;

/**
 * Crée et gère des ID à attribuer à des objets
 */
public class IDFactory {

    /**
     * Liste des objets enregistés
     */
    private static ArrayList<Object> LIST = new ArrayList<>();

    private IDFactory(){}

    private IDFactory(ArrayList<Object> list){
        LIST = list;
    }

    @SuppressWarnings("unchecked")
    public static IDFactory getInstance(){
        return new IDFactory((ArrayList) LIST.clone());
    }

    /**
     * Obtenir un nouvel ID. Si l'élément était déjà contenu dans liste, il est retiré et ajouté de nouveau pour obtenir un nouvel ID
     * @param object Objet sur lequel on veut ajouter un ID
     * @return ID créé
     */
    public int newID(Object object){
        LIST.remove(object);
        LIST.add(object);
        return LIST.indexOf(object);
    }

    /**
     * Retourne un objet selon son ID
     * @param id ID
     * @return L'objet
     * @throws ArrayIndexOutOfBoundsException Si l'ID ne correspond pas à un objet
     */
    public Object getObject(int id){
        return LIST.get(id);
    }
}
