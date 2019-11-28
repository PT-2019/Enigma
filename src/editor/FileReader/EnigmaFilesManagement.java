package editor.FileReader;

import editor.Enigma.Advice;
import editor.Enigma.Condition.Activated;
import editor.Enigma.Condition.Condition;
import editor.Enigma.Condition.HaveInHands;
import editor.Enigma.Enigma;
import editor.Enigma.Operation.Operation;
import editor.Enigma.Operation.Summon;
import editor.Enigma.Operation.Unlock;
import editor.Entity.Item.Door;
import editor.Entity.Item.Switch;
import editor.Entity.Player.Player;
import editor.Enums.*;
import editor.map.Case;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.Iterator;

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
     * Syntaxe d'une indentation
     */
    private final static String INDENTATION = "     ";

    /**
     * Syntaxe d'un nouveau tableau de "classe"
     */
    private final static String CLASS_TAB_SYNTAX = ": [";

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
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < this.indentation; i++) s.append(INDENTATION);
        return s.toString();
    }

    /**
     * Enregistre des énigmes dans un fichier
     * @param enigmas Enigmes à enregistrer
     * @throws IOException En cas de problème d'écriture
     * @throws NullPointerException Si enigmas est null
     */
    public void writeEnigmas(String filePath, ArrayList<Enigma> enigmas) throws IOException {
        if(!filePath.endsWith(".json")) throw new IllegalArgumentException("Le fichier n'est pas dans un format valide \".json\"");

        this.indentation = 0;
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        //Ecriture des énigmes
        writer.write( "Enigma" + CLASS_TAB_SYNTAX);

        for (Enigma e: enigmas) {
            HashMap<Attributes,String> enigmaAttributes = e.objectToMap();

            this.indentation++;
            writer.newLine();
            writer.write(this.getIndentation() + NEW_OBJECT_SYNTAX);
            this.indentation++;
            writer.newLine();

            //////ATTRIBUTS ENIGME//////
            writer.write( this.getIndentation() + NOT_ATTRIBUTE_BEFORE_SYNTAX + EnigmaAttributes.PATH.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.PATH));
            writer.newLine();

            writer.write( this.getIndentation() + EnigmaAttributes.TITLE.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + enigmaAttributes.get(EnigmaAttributes.TITLE) + "\"");
            writer.newLine();

            writer.write( this.getIndentation() + EnigmaAttributes.DESCRIPTION.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + enigmaAttributes.get(EnigmaAttributes.DESCRIPTION) + "\"");
            writer.newLine();

            writer.write( this.getIndentation() + EnigmaAttributes.CURRENT_ADVICE_INDEX.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.CURRENT_ADVICE_INDEX));
            writer.newLine();

            writer.write( this.getIndentation() + EnigmaAttributes.KNOWN.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.KNOWN));
            writer.newLine();

            //Ecriture des indices de l'énigme actuelle
            writer.write( this.getIndentation() + EnigmaAttributes.ADVICES.toLowerString() + CLASS_TAB_SYNTAX);

            Iterator<Advice> ita = e.getAllAdvices();
            while (ita.hasNext()) {
                Advice a = ita.next();
                HashMap<Attributes, String> adviceAttributes = a.objectToMap();

                this.indentation++;
                writer.newLine();
                writer.write(this.getIndentation() + NEW_OBJECT_SYNTAX);
                this.indentation++;
                writer.newLine();

                //////ATTRIBUTS INDICE//////
                writer.write(  this.getIndentation() + NOT_ATTRIBUTE_BEFORE_SYNTAX + AdviceAttributes.PATH.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + adviceAttributes.get(AdviceAttributes.PATH));
                writer.newLine();

                writer.write(this.getIndentation() + AdviceAttributes.ADVICE.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + adviceAttributes.get(AdviceAttributes.ADVICE) + "\"");
                writer.newLine();

                writer.write(this.getIndentation() + AdviceAttributes.DELAY.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + adviceAttributes.get(AdviceAttributes.DELAY));
                this.indentation--;
                writer.newLine();
                //////FIN ATTRIBUTS INDICE//////

                writer.write(this.getIndentation() + END_OBJECT_SYNTAX);
                this.indentation--;
                writer.newLine();
            }

            writer.write(this.getIndentation() + END_CLASS_TAB_SYNTAX);
            writer.newLine();

            //Ecriture des condition de l'énigme actuelle
            writer.write( this.getIndentation() + EnigmaAttributes.CONDITIONS.toLowerString() + CLASS_TAB_SYNTAX);

            Iterator<Condition> itc = e.getAllConditions();
            while (itc.hasNext()) {
                Condition c = itc.next();
                HashMap<Attributes, String> conditionAttributes = c.objectToMap();

                this.indentation++;
                writer.newLine();
                writer.write(this.getIndentation() + NEW_OBJECT_SYNTAX);
                this.indentation++;
                writer.newLine();

                //////ATTRIBUTS CONDITION//////
                writer.write(this.getIndentation() + NOT_ATTRIBUTE_BEFORE_SYNTAX + ConditionAttributes.PATH.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + conditionAttributes.get(ConditionAttributes.PATH));
                writer.newLine();

                writer.write(this.getIndentation() + ConditionAttributes.ENTITY.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + conditionAttributes.get(ConditionAttributes.ENTITY));
                this.indentation--;
                writer.newLine();
                //////FIN ATTRIBUTS CONDITION//////

                writer.write(this.getIndentation() + END_OBJECT_SYNTAX);
                this.indentation--;
                writer.newLine();
            }

            writer.write(this.getIndentation() + END_CLASS_TAB_SYNTAX);
            writer.newLine();

            //Ecriture des opérations de l'énigme actuelle
            writer.write( this.getIndentation() + EnigmaAttributes.OPERATIONS.toLowerString() + CLASS_TAB_SYNTAX);

            Iterator<Operation> ito = e.getAllOperations();
            while (ito.hasNext()) {
                Operation o = ito.next();
                HashMap<Attributes, String> operationAttributes = o.objectToMap();

                this.indentation++;
                writer.newLine();
                writer.write(this.getIndentation() + NEW_OBJECT_SYNTAX);
                this.indentation++;
                writer.newLine();

                //////ATTRIBUTS OPERATION//////
                writer.write(this.getIndentation() + NOT_ATTRIBUTE_BEFORE_SYNTAX + OperationAttributes.PATH.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(OperationAttributes.PATH));
                writer.newLine();

                writer.write(this.getIndentation() + OperationAttributes.ENTITY.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(OperationAttributes.ENTITY));
                this.indentation--;
                writer.newLine();
                //////FIN ATTRIBUTS OPERATION//////

                writer.write(this.getIndentation() + END_OBJECT_SYNTAX);
                this.indentation--;
                writer.newLine();

                //Si l'opération est de type "faire apparître" car les attributs diffères
                if(o instanceof Summon) {

                    this.indentation++;
                    writer.newLine();
                    writer.write(this.getIndentation() + NEW_OBJECT_SYNTAX);
                    this.indentation++;
                    writer.newLine();

                    //////ATTRIBUTS SUMMON//////
                    writer.write(this.getIndentation() + NOT_ATTRIBUTE_BEFORE_SYNTAX + SummonAttributes.PATH.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(SummonAttributes.PATH));
                    writer.newLine();

                    writer.write(this.getIndentation() + SummonAttributes.ENTITY.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(SummonAttributes.ENTITY));
                    writer.newLine();

                    writer.write(this.getIndentation() + SummonAttributes.CASE.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(SummonAttributes.CASE));
                    this.indentation--;
                    writer.newLine();
                    //////FIN ATTRIBUTS SUMMON//////

                    writer.write(this.getIndentation() + END_OBJECT_SYNTAX);
                    this.indentation--;
                    writer.newLine();
                }else{
                    //Si ce n'est pas de type "faire apparaître"

                    this.indentation++;
                    writer.newLine();
                    writer.write(this.getIndentation() + NEW_OBJECT_SYNTAX);
                    this.indentation++;
                    writer.newLine();

                    //////ATTRIBUTS OPERATION//////
                    writer.write(this.getIndentation() + NOT_ATTRIBUTE_BEFORE_SYNTAX + OperationAttributes.PATH.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(OperationAttributes.PATH));
                    writer.newLine();

                    writer.write(this.getIndentation() + OperationAttributes.ENTITY.toLowerString() + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(OperationAttributes.ENTITY));
                    this.indentation--;
                    writer.newLine();
                    //////FIN ATTRIBUTS OPERATION//////

                    writer.write(this.getIndentation() + END_OBJECT_SYNTAX);
                    this.indentation--;
                    writer.newLine();
                }
            }
            //////FIN ATTRIBUTS ENIGME//////

            writer.write(this.getIndentation() + END_CLASS_TAB_SYNTAX);
            this.indentation--;
            writer.newLine();

            writer.write(this.getIndentation() + END_OBJECT_SYNTAX);
            this.indentation--;
            writer.newLine();
        }

        writer.write(this.getIndentation() + END_CLASS_TAB_SYNTAX);
        this.indentation--;
        writer.newLine();

        writer.close();
    }

    /**
     *
     * @param filePath
     * @return
     * @throws IOException
     * @throws NumberFormatException
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     */
    public ArrayList<Enigma> readEnigmas(String filePath) throws IOException {
        if(!filePath.endsWith(".json")) throw new IllegalArgumentException("Le fichier n'est pas dans un format valide \".json\"");

        ArrayList<Enigma> enigmas = new ArrayList<Enigma>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String read;
        String filtred = "";
        int braceCount = 0;
        int bracketCount = 0;
        int line = 0;
        Enigma currentEnigma = null;
        boolean pass;

        if((read  = reader.readLine()).contains(CLASS_TAB_SYNTAX) && read.substring(0,read.indexOf(CLASS_TAB_SYNTAX)).equals("Enigma")){
            bracketCount++;
            line++;
            while((read = reader.readLine()) != null) {
                line++;
                pass = false;

                if (read.contains(NEW_OBJECT_SYNTAX)) {
                    if (braceCount == 0 && bracketCount == 1){
                        System.out.println(currentEnigma);
                        currentEnigma = new Enigma();
                    }
                    braceCount++;
                    pass = true;
                }

                if (read.contains(END_OBJECT_SYNTAX)) {
                    if(braceCount > 0) braceCount--;
                    pass = true;
                }

                if (read.contains(END_CLASS_TAB_SYNTAX)) {
                    if(bracketCount > 0) bracketCount--;
                    pass = true;
                }

                if (!pass){
                    if(currentEnigma == null) throw new IllegalStateException(line + ": Le fichier n'est pas dans un format valide. Il est peut être corrompu");
                    if(read.contains(CLASS_TAB_SYNTAX)) {

                        filtred = read.substring(0,read.indexOf(CLASS_TAB_SYNTAX)).trim();
                        System.out.println(filtred);

                        if(filtred.equals(EnigmaAttributes.ADVICES.toLowerString())){
                            this.readAdvices(reader,line);
                        }

                    }else if(read.contains(CLASSIC_ATTRIBUTE_SYNTAX)) {

                        filtred = read.substring(0,read.indexOf(CLASSIC_ATTRIBUTE_SYNTAX)).trim();
                        String value = read.substring(read.indexOf(CLASSIC_ATTRIBUTE_SYNTAX)).replace(CLASSIC_ATTRIBUTE_SYNTAX, "");

                        if(filtred.equals(EnigmaAttributes.TITLE.toLowerString())){
                            currentEnigma.setTitle(value.replaceAll("\"",""));
                        }

                        if(filtred.equals(EnigmaAttributes.DESCRIPTION.toLowerString())){
                            currentEnigma.setDescription(value.replaceAll("\"",""));
                        }

                        if(filtred.equals(EnigmaAttributes.CURRENT_ADVICE_INDEX.toLowerString())){
                                currentEnigma.setCurrentAdvice(Integer.parseInt(value));
                        }

                        if(filtred.equals(EnigmaAttributes.KNOWN.toLowerString())){
                            currentEnigma.setIsKnown(Boolean.parseBoolean(value));
                        }
                    }else if(!read.equals("")) throw new IllegalStateException(line + ": Le fichier n'est pas dans un format valide. Il est peut être corrompu");
                }
            }

            if(braceCount > 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade fermante manquante");
            if(braceCount < 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade ouvrante manquante");
            if(bracketCount > 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Crochet fermant manquant");
            if(bracketCount < 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Crochet ouvrant manquant");
        }else throw new IllegalStateException(line + ": Le fichier n'est pas dans un format valide. Il est peut être corrompu");

        return (ArrayList<Enigma>)enigmas.clone();
    }

    public ArrayList<Advice> readAdvices(BufferedReader reader, int line) throws IOException {

        ArrayList<Advice> advices = new ArrayList<Advice>();
        String read;
        String filtred = "";
        int braceCount = 0;
        int bracketCount = 0;
        Advice currentAdvice = null;
        boolean pass;

        bracketCount++;
        while((read = reader.readLine()) != null) {
            pass = false;

            if (read.contains(NEW_OBJECT_SYNTAX)) {
                if (braceCount == 0 && bracketCount == 1){
                    System.out.println(currentAdvice);
                    currentAdvice = new Advice("null");
                }
                braceCount++;
                pass = true;
            }

            if (read.contains(END_OBJECT_SYNTAX)) {
                if(braceCount > 0) braceCount--;
                pass = true;
            }

            if (read.contains(END_CLASS_TAB_SYNTAX)) {
                if(bracketCount > 0) bracketCount--;
                pass = true;
            }

            if (!pass){
                if(currentAdvice == null) throw new IllegalStateException(line + ": Le fichier n'est pas dans un format valide. Il est peut être corrompu");
                if(read.contains(CLASSIC_ATTRIBUTE_SYNTAX)) {

                    filtred = read.substring(0,read.indexOf(CLASSIC_ATTRIBUTE_SYNTAX)).trim();
                    String value = read.substring(read.indexOf(CLASSIC_ATTRIBUTE_SYNTAX)).replace(CLASSIC_ATTRIBUTE_SYNTAX, "");


                }else if(!read.equals("")) throw new IllegalStateException(line + ": Le fichier n'est pas dans un format valide. Il est peut être corrompu");
            }
        }

        if(braceCount > 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade fermante manquante");
        if(braceCount < 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Accolade ouvrante manquante");
        if(bracketCount > 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Crochet fermant manquant");
        if(bracketCount < 0) throw new IllegalStateException("Le fichier n'est pas dans un format valide. Crochet ouvrant manquant");

        return (ArrayList<Advice>)advices.clone();
    }

    public static void main(String[] args) {
        EnigmaFilesManagement efw = new EnigmaFilesManagement();
        Enigma[] en = new Enigma[2];
        /*en[0] = new Enigma("enigme","oui c'est une énigme");
        en[0].addAdvice(new Advice("advice"));
        en[0].addAdvice(new Advice("advice2"));
        en[0].addCondition(new HaveInHands(new Switch(0)));
        en[0].addCondition(new Activated(new Switch(1)));
        en[0].addOperation(new Summon(new Player(2),new Case(3)));
        en[0].addOperation(new Unlock(new Door(4)));
        en[1] = new Enigma("enigme","oui c'est une énigme");
        en[1].addAdvice(new Advice("advice"));
        en[1].addAdvice(new Advice("advice2"));
        en[1].addCondition(new HaveInHands(new Switch(0)));
        en[1].addCondition(new Activated(new Switch(1)));
        en[1].addOperation(new Summon(new Player(2),new Case(3)));
        en[1].addOperation(new Unlock(new Door(4)));*/

        try {
            /*efw.writeEnigmas("TestWrite/write.json",en);*/
            ArrayList<Enigma> enigmas = efw.readEnigmas("TestWrite/write.json");
        }catch(IOException e){
            System.err.println("oups"+e.getMessage());
        }
    }
}
