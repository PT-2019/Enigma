package editor.Enigma;

import editor.Enigma.Condition.Condition;
import editor.Enigma.Operation.Operation;
import editor.Entity.Interface.Content;
import editor.Entity.Player.Player;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ArrayList;

public class Enigma implements ActionListener {

    private String title;
    private String description;
    private ArrayList<Condition> conditions;
    private ArrayList<Operation> operations;
    private ArrayList<String> advices;
    private int currentAdvice;
    private boolean known;
    private Timer timer;
    private int timeBetweenAdvices;
    private final static int ONE_MINUTES_IN_MILLISECOND = 60000;
    private final static int ADVICE_INDEX_STARTING_VALUE = -1;

    public Enigma(){
        this.currentAdvice = ADVICE_INDEX_STARTING_VALUE;
        this.title = "";
        this.description = "";
        this.known = false;
        this.timeBetweenAdvices = 2;
        this.conditions = new ArrayList<Condition>();
        this.operations = new ArrayList<Operation>();
        this.advices = new ArrayList<String>();
    }

    public Enigma(String title, String description){
        this.currentAdvice = ADVICE_INDEX_STARTING_VALUE;
        this.title = title;
        this.description = description;
        this.known = false;
        this.timeBetweenAdvices = 2;
        this.conditions = new ArrayList<Condition>();
        this.operations = new ArrayList<Operation>();
        this.advices = new ArrayList<String>();
    }

    public void verifyConditions(Player p){
        for (Condition condition : this.conditions) {
            //On teste que les conditions sont remplies, si ce n'est pas le cas, la méthode s'arrête là
            if(!condition.verify(p)){
                System.out.println("Toutes les conditions n'ont pas été validées");
                return;
            }
        }

        //On lance toutes les opérations de l'enigme
        for (Operation operation : this.operations) {
            operation.doOperation(p);
        }
    }

    public void addCondition(Condition c){
        if(this.conditions.contains(c)) throw new IllegalArgumentException("Cet élément existe déjà dans la liste");
        this.conditions.add(c);
    }

    public void removeCondition(Condition c){
        this.conditions.remove(c);
    }

    public Iterator<Condition> getAllConditions(){
        return this.conditions.iterator();
    }

    public void addOperation(Operation o){
        if(this.operations.contains(o)) throw new IllegalArgumentException("Cet élément existe déjà dans la liste");
        this.operations.add(o);
    }

    public void removeOperation(Operation o){
        this.operations.remove(o);
    }

    public Iterator<Operation> getAllOperations(){
        return this.operations.iterator();
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addAdvice(String a){
        if(this.advices.contains(a)) throw new IllegalArgumentException("Cet élément existe déjà dans la liste");
        this.advices.add(a);
    }

    public void removeAdvice(String a){
        this.advices.remove(a);
    }

    public void switchAdvices(int index1, int index2){
        String a = this.advices.get(index1);
        this.advices.set(index1,this.advices.get(index2));
        this.advices.set(index2,a);
    }

    public String getAdvice(){
        if(this.currentAdvice != ADVICE_INDEX_STARTING_VALUE) return this.advices.get(this.currentAdvice);
        else return "Aucune aide pour l'instant";
    }

    public ArrayList<String> get(){
        return this.advices;
    }

    public Iterator<String> getAllAdvices(){
        return this.advices.iterator();
    }

    public void setTimeBetweenAdvices(int minutes){
        //verifier qu'il est bien inférieur à la durée maximale de la partie
        this.timeBetweenAdvices = minutes;
    }

    public int getTimeBetweenAdvices() {
        return this.timeBetweenAdvices;
    }

    public boolean isKnown(){
        return this.known;
    }

    public void discovered(){
        this.known = true;
        this.timer = new Timer(this.timeBetweenAdvices * ONE_MINUTES_IN_MILLISECOND,this);
        this.timer.setRepeats(true);
        this.timer.start();
        System.out.println("Nouvelle enigme découverte!");
        System.out.println(this.getTitle()+" : "+this.getDescription());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println((this.currentAdvice + 1)+" "+this.advices.size());
        if(this.currentAdvice + 1 < this.advices.size()) {
            this.currentAdvice++;
        }else {
            this.timer.stop();
        }
    }

    @Override
    public String toString(){
        return "[Enigma  : title = \"" + this.title + "\", descrption = \"" + this.description + "\", isKnown = " + this.isKnown() + ", timeBetweenAdvices = " + this.timeBetweenAdvices + ", currentAdviceIndex = " + this.currentAdvice + ", currentAdvice = \"" + this.getAdvice() + "\"";
    }

    public String toLongString(){
        StringBuilder s = new StringBuilder("[Enigma  : title = \"" + this.title + "\", descrption = \"" + this.description + "\", isKnown = " + this.isKnown() + ", timeBetweenAdvices = " + this.timeBetweenAdvices + ", currentAdviceIndex = " + this.currentAdvice + ", currentAdvice = \"" + this.getAdvice() + "\", allAdvices = {");
        int sizeA = this.advices.size() - 1;
        int sizeC = this.conditions.size() - 1;
        int sizeO = this.operations.size() - 1;
        int i = 0;

        for(String a : this.advices) {
            s.append("\"").append(a).append("\"");
            if(i < sizeA) s.append(", ");
            i++;
        }

        i = 0;
        s.append("}, allCondtitions = {");
        for(Condition c : this.conditions) {
            s.append(c);
            if(i < sizeC) s.append(", ");
            i++;
        }

        i = 0;
        s.append("}, allOperations = {");
        for(Operation o : this.operations) {
            s.append(o);
            if(i < sizeO) s.append(", ");
            i++;
        }

        s.append("}]");
        return s.toString();
    }
}
