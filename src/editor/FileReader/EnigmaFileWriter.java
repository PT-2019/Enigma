package editor.FileReader;

import editor.Enigma.Enigma;
import editor.Enums.Attributes;
import editor.Enums.EnigmaAttributes;

import java.io.*;
import java.util.HashMap;

/**
 * Classe responsable de l'écriture de fichier pour la sauvegarde des énigmes
 * @see editor.Enigma.Enigma
 * @version 2.0
 */
public class EnigmaFileWriter {

    /**
     * Chemin vers le fichier de sauvegarde
     */
    private final static String FILE_PATH = "TestWrite/write.json";

    private BufferedWriter writer;
    private int indentation;

    private final static String INDENTATION = "     ";
    private final static String CLASS_SYNTAX = ": [";
    private final static String END_CLASS_SYNTAX = "]";
    private final static String NEW_OBJECT_SYNTAX = "{";
    private final static String END_OBJECT_SYNTAX = "}";
    private final static String CLASSIC_ATTRIBUTE_SYNTAX = ": ";

    /**
     *
     * @param enigmas
     * @throws IOException
     * @throws NullPointerException Si enigmas est null
     */
    public void writeEnigmas(Enigma[] enigmas) throws IOException {
        this.indentation = 0;
        int i;
        this.writer = new BufferedWriter(new FileWriter(FILE_PATH));

        for (Enigma e: enigmas) {
            HashMap<Attributes,Object> enigmaAttributes = e.objectToMap();

            this.writer.write( enigmaAttributes.get(EnigmaAttributes.PATH) + CLASS_SYNTAX);
            this.newLineMoreIndentation();

            this.writer.write(NEW_OBJECT_SYNTAX);
            this.newLineMoreIndentation();

            this.writer.write( EnigmaAttributes.TITLE + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.TITLE));
            this.newLine();

            this.writer.write( EnigmaAttributes.DESCRITPION + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.DESCRITPION));
            this.newLine();

            this.writer.write( EnigmaAttributes.CURRENT_ADVICE_INDEX + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.CURRENT_ADVICE_INDEX));
            this.newLine();

            this.writer.write( EnigmaAttributes.KNOWN + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.KNOWN));
            this.newLineLessIndentation();

            this.writer.write(END_OBJECT_SYNTAX);
            this.newLineLessIndentation();

            this.writer.write(END_CLASS_SYNTAX);
            this.newLineLessIndentation();
        }

        this.writer.close();
    }

    private void newLine() throws IOException {
        this.writer.newLine();
        for(int i = 0; i < this.indentation; i++) this.writer.write(INDENTATION);
    }

    private void newLineMoreIndentation() throws IOException {
        this.indentation++;
        this.writer.newLine();
        for(int i = 0; i < this.indentation; i++) this.writer.write(INDENTATION);
    }

    private void newLineLessIndentation() throws IOException {
        this.indentation--;
        this.writer.newLine();
        for(int i = 0; i < this.indentation; i++) this.writer.write(INDENTATION);
    }

    public static void main(String[] args) {
        EnigmaFileWriter efw = new EnigmaFileWriter();
        Enigma[] en = new Enigma[1];
        en[0] = new Enigma("onui","grave");
        try {
            efw.writeEnigmas(en);
        }catch(IOException e){
            System.err.println("oups");
        }
    }
}
