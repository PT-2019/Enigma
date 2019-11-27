package editor.FileReader;

import editor.Enigma.Advice;
import editor.Enigma.Condition.Activated;
import editor.Enigma.Condition.Answer;
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
import org.lwjgl.Sys;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

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
     * Saute une ligne dans l'écriture de fichier
     * @throws IOException En cas d'erreur d'écriture
     */
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
        if(this.indentation > 0) this.indentation--;
        this.writer.newLine();
        for(int i = 0; i < this.indentation; i++) this.writer.write(INDENTATION);
    }

    /**
     * Enregistre des énigmes dans un fichier
     * @param enigmas Enigmes à enregistrer
     * @throws IOException En cas de problème d'écriture
     * @throws NullPointerException Si enigmas est null
     */
    public void writeEnigmas(Enigma[] enigmas) throws IOException {
        this.indentation = 0;
        this.writer = new BufferedWriter(new FileWriter(FILE_PATH));

        for (Enigma e: enigmas) {
            HashMap<Attributes,String> enigmaAttributes = e.objectToMap();

            this.writer.write( "Enigma" + CLASS_SYNTAX);
            this.newLineMoreIndentation();

            this.writer.write(NEW_OBJECT_SYNTAX);
            this.newLineMoreIndentation();

            this.writer.write( EnigmaAttributes.PATH + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.PATH));
            this.newLine();

            this.writer.write( EnigmaAttributes.TITLE + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.TITLE));
            this.newLine();

            this.writer.write( EnigmaAttributes.DESCRIPTION + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.DESCRIPTION));
            this.newLine();

            this.writer.write( EnigmaAttributes.CURRENT_ADVICE_INDEX + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.CURRENT_ADVICE_INDEX));
            this.newLine();

            this.writer.write( EnigmaAttributes.KNOWN + CLASSIC_ATTRIBUTE_SYNTAX + enigmaAttributes.get(EnigmaAttributes.KNOWN));
            this.newLine();

            this.writer.write( EnigmaAttributes.ADVICES + CLASS_SYNTAX);

            Iterator<Advice> ita = e.getAllAdvices();
            while (ita.hasNext()) {
                Advice a = ita.next();
                HashMap<Attributes, String> adviceAttributes = a.objectToMap();

                this.newLineMoreIndentation();
                this.writer.write(NEW_OBJECT_SYNTAX);
                this.newLineMoreIndentation();

                this.writer.write(AdviceAttributes.PATH + CLASSIC_ATTRIBUTE_SYNTAX + adviceAttributes.get(AdviceAttributes.PATH));
                this.newLine();

                this.writer.write(AdviceAttributes.ADVICE + CLASSIC_ATTRIBUTE_SYNTAX + adviceAttributes.get(AdviceAttributes.ADVICE));
                this.newLine();

                this.writer.write(AdviceAttributes.DELAY + CLASSIC_ATTRIBUTE_SYNTAX + adviceAttributes.get(AdviceAttributes.DELAY));
                this.newLineLessIndentation();

                this.writer.write(END_OBJECT_SYNTAX);
                this.newLineLessIndentation();
            }

            this.writer.write(END_CLASS_SYNTAX);
            this.newLine();

            this.writer.write( EnigmaAttributes.CONDITIONS + CLASS_SYNTAX);

            Iterator<Condition> itc = e.getAllConditions();
            while (itc.hasNext()) {
                Condition c = itc.next();
                HashMap<Attributes, String> conditionAttributes = c.objectToMap();

                this.newLineMoreIndentation();
                this.writer.write(NEW_OBJECT_SYNTAX);
                this.newLineMoreIndentation();

                this.writer.write(ConditionAttributes.PATH + CLASSIC_ATTRIBUTE_SYNTAX + conditionAttributes.get(ConditionAttributes.PATH));
                this.newLine();

                this.writer.write(ConditionAttributes.ENTITY + CLASSIC_ATTRIBUTE_SYNTAX + conditionAttributes.get(ConditionAttributes.ENTITY));
                this.newLineLessIndentation();

                this.writer.write(END_OBJECT_SYNTAX);
                this.newLineLessIndentation();
            }

            this.writer.write(END_CLASS_SYNTAX);
            this.newLine();

            this.writer.write( EnigmaAttributes.OPERATIONS + CLASS_SYNTAX);

            Iterator<Operation> ito = e.getAllOperations();
            while (ito.hasNext()) {
                Operation o = ito.next();
                HashMap<Attributes, String> operationAttributes = o.objectToMap();

                if(o instanceof Summon) {

                    this.newLineMoreIndentation();
                    this.writer.write(NEW_OBJECT_SYNTAX);
                    this.newLineMoreIndentation();

                    this.writer.write(SummonAttributes.PATH + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(SummonAttributes.PATH));
                    this.newLine();

                    this.writer.write(SummonAttributes.ENTITY + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(SummonAttributes.ENTITY));
                    this.newLine();

                    this.writer.write(SummonAttributes.CASE + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(SummonAttributes.CASE));
                    this.newLineLessIndentation();

                    this.writer.write(END_OBJECT_SYNTAX);
                    this.newLineLessIndentation();
                }else{

                    this.newLineMoreIndentation();
                    this.writer.write(NEW_OBJECT_SYNTAX);
                    this.newLineMoreIndentation();

                    this.writer.write(OperationAttributes.PATH + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(OperationAttributes.PATH));
                    this.newLine();

                    this.writer.write(OperationAttributes.ENTITY + CLASSIC_ATTRIBUTE_SYNTAX + operationAttributes.get(OperationAttributes.ENTITY));
                    this.newLineLessIndentation();

                    this.writer.write(END_OBJECT_SYNTAX);
                    this.newLineLessIndentation();
                }
            }

            this.writer.write(END_CLASS_SYNTAX);
            this.newLineLessIndentation();

            this.writer.write(END_OBJECT_SYNTAX);
            this.newLineLessIndentation();

            this.writer.write(END_CLASS_SYNTAX);
            this.newLineLessIndentation();
        }

        this.writer.close();
    }

    public static void main(String[] args) {
        EnigmaFileWriter efw = new EnigmaFileWriter();
        Enigma[] en = new Enigma[2];
        en[0] = new Enigma("enigme","oui c'est une énigme");
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
        en[1].addOperation(new Unlock(new Door(4)));

        try {
            efw.writeEnigmas(en);
        }catch(IOException e){
            System.err.println("oups"+e.getMessage());
        }
    }
}
