package editor.FileReader;

import editor.Enigma.Advice;
import editor.Enigma.Condition.Activated;
import editor.Enigma.Condition.HaveInHands;
import editor.Enigma.Enigma;
import editor.Enigma.Operation.Summon;
import editor.Enigma.Operation.Unlock;
import editor.Entity.Item.Door;
import editor.Entity.Item.Switch;
import editor.Entity.Player.Player;
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
 * Ecrit des objets dans un fichier.
 * Ecrit uniquement des énigmes pour l'instant
 * @see editor.Enigma.Enigma
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
     * @throws IllegalArgumentException Si le fichier n'est pas un .json ou si les énigmes transmises ne correspondent pas à un format valide
     */
    @SuppressWarnings("unchecked")
    public static void writeEnigmas(String filePath, List<Enigma> enigmas) throws IOException {
        if(!filePath.endsWith(".json")) throw new IllegalArgumentException("Le fichier n'est pas dans un format valide \".json\"");

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        int indentation = 0;

        writer.write(getIndentationToString(indentation) + NEW_OBJECT_SYNTAX);

        boolean firstEnigma = true;
        for(Enigma e: enigmas) {
            HashMap<String,Object> hash = e.objectToMap();

            if(hash.containsKey("path")){
                String className = (String)hash.get("path");

                if(firstEnigma) {
                    indentation++;
                    writer.newLine();
                    writer.write(getIndentationToString(indentation) + "\"" + className + "\"" + NEW_CLASS_TAB_SYNTAX);
                    firstEnigma = false;
                } else {
                    writer.write(",");
                    writer.newLine();
                }

                indentation++;
                writer.newLine();
                writer.write(getIndentationToString(indentation) + NEW_OBJECT_SYNTAX);

                boolean firstAttr = true;
                for(Map.Entry<String,Object> attribute : hash.entrySet()) {
                    if(firstAttr) firstAttr = false;
                    else writer.write(",");

                    if(attribute.getValue() instanceof List){

                        writer.newLine();
                        indentation++;
                        writer.write(getIndentationToString(indentation) + "\"" + attribute.getKey() + "\"" + NEW_CLASS_TAB_SYNTAX);

                        writer.newLine();
                        indentation++;
                        writer.write(getIndentationToString(indentation) + NEW_OBJECT_SYNTAX);

                        boolean firstAttrClass = true;
                        for (Object o: (ArrayList)attribute.getValue()) {
                            if(firstAttrClass) firstAttrClass = false;
                            else {
                                writer.newLine();
                                writer.write(getIndentationToString(indentation) + END_OBJECT_SYNTAX + ",");

                                writer.newLine();
                                writer.newLine();
                                writer.write(getIndentationToString(indentation) + NEW_OBJECT_SYNTAX);
                            }

                            if(o instanceof Map){
                                write(writer, (HashMap<String, Object>) o, indentation);
                            }
                            else throw new IllegalArgumentException("Erreur dans le map de l'objet: " + className);
                        }

                        writer.newLine();
                        writer.write(getIndentationToString(indentation) + END_OBJECT_SYNTAX);

                        writer.newLine();
                        indentation--;
                        writer.write(getIndentationToString(indentation) + END_CLASS_TAB_SYNTAX);
                        indentation--;

                    }else if (attribute.getValue() instanceof String) {
                        if(!attribute.getKey().equals("path")) {
                            writer.newLine();
                            writer.write(getIndentationToString(indentation + 1) + "\"" + attribute.getKey() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + attribute.getValue() + "\"");
                        }else firstAttr = true;

                    } else throw new IllegalArgumentException("Erreur dans le map de l'objet: " + className + ". Ni un List, ni un String");
                }

                writer.newLine();
                writer.write(getIndentationToString(indentation) + END_OBJECT_SYNTAX);
                indentation--;

            }else throw new IllegalStateException("Impossible de trouver le chemin de la classe");
        }

        writer.newLine();
        writer.write(getIndentationToString(indentation) + END_CLASS_TAB_SYNTAX);

        indentation--;
        writer.newLine();
        writer.write(getIndentationToString(indentation) + END_OBJECT_SYNTAX);

        writer.close();
    }

    /**
     * Ecrit un objet dans le fichier
     * @param writer Ecrivain dans le fichier en question
     * @param object Object à écrire
     * @param indentation Nombre d'indentaions actuel
     * @throws IOException En cas d'erreur d'écriture
     * @throws IllegalArgumentException Si l'objet transmis ne correspond pas à un format valide
     */
    @SuppressWarnings("unchecked")
    private static void write(BufferedWriter writer, Map<String,Object> object, int indentation) throws IOException {

        if(object.containsKey("path")){
            String className = (String)object.get("path");

            indentation++;
            writer.newLine();
            writer.write(getIndentationToString(indentation) + "\"" + className + "\"" + NEW_CLASS_TAB_SYNTAX);

            indentation++;
            writer.newLine();
            writer.write(getIndentationToString(indentation) + NEW_OBJECT_SYNTAX);

            boolean first = true;
            for(Map.Entry<String,Object> attribute : object.entrySet()) {
                if(first) first = false;
                else writer.write(",");

                if(attribute.getValue() instanceof List){

                    writer.newLine();
                    indentation++;
                    writer.write(getIndentationToString(indentation) + "\"" + attribute.getKey() + "\"" + NEW_CLASS_TAB_SYNTAX);

                    writer.newLine();
                    indentation++;
                    writer.write(getIndentationToString(indentation) + NEW_OBJECT_SYNTAX);

                    boolean first2 = true;
                    for (Object o: (ArrayList)attribute.getValue()) {
                        if(first2) first2 = false;
                        else writer.write(",");

                        if(o instanceof Map){
                            write(writer, (HashMap<String, Object>) o, indentation);
                        }
                        else throw new IllegalArgumentException("Erreur dans le map de l'objet: " + className);
                    }

                    writer.newLine();
                    writer.write(getIndentationToString(indentation) + END_OBJECT_SYNTAX);

                    writer.newLine();
                    indentation--;
                    writer.write(getIndentationToString(indentation) + END_CLASS_TAB_SYNTAX);
                    indentation--;

                }else if (attribute.getValue() instanceof String) {
                        if(!attribute.getKey().equals("path")) {
                            writer.newLine();
                            writer.write(getIndentationToString(indentation + 1) + "\"" + attribute.getKey() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + attribute.getValue() + "\"");
                        }else first = true;

                    } else throw new IllegalArgumentException("Erreur dans le map de l'objet: " + className + ". Ni un List, ni un String");
            }

            writer.newLine();
            writer.write(getIndentationToString(indentation) + END_OBJECT_SYNTAX);

            indentation--;
            writer.newLine();
            writer.write(getIndentationToString(indentation) + END_CLASS_TAB_SYNTAX);

        }else throw new IllegalStateException("Impossible de trouver le chemin de la classe");
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

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ArrayList<Enigma> enigmas = new ArrayList<Enigma>();
        Enigma e = new Enigma("enigme","oui c'est une énigme");
        e.addAdvice(new Advice("advice"));
        e.addAdvice(new Advice("advice2"));
        e.addCondition(new HaveInHands(new Switch(0)));
        e.addCondition(new Activated(new Switch(1)));
        e.addOperation(new Summon(new Player(2),new Case(3)));
        e.addOperation(new Unlock(new Door(4)));

        Enigma e2 = new Enigma("enigme2","oui c'est une énigme");
        e2.addAdvice(new Advice("advice"));
        e2.addAdvice(new Advice("advice2"));
        e2.addCondition(new HaveInHands(new Switch(0)));
        e2.addCondition(new Activated(new Switch(1)));
        e2.addOperation(new Unlock(new Door(3)));
        e2.addOperation(new Unlock(new Door(4)));

        enigmas.add(e);
        enigmas.add(e2);

        Write.writeEnigmas("TestWrite/write.json",enigmas);
        for (Enigma en: enigmas) {
            System.out.println(en.toLongString());
        }
        enigmas = Read.readEnigmas("TestWrite/write.json");
        for (Enigma en: enigmas) {
            System.out.println(en.toLongString());
        }
    }
}
