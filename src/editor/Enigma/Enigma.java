package editor.Enigma;

import editor.Enigma.Condition.Condition;
import editor.Enigma.Operation.Operation;
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
    private final static int ONE_MINUTES_IN_MILLISECOND = 60000;
    private static int TIME_BETWEEN_ADVICES = 2;
    private final static int CURRENT_ADVICE_INDEX_STARTING_VALUE = -1;

    public Enigma(){
        this.currentAdvice = CURRENT_ADVICE_INDEX_STARTING_VALUE;
        this.title = "";
        this.description = "";
        this.known = false;
        this.conditions = new ArrayList<Condition>();
        this.operations = new ArrayList<Operation>();
        this.advices = new ArrayList<String>();
    }

    public Enigma(String title, String description){
        this.currentAdvice = CURRENT_ADVICE_INDEX_STARTING_VALUE;
        this.title = title;
        this.description = description;
        this.known = false;
        this.conditions = new ArrayList<Condition>();
        this.operations = new ArrayList<Operation>();
        this.advices = new ArrayList<String>();
    }

    public void verifyConditions(Player p){
        Iterator<Condition> conditions = this.conditions.iterator();
        Condition condition;
        while (conditions.hasNext()) {
            condition = conditions.next();
            //On teste que les conditions sont remplies, si ce n'est pas le cas, la méthode s'arrête là
            if(!condition.verify(p)){
                System.out.println("Toutes les conditions n'ont pas été validées");
                return;
            }
        }

        Iterator<Operation> operations = this.operations.iterator();
        Operation operation;
        //On lance toutes les opérations de l'enigme
        while (operations.hasNext()) {
            operation = operations.next();
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
        if(this.currentAdvice != CURRENT_ADVICE_INDEX_STARTING_VALUE) return this.advices.get(this.currentAdvice);
        else return "Aucune aide pour l'instant";
    }

    public ArrayList<String> get(){
        return this.advices;
    }

    public Iterator<String> getAllAdvices(){
        return this.advices.iterator();
    }

    public void setTimeBetweenAdvices(int minutes){
        TIME_BETWEEN_ADVICES = minutes;
    }

    public static int getTimeBetweenAdvices() {
        return TIME_BETWEEN_ADVICES;
    }

    public boolean isKnown(){
        return this.known;
    }

    public void discovered(){
        this.known = true;
        this.timer = new Timer(TIME_BETWEEN_ADVICES * ONE_MINUTES_IN_MILLISECOND,this);
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
}
