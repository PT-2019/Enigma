package editor.FileReader;

import editor.Enigma.Advice;
import editor.Enigma.Enigma;
import org.lwjgl.Sys;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    private static String extractBefore(String string, String regex){
        return string.substring(0,string.indexOf(regex)).replace("\"","").replace(",","").trim();
    }

    private static String extractAfter(String string, String regex){
        return string.substring(string.indexOf(regex)).replace(regex,"").replace("\"","").replace(",","").trim();
    }


    public static ArrayList<Enigma> readEnigmas(String filePath) throws IOException, InterruptedException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(!filePath.endsWith(".json")) throw new IllegalArgumentException("Le fichier n'est pas dans un format valide \".json\"");

        ArrayList<Object> objects = new ArrayList<>();
        ArrayList<Enigma> enigmas = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        objects = read(reader,0, 0,0);

        for (Object o: objects) {
            enigmas.add((Enigma) o);
        }

        reader.close();
        return enigmas;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Object> read(BufferedReader reader, int line, int braceCount, int bracketCount) throws IOException, InterruptedException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        HashMap<String,Object> attributes = new HashMap<>();
        ArrayList<Object> objects = new ArrayList<>();
        String read;
        String className = "";
        boolean firstBrace = true;
        int startingClassBrace = -1;
        int startingClassBracket = -1;

        while((read = reader.readLine()) != null){
            read = read.trim();
            line++;

            if (read.contains(NEW_OBJECT_SYNTAX)){
                if(!firstBrace) braceCount++;
                if(startingClassBrace < 0 && !firstBrace){
                    startingClassBrace = braceCount;
                }

                if (firstBrace) firstBrace = false;
            }

            if (read.contains(END_OBJECT_SYNTAX)){
                if(braceCount == startingClassBrace){

                    Class c = Class.forName(className);
                    Constructor constr = c.getConstructor(Class.forName("java.util.Map"));
                    objects.add(constr.newInstance(attributes));

                    attributes = new HashMap<>();
                    startingClassBrace = -1;
                }
                braceCount--;

                if(!read.contains(END_OBJECT_SYNTAX + ",") && braceCount == 0) break;
            }

            if (read.contains(END_CLASS_TAB_SYNTAX)){
                if(bracketCount == startingClassBracket){
                    attributes = new HashMap<>();
                    startingClassBracket = 1;
                }
                bracketCount--;
            }

            if (read.contains(NEW_CLASS_TAB_SYNTAX)){
                bracketCount++;
                if(startingClassBracket < 0){
                    startingClassBracket = bracketCount;
                    className = extractBefore(read, NEW_CLASS_TAB_SYNTAX);
                } else {
                    System.out.println(read+" "+extractBefore(read, NEW_CLASS_TAB_SYNTAX)+" "+extractBefore(read, NEW_CLASS_TAB_SYNTAX).contains("."));
                    if(extractBefore(read, NEW_CLASS_TAB_SYNTAX).contains(".")) attributes.put(extractBefore(read, NEW_CLASS_TAB_SYNTAX),read(reader, line, braceCount, bracketCount, extractBefore(read, NEW_CLASS_TAB_SYNTAX)));
                    else attributes.put(extractBefore(read, NEW_CLASS_TAB_SYNTAX),read(reader, line, braceCount, bracketCount));
                }

            } else {
                if (read.contains(CLASSIC_ATTRIBUTE_SYNTAX)){
                    attributes.put(extractBefore(read, CLASSIC_ATTRIBUTE_SYNTAX),extractAfter(read, CLASSIC_ATTRIBUTE_SYNTAX));
                }
            }
            //System.out.println(line+" "+read+" "+braceCount+" "+bracketCount+" "+startingClassBrace+" "+startingClassBracket);
        }

        /*if (braceCount > 0) throw new IllegalStateException("Accolade fermante manquante");
        if (braceCount < 0) throw new IllegalStateException("Accolade ouvrante manquante");
        if (bracketCount > 0) throw new IllegalStateException("Crochet fermant manquant");
        if (bracketCount < 0) throw new IllegalStateException("Crochet ovrant manquant");*/

        return objects;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Object> read(BufferedReader reader, int line, int braceCount, int bracketCount, String className) throws IOException, InterruptedException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        HashMap<String,Object> attributes = new HashMap<>();
        ArrayList<Object> objects = new ArrayList<>();
        String read;
        boolean firstBrace = true;
        int startingClassBrace = -1;
        int startingClassBracket = -1;

        while((read = reader.readLine()) != null){
            read = read.trim();
            line++;

            if (read.contains(NEW_OBJECT_SYNTAX)){
                if(!firstBrace) braceCount++;
                if(startingClassBrace < 0 && !firstBrace){
                    startingClassBrace = braceCount;
                }

                if (firstBrace) firstBrace = false;
            }

            if (read.contains(END_OBJECT_SYNTAX)){
                if(braceCount == startingClassBrace){

                    Class c = Class.forName(className);
                    Constructor constr = c.getConstructor(Class.forName("java.util.Map"));
                    objects.add(constr.newInstance(attributes));

                    attributes = new HashMap<>();
                    startingClassBrace = -1;
                }
                braceCount--;

                if(!read.contains(END_OBJECT_SYNTAX + ",") && braceCount == 0) break;
            }

            if (read.contains(END_CLASS_TAB_SYNTAX)){
                if(bracketCount == startingClassBracket){
                    attributes = new HashMap<>();
                    startingClassBracket = 1;
                }
                bracketCount--;
            }

            if (read.contains(NEW_CLASS_TAB_SYNTAX)){
                bracketCount++;
                if(startingClassBracket < 0){
                    startingClassBracket = bracketCount;
                } else {
                    System.out.println(read+" "+extractBefore(read, NEW_CLASS_TAB_SYNTAX));
                    if(extractBefore(read, NEW_CLASS_TAB_SYNTAX).contains(".")) attributes.put(extractBefore(read, NEW_CLASS_TAB_SYNTAX),read(reader, line, braceCount, bracketCount, extractBefore(read, NEW_CLASS_TAB_SYNTAX)));
                    else attributes.put(extractBefore(read, NEW_CLASS_TAB_SYNTAX),read(reader, line, braceCount, bracketCount));
                }

            } else {
                if (read.contains(CLASSIC_ATTRIBUTE_SYNTAX)){
                    attributes.put(extractBefore(read, CLASSIC_ATTRIBUTE_SYNTAX),extractAfter(read, CLASSIC_ATTRIBUTE_SYNTAX));
                }
            }
            //System.out.println(line+" "+read+" "+braceCount+" "+bracketCount+" "+startingClassBrace+" "+startingClassBracket);
        }

        /*if (braceCount > 0) throw new IllegalStateException("Accolade fermante manquante");
        if (braceCount < 0) throw new IllegalStateException("Accolade ouvrante manquante");
        if (bracketCount > 0) throw new IllegalStateException("Crochet fermant manquant");
        if (bracketCount < 0) throw new IllegalStateException("Crochet ovrant manquant");*/

        return objects;
    }
}
