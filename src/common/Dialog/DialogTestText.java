package common.Dialog;

import java.awt.*;

/**
 * Cette classe ne contient pas de noeud, c'est la feuille de l'arbre de dialogue
 * trop complexe
 */
@Deprecated
public class DialogTestText extends DialogTest {
    public DialogTestText(){
    }

    @Override
    public void addText(String text) {
        this.text = text;
    }

    @Override
    public void addText(String text, String[] choice) {
        this.text = text;
        this.choice = choice;
        this.isChoice = true;
    }
}