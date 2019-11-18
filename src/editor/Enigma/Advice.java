package editor.Enigma;

/**
 * Advice définie les indices donnés aux joueurs pour les aider à résoudre une énigme
 * Les indices sont utilisés dans les {@link editor.Enigma.Enigma énigmes}
 * @see editor.Enigma.Enigma
 * @version 1.0
 */
public class Advice {

    /**
     * Texte de l'indice
     */
    private String advice;

    /**
     * @param a Indice
     * @throws IllegalArgumentException Si la chaîne de caractères est vide
     */
    public Advice(String a){
        if(a.length() < 1) throw new IllegalArgumentException("L'indice ne peut pas être vide");
        this.advice = a;
    }

    /**
     * Indiquer l'indice
     * @param a Indice
     * @throws IllegalArgumentException Si la chaîne de caractères est vide
     */
    public void setAdvice(String a){
        if(a.length() < 1) throw new IllegalArgumentException("L'indice ne peut pas être vide");
        this.advice = a;
    }

    /**
     * Obtenir l'indice
     * @return Indice
     */
    public String getAdvice(){
        return this.advice;
    }

    /**
     * Compare deux énigmes
     * @param o Enigme
     * @return true si les deux énigles sont les même, false sinon
     */
    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Advice)) return false;

        Advice a = (Advice)o;
        return (this.advice.equals(a.getAdvice()));
    }

    /**
     * Version texte de l'objet
     * @return Texte représentant l'objet
     */
    @Override
    public String toString(){
        return "[Advice  : advice = \"" + this.advice + "\"]";
    }
}
