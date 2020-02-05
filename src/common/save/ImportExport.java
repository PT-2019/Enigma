package common.save;

import api.utils.Utility;
import common.hud.EnigmaAlert;
import common.hud.EnigmaLabel;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import data.config.Config;
import editor.EditorLauncher;
import game.EnigmaGame;

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
public class ImportExport {

    /**
     * Indicateur d'une fin de chaine
     */
    private final static char STRING_END = '\0';

    /**
     * Convertie une chaine de caractères en octets correspondants
     * true = 1, false = 0
     * @param s Chaine
     * @return Octets correpondants aux caractères
     */
    private static ArrayList<boolean[]> toBytes(String s){
        ArrayList<boolean[]> bytes = new ArrayList<>();
        s += STRING_END;

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

    /**
     * Lit des bits en les convertissant en chaine de caractère
     * S'arrête à la rencontre d'un '\0'
     * @param reader Lecteur
     * @return Chaine de caractère
     * @throws IOException En cas d'erreur de lecture
     */
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
     * Exporte une map
     * @param mapName Nom de la map
     * @param exportPath Chemin où créer le fichier exporté
     * @throws IllegalStateException En cas d'erreur de lecture ou d'écriture
     * @throws IllegalStateException Si l'export est annulé
     */
    public static void exportMap(String mapName, String exportPath) throws IOException {
        File file = new File(exportPath + mapName + Config.MAP_EXPORT_EXTENSION);

        if(file.exists()) {
            if(!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
                    new Dimension(600,250),
                    "Un fichier nommé \"" + mapName + Config.MAP_EXPORT_EXTENSION + "\" existe déjà, remplacer?")){
                throw new IllegalStateException("Export annulé");
            }
        }

        DataOutputStream writer = new DataOutputStream(new FileOutputStream(file));

        try {
            String[] toWrite = new String[4];
            toWrite[0] = mapName;
            toWrite[1] = Utility.readFile(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION);
            toWrite[2] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION);
            toWrite[3] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION);

            //Ecriture
            for(String s : toWrite)
                for (boolean[] tab : ImportExport.toBytes(s))
                    for (boolean b : tab)
                        writer.writeBoolean(b);

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
     * @throws IOException En cas d'erreur de lecture ou d'écriture
     * @throws IllegalStateException Si l'export est annulé
     */
    public static void exportGame(String mapName, String gameName, String exportPath) throws IOException {
        File file = new File(exportPath + mapName + Config.GAME_EXPORT_EXTENSION);

        if(file.exists()) {
            if(!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
                    new Dimension(600,250),
                    "Un fichier nommé \"" + mapName + Config.GAME_EXPORT_EXTENSION + "\" existe déjà, remplacer?")){
                throw new IllegalStateException("Export annulé");
            }
        }

        DataOutputStream writer = new DataOutputStream(new FileOutputStream(file));

        try {
            String[] toWrite = new String[6];
            toWrite[0] = mapName;
            toWrite[1] = gameName;
            toWrite[2] = Utility.readFile(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION);
            toWrite[3] = Utility.readFile(Config.GAME_DATA_FOLDER + gameName + Config.DATA_EXTENSION);
            toWrite[4] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION);
            toWrite[5] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION);

            //Ecriture
            for(String s : toWrite)
                for(boolean[] tab : ImportExport.toBytes(s))
                    for(boolean b : tab)
                        writer.writeBoolean(b);

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
     * @throws IllegalStateException Si l'import est annulé
     */
    public static void importMap(String importPath) throws IOException {
        if(!importPath.endsWith(Config.MAP_EXPORT_EXTENSION))
            throw new IllegalArgumentException("Ce n'est pas un " + Config.MAP_EXPORT_EXTENSION);

        DataInputStream reader = new DataInputStream(new FileInputStream(importPath));
        BufferedWriter writer;
        try {
            //Récupération du nom
            String mapName = ImportExport.readToString(reader);

            for(String s : Utility.getAllMapName()) {
                if (s.equals(mapName)){
                    if(!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
                            new Dimension(600,250),
                            "Une map nommée \"" + mapName + "\" existe déjà, remplacer?")){
                        throw new IllegalStateException("Import annulé");
                    }
                }
            }

            //Récupération des données de la map
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION)));
            writer.write(ImportExport.readToString(reader));
            writer.close();

            //Récupération de la map
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION)));
            writer.write(ImportExport.readToString(reader));
            writer.close();

            //Récupération des énigmes
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION)));
            writer.write(ImportExport.readToString(reader));
            writer.close();

        }catch (EOFException e){
            reader.close();
        }
    }

    /**
     * Importe une partie
     * @param importPath Chemin du fichier à importer
     * @throws IOException En case d'erreur de lecture ou d'écriture
     * @throws IllegalArgumentException Si le fichier n'est pas un .enigma
     * @throws IllegalStateException Si l'import est annulé
     */
    public static void importGame(String importPath) throws IOException {
        if(!importPath.endsWith(Config.GAME_EXPORT_EXTENSION))
            throw new IllegalArgumentException("Ce n'est pas un " + Config.GAME_EXPORT_EXTENSION);

        DataInputStream reader = new DataInputStream(new FileInputStream(importPath));
        BufferedWriter writer;
        try {
            //Récupération du nom
            String mapName = ImportExport.readToString(reader);
            String gameName = ImportExport.readToString(reader);

            for(String s : Utility.getAllMapName()) {
                if (s.equals(mapName)){
                    if(!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
                            new Dimension(600,250),
                            "Une map nommée \"" + mapName + "\" existe déjà, remplacer?")){
                        throw new IllegalStateException("Import annulé");
                    }
                }
            }

            if(gameName.length() > 0) {
                for (String s : Utility.getAllGameName()) {
                    if (s.equals(gameName)) {
                        if (!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
                                new Dimension(600, 250),
                                "Une partie nommée \"" + gameName + "\" existe déjà, remplacer?")) {
                            throw new IllegalStateException("Import annulé");
                        }
                    }
                }
            }
            //Récupération des données de la map
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION)));
            writer.write(ImportExport.readToString(reader));
            writer.close();

            //Récupération des données du jeu
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.GAME_DATA_FOLDER + gameName + Config.DATA_EXTENSION)));
            writer.write(ImportExport.readToString(reader));
            writer.close();

            //Récupération de la map
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION)));
            writer.write(ImportExport.readToString(reader));
            writer.close();

            //Récupération des énigmes
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION)));
            writer.write(ImportExport.readToString(reader));
            writer.close();

        }catch (EOFException e){
            reader.close();
        }
    }
}
