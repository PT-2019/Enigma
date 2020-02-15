package common.Dialog;

import java.util.ArrayList;

/**
 * Classe actuel du dialog
 */
public class Dialog {
    /**
     * Les différent texte du dialog
     */
    private ArrayList<String> text;

    /**
     * Nombre maximum de caractère par frame
     */
    private static int MAX_CHAR = 150;

    /**
     * Pour savoir si il y a un choix
     */
    private boolean isChoice;
    /**
     * Les différent choix possible
     */
    private String[] choice;

    private int index = 0;

    public Dialog(String content){
        this.text = new ArrayList<>();
        this.isChoice = false;
        addText(content);
    }

    public Dialog(String content,String[] choice){
        this.text = new ArrayList<>();
        this.isChoice = true;
        this.choice = choice;
        addText(content);
    }

    private void addText(String content){
        if (content.length() <= MAX_CHAR) {
            this.text.add(content);
        }else {
            while (content.length() - MAX_CHAR*2 > 0){
                this.text.add(content.substring(0,MAX_CHAR));
                content = content.substring(MAX_CHAR);
            }
            this.text.add(content);
        }
    }

    public boolean isChoice() {
        return isChoice;
    }

    public String[] getChoice() {
        return choice;
    }

    public boolean isFinish(){
        if (index == text.size()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * A travers cette méthode on parcours 1 fois le dialogue
     * @return
     */
    public String getCurrentText(){
        index ++;
        return text.get(index-1);
    }
}
