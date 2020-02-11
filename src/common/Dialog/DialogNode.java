package common.Dialog;

/**
 * On peut voir un dialogue comme un arbre, chaque noeud est limité à un certain nombre
 *  de caractère. La classe Dialog
 */
public class DialogNode extends Dialog{

    private Dialog left;

    private Dialog right;

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
                this.right = new DialogText();
            } else{
                this.right = new DialogNode();
            }
            this.right.addText(text.substring(MAX_CHAR));
        }
    }

    public Dialog getRight(){
        return this.right;
    }

    public Dialog getLeft(){
        return this.right;
    }
}
