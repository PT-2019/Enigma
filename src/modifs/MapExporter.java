package modifs;

import java.io.*;

public class MapExporter {

    public static void encode(String mapName, String exportPath) throws IOException {
        File map = new File("assets/files/map/" + mapName + ".tmx");
        File data = new File("assets/files/data/" + mapName + ".tmx");
        File enigma = new File("assets/files/map/" + mapName + ".json");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(exportPath + mapName + ".enigma")));
        BufferedReader reader;
        String read;
        int dataCount = 4;

        writer.write(MapExporter.putInSyntax("name",mapName));
        writer.newLine();

        writer.write(MapExporter.putInSyntax("author",mapName));
        writer.newLine();

        writer.write(MapExporter.putInSyntax("map",String.valueOf((dataCount + 1)) ));
        writer.newLine();

        writer.write(MapExporter.putInSyntax("enigma",String.valueOf((dataCount + MapExporter.countLines(map)) )));
        writer.newLine();

        reader = new BufferedReader(new InputStreamReader(new FileInputStream(map)));
        while((read = reader.readLine()) != null) {
            writer.write(read);
            writer.newLine();
        }

        reader = new BufferedReader(new InputStreamReader(new FileInputStream(enigma)));
        while((read = reader.readLine()) != null) {
            writer.write(read);
            writer.newLine();
        }

        writer.close();
    }

    private static String putInSyntax(String data, String value){
        return data + ":" + value;
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
