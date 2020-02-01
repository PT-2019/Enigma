package modifs;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Sauvegarde les données d'une map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 01 fevrier 2020
 * @since 6.0
 */
public class MapDataSave {

    /**
     * En-tête
     */
    private final static String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    /**
     * Début
     */
    private final static String START = "<DataMap>";
    /**
     * Fin
     */
    private final static String END = "</DataMap>";
    /**
     * Indentation
     */
    private final static String INDENTATION = "  ";

    /**
     * Convertie une data et sa valeur en une chaine unique avec une syntaxe précise
     * @return Data et sa valeur dans une syntaxe
     */
    private static String putInSyntax(String name, String value){
        return "<" + name + ">" + value + "</" + name + ">";
    }

    /**
     * Décode le nom d'une data
     * @return Nom de la data
     */
    private static String getNameFromSyntax(String s){
        return s.substring(s.indexOf("<"),s.indexOf(">")).replace("<","").trim();
    }

    /**
     * Décode la valeur d'une data
     * @return Valeur de la data
     */
    private static String getValueFromSyntax(String s, String name){
        return s.replace("<" + name + ">","").replace("</" + name + ">","").trim();
    }

    /**
     * Ecrit les données d'une map
     * @param data Données
     * @throws IOException En cas d'erreur d'écriture
     */
    public static void write(MapData data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("assets/files/data/" + data.getName() + ".tmx")));
        HashMap<String,String> attr = data.getData();

        writer.write(HEADER);
        writer.newLine();
        writer.write(START);
        writer.newLine();

        for(Map.Entry<String,String> entry : attr.entrySet()){
            writer.write(INDENTATION + MapDataSave.putInSyntax(entry.getKey(),entry.getValue()));
            writer.newLine();
        }

        writer.write(END);
        writer.close();
    }

    /**
     * Lis les données d'une map
     * @param mapName Nom de la map
     * @return Les données
     * @throws IOException En cas d'erreur de lecture
     */
    public static MapData read(String mapName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("assets/files/data/" + mapName + ".tmx")));
        HashMap<String,String> attr = new HashMap<>();
        String read;

        while((read = reader.readLine()) != null){
            if(!read.equals(HEADER) && !read.equals(START) && !read.equals(END)){
                String name = MapDataSave.getNameFromSyntax(read);
                attr.put(name,MapDataSave.getValueFromSyntax(read,name));
            }
        }

        reader.close();
        return new MapData(attr);
    }
}
