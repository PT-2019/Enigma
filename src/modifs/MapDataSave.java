package modifs;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MapDataSave {

    private final static String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private final static String START = "<DatMap>";
    private final static String END = "</DatMap>";

    private static String putInSyntax(String name, String value){
        return "<" + name + ">" + value + "</" + name + ">";
    }

    private static String getNameFromSyntax(String s){
        return s.substring(s.indexOf("<"),s.indexOf(">")).trim();
    }

    private static String getValueFromSyntax(String s){
        return s.replaceAll(s.substring(s.indexOf("<"),s.indexOf(">")),"").trim();
    }

    public static void write(MapData data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("assets/files/data/" + data.getName() + ".tmx")));
        HashMap<String,String> attr = data.getData();

        writer.write(HEADER);
        writer.newLine();
        writer.write(START);
        writer.newLine();

        for(Map.Entry<String,String> entry : attr.entrySet()){
            writer.write(MapDataSave.putInSyntax(entry.getKey(),entry.getValue()));
            writer.newLine();
        }

        writer.write(END);
    }

    public static MapData read(String mapName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("assets/files/data/" + mapName + ".tmx")));
        MapData data = new MapData();
        HashMap<String,String> attr = new HashMap<>();
        String read;

        while((read = reader.readLine()) != null){
            if(!read.equals(HEADER) && !read.equals(START) && !read.equals(END))
                attr.put(MapDataSave.getNameFromSyntax(read),MapDataSave.getValueFromSyntax(read));
        }

        data.initFromData(attr);
        return data;
    }
}
