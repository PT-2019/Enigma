package common.Dialog;

import java.util.ArrayList;

/**
 * Classe pour représenter un dialog avec un arbre trop complexe
 * @see Dialog
 */
@Deprecated
public abstract class DialogTest {

    /**
     * Les différent texte du dialog
     */
    protected String text;

    /**
     * Nombre maximum de caractère par frame
     */
    protected static int MAX_CHAR = 150;

    /**
     * Pour savoir si il y a un choix
     */
    protected boolean isChoice;
    /**
     * Les différent choix possible
     */
    protected String[] choice;

    public void addText(String text) {

    }

    public void addText(String text, String[] choice) {

    }
}
