package editor.enigma;

import editor.attributes.Attributes;

import java.util.HashMap;
import java.util.Map;

/**
 * Advice définie les indices donnés aux joueurs pour les aider à résoudre une énigme
 * Les indices sont utilisés dans les {@link editor.enigma.Enigma énigmes}
 * @see editor.enigma.Enigma
 * @version 2.2
 */
public class Advice {

    /**
     * Texte de l'indice
     */
    private String advice;

    /**
     * Délai avant de montrer l'indice (2 minute de base)
     */
    private int delay;

    /**
     * @param a Indice
     * @throws IllegalArgumentException Si la chaîne de caractères est vide
     */
    public Advice(String a){
        if(a.length() < 1) throw new IllegalArgumentException("L'indice ne peut pas être vide");
        this.advice = a;
        this.delay = 2;
    }

    /**
     * @param a Indice
     * @param delay Délai en minute avant de montrer l'indice
     * @throws IllegalArgumentException Si la chaîne de caractères est vide
     * @throws IllegalArgumentException Si le délai est inférieur à 0
     */
    public Advice(String a, int delay){
        if(a.length() < 1) throw new IllegalArgumentException("L'indice ne peut pas être vide");
        if(delay < 1) throw new IllegalArgumentException("Le délai ne peut pas être inférieur à 0");
        this.advice = a;
        this.delay = delay;
    }

    /**
     * @param attributes Attributs de la classe
     * @throws IllegalArgumentException Si un attribut est manquant
     */
    public Advice(Map<String,Object> attributes) {
        if (attributes.containsKey(Attributes.ADVICE)) this.advice = (String) attributes.get(Attributes.ADVICE);
        else throw new IllegalArgumentException("Attribut \"advice\" abscent");
        if (attributes.containsKey(Attributes.DELAY)) this.delay = Integer.parseInt((String) attributes.get(Attributes.DELAY));
        else throw new IllegalArgumentException("Attribut \"delay\" abscent");
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
     * Indiquer le délai avant de montrer l'indice
     * @param delay Délai en minute avant de montrer l'indice
     * @throws IllegalArgumentException Si le délai est inférieur à 0
     */
    public void setDelay(int delay){
        if(delay < 1) throw new IllegalArgumentException("Le délai ne peut pas être inférieur à 0");
        this.delay = delay;
    }

    /**
     * Obtenir l'indice
     * @return Indice
     */
    public String getAdvice(){
        return this.advice;
    }

    /**
     * Obtenir le délai avant de montrer l'indice
     * @return Le délai en minute
     */
    public int getDelay(){
        return this.delay;
    }

    /**
     * Obtenir un EnumMap de l'objet avec ses attributs et leur état
     * @return EnumMap de l'objet
     * @see editor.attributes.Attributes
     */
    public HashMap<String,Object> objectToMap(){
        HashMap<String,Object> object = new HashMap<>();
        object.put(Attributes.PATH,this.getClass().getName());
        object.put(Attributes.ADVICE,this.advice);
        object.put(Attributes.DELAY,this.delay + "");
        return object;
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
        return "[Advice  : advice = \"" + this.advice + "\", delay = " + this.delay + "]";
    }
}