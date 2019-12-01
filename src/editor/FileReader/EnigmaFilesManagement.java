package editor.FileReader;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import editor.Enigma.Advice;
import editor.Enigma.Condition.*;
import editor.Enigma.Enigma;
import editor.Enigma.Operation.Give;
import editor.Enigma.Operation.Operation;
import editor.Enigma.Operation.Summon;
import editor.Enigma.Operation.Unlock;
import editor.Entity.Interface.Entity;
import editor.Entity.Item.Activatable;
import editor.Entity.Item.Door;
import editor.Entity.Item.Switch;
import editor.Entity.Player.Player;
import editor.Enums.*;
import editor.map.Case;
import org.lwjgl.Sys;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Classe responsable de l'écriture de fichier pour la sauvegarde des énigmes
 * @see editor.Enigma.Enigma
 * @version 2.2
 */
public class EnigmaFilesManagement {

    /**
     * Contient le nombre d'intentaions
     */
    private int indentation;

    /**
     * Ecrivain
     */
    private BufferedWriter writer;

    /**
     * Lecteur
     */
    private BufferedReader reader;

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

    /**
     * Permet d'obtenir l'indentation sous forme de String
     * @return Indentation
     */
    private String getIndentation(){
        return INDENTATION.repeat(Math.max(0, this.indentation));
    }

    /**
     * Enregistre les indices dans un fichier
     * @param advices Indices à enregistrer
     * @throws IOException En cas d'erreur d'écriture
     */
    private void writeAdvices(Iterator<Advice> advices) throws IOException {

        while (advices.hasNext()) {
            HashMap<Attributes,String> advicesAtt = advices.next().objectToMap();
            boolean firstRound = true;

            this.writer.newLine();
            this.writer.write(this.getIndentation() + NEW_OBJECT_SYNTAX);
            this.indentation++;

            for (AdviceAttributes aAtt : AdviceAttributes.values()) {
                if(!firstRound) this.writer.write(",");
                else firstRound = false;
                this.writer.newLine();
                if(aAtt.equals(AdviceAttributes.PATH)) this.writer.write(this.getIndentation() + "\"" + NOT_ATTRIBUTE_BEFORE_SYNTAX + aAtt.toLowerString() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + advicesAtt.get(aAtt) + "\"");
                else this.writer.write(this.getIndentation() + "\"" + aAtt.toLowerString() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + advicesAtt.get(aAtt) + "\"");
            }

            this.writer.newLine();
            this.indentation--;
            this.writer.write(this.getIndentation() + END_OBJECT_SYNTAX);
            if (advices.hasNext()) this.writer.write(",");
            this.writer.newLine();
        }
    }

    /**
     * Enregistre les conditions dans un fichier
     * @param conditions Conditions à enregistrer
     * @throws IOException En cas d'erreur d'écriture
     */
    private void writeConditions(Iterator<Condition> conditions) throws IOException {

        while (conditions.hasNext()) {
            Condition c = conditions.next();
            HashMap<Attributes,String> conditionsAtt = c.objectToMap();
            boolean firstRound = true;

            this.writer.newLine();
            this.writer.write(this.getIndentation() + NEW_OBJECT_SYNTAX);
            this.indentation++;

            switch(c.getClass().getSimpleName()){
                default:
                    for (ConditionAttributes cAtt : ConditionAttributes.values()) {
                        if(!firstRound) this.writer.write(",");
                        else firstRound = false;
                        this.writer.newLine();
                        if(cAtt.equals(ConditionAttributes.PATH)) this.writer.write(this.getIndentation() + "\"" + NOT_ATTRIBUTE_BEFORE_SYNTAX + cAtt.toLowerString() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + conditionsAtt.get(cAtt) + "\"");
                        else this.writer.write(this.getIndentation() + "\"" + cAtt.toLowerString() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + conditionsAtt.get(cAtt) + "\"");
                    }
                    break;
            }


            this.writer.newLine();
            this.indentation--;
            this.writer.write(this.getIndentation() + END_OBJECT_SYNTAX);
            if (conditions.hasNext()) this.writer.write(",");
            this.writer.newLine();
        }
    }

    /**
     * Enregistre des opérations dans un fichier
     * @param operations Opérations à enregistrer
     * @throws IOException En cas d'erreur d'écriture
     */
    private void writeOperations(Iterator<Operation> operations) throws IOException {

        while (operations.hasNext()) {
            Operation o = operations.next();
            HashMap<Attributes,String> operationsAtt = o.objectToMap();
            boolean firstRound = true;

            this.writer.newLine();
            this.writer.write(this.getIndentation() + NEW_OBJECT_SYNTAX);
            this.indentation++;

            switch (o.getClass().getSimpleName()) {
                default:
                    for (OperationAttributes oAtt : OperationAttributes.values()) {
                        if (!firstRound) this.writer.write(",");
                        else firstRound = false;
                        this.writer.newLine();
                        if (oAtt.equals(OperationAttributes.PATH)) this.writer.write(this.getIndentation() + "\"" + NOT_ATTRIBUTE_BEFORE_SYNTAX + oAtt.toLowerString() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + operationsAtt.get(oAtt) + "\"");
                        else this.writer.write(this.getIndentation() + "\"" + oAtt.toLowerString() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + operationsAtt.get(oAtt) + "\"");
                    }
                    break;
                case "Summon":
                    for (SummonAttributes sAtt : SummonAttributes.values()) {
                        if (!firstRound) this.writer.write(",");
                        else firstRound = false;
                        this.writer.newLine();
                        if (sAtt.equals(SummonAttributes.PATH)) this.writer.write(this.getIndentation() + "\"" + NOT_ATTRIBUTE_BEFORE_SYNTAX + sAtt.toLowerString() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + operationsAtt.get(sAtt) + "\"");
                        else this.writer.write(this.getIndentation() + "\"" + sAtt.toLowerString() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + operationsAtt.get(sAtt) + "\"");
                    }
                    break;
            }

            this.writer.newLine();
            this.indentation--;
            this.writer.write(this.getIndentation() + END_OBJECT_SYNTAX);
            if (operations.hasNext()) this.writer.write(",");
            this.writer.newLine();
        }
    }

    /**
     * Enregistre des énigmes dans un fichier
     * @param enigmas Enigmes à enregistrer
     * @throws IOException En cas de problème d'écriture
     * @throws IllegalArgumentException Si le fichier n'est pas un ".json"
     */
    public void writeEnigmas(String filePath, ArrayList<Enigma> enigmas) throws IOException {
        if(!filePath.endsWith(".json")) throw new IllegalArgumentException("Le fichier n'est pas dans un format valide \".json\"");

        this.indentation = 0;
        this.writer = new BufferedWriter(new FileWriter(filePath));

        //Ecriture des énigmes
        this.writer.write(NEW_OBJECT_SYNTAX);
        this.indentation++;

        this.writer.newLine();
        this.writer.write( this.getIndentation() + "\"Enigmas\"" + NEW_CLASS_TAB_SYNTAX);
        this.indentation++;

        boolean firstEnigma = true;
        for (Enigma e: enigmas) {
            if(!firstEnigma) {
                this.writer.write(",");
                this.writer.newLine();
            }
            else firstEnigma = false;

            this.writer.newLine();
            this.writer.write(this.getIndentation() + NEW_OBJECT_SYNTAX);
            this.indentation++;

            boolean firstAtt = true;
            HashMap<Attributes,String> enigmaAttributes = e.objectToMap();
            for (EnigmaAttributes eAtt : EnigmaAttributes.values()) {
                if(!firstAtt) this.writer.write(",");
                else firstAtt = false;
                this.writer.newLine();

                switch (eAtt){
                    default:
                        this.writer.write( this.getIndentation() + "\"" + eAtt.toLowerString() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + enigmaAttributes.get(eAtt) + "\"");
                        break;
                    case PATH:
                        this.writer.write( this.getIndentation() + "\"" + NOT_ATTRIBUTE_BEFORE_SYNTAX + eAtt.toLowerString() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + enigmaAttributes.get(eAtt) + "\"");
                        break;
                    case ADVICES:
                        this.writer.write( this.getIndentation() + "\"" + eAtt.toLowerString() + "\"" + NEW_CLASS_TAB_SYNTAX);
                        this.indentation++;
                        this.writeAdvices(e.getAllAdvices());
                        this.indentation--;
                        this.writer.write(this.getIndentation() + END_CLASS_TAB_SYNTAX);
                        break;
                    case CONDITIONS:
                        this.writer.write( this.getIndentation() + "\"" + eAtt.toLowerString() + "\"" + NEW_CLASS_TAB_SYNTAX);
                        this.indentation++;
                        this.writeConditions(e.getAllConditions());
                        this.indentation--;
                        this.writer.write(this.getIndentation() + END_CLASS_TAB_SYNTAX);
                        break;
                    case OPERATIONS:
                        this.writer.write( this.getIndentation() + "\"" + eAtt.toLowerString() + "\"" + NEW_CLASS_TAB_SYNTAX);
                        this.indentation++;
                        this.writeOperations(e.getAllOperations());
                        this.indentation--;
                        this.writer.write(this.getIndentation() + END_CLASS_TAB_SYNTAX);
                        break;
                }
            }

            this.writer.newLine();
            this.indentation--;
            this.writer.write(this.getIndentation() + END_OBJECT_SYNTAX);
        }

        this.indentation--;
        this.writer.newLine();
        this.writer.write(this.getIndentation() + END_CLASS_TAB_SYNTAX);

        this.writer.newLine();
        this.indentation--;
        this.writer.write(this.getIndentation() + END_OBJECT_SYNTAX);

        this.writer.close();
    }

    /**
     * Extrait le nom d'un attribut depuis une ligne lue dans un fichier
     * @param read Ligne lue
     * @return Le nom de l'attribut, une chaine vide si la ligne ne concerne pas le nom d'un attribut
     */
    private String extractAttributeName(String read){
        if(read.contains(CLASSIC_ATTRIBUTE_SYNTAX) && !read.contains(NEW_CLASS_TAB_SYNTAX)) return read.substring(0,read.indexOf(CLASSIC_ATTRIBUTE_SYNTAX)).replaceAll("\"", "");
        else return "";
    }

    /**
     * Extrait le nom d'une classe depuis une ligne lue dans un fichier
     * @param read Ligne lue
     * @return Le nom de la classe, une chaine vide si la ligne ne concerne pas le nom d'une classe
     */
    private String extractClassName(String read){
        if(read.contains(NEW_CLASS_TAB_SYNTAX)) return read.substring(0,read.indexOf(NEW_CLASS_TAB_SYNTAX)).replaceAll("\"", "");
        else return "";
    }

    /**
     * Extrait la valeur d'un attribut depuis une ligne lue dans un fichier
     * @param read Ligne lue
     * @return La valeur de l'attribut, une chaine si la ligne ne concerne pas la valeur d'un attribut
     */
    private String extractAttributeValue(String read){

        if(read.contains(CLASSIC_ATTRIBUTE_SYNTAX)) return read.replaceAll("\"", "").replaceAll(",", "").substring(read.indexOf(CLASSIC_ATTRIBUTE_SYNTAX)).replace(CLASSIC_ATTRIBUTE_SYNTAX,"");
        else return "";
    }

    /**
     * Lit les indices et les instancie
     * @return Les indices instanciés
     * @throws IOException En cas d'erreur de lecture
     */
    @SuppressWarnings("unchecked")
    private ArrayList<Advice> readAdvices(int line) throws IOException {

        ArrayList<Advice> advices = new ArrayList<Advice>();
        String read;
        int braceCount = 0;
        HashMap<AdviceAttributes,String> aAtt = new HashMap<AdviceAttributes,String>();

        while ((read = this.reader.readLine()) != null) {
            line++;
            read = read.trim();
            String name = this.extractAttributeName(read);

            if (!name.equals("")){
                if(name.equals(AdviceAttributes.ADVICE.toLowerString())) aAtt.put(AdviceAttributes.ADVICE,this.extractAttributeValue(read));
                if(name.equals(AdviceAttributes.DELAY.toLowerString())) aAtt.put(AdviceAttributes.DELAY,this.extractAttributeValue(read));
            }else{
                if (read.equals(NEW_OBJECT_SYNTAX)) braceCount++;

                if(read.contains(END_OBJECT_SYNTAX)){
                    braceCount--;
                    if(braceCount == 0) advices.add(new Advice(aAtt.get(AdviceAttributes.ADVICE),Integer.parseInt(aAtt.get(AdviceAttributes.DELAY))));
                    if(!read.contains(END_OBJECT_SYNTAX + ",")) break;
                }
            }
        }

        if (braceCount > 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade fermante manquante");
        if (braceCount < 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade ouvrante manquante");

        return (ArrayList<Advice>)advices.clone();
    }

    /**
     * Lit les conditions et les instancie
     * @return Les conditions instanciées
     * @throws IOException En cas d'erreur de lecture
     */
    @SuppressWarnings("unchecked")
    private ArrayList<Condition> readConditions(int line) throws IOException {

        ArrayList<Condition> conditions = new ArrayList<Condition>();
        String read;
        String className = "";
        int braceCount = 0;
        HashMap<ConditionAttributes,String> cAtt = new HashMap<ConditionAttributes,String>();

        while ((read = this.reader.readLine()) != null) {
            line++;
            read = read.trim();
            String name = this.extractAttributeName(read);

            if (!name.equals("")){
                if(name.equals(NOT_ATTRIBUTE_BEFORE_SYNTAX + ConditionAttributes.PATH.toLowerString())) {
                    String[] split = this.extractAttributeValue(read).split("\\.");
                    className = split[split.length - 1];
                }
                if(name.equals(AdviceAttributes.ADVICE.toLowerString())) cAtt.put(ConditionAttributes.ENTITY,this.extractAttributeValue(read));
            }else{
                if (read.equals(NEW_OBJECT_SYNTAX)) braceCount++;

                if(read.contains(END_OBJECT_SYNTAX)){
                    braceCount--;
                    if(braceCount == 0){

                        switch (className){
                            case "Activated":
                                conditions.add(new Activated(new Switch()));
                                break;
                            case "Answer":
                                conditions.add(new Answer());
                                break;
                            case "HaveInHands":
                                conditions.add(new HaveInHands(new Switch()));
                                break;
                            case "HaveInInventory":
                                conditions.add(new HaveInInventory(new Switch()));
                                break;
                        }
                    }
                    if(!read.contains(END_OBJECT_SYNTAX + ",")) break;
                }
            }
        }

        if (braceCount > 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade fermante manquante");
        if (braceCount < 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade ouvrante manquante");

        return (ArrayList<Condition>)conditions.clone();
    }

    /**
     * Lit les opérations et les instancie
     * @return Les opérations instanciées
     * @throws IOException En cas d'erreur de lecture
     */
    @SuppressWarnings("unchecked")
    private ArrayList<Operation> readOperations(int line) throws IOException {

        ArrayList<Operation> operations = new ArrayList<Operation>();
        String read;
        String className = "";
        int braceCount = 0;
        HashMap<OperationAttributes,String> oAtt = new HashMap<OperationAttributes,String>();

        while ((read = this.reader.readLine()) != null) {
            line++;
            read = read.trim();
            String name = this.extractAttributeName(read);

            if (!name.equals("")){
                if(name.equals(NOT_ATTRIBUTE_BEFORE_SYNTAX + OperationAttributes.PATH.toLowerString())) {
                    String[] split = this.extractAttributeValue(read).split("\\.");
                    className = split[split.length - 1];
                }
                if(name.equals(AdviceAttributes.ADVICE.toLowerString())) oAtt.put(OperationAttributes.ENTITY,this.extractAttributeValue(read));
            }else{
                if (read.equals(NEW_OBJECT_SYNTAX)) braceCount++;

                if(read.contains(END_OBJECT_SYNTAX)){
                    braceCount--;
                    if(braceCount == 0){

                        switch (className){
                            case "Give":
                                operations.add(new Give(new Switch()));
                                break;
                            case "Unlock":
                                operations.add(new Unlock(new Door()));
                                break;
                            case "Summon":
                                operations.add(new Summon(new Player(),new Case()));
                                break;
                        }
                    }
                    if(!read.contains(END_OBJECT_SYNTAX + ",")) break;
                }
            }
        }

        if (braceCount > 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade fermante manquante");
        if (braceCount < 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade ouvrante manquante");

        return (ArrayList<Operation>)operations.clone();
    }

    /**
     * Lit un fichier json d'énigmes et les instancie
     * @param filePath Chemin du fichier
     * @return Les énigmes instannciées
     * @throws IOException En cas d'erreur de lecture
     * @throws IllegalArgumentException Si le fichier n'est pas un ".json"
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Enigma> readEnigmas(String filePath) throws IOException {
        if(!filePath.endsWith(".json")) throw new IllegalArgumentException("Le fichier n'est pas dans un format valide \".json\"");

        ArrayList<Enigma> enigmas = new ArrayList<Enigma>();
        this.reader = new BufferedReader(new FileReader(filePath));
        String read;
        ArrayList<Advice> advices = null;
        ArrayList<Condition> conditions = new ArrayList<Condition>();
        ArrayList<Operation> operations = new ArrayList<Operation>();
        int braceCount = 0;
        int bracketCount = 0;
        int line = 0;
        HashMap<EnigmaAttributes,String> eAtt = new HashMap<EnigmaAttributes,String>();

        if(this.reader.readLine().trim().equals(NEW_OBJECT_SYNTAX)){
            braceCount++;
            line++;

            if(this.extractClassName(this.reader.readLine().trim()).equals("Enigmas")) {
                bracketCount++;
                line++;

                while ((read = this.reader.readLine()) != null) {
                    read = read.trim();
                    line++;
                    String name;

                    if(!(name = this.extractAttributeName(read)).equals("")){
                        String value = this.extractAttributeValue(read);
                        if(name.equals(EnigmaAttributes.TITLE.toLowerString())) eAtt.put(EnigmaAttributes.TITLE,value);
                        if(name.equals(EnigmaAttributes.DESCRIPTION.toLowerString())) eAtt.put(EnigmaAttributes.DESCRIPTION,value);
                        if(name.equals(EnigmaAttributes.KNOWN.toLowerString())) eAtt.put(EnigmaAttributes.KNOWN,value);
                        if(name.equals(EnigmaAttributes.CURRENT_ADVICE_INDEX.toLowerString())) eAtt.put(EnigmaAttributes.CURRENT_ADVICE_INDEX,value);

                    }else {
                        if (!(name = this.extractClassName(read)).equals("")) {
                            bracketCount++;

                            if (name.equals(EnigmaAttributes.ADVICES.toLowerString())) advices = this.readAdvices(line);
                            if (name.equals(EnigmaAttributes.CONDITIONS.toLowerString())) conditions = this.readConditions(line);
                            if (name.equals(EnigmaAttributes.OPERATIONS.toLowerString())) operations = this.readOperations(line);

                        } else {
                            if (read.contains(NEW_OBJECT_SYNTAX)) braceCount++;
                            if (read.contains(END_CLASS_TAB_SYNTAX)) bracketCount--;
                            if (read.contains(END_OBJECT_SYNTAX)) {
                                braceCount--;
                                if (braceCount == 1 && bracketCount == 1)
                                    enigmas.add(new Enigma(eAtt.get(EnigmaAttributes.TITLE), eAtt.get(EnigmaAttributes.DESCRIPTION), Boolean.parseBoolean(eAtt.get(EnigmaAttributes.KNOWN)), Integer.parseInt(eAtt.get(EnigmaAttributes.CURRENT_ADVICE_INDEX)), advices, conditions, operations));
                            }
                        }
                    }
                }

                if (braceCount > 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade fermante manquante");
                if (braceCount < 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade ouvrante manquante");
                if (bracketCount > 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Crochet fermant manquant");
                if (bracketCount < 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Crochet ouvrant manquant");
            }else throw new IllegalStateException(line + ": Le fichier n'est pas dans un format valide. Il est peut être corrompu");
        }else throw new IllegalStateException(line + ": Le fichier n'est pas dans un format valide. Il est peut être corrompu");

        return (ArrayList<Enigma>)enigmas.clone();
    }

    public static void main(String[] args) {
        EnigmaFilesManagement efw = new EnigmaFilesManagement();
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

        try {
            efw.writeEnigmas("TestWrite/write.json",enigmas);
           enigmas = efw.readEnigmas("TestWrite/write.json");
            for (Enigma en: enigmas) {
                System.out.println(en.toLongString());
            }
        }catch(IOException ex){
            System.err.println("oups"+ex.getMessage());
        }
    }
}
