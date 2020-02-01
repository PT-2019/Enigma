package modifs;

import api.utils.Utility;

import java.io.*;

/**
 * Exporteur de map
 * Exporte : les enigmes, la map, les datas de la map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 01 fevrier 2020
 * @since 6.0
 */
public class MapExporter {

    /**
     * Data nom de la map
     */
    private final static String NAME = "name";
    /**
     * Data ligne de début de la map
     */
    private final static String MAP = "map";
    /**
     * Data ligne de début des énigmes
     */
    private final static String ENIGMAS = "enigmas";
    /**
     * Data ligne de début des datas
     */
    private final static String DATA = "data";
    /**
     * Data nom de la map
     */
    private final static int DATA_COUNT = 4;

    /**
     * Convertie une data et sa valeur en une chaine unique avec une syntaxe précise
     * @return Data et sa valeur dans une syntaxe
     */
    private static String putInSyntax(String data, String value){
        return data + ":" + value;
    }

    /**
     * Décode le nom d'une data
     * @return Nom de la data
     */
    private static String getNameFromSyntax(String s){
        return s.substring(0,s.indexOf(":")).trim();
    }

    /**
     * Décode la valeur d'une data
     * @return Valeur de la data
     */
    private static String getValueFromSyntax(String s){
        return s.substring(s.indexOf(":")).replace(":","").trim();
    }

    /**
     * Compte les lignes d'un fichier
     * @return Lignes dans le fichier
     * @throws IOException En cas d'erreur de lecture
     */
    public static int countLines(String filePath) throws IOException {
        try (InputStream is = new FileInputStream(filePath)) {
            int count = 1;
            for (int aChar = 0; aChar != -1; aChar = is.read())
                count += aChar == '\n' ? 1 : 0;
            is.close();
            return count;
        }
    }

    /**
     * Exporte une map
     * @param mapName Nom de la map
     * @param exportPath Chemin où créer le fichier exporté
     * @throws IOException En case d'erreur de lecture ou d'écriture
     */
    public static void write(String mapName, String exportPath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(exportPath + mapName + ".enigma")));
        String map = "assets/files/map/" + mapName + ".tmx";
        String data = "assets/files/data/" + mapName + ".tmx";
        String enigmas = "assets/files/map/" + mapName + ".json";
        int mapLines = MapExporter.countLines(map);
        int dataLines = MapExporter.countLines(data);

        writer.write(MapExporter.putInSyntax(NAME, mapName));
        writer.newLine();

        writer.write(MapExporter.putInSyntax(DATA, String.valueOf( (DATA_COUNT + 1)) ));
        writer.newLine();

        writer.write(MapExporter.putInSyntax(MAP, String.valueOf( (DATA_COUNT + dataLines) )));
        writer.newLine();

        writer.write(MapExporter.putInSyntax(ENIGMAS, String.valueOf( (DATA_COUNT + dataLines + mapLines - 1) )));
        writer.newLine();

        writer.write(Utility.readFile(data));
        writer.write(Utility.readFile(map));
        writer.write(Utility.readFile(enigmas));

        writer.close();
    }

    /**
     * Importe une map
     * @param filePath Chemin du fichier à importer
     * @throws IOException En case d'erreur de lecture ou d'écriture
     * @throws IllegalArgumentException Si le fichier n'est pas un .enigma
     * @throws IllegalStateException Si une data est corrompue
     * @throws NumberFormatException Si un numéro de ligne est corrompu
     */
    public static void read(String filePath) throws IOException {
        if(!filePath.endsWith(".enigma"))
            throw new IllegalArgumentException("Ce n'est pas un .enigma!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        BufferedWriter writer;
        int mapLine;
        int enigmasLine;
        int line = 1;
        String read;
        String mapName;

        if((read = reader.readLine()) != null && MapExporter.getNameFromSyntax(read).equals(NAME)) {
            mapName = MapExporter.getValueFromSyntax(read);
        } else
            throw new IllegalStateException("nom corrompu");
        line++;

        if((read = reader.readLine()) == null || !MapExporter.getNameFromSyntax(read).equals(DATA))
            throw new IllegalStateException("ligne data corrompue");
        line++;

        if((read = reader.readLine()) != null && MapExporter.getNameFromSyntax(read).equals(MAP)) {
            mapLine = Integer.parseInt(MapExporter.getValueFromSyntax(read));
        } else
            throw new IllegalStateException("ligne map corrompue");
        line++;

        if((read = reader.readLine()) != null && MapExporter.getNameFromSyntax(read).equals(ENIGMAS)) {
            enigmasLine = Integer.parseInt(MapExporter.getValueFromSyntax(read));
        } else
            throw new IllegalStateException("ligne enigmas corrompue");
        line++;

        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("assets/files/data/" + mapName + ".tmx")));
        for(; line < mapLine && (read = reader.readLine()) != null; line++){
            writer.write(read);
            writer.newLine();
        }
        writer.close();

        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("assets/files/map/" + mapName + ".tmx")));
        for(; line < enigmasLine && (read = reader.readLine()) != null; line++){
            writer.write(read);
            writer.newLine();
        }
        writer.close();

        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("assets/files/map/" + mapName + ".json")));
        while((read = reader.readLine()) != null){
            writer.write(read);
            writer.newLine();
        }
        writer.close();
    }
}
