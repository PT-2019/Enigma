package common.save;

import api.utils.Utility;
import data.config.Config;

import java.io.*;
import java.util.ArrayList;

/**
 * Exporteur de map
 * Exporte : les enigmes, la map, les données de la map
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0 01 fevrier 2020
 * @since 6.0
 */
public class ImportExport {

    /**
     * Données nom de la map
     */
    private final static String NAME = "name";
    /**
     * Données ligne de début de la map
     */
    private final static String MAP = "map";
    /**
     * Données ligne de début des énigmes
     */
    private final static String ENIGMAS = "enigmas";
    /**
     * Données ligne de début des données de la map
     */
    private final static String MAP_DATA = "mapData";
    /**
     * Données ligne de début des données de la partie
     */
    private final static String GAME_DATA = "gameData";

    /**
     * Nombre de données
     */
    private final static int HEAD_DATA_SIZE = 6;

    /**
     * Convertie une donnée et sa valeur en une chaine unique avec une syntaxe précise
     * @return Données et sa valeur dans une syntaxe
     */
    private static String putInSyntax(String data, String value){
        return data + ":" + value;
    }

    /**
     * Décode le nom d'une donnée
     * @return Nom de la donnée
     */
    private static String getNameFromSyntax(String s){
        return s.substring(0,s.indexOf(":")).trim();
    }

    /**
     * Décode la valeur d'une donnée
     * @return Valeur de la donnée
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        String read;
        int count = 0;

        while((read = reader.readLine()) != null) {
            if(!read.equals(""))
                count++;
        }

        reader.close();
        return count;
    }

    /**
     * Exporte une map
     * @param mapName Nom de la map
     * @param exportPath Chemin où créer le fichier exporté
     * @throws IllegalStateException En case d'erreur de lecture ou d'écriture
     */
    public static void exportMap(String mapName, String exportPath) throws IOException {
        DataOutputStream writer = new DataOutputStream(new FileOutputStream(exportPath + mapName + Config.EXPORT_EXTENSION));
        try {
            String map = Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION;
            String mapData = Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION;
            String enigmas = Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION;
            int mapLineCount = ImportExport.countLines(map);
            int mapDataLineCount = ImportExport.countLines(mapData);
            int gameDataLineCount = 0;

            writer.writeChars(mapName);
            writer.writeChar('\0');
            writer.writeByte(HEAD_DATA_SIZE);
            writer.writeChar('\0');
            writer.writeByte(-1);
            writer.writeChar('\0');
            writer.writeByte((HEAD_DATA_SIZE + mapDataLineCount + gameDataLineCount));
            writer.writeChar('\0');
            writer.writeByte((HEAD_DATA_SIZE + mapDataLineCount + gameDataLineCount + mapLineCount));
            writer.writeChar('\0');

            writer.writeChars(Utility.readFile(mapData).replaceAll("\n","\0"));
            writer.writeChars(Utility.readFile(map).replaceAll("\n","\0"));
            writer.writeChars(Utility.readFile(enigmas).replaceAll("\n","\0"));

            writer.close();
        }catch (IOException e){
            writer.close();
            throw new IOException("export error");
        }
    }

    public static void main(String[] args) throws IOException {
        ImportExport.exportMap("gbdvsdvsd","assets/files/user/");
    }

    /**
     * Exporte une map
     * @param mapName Nom de la map
     * @param exportPath Chemin où créer le fichier exporté
     * @throws IOException En case d'erreur de lecture ou d'écriture
     */
    public static void exportGame(String mapName, String exportPath) throws IOException {
        DataOutputStream writer = new DataOutputStream(new FileOutputStream(exportPath + mapName + Config.EXPORT_EXTENSION));
        try {
            String map = Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION;
            String mapData = Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION;
            String enigmas = Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION;
            String gameData = Config.GAME_DATA_FOLDER + mapName + Config.DATA_EXTENSION;
            int mapLineCount = ImportExport.countLines(map);
            int mapDataLineCount = ImportExport.countLines(mapData);
            int gameDataLineCount = ImportExport.countLines(gameData);

            writer.writeChars(mapName);
            writer.writeChar('\0');
            writer.writeByte(HEAD_DATA_SIZE);
            writer.writeChar('\0');
            writer.writeByte((HEAD_DATA_SIZE + mapDataLineCount));
            writer.writeChar('\0');
            writer.writeByte((HEAD_DATA_SIZE + mapDataLineCount + gameDataLineCount));
            writer.writeChar('\0');
            writer.writeByte((HEAD_DATA_SIZE + mapDataLineCount + gameDataLineCount + mapLineCount));
            writer.writeChar('\0');

            writer.writeChars(Utility.readFile(mapData).replaceAll("\n","\0"));
            writer.writeChars(Utility.readFile(gameData).replaceAll("\n","\0"));
            writer.writeChars(Utility.readFile(map).replaceAll("\n","\0"));
            writer.writeChars(Utility.readFile(enigmas).replaceAll("\n","\0"));

            writer.close();
        }catch(IOException e){
            writer.close();
            throw new IOException("export error");
        }
    }

    /**
     * Importe une map
     * @param importPath Chemin du fichier à importer
     * @throws IOException En case d'erreur de lecture ou d'écriture
     * @throws IllegalArgumentException Si le fichier n'est pas un .enigma
     * @throws IllegalStateException Si une donnée est corrompue
     * @throws NumberFormatException Si un numéro de ligne est corrompu
     */
    public static void importGameOrMap(String importPath) throws IOException {
        if(!importPath.endsWith(Config.EXPORT_EXTENSION))
            throw new IllegalArgumentException("Ce n'est pas un .enigma!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(importPath)));
        BufferedWriter writer;
        int mapLine = -1;
        int mapDataLine = -1;
        int gameDataLine = -1;
        int enigmasLine = -1;
        int line = 1;
        String read;
        String mapName = null;

        /*ArrayList<String> data = ImportExport.getDataList();
        for(String key : data){
            line++;
            if((read = reader.readLine()) != null){
                switch (key){
                    case NAME:
                        mapName = ImportExport.getValueFromSyntax(read);
                        break;
                    case MAP:
                        mapLine = Integer.parseInt(ImportExport.getValueFromSyntax(read));
                        break;
                    case ENIGMAS:
                        enigmasLine = Integer.parseInt(ImportExport.getValueFromSyntax(read));
                        break;
                    case MAP_DATA:
                        mapDataLine = Integer.parseInt(ImportExport.getValueFromSyntax(read));
                        break;
                    case GAME_DATA:
                        gameDataLine = Integer.parseInt(ImportExport.getValueFromSyntax(read));
                        break;
                }
            }else
                throw new IllegalStateException(key + " corrompu");
        }*/

        int tmpLine = mapLine;
        if(gameDataLine > 0)
            tmpLine = gameDataLine;

        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION)));
        for (; line < tmpLine && (read = reader.readLine()) != null; line++) {
            if(!read.equals("")) {
                writer.write(read);
                writer.newLine();
            }
        }
        writer.close();

        if(gameDataLine > 0) {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.GAME_DATA_FOLDER + mapName + Config.DATA_EXTENSION)));
            for (; line < mapLine && (read = reader.readLine()) != null; line++) {
                if(!read.equals("")) {
                    writer.write(read);
                    writer.newLine();
                }
            }
            writer.close();
        }

        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION)));
        for(; line < enigmasLine && (read = reader.readLine()) != null; line++){
            if(!read.equals("")) {
                writer.write(read);
                writer.newLine();
            }
        }
        writer.close();

        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION)));
        while((read = reader.readLine()) != null){
            if(!read.equals("")) {
                writer.write(read);
                writer.newLine();
            }
        }
        writer.close();
    }
}
