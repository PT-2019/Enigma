package common.save;

import api.ui.CustomWindow;
import api.utils.Utility;
import common.hud.*;
import data.NeedToBeTranslated;
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
 * @version 6.5 01 fevrier 2020
 * @since 6.0
 */
public class ImportExport {
    /**
     * Textes
     */
    private static final String REPLACE_FILE = NeedToBeTranslated.REPLACE_FILE;
    private static final String REPLACE_MAP = NeedToBeTranslated.REPLACE_MAP;
    private static final String REPLACE_GAME = NeedToBeTranslated.REPLACE_GAME;
    private static final String EXPORT_ENDED = NeedToBeTranslated.EXPORT_ENDED;
    private static final String IMPORT_ENDED = NeedToBeTranslated.IMPORT_ENDED;
    private static final String IMPORT = NeedToBeTranslated.IMPORT;
    private static final String EXPORT = NeedToBeTranslated.EXPORT;

    /**
     * Indicateur d'une fin de chaine
     */
    private final static char STRING_END = '\0';

    /**
     * Exporte une map
     * @param mapName Nom de la map
     * @param exportPath Chemin où créer le fichier exporté
     * @throws IOException En cas d'erreur de lecture ou d'écriture
     * @throws IllegalStateException Si l'export est annulé
     */
    public static void exportMap(String mapName, String exportPath) throws IOException {
        ExportMap exportMap = new ExportMap(mapName,exportPath);
        exportMap.start();
        if(exportMap.getIOException() != null)
            throw exportMap.getIOException();
        if(exportMap.getIllegalStateException() != null)
            throw exportMap.getIllegalStateException();
    }

    /**
     * Exporte une map
     * @param mapName Nom de la map
     * @param exportPath Chemin où créer le fichier exporté
     * @throws IOException En cas d'erreur de lecture ou d'écriture
     * @throws IllegalStateException Si l'export est annulé
     */
    public static void exportGame(String mapName, String gameName, String exportPath) throws IOException {
        ExportGame exportGame = new ExportGame(mapName,gameName,exportPath);
        exportGame.start();
        if(exportGame.getIOException() != null)
            throw exportGame.getIOException();
        if(exportGame.getIllegalStateException() != null)
            throw exportGame.getIllegalStateException();
    }

    /**
     * Importe une map
     * @param importPath Chemin du fichier à importer
     * @throws IOException En case d'erreur de lecture ou d'écriture
     * @throws IllegalArgumentException Si le fichier n'est pas un .egmm
     * @throws IllegalStateException Si l'import est annulé
     */
    public static void importMap(String importPath) throws IOException {
        ImportMap importMap = new ImportMap(importPath);
        importMap.start();
        if(importMap.getIOException() != null)
            throw importMap.getIOException();
        if(importMap.getIllegalStateException() != null)
            throw importMap.getIllegalStateException();
        if(importMap.getIllegalArgumentException() != null)
            throw importMap.getIllegalArgumentException();
    }

    /**
     * Importe une partie
     * @param importPath Chemin du fichier à importer
     * @throws IOException En case d'erreur de lecture ou d'écriture
     * @throws IllegalArgumentException Si le fichier n'est pas un .egmg
     * @throws IllegalStateException Si l'import est annulé
     */
    public static void importGame(String importPath) throws IOException {
        ImportGame importGame = new ImportGame(importPath);
        importGame.start();
        if(importGame.getIOException() != null)
            throw importGame.getIOException();
        if(importGame.getIllegalStateException() != null)
            throw importGame.getIllegalStateException();
        if(importGame.getIllegalArgumentException() != null)
            throw importGame.getIllegalArgumentException();
    }

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
    private static String readToString(DataInputStream reader, EnigmaProgressPopup epp, int value) throws IOException{
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
            value += Character.BYTES;
            epp.update(value);
        }

        return read.toString();
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
     * Exporte une map
     *
     * @author Jorys-Micke ALAÏS
     * @author Louka DOZ
     * @author Loic SENECAT
     * @author Quentin RAMSAMY-AGEORGES
     * @version 6.0 06 fevrier 2020
     * @since 6.0
     * @see common.save.ImportExport
     */
    private static class ExportMap extends Thread {
        private String mapName;
        private String exportPath;
        private IOException ioException;
        private IllegalStateException isException;

        /**
         * @param mapName Nom de la map
         * @param exportPath Chemin d'exportation
         */
        public ExportMap(String mapName, String exportPath){
            this.mapName = mapName;
            this.exportPath = exportPath;
            this.ioException = null;
            this.isException = null;
        }

        /**
         * Obtenir l'IOException
         * @return IOException si elle est sortie, null sinon
         */
        public IOException getIOException(){
            return this.ioException;
        }

        /**
         * Obtenir l'IllegalStateException
         * @return IllegalStateException si elle est sortie, null sinon
         */
        public IllegalStateException getIllegalStateException(){
            return this.isException;
        }

        @Override
        public void run(){
            File file = new File(exportPath + mapName + Config.MAP_EXPORT_EXTENSION);

            if(file.exists()) {
                if(!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
                        new Dimension(600,250),
                        REPLACE_FILE)){
                    this.isException = new IllegalStateException("Export annulé");
                    return;
                }
            }

            try {
                DataOutputStream writer = new DataOutputStream(new FileOutputStream(file));
                String[] toWrite = new String[4];
                toWrite[0] = mapName;
                toWrite[1] = Utility.readFile(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION);
                toWrite[2] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION);
                toWrite[3] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION);
                int value = 0;
                int sum = 0;
                for(String s : toWrite)
                    sum += s.length() + 1;

                EnigmaProgressPopup epp = new EnigmaProgressPopup("Exportation",value,sum,EnigmaProgressPopup.PERCENT_TYPE);
                epp.setActionOnMaxValueReached(EnigmaProgressPopup.END_ON_MAX_VALUE_REACHED);
                epp.getWindow().setLocation(CustomWindow.SOUTH);
                epp.begin();

                //Ecriture
                for(String s : toWrite) {
                    for (boolean[] tab : ImportExport.toBytes(s)) {
                        for (boolean b : tab)
                            writer.writeBoolean(b);
                        value++;
                        epp.update(value);
                    }
                }

                writer.close();
                EnigmaGame.getCurrentScreen().showToast(EXPORT_ENDED);
            }catch (IOException e){
                this.ioException = new IOException("export error");
            }catch (IllegalStateException e){
                this.isException = new IllegalStateException("Export error");
            }
        }
    }

    /**
     * Exporte une partie
     *
     * @author Jorys-Micke ALAÏS
     * @author Louka DOZ
     * @author Loic SENECAT
     * @author Quentin RAMSAMY-AGEORGES
     * @version 6.0 06 fevrier 2020
     * @since 6.0
     * @see common.save.ImportExport
     */
    private static class ExportGame extends Thread {
        private String mapName;
        private String gameName;
        private String exportPath;
        private IOException ioException;
        private IllegalStateException isException;

        /**
         * @param mapName Nom de la map
         * @param gameName Nom de la partie
         * @param exportPath Chemin d'exportation
         */
        public ExportGame(String mapName, String gameName, String exportPath){
            this.mapName = mapName;
            this.gameName = gameName;
            this.exportPath = exportPath;
            this.ioException = null;
            this.isException = null;
        }

        /**
         * Obtenir l'IOException
         * @return IOException si elle est sortie, null sinon
         */
        public IOException getIOException(){
            return this.ioException;
        }

        /**
         * Obtenir l'IllegalStateException
         * @return IllegalStateException si elle est sortie, null sinon
         */
        public IllegalStateException getIllegalStateException(){
            return this.isException;
        }

        @Override
        public void run(){
            File file = new File(exportPath + mapName + Config.GAME_EXPORT_EXTENSION);

            if(file.exists()) {
                if(!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
                        new Dimension(600,250),
                        REPLACE_FILE)){
                    this.isException = new IllegalStateException("Export annulé");
                    return;
                }
            }

            try {
                DataOutputStream writer = new DataOutputStream(new FileOutputStream(file));
                String[] toWrite = new String[6];
                toWrite[0] = mapName;
                toWrite[1] = gameName;
                toWrite[2] = Utility.readFile(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION);
                toWrite[3] = Utility.readFile(Config.GAME_DATA_FOLDER + gameName + Config.DATA_EXTENSION);
                toWrite[4] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION);
                toWrite[5] = Utility.readFile(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION);
                int value = 0;
                int sum = 0;
                for(String s : toWrite)
                    sum += s.length() + 1;

                EnigmaProgressPopup epp = new EnigmaProgressPopup(EXPORT,value,sum,EnigmaProgressPopup.PERCENT_TYPE);
                epp.setActionOnMaxValueReached(EnigmaProgressPopup.END_ON_MAX_VALUE_REACHED);
                epp.getWindow().setLocation(CustomWindow.SOUTH);
                epp.begin();

                //Ecriture
                for(String s : toWrite) {
                    for (boolean[] tab : ImportExport.toBytes(s)) {
                        for (boolean b : tab)
                            writer.writeBoolean(b);
                        value++;
                        epp.update(value);
                    }
                }

                writer.close();
                EnigmaGame.getCurrentScreen().showToast(EXPORT_ENDED);
            }catch (IOException e){
                this.ioException = new IOException("export error");
            }catch (IllegalStateException e){
                this.isException = new IllegalStateException("Export error");
            }
        }
    }

    /**
     * Import une map
     *
     * @author Jorys-Micke ALAÏS
     * @author Louka DOZ
     * @author Loic SENECAT
     * @author Quentin RAMSAMY-AGEORGES
     * @version 6.0 06 fevrier 2020
     * @since 6.0
     * @see common.save.ImportExport
     */
    private static class ImportMap extends Thread {
        private String importPath;
        private IOException ioException;
        private IllegalStateException isException;
        private IllegalArgumentException iaException;

        /**
         * @param importPath Chemin du fichier à importer
         */
        public ImportMap(String importPath){
            this.importPath = importPath;
            this.ioException = null;
            this.isException = null;
            this.iaException = null;
        }

        /**
         * Obtenir l'IOException
         * @return IOException si elle est sortie, null sinon
         */
        public IOException getIOException(){
            return this.ioException;
        }

        /**
         * Obtenir l'IllegalStateException
         * @return IllegalStateException si elle est sortie, null sinon
         */
        public IllegalStateException getIllegalStateException(){
            return this.isException;
        }

        /**
         * Obtenir l'IllegalArgumentException
         * @return IllegalArgumentException si elle est sortie, null sinon
         */
        public IllegalArgumentException getIllegalArgumentException(){
            return this.iaException;
        }

        @Override
        public void run() {
            try {
                if (!importPath.endsWith(Config.MAP_EXPORT_EXTENSION))
                    this.iaException = new IllegalArgumentException("Ce n'est pas un " + Config.MAP_EXPORT_EXTENSION);

                FileInputStream file = new FileInputStream(importPath);
                DataInputStream reader = new DataInputStream(file);
                BufferedWriter writer;
                try {
                    String read;
                    int value = 0;
                    int sum = file.available() / (Character.BYTES * 2);
                    EnigmaProgressPopup epp = new EnigmaProgressPopup(IMPORT,value,sum,EnigmaProgressPopup.PERCENT_TYPE);
                    epp.setActionOnMaxValueReached(EnigmaProgressPopup.END_ON_MAX_VALUE_REACHED);
                    epp.getWindow().setLocation(CustomWindow.SOUTH);
                    epp.begin();

                    //Récupération du nom
                    String mapName = ImportExport.readToString(reader,epp,value);
                    value += (mapName.getBytes().length + 1) * Character.BYTES;

                    for (String s : Utility.getAllMapName()) {
                        if (s.equals(mapName)) {
                            if (!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
                                    new Dimension(600, 250),
                                    REPLACE_MAP)) {
                                this.isException = new IllegalStateException("Import annulé");
                            }
                        }
                    }

                    //Récupération des données de la map
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION)));
                    read = ImportExport.readToString(reader,epp,value);
                    writer.write(read);
                    value += (read.getBytes().length + 1) * Character.BYTES;
                    epp.update(value);
                    writer.close();

                    //Récupération de la map
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION)));
                    read = ImportExport.readToString(reader,epp,value);
                    writer.write(read);
                    value += (read.getBytes().length + 1) * Character.BYTES;
                    epp.update(value);
                    writer.close();

                    //Récupération des énigmes
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION)));
                    read = ImportExport.readToString(reader,epp,value);
                    writer.write(read);
                    value += (read.getBytes().length + 1) * Character.BYTES;
                    epp.update(value);
                    writer.close();

                    EnigmaGame.getCurrentScreen().showToast(IMPORT_ENDED);
                } catch (EOFException e) {
                    reader.close();
                }
            } catch (IOException e) {
                this.ioException = new IOException("import error");
            }
        }
    }

    /**
     * Import une partie
     *
     * @author Jorys-Micke ALAÏS
     * @author Louka DOZ
     * @author Loic SENECAT
     * @author Quentin RAMSAMY-AGEORGES
     * @version 6.0 06 fevrier 2020
     * @since 6.0
     * @see common.save.ImportExport
     */
    private static class ImportGame extends Thread {
        private String importPath;
        private IOException ioException;
        private IllegalStateException isException;
        private IllegalArgumentException iaException;

        /**
         * @param importPath Chemin du fichier à importer
         */
        public ImportGame(String importPath){
            this.importPath = importPath;
            this.ioException = null;
            this.isException = null;
            this.iaException = null;
        }

        /**
         * Obtenir l'IOException
         * @return IOException si elle est sortie, null sinon
         */
        public IOException getIOException(){
            return this.ioException;
        }

        /**
         * Obtenir l'IllegalStateException
         * @return IllegalStateException si elle est sortie, null sinon
         */
        public IllegalStateException getIllegalStateException(){
            return this.isException;
        }

        /**
         * Obtenir l'IllegalArgumentException
         * @return IllegalArgumentException si elle est sortie, null sinon
         */
        public IllegalArgumentException getIllegalArgumentException(){
            return this.iaException;
        }

        @Override
        public void run() {
            try {
                if (!importPath.endsWith(Config.MAP_EXPORT_EXTENSION))
                    this.iaException = new IllegalArgumentException("Ce n'est pas un " + Config.GAME_EXPORT_EXTENSION);

                FileInputStream file = new FileInputStream(importPath);
                DataInputStream reader = new DataInputStream(file);
                BufferedWriter writer;
                try {
                    String read;
                    int value = 0;
                    int sum = file.available() / (Character.BYTES * 2);
                    EnigmaProgressPopup epp = new EnigmaProgressPopup(IMPORT,value,sum,EnigmaProgressPopup.PERCENT_TYPE);
                    epp.setActionOnMaxValueReached(EnigmaProgressPopup.END_ON_MAX_VALUE_REACHED);
                    epp.getWindow().setLocation(CustomWindow.SOUTH);
                    epp.begin();

                    //Récupération du nom
                    String mapName = ImportExport.readToString(reader,epp,value);
                    value += (mapName.getBytes().length + 1) * Character.BYTES;
                    String gameName = ImportExport.readToString(reader,epp,value);
                    value += (gameName.getBytes().length + 1) * Character.BYTES;

                    for (String s : Utility.getAllMapName()) {
                        if (s.equals(mapName)) {
                            if (!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
                                    new Dimension(600, 250),
                                    REPLACE_MAP)) {
                                this.isException = new IllegalStateException("Import annulé");
                            }
                        }
                    }

                    if(gameName.length() > 0) {
                        for (String s : Utility.getAllGameName()) {
                            if (s.equals(gameName)) {
                                if (!EnigmaOptionPane.showConfirmDialog(EditorLauncher.getInstance().getWindow(),
                                        new Dimension(600, 250),
                                        REPLACE_GAME)) {
                                    this.isException = new IllegalStateException("Import annulé");
                                }
                            }
                        }
                    }
                    //Récupération des données de la map
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_DATA_FOLDER + mapName + Config.DATA_EXTENSION)));
                    read = ImportExport.readToString(reader,epp,value);
                    writer.write(read);
                    value += (read.getBytes().length + 1) * Character.BYTES;
                    epp.update(value);
                    writer.close();

                    //Récupération des données du jeu
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.GAME_DATA_FOLDER + gameName + Config.DATA_EXTENSION)));
                    read = ImportExport.readToString(reader,epp,value);
                    writer.write(read);
                    value += (read.getBytes().length + 1) * Character.BYTES;
                    epp.update(value);
                    writer.close();

                    //Récupération de la map
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.MAP_EXTENSION)));
                    read = ImportExport.readToString(reader,epp,value);
                    writer.write(read);
                    value += (read.getBytes().length + 1) * Character.BYTES;
                    epp.update(value);
                    writer.close();

                    //Récupération des énigmes
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Config.MAP_FOLDER + mapName + Config.ENIGMA_EXTENSION)));
                    read = ImportExport.readToString(reader,epp,value);
                    writer.write(read);
                    value += (read.getBytes().length + 1) * Character.BYTES;
                    epp.update(value);
                    writer.close();

                    EnigmaGame.getCurrentScreen().showToast(IMPORT_ENDED);
                } catch (EOFException e) {
                    reader.close();
                }
            } catch (IOException e) {
                this.ioException = new IOException("import error");
            }
        }
    }
}
