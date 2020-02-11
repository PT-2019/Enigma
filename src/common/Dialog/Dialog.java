package common.Dialog;

/**
 * Cette classe représente les branches et feuille de l'arbre de dialogue.
 */
public abstract class Dialog {

    /**
     * Nombre maximum de caractère par noeud
     */
    protected static int MAX_CHAR = 150;

    protected String text;

    /**
     * permet de rajouter du texte au dialogue
     */
    public abstract void addText(String text);

    public String getText(){
        return this.text;
    }
}
