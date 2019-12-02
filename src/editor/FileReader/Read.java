package editor.FileReader;

import editor.Enigma.Enigma;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Read {

    /**
     * Syntaxe d'une indentation
     */
    private final static String INDENTATION = "     ";

    /**
     * Syntaxe d'un nouveau tableau de "classe"
     */
    private final static String NEW_CLASS_TAB_SYNTAX = ": [";

    /**
     * Syntaxe de fin d'un tableau de "classe"
     */
    private final static String END_CLASS_TAB_SYNTAX = "]";

    /**
     * Syntaxe d'un nouvel objet
     */
    private final static String NEW_OBJECT_SYNTAX = "{";

    /**
     * Syntaxe de fin d'un objet
     */
    private final static String END_OBJECT_SYNTAX = "}";

    /**
     * Syntaxe d'un attribut
     */
    private final static String CLASSIC_ATTRIBUTE_SYNTAX = ": ";

    public static ArrayList<Enigma> readEnigmas(String filePath) throws IOException {
        if(!filePath.endsWith(".json")) throw new IllegalArgumentException("Le fichier n'est pas dans un format valide \".json\"");

        ArrayList<Enigma> enigmas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        reader.close();
        return enigmas;
    }
}
