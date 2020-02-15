package common.Dialog;

/**
 * On peut voir un dialogue comme un arbre, chaque noeud est limité à un certain nombre
 *  de caractère. La classe Dialog
 *  trop complexe
 */
@Deprecated
public class DialogNode extends DialogTest{
    /**
     * composant gauche de la branche
     */
    private DialogTest left;

    /**
     * composant droite de la branche
     */
    private DialogTest right;

    public DialogNode(){
    }

    @Override
    public void addText(String text){
        if (text.length() <= MAX_CHAR){
            this.text = text;
        }else{
            //pour l'instant fonctionne uniquement pour un dialogue simple
            this.text = text.substring(0,MAX_CHAR);
            //on test pour svoir si il faut créer une branche ou une feuille
            if (text.length() - MAX_CHAR*2 <= 0){
                this.right = new DialogTestText();
            } else{
                this.right = new DialogNode();
            }
            this.right.addText(text.substring(MAX_CHAR));
        }
    }

    @Override
    public void addText(String text, String[] choice) {
        if (text.length() <= MAX_CHAR){
            this.text = text;
        }else{
            //pour l'instant fonctionne uniquement pour un dialogue simple
            this.text = text.substring(0,MAX_CHAR);
            //on test pour svoir si il faut créer une branche ou une feuille
            if (text.length() - MAX_CHAR*2 <= 0){
                this.right = new DialogTestText();
            } else{
                this.right = new DialogNode();
            }
            this.right.addText(text.substring(MAX_CHAR));
        }
        this.choice = choice;
        isChoice = true;
    }

    public DialogTest getRight(){
        return this.right;
    }

    public DialogTest getLeft(){
        return this.right;
    }
}
