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
     * Pour savoir si il y a des choix dans le dialogue
     */
    protected boolean isChoice;

    protected String choice[];

    /**
     * permet de rajouter du texte au dialogue
     */
    public abstract void addText(String text);

    /**
     * permet de rajouter du texte au dialogue et des choix
     */
    public abstract void addText(String text,String[] choice);

    public String getText(){
        return this.text;
    }

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean b){
        isChoice = b;
    }

    public String[] getChoice(){
        return choice;
    }
}
