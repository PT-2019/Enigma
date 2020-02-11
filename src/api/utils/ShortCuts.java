package api.utils;

import com.badlogic.gdx.Input;

import java.util.ArrayList;

/**
 * Classe des racourcis de l'éditeur
 */
public class ShortCuts {

    private ArrayList<Integer> listKeys;

    private int main;

    public ShortCuts(int main,int ... others){
        this.main = main;
        listKeys = new ArrayList<>();
        for (int key: others) {
            listKeys.add(key);
        }
    }

    public boolean needAlt(){
        for (int key: listKeys) {
            if (key == Input.Keys.ALT_LEFT){
                return true;
            }
        }
        return false;
    }

    public boolean needCtrl(){
        for (int key: listKeys) {
            if (key == Input.Keys.CONTROL_LEFT){
                return true;
            }
        }
        return false;
    }

    public int getMain(){
        return main;
    }
}
