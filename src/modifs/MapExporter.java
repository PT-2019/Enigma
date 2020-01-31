package modifs;

import java.io.*;
import java.util.HashMap;

public class MapExporter {

    public static void encode(String mapName, String exportPath) throws IOException {
        File tmx = new File("assets/files/map/" + mapName + ".tmx");
        File json = new File("assets/files/map/" + mapName + ".json");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(exportPath + mapName + ".enigma")));
        BufferedReader reader;
        String read;
        int dataCount = 4;

        writer.write(MapExporter.putInSyntax("name",mapName));
        writer.newLine();

        writer.write(MapExporter.putInSyntax("author",mapName));
        writer.newLine();

        writer.write(MapExporter.putInSyntax("tmx",String.valueOf((dataCount + 1)) ));
        writer.newLine();

        writer.write(MapExporter.putInSyntax("json",String.valueOf((dataCount + MapExporter.countLines(tmx)) )));
        writer.newLine();

        reader = new BufferedReader(new InputStreamReader(new FileInputStream(tmx)));
        while((read = reader.readLine()) != null) {
            writer.write(read);
            writer.newLine();
        }

        reader = new BufferedReader(new InputStreamReader(new FileInputStream(json)));
        while((read = reader.readLine()) != null) {
            writer.write(read);
            writer.newLine();
        }

        writer.close();
    }

    private static String putInSyntax(String data, String value){
        return "<" + data + ">" + value + "</" + data + ">";
    }

    public static int countLines(File input) throws IOException {
        try (InputStream is = new FileInputStream(input)) {
            int count = 1;
            for (int aChar = 0; aChar != -1; aChar = is.read())
                count += aChar == '\n' ? 1 : 0;
            is.close();
            return count;
        }
    }

    public static void main(String[] args) throws IOException {
        MapData d = new MapData();
        MapExporter.encode("test","assets/files/exports/");
    }
}
