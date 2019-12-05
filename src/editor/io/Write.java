package editor.io;

import editor.enigma.Advice;
import editor.enigma.condition.Activated;
import editor.enigma.condition.HaveInHands;
import editor.enigma.Enigma;
import editor.enigma.operation.Summon;
import editor.enigma.operation.Unlock;
import editor.entity.item.Door;
import editor.entity.item.Switch;
import editor.entity.player.Player;
import editor.map.Case;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ecrit des objets dans un fichier
 * @version 2.0
 */
public class Write {

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

    /**
     * Ecrit des énigmes dans un fichier
     * @param filePath Chemin vers le fichier
     * @param enigmas Enigmes à écrire
     * @throws IOException En cas d'erreur d'écriture
     * @throws IllegalArgumentException Si le fichier n'est pas un ".json"
     */
    @SuppressWarnings("unchecked")
    public static void writeEnigmas(String filePath, List<Enigma> enigmas) throws IOException {
        if(!filePath.endsWith(".json")) throw new IllegalArgumentException("Le fichier n'est pas dans un format valide \".json\"");

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        int indentation = 0;

        writer.write(getIndentationToString(indentation) + NEW_OBJECT_SYNTAX);
        indentation++;

        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        for(Enigma e: enigmas){
           list.add(e.objectToMap());
        }

        writeList(writer, "Enigmas", list, indentation);

        writer.newLine();
        indentation--;
        writer.write(getIndentationToString(indentation) + END_OBJECT_SYNTAX);

        writer.close();
    }

    /**
     * Ecrit une liste d'objets dans un fichier
     * @param writer Ecrivain
     * @param name Nom de la liste
     * @param list Liste à écrire
     * @param indentation Nombre d'indentations actuelles
     * @throws IOException En cas d'erreur d'écriture
     */
    private static void writeList(BufferedWriter writer, String name, List<HashMap<String,Object>> list, int indentation) throws IOException {
        writer.newLine();
        writer.write(getIndentationToString(indentation) + "\"" + name + "\"" + NEW_CLASS_TAB_SYNTAX);
        indentation++;

        boolean first = true;
        for(HashMap<String,Object> map: list){
            if(!first){
                writer.write(",");
                writer.newLine();
            } else first = false;

            writer.newLine();
            writer.write(getIndentationToString(indentation) + NEW_OBJECT_SYNTAX);
            indentation++;

            writeObject(writer, map, indentation);

            indentation--;
            writer.newLine();
            writer.write(getIndentationToString(indentation) + END_OBJECT_SYNTAX);
        }

        indentation--;
        writer.newLine();
        writer.write(getIndentationToString(indentation) + END_CLASS_TAB_SYNTAX);
    }

    /**
     * Ecrit un objet dans un fichier
     * @param writer Ecrivain
     * @param object Objet à écrire
     * @param indentation Nombre d'indentations actuelles
     * @throws IOException En cas d'erreur d'écriture
     * @throws IllegalStateException En cas d'erreur dans la map des attributs de l'objets
     */
    @SuppressWarnings("unchecked")
    private static void writeObject(BufferedWriter writer, HashMap<String,Object> object, int indentation) throws IOException {
        if(object.containsKey("path")) {
            String className = (String) object.get("path");
            object.remove("path");

            writer.newLine();
            writer.write(getIndentationToString(indentation) + "\"" + className + "\"" + NEW_CLASS_TAB_SYNTAX);
            indentation++;

            writer.newLine();
            writer.write(getIndentationToString(indentation) + NEW_OBJECT_SYNTAX);
            indentation++;

            boolean first = true;
            for(Map.Entry<String,Object> attribute : object.entrySet()) {
                if(!first) writer.write(",");
                else first = false;

                if(attribute.getValue() instanceof String) {
                    writeAttribute(writer, attribute.getKey(), (String) attribute.getValue(), indentation);

                } else if(attribute.getValue() instanceof List){
                    writeList(writer, attribute.getKey(), (List)attribute.getValue(), indentation);
                    } else throw new IllegalArgumentException("Erreur dans le map de l'objet: " + className + ". Ni un List, ni un String");
            }

            indentation--;
            writer.newLine();
            writer.write(getIndentationToString(indentation) + END_OBJECT_SYNTAX);
            indentation--;

            writer.newLine();
            writer.write(getIndentationToString(indentation) + END_CLASS_TAB_SYNTAX);

        }else throw new IllegalStateException("Impossible de trouver le chemin de la classe");
    }

    /**
     * Ecrit un attribut dans un fichier
     * @param writer Ecrivain
     * @param name Nom de l'attribut
     * @param value Valeur de l'attribut
     * @param indentation Nombre d'indentations actuelles
     * @throws IOException En cas d'erreur d'écriture
     */
    private static void writeAttribute(BufferedWriter writer, String name, String value, int indentation) throws IOException {
        writer.newLine();
        writer.write(getIndentationToString(indentation) + "\"" + name + "\"" +  CLASSIC_ATTRIBUTE_SYNTAX + "\"" + value + "\"");
    }

    /**
     * Obtenir un chaine de caractères d'indentations
     * @param indentation Nombre d'indentations
     * @return Une String du nombre d'indentation
     */
    private static String getIndentationToString(int indentation){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < indentation; i++) s.append(INDENTATION);
        return s.toString();
    }
}
