package editor.Enums;

/**
 * Nom des attributs d'une opération "faire apparare"
 * @see editor.Enigma.Operation.Summon
 * @see editor.Enums.Attributes
 * @version 2.1
 */
public enum SummonAttributes implements Attributes {

    /**
     * Chemin de la classe
     */
    PATH,

    /**
     * Entité
     */
    ENTITY,

    /**
     * Case où faire apparaître
     */
    CASE;

    /**
     * Obtenir sous forme de String en minuscule
     * @return Chaine de caractères en minuscule
     */
    public String toLowerString(){
        return this.toString().toLowerCase();
    }
}

