package editor.Enums;

/**
 * Nom des attributs d'une condition
 * @see editor.Enigma.Condition.Condition
 * @see editor.Enums.Attributes
 * @version 2.1
 */
public enum ConditionAttributes implements Attributes {

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

