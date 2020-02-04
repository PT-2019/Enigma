package common.save;

import api.utils.Utility;
import common.hud.EnigmaAlert;
import common.hud.EnigmaLabel;
import common.hud.EnigmaWindow;
import data.config.Config;

import javax.swing.*;
import java.awt.*;
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
public class ImportExport{

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
     * true = 1, false = 0
     * @param s Chaine
     * @return Octets correpondants aux caractères
     */
    private static ArrayList<boolean[]> toBytes(String s){
        ArrayList<boolean[]> bytes = new ArrayList<>();

        for(char c : s.toCharArray()){
            String sByte = Integer.toBinaryString(c);
            int sLen = sByte.length();
            boolean[] bByte = new boolean[8];
            int bLen = bByte.length;

            for(int i = 0; i < bLen; i++){
                if(i < sLen)
                    bByte[(bLen - sLen) + i] = (sByte.charAt(i) == '1');
                else
                    bByte[(bLen - i) - 1] = false;
            }

            bytes.add(bByte);
        }

        return bytes;
    }

    private static String readToString(DataInputStream reader) throws IOException{
        StringBuilder read = new StringBuilder();

        while(true) {
            StringBuilder sByte = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                if(reader.readBoolean())
                    sByte.append('1');
                else
                    sByte.append('0');
            }
            char c = (char) Integer.parseInt(sByte.toString(), 2);

            if(c == '\0')
                break;
            read.append(c);
        }

        return read.toString();
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

    private static EnigmaAlert initLoadingAlert(String mapPath, EnigmaLabel loadingComponent){
        EnigmaLabel title = new EnigmaLabel("Exportation en cours vers :");
        EnigmaLabel path = new EnigmaLabel(mapPath);
        title.setVerticalTextPosition(JLabel.BOTTOM);
        loadingComponent.setVerticalTextPosition(JLabel.TOP);
        EnigmaAlert window = new EnigmaAlert(50,50);
        window.setMinimumSize(new Dimension(50,50));
        window.getContentSpace().setLayout(new GridLayout(3,1));
        window.getContentSpace().add(title);
        window.getContentSpace().add(path);
        window.getContentSpace().add(loadingComponent);
        window.setWindowBackground(Color.DARK_GRAY);
        window.setAlwaysOnTop(true);
        window.setResizable(false);
        window.setMenuBarVisible(false);

        return window;
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

            EnigmaLabel loadingComponent = new EnigmaLabel("0%");
            EnigmaAlert window = ImportExport.initLoadingAlert(exportPath + mapName + Config.EXPORT_EXTENSION,loadingComponent);
            window.setVisible(true);

            for(boolean[] tab : ImportExport.toBytes(mapName + STRING_END))
                for(boolean b : tab)
                    writer.writeBoolean(b);

            for(boolean[] tab : ImportExport.toBytes(mapData + STRING_END + STRING_END)) { //2 STRING_END pour les mapData et les gameData qui sont vides
                for(boolean b : tab)
                    writer.writeBoolean(b);
                count++;
                ImportExport.refreshLoading(loadingComponent,count,sum);
            }

            for(boolean[] tab : ImportExport.toBytes(map + STRING_END)){
                for(boolean b : tab)
                    writer.writeBoolean(b);
                count++;
                ImportExport.refreshLoading(loadingComponent,count,sum);
            }

            for(boolean[] tab : ImportExport.toBytes(enigmas + STRING_END)) {
                for(boolean b : tab)
                    writer.writeBoolean(b);
                count++;
                ImportExport.refreshLoading(loadingComponent,count,sum);
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

            EnigmaLabel loadingComponent = new EnigmaLabel("0%");
            EnigmaAlert window = ImportExport.initLoadingAlert(exportPath + mapName + Config.EXPORT_EXTENSION,loadingComponent);
            window.setVisible(true);

            for(boolean[] tab : ImportExport.toBytes(mapName + STRING_END))
                for (boolean b : tab)
                    writer.writeBoolean(b);

            for(boolean[] tab : ImportExport.toBytes(mapData + STRING_END)) {
                for(boolean b : tab)
                    writer.writeBoolean(b);
                count++;
                ImportExport.refreshLoading(loadingComponent,count,sum);
            }

            for(boolean[] tab : ImportExport.toBytes(gameData + STRING_END)) {
                for(boolean b : tab)
                    writer.writeBoolean(b);
                count++;
                ImportExport.refreshLoading(loadingComponent,count,sum);
            }

            for(boolean[] tab : ImportExport.toBytes(map + STRING_END)){
                for(boolean b : tab)
                    writer.writeBoolean(b);
                count++;
                ImportExport.refreshLoading(loadingComponent,count,sum);
            }

           for(boolean[] tab : ImportExport.toBytes(enigmas + STRING_END)) {
               for(boolean b : tab)
                   writer.writeBoolean(b);
               count++;
               ImportExport.refreshLoading(loadingComponent,count,sum);
           }

           window.dispose();
           writer.close();
        }catch(IOException e){
            writer.close();
            throw new IOException("export error");
        }
    }

    public static void main(String[] args) throws IOException {
        ImportExport.importGameOrMap("/export/home/an18/dozl/Desktop/aa.enigma");
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

        DataInputStream reader = new DataInputStream(new FileInputStream(importPath));
        BufferedWriter writer;
        try {
            String mapName = ImportExport.readToString(reader);
            System.out.println(mapName);
            mapName = "testtest";

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION)));
            writer.write(ImportExport.readToString(reader));
            writer.close();

            String game;
            if((game = ImportExport.readToString(reader)).length() > 0) {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.GAME_DATA_FOLDER + mapName + Config.DATA_EXTENSION)));
                writer.write(game);
                writer.close();
            }

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION)));
            writer.write(ImportExport.readToString(reader));
            writer.close();

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION)));
            writer.write(ImportExport.readToString(reader));
            writer.close();

        }catch (EOFException e){
            reader.close();
        }
    }
}
