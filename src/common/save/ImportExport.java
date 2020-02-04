package common.save;

import api.ui.CustomAlert;
import api.ui.base.WindowSize;
import api.utils.Utility;
import common.hud.EnigmaAlert;
import common.hud.EnigmaLabel;
import common.hud.EnigmaWindow;
import data.config.Config;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
     * Indicateur d'une fin de chaine
     */
    private final static char STRING_END = '\0';

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
     * Convertie une chaine de caractères en octets correspondants
     * @param s Chaine
     * @return Octets correpondants aux caractères
     */
    private static ArrayList<boolean[]> toBytes(String s){
        ArrayList<boolean[]> bytes = new ArrayList<>();

        for(char c : s.toCharArray()){
            String sByte = Integer.toBinaryString(c);
            int sLen = sByte.length();
            boolean[] bByte = new boolean[8];

            for(int i= 0; i < 8; i++){
                if(i < sLen){
                    if(sByte.charAt(i) == '0')
                        bByte[i] = false;
                    else
                        bByte[i] = true;
                }else
                    bByte[i] = false;
            }

            bytes.add(bByte);
        }

        return bytes;
    }

    /**
     * Rafraichi le chargement
     * @param loading Label de chargement
     * @param count Compte
     * @param sum Total
     */
    private static void refreshLoading(EnigmaLabel loading, int count, int sum){
        double d = (double) count / sum;
        double i = d * 100;
        loading.setText((int)i + "%");
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
            String map = Utility.readFile(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION);
            String mapData =  Utility.readFile(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION);
            String gameData =  "";
            String enigmas =  Utility.readFile(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION);
            int count = 0;
            int sum = (mapData.length() + 1) + (gameData.length() + 1) + (map.length() + 1) + (enigmas.length() + 1);

            EnigmaLabel title = new EnigmaLabel("Exportation en cours vers :");
            EnigmaLabel path = new EnigmaLabel(exportPath + mapName + Config.EXPORT_EXTENSION);
            EnigmaLabel loading = new EnigmaLabel("0%");
            title.setVerticalTextPosition(JLabel.BOTTOM);
            loading.setVerticalTextPosition(JLabel.TOP);
            EnigmaAlert window = new EnigmaAlert(50,50);
            window.setMinimumSize(new Dimension(50,50));
            window.getContentSpace().setLayout(new GridLayout(3,1));
            window.getContentSpace().add(title);
            window.getContentSpace().add(path);
            window.getContentSpace().add(loading);
            window.setWindowBackground(Color.DARK_GRAY);
            window.setAlwaysOnTop(true);
            window.setResizable(false);
            window.setMenuBarVisible(false);
            window.setVisible(true);

            for(String s : ImportExport.toBytes(mapName + STRING_END))
                writer.writeChars(s);

            for(String s : ImportExport.toBytes(mapData + STRING_END)) {
                writer.writeChars(s);
                count++;
                ImportExport.refreshLoading(loading,count,sum);
            }

            for(String s : ImportExport.toBytes(gameData + STRING_END)) {
                writer.writeChars(s);
                count++;
                ImportExport.refreshLoading(loading,count,sum);
            }

            for(String s : ImportExport.toBytes(map + STRING_END)){
                writer.writeChars(s);
                count++;
                ImportExport.refreshLoading(loading,count,sum);
            }

            for(String s : ImportExport.toBytes(enigmas + STRING_END)) {
                writer.writeChars(s);
                count++;
                ImportExport.refreshLoading(loading,count,sum);
            }

            window.dispose();
            writer.close();
        }catch (IOException e){
            writer.close();
            throw new IOException("export error");
        }
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
            String map = Utility.readFile(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION);
            String mapData =  Utility.readFile(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION);
            String gameData =  Utility.readFile(Config.GAME_DATA_FOLDER + mapName + Config.DATA_EXTENSION);
            String enigmas =  Utility.readFile(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION);
            int count = 0;
            int sum = (mapData.length() + 1) + (gameData.length() + 1) + (map.length() + 1) + (enigmas.length() + 1);

            EnigmaLabel title = new EnigmaLabel("Exportation en cours vers :");
            EnigmaLabel path = new EnigmaLabel(exportPath + mapName + Config.EXPORT_EXTENSION);
            EnigmaLabel loading = new EnigmaLabel("0%");
            title.setVerticalTextPosition(JLabel.BOTTOM);
            loading.setVerticalTextPosition(JLabel.TOP);
            EnigmaAlert window = new EnigmaAlert(50,50);
            window.setMinimumSize(new Dimension(50,50));
            window.getContentSpace().setLayout(new GridLayout(3,1));
            window.getContentSpace().add(title);
            window.getContentSpace().add(path);
            window.getContentSpace().add(loading);
            window.setWindowBackground(Color.DARK_GRAY);
            window.setAlwaysOnTop(true);
            window.setResizable(false);
            window.setMenuBarVisible(false);
            window.setVisible(true);

            for(String s : ImportExport.toBytes(mapName + STRING_END))
                writer.writeChars(s);

            for(String s : ImportExport.toBytes(mapData + STRING_END)) {
                writer.writeChars(s);
                count++;
                ImportExport.refreshLoading(loading,count,sum);
            }

            for(String s : ImportExport.toBytes(gameData + STRING_END)) {
                writer.writeChars(s);
                count++;
                ImportExport.refreshLoading(loading,count,sum);
            }

            for(String s : ImportExport.toBytes(map + STRING_END)){
                writer.writeChars(s);
                count++;
                ImportExport.refreshLoading(loading,count,sum);
            }

           for(String s : ImportExport.toBytes(enigmas + STRING_END)) {
               writer.writeChars(s);
               count++;
               ImportExport.refreshLoading(loading,count,sum);
           }

           window.dispose();
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

        /*DataInputStream r = new DataInputStream(new FileInputStream(exportPath + mapName + Config.EXPORT_EXTENSION));
            StringBuilder s = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            while(true) {
                s.delete(0,s.length());
                for (int i = 0; i < 8; i++) {
                    s.append(r.readChar());
                }
                char c = (char) Integer.parseInt(s.toString(), 2);
                if(c == '\0')
                    break;
                else
                    s2.append(c);
            }
            System.out.println(s2);*/

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
