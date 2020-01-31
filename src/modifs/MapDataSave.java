package modifs;

import java.io.*;
import java.util.HashMap;

public class MapDataSave {

    public static void write(MapData data) throws FileNotFoundException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("assets/files/map/" + data.getName() + ".json")));
        HashMap<String,String> attr = data.getData();
    }
}
