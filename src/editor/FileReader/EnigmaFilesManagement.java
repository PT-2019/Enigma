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
                        if (oAtt.equals(OperationAttributes.PATH)) this.writer.write(this.getIndentation() + "\"" + NOT_ATTRIBUTE_BEFORE_SYNTAX + oAtt + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + operationsAtt.get(oAtt) + "\"");
                        else this.writer.write(this.getIndentation() + "\"" + oAtt.toLowerString() + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + operationsAtt.get(oAtt) + "\"");
                    }
                    break;
                case "Summon":
                    for (SummonAttributes sAtt : SummonAttributes.values()) {
                        if (!firstRound) this.writer.write(",");
                        else firstRound = false;
                        this.writer.newLine();
                        if (sAtt.equals(SummonAttributes.PATH)) this.writer.write(this.getIndentation() + "\"" + NOT_ATTRIBUTE_BEFORE_SYNTAX + sAtt + "\"" + CLASSIC_ATTRIBUTE_SYNTAX + "\"" + operationsAtt.get(sAtt) + "\"");
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
        this.writer.write( this.getIndentation() + "\"Enigmas\"" + CLASS_TAB_SYNTAX);
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
                        this.writer.write( this.getIndentation() + "\"" + eAtt.toLowerString() + "\"" + CLASS_TAB_SYNTAX);
                        this.indentation++;
                        this.writeAdvices(e.getAllAdvices());
                        this.indentation--;
                        this.writer.write(this.getIndentation() + END_CLASS_TAB_SYNTAX);
                        break;
                    case CONDITIONS:
                        this.writer.write( this.getIndentation() + "\"" + eAtt.toLowerString() + "\"" + CLASS_TAB_SYNTAX);
                        this.indentation++;
                        this.writeConditions(e.getAllConditions());
                        this.indentation--;
                        this.writer.write(this.getIndentation() + END_CLASS_TAB_SYNTAX);
                        break;
                    case OPERATIONS:
                        this.writer.write( this.getIndentation() + "\"" + eAtt.toLowerString() + "\"" + CLASS_TAB_SYNTAX);
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
     *
     * @param filePath
     * @return
     * @throws IOException
     * @throws NumberFormatException
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
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
            /*efw.writeEnigmas("TestWrite/write.json",enigmas)*/
           enigmas = efw.readEnigmas("TestWrite/write.json");
        }catch(IOException ex){
            System.err.println("oups"+ex.getMessage());
        }
    }
}
