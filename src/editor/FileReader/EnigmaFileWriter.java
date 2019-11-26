package editor.FileReader;

import java.util.ArrayList;

public class EnigmaFileWriter {

    private static ArrayList<Object> LIST = new ArrayList<Object>();

    public static int getID(Object o){
        if(!LIST.contains(o)){
            LIST.add(o);
        }
        return LIST.indexOf(o);
    }
}
