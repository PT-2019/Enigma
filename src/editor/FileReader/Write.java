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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Syntaxe d'un élément qui n'est pas un attribut
     */
    private final static String NOT_ATTRIBUTE_BEFORE_SYNTAX = "~";

    public static void write(String filePath, ArrayList<Enigma> enigmas) throws IOException {
        if(!filePath.endsWith(".json")) throw new IllegalArgumentException("Le fichier n'est pas dans un format valide \".json\"");

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        int indentaion = 0;
        for(Enigma e: enigmas) write(writer, e.objectToMap(), indentaion);
        writer.close();
    }

    @SuppressWarnings("unchecked")
    private static void write(BufferedWriter writer, HashMap<String,Object> object, int indentation) throws IOException {

        if(object.containsKey("path")){
            String className = (String)object.get("path");
            className = className.substring(className.lastIndexOf(".")).replace(".","");

            writer.write(getIndentation(indentation) + NEW_OBJECT_SYNTAX);
            indentation++;
            writer.newLine();

            writer.write(getIndentation(indentation) + "\"" + className + "\"" + NEW_CLASS_TAB_SYNTAX);
            indentation++;
            writer.newLine();

            writer.write(getIndentation(indentation) + NEW_OBJECT_SYNTAX);
            indentation++;

            boolean first = true;
            for(Map.Entry<String,Object> attribute : object.entrySet()) {
                if(first) first = false;
                else writer.write(",");

                if(attribute.getValue() instanceof List){
                    for (Object o: (ArrayList)attribute.getValue()) {
                        if(o instanceof Map) write(writer, (HashMap<String, Object>) o, indentation--);
                        else throw new IllegalArgumentException("Erreur dans le map de l'objet: " + className);
                    }
                }else if (attribute.getValue() instanceof String) {
                        writer.newLine();
                        if(attribute.getKey().equals("path")) writer.write(getIndentation(indentation) + "\"" + NOT_ATTRIBUTE_BEFORE_SYNTAX + attribute.getKey() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + attribute.getValue() + "\"");
                        else writer.write(getIndentation(indentation) + "\"" + attribute.getKey() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + attribute.getValue() + "\"");

                    } else throw new IllegalArgumentException("Erreur dans le map de l'objet: " + className + ". Ni un List, ni un String");
            }

            indentation--;
            writer.newLine();
            writer.write(getIndentation(indentation) + END_OBJECT_SYNTAX);
            writer.newLine();

            writer.write(getIndentation(indentation) + END_CLASS_TAB_SYNTAX);
            writer.newLine();

        }else throw new IllegalStateException("Impossible de trouver le chemin de la classe");
    }

    private static String getIndentation(int indentation){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < indentation; i++) s.append(INDENTATION);
        return s.toString();
    }

    public static void main(String[] args) throws IOException {
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
        e2.addOperation(new Summon(new Player(2),new Case(3)));
        e2.addOperation(new Unlock(new Door(4)));

        enigmas.add(e);
        enigmas.add(e2);

        Write.write("TestWrite/write.json",enigmas);
    }
}
