package editor.Enums;

/**
 * Nom des attributs d'une énigme
 * @see editor.Enigma.Enigma
 * @see editor.Enums.Attributes
 * @version 2.1
 */
public enum EnigmaAttributes implements Attributes {

    /**
     * Chemin de la classe
     */
    PATH,

    /**
     * Indice
     */
    TITLE,

    /**
     * Délai
     */
    DESCRIPTION,

    /**
     * L'énimge à été découverte ou nom
     */
    KNOWN,

    /**
     * Index désignant l'indice actuel
     */
    CURRENT_ADVICE_INDEX,

    /**
     * Conditions de l'énigme
     */
    CONDITIONS,

    /**
     * Opérations de l'énigme
     */
    OPERATIONS,

    /**
     * Indices de l'énigme
     */
    ADVICES;

    /**
     * Obtenir sous forme de String en minuscule
     * @return Chaine de caractères en minuscule
     */
    public String toLowerString(){
        return this.toString().toLowerCase();
    }
}

