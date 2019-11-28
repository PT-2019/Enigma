package editor.Enums;

/**
 * Nom des attributs d'une opération
 * @see editor.Enigma.Operation.Operation
 * @see editor.Enums.Attributes
 * @version 2.1
 */
public enum OperationAttributes implements Attributes {

    /**
     * Chemin de la classe
     */
    PATH,

    /**
     * Entité
     */
    ENTITY;

    /**
     * Obtenir sous forme de String en minuscule
     * @return Chaine de caractères en minuscule
     */
    public String toLowerString(){
        return this.toString().toLowerCase();
    }
}

