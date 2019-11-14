package editor.Enigma;

import editor.Enigma.Condition.Condition;
import editor.Enigma.Operation.Operation;
import editor.Entity.Player.Player;
import org.lwjgl.Sys;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ArrayList;

public class Enigma implements ActionListener {

    private String title;
    private String description;
    private ArrayList<Condition> conditions;
    private ArrayList<Operation> operations;
    private ArrayList<Advice> advices;
    private int currentAdvice;
    private boolean known;

    public Enigma(){
        this.currentAdvice = -1;
        this.title = "";
        this.description = "";
        this.known = false;
        this.conditions = new ArrayList<Condition>();
        this.operations = new ArrayList<Operation>();
        this.advices = new ArrayList<Advice>();
    }

    public Enigma(String title, String description){
        this.currentAdvice = -1;
        this.title = title;
        this.description = description;
        this.known = false;
        this.conditions = new ArrayList<Condition>();
        this.operations = new ArrayList<Operation>();
        this.advices = new ArrayList<Advice>();
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
        this.conditions.add(c);
    }

    public void removeCondition(Condition c){
        this.conditions.remove(c);
    }

    public void switchConditions(int index1, int index2){
        Condition c = this.conditions.get(index1);
        this.conditions.set(index1,this.conditions.get(index2));
        this.conditions.set(index2,c);
    }

    public void addOperation(Operation o){
        this.operations.add(o);
    }

    public void removeOperation(Operation o){
        this.operations.remove(o);
    }

    public void switchOperations(int index1, int index2){
        Operation o = this.operations.get(index1);
        this.operations.set(index1,this.operations.get(index2));
        this.operations.set(index2,o);
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

    public Advice getAdvice(){
        if(this.currentAdvice != -1) return this.advices.get(this.currentAdvice);
        else return null;
    }

    public String getTextAdvice(){
        if(this.currentAdvice != -1) return this.advices.get(this.currentAdvice).getAdvice();
        else return "";
    }

    public void addAdvice(Advice a){
        this.advices.add(a);
    }

    public void removeAdvice(Advice a){
        this.advices.remove(a);
    }

    public void switchAdvices(int index1, int index2){
        Advice a = this.advices.get(index1);
        this.advices.set(index1,this.advices.get(index2));
        this.advices.set(index2,a);
    }

    public boolean isKnown(){
        return this.known;
    }

    public void hasBeenDiscovered(){
        this.actionPerformed(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.currentAdvice++;
        if(this.currentAdvice < this.advices.size()){
            Timer t = new Timer(this.advices.get(this.currentAdvice).getDuration(),this);
            t.start();
        }
    }
}
