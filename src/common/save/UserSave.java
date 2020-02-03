package common.save;

import common.data.UserData;
import data.config.Config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserSave {

    /**
     * Balise d'en-tête
     */
    private final static String HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    /**
     * Balise GameData de début
     */
    private final static String START_USER_DATA = "<UserData>";
    /**
     * Balise GameData de fin
     */
    private final static String END_USER_DATA = "</UserData>";
    /**
     * Indentation
     */
    private final static String INDENTATION = "  ";

    /**
     * Convertie une donnée et sa valeur en une chaine unique avec une syntaxe précise
     * @param name Nom de la donnée
     * @param value Valeur de la donnée
     * @return Data et sa valeur dans une syntaxe
     */
    private static String putInSyntax(String name, String value){
        return "<" + name + ">" + value + "</" + name + ">";
    }

    /**
     * Décode le nom d'une donnée
     * @param s Chaine
     * @return Nom de la donnée
     */
    private static String getNameFromSyntax(String s){
        return s.substring(s.indexOf("<"),s.indexOf(">")).replace("<","").trim();
    }

    /**
     * Décode la valeur d'une donnée
     * @param s Chaine
     * @param name Nom de la donnée
     * @return Valeur de la donnée
     */
    private static String getValueFromSyntax(String s, String name){
        return s.replace("<" + name + ">","").replace("</" + name + ">","").trim();
    }

    /**
     * Indique si une donnée est contenue dans la chaine
     * @param s Chaine
     * @return Valeur de la donnée
     */
    private static boolean isData(String s){
        return !(s.equals(HEADER) || s.equals(START_USER_DATA) || s.equals(END_USER_DATA));
    }

    public static UserData read() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(Config.USER_DATA_FILE_FOLDER)));

        HashMap<String,String> data = new HashMap<>();
        String read;

        while((read = reader.readLine()) != null){
            if(UserSave.isData(read)){
                String name = UserSave.getNameFromSyntax(read);
                data.put(name, UserSave.getValueFromSyntax(read,name));
            }
        }

        reader.close();
        return new UserData(data);
    }

    public static void write(UserData data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.USER_DATA_FILE_FOLDER)));

        writer.write(HEADER);
        writer.newLine();
        writer.write(START_USER_DATA);
        writer.newLine();

        for(Map.Entry<String,String> entry : data.getData().entrySet()){
            writer.write(INDENTATION + UserSave.putInSyntax(entry.getKey(),entry.getValue()));
            writer.newLine();
        }

        writer.write(END_USER_DATA);
        writer.close();
    }
}
