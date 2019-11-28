package editor.Enums;

/**
 * Nom des attributs d'un indice
 * @see editor.Enigma.Advice
 * @see editor.Enums.Attributes
 * @version 2.1
 */
public enum AdviceAttributes implements Attributes {

    /**
     * Chemin de la classe
     */
    PATH,

    /**
     * Indice
     */
    ADVICE,

    /**
     * Délai
     */
    DELAY;

    /**
     * Obtenir sous forme de String en minuscule
     * @return Chaine de caractères en minuscule
     */
    public String toLowerString(){
        return this.toString().toLowerCase();
    }
}
