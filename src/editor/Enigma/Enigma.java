package editor.Enigma;

import editor.Enigma.Condition.Condition;
import editor.Enigma.Operation.Operation;
import editor.Entity.Player.Player;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Enigma gère une énigme. Une énigme est définie par les {@link editor.Enigma.Condition.Condition conditions} à satisfaire pour résoudre l'énigme ainsi que les {@link editor.Enigma.Operation.Operation opérations} réalisées si toutes les conditions sont satisfaites.
 * Une énigme contient aussi des indices déstinés aux joueurs.
 * @see Condition
 * @see Operation
 * @version 1.0
 */
public class Enigma implements ActionListener {

    /**
     * Titre
     */
    private String title;

    /**
     * Description
     */
    private String description;

    /**
     * Conditions
     */
    private ArrayList<Condition> conditions;

    /**
     * Opérations
     */
    private ArrayList<Operation> operations;

    /**
     * Indices. Ils sont rangés par ordre d'importance
     */
    private ArrayList<String> advices;

    /**
     * Index pointant l'indice actuel
     */
    private int currentAdvice;

    /**
     * Est-ce que l'énigme à été trouvée
     */
    private boolean known;

    /**
     * Chronomètre le temps qui sépare deux énigmes
     */
    private Timer timer;

    /**
     * Indique le temps qui sépare deux énigmes
     */
    private int timeBetweenAdvices;

    /**
     * Une minute en millisecondes
     */
    private final static int ONE_MINUTES_IN_MILLISECOND = 60000;

    /**
     * Valeur de départ de l'index pointant l'indice actuel
     */
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

    /**
     * @param title Titre de l'énigme
     * @param description Description de l'énigme
     */
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

    /**
     * Vérifie que toutes les conditions sont satisfaites
     * @param p Joueur ayant intéragit avec l'entité ayant appelé cette méthode
     */
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

    /**
     * Ajoute une condition
     * @param c Condition à ajouter
     * @throws IllegalArgumentException Si la condition existe déjà dans l'énigme
     */
    public void addCondition(Condition c){
        if(this.conditions.contains(c)) throw new IllegalArgumentException("Cet élément existe déjà dans la liste");
        this.conditions.add(c);
    }

    /**
     * Retire une condition
     * @param c Condition à retirer
     */
    public void removeCondition(Condition c){
        this.conditions.remove(c);
    }

    /**
     * Obtenir la liste des conditions
     * @return Iterator<Condition> de toutes les conditions de l'énigme
     */
    public Iterator<Condition> getAllConditions(){
        return this.conditions.iterator();
    }

    /**
     * Ajoute une opération
     * @param o Opération à ajouter
     * @throws IllegalArgumentException Si l'opération existe déjà dans l'énigme
     */
    public void addOperation(Operation o){
        if(this.operations.contains(o)) throw new IllegalArgumentException("Cet élément existe déjà dans la liste");
        this.operations.add(o);
    }

    /**
     * Retire une opération
     * @param o Opération à retirer
     */
    public void removeOperation(Operation o){
        this.operations.remove(o);
    }

    /**
     * Obtenir la liste des opérations
     * @return Iterator<Operation> de toutes les opérations de l'énigme
     */
    public Iterator<Operation> getAllOperations(){
        return this.operations.iterator();
    }

    /**
     * Obtenir le titre de l'énigme
     * @return Titre de l'énigme, chaine vide sinon
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Obtenir la description de l'énigme
     * @return Description de l'énigme, chaine vide sinon
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Indique le titre
     * @param title Titre
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Indique la description
     * @param description Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Ajoute un indice
     * @param a Indice à ajouter
     * @throws IllegalArgumentException Si l'indice existe déjà dans l'énigme
     */
    public void addAdvice(String a){
        if(this.advices.contains(a)) throw new IllegalArgumentException("Cet élément existe déjà dans la liste");
        this.advices.add(a);
    }

    /**
     * Retire un indice
     * @param a Indice à retirer
     */
    public void removeAdvice(String a){
        this.advices.remove(a);
    }

    /**
     * Echange de position deux indices
     * @param index1 Index du premier indice
     * @param index2 Index du second indice
     */
    public void switchAdvices(int index1, int index2){
        String a = this.advices.get(index1);
        this.advices.set(index1,this.advices.get(index2));
        this.advices.set(index2,a);
    }

    /**
     * Obtenir l'indice actuel
     * @return Indice actuel
     */
    public String getAdvice(){
        if(this.currentAdvice != ADVICE_INDEX_STARTING_VALUE) return this.advices.get(this.currentAdvice);
        else return "Aucune aide pour l'instant";
    }

    /**
     * Obtenir la liste des indices
     * @return Iterator<String> de tous les indices de l'énigme
     */
    public Iterator<String> getAllAdvices(){
        return this.advices.iterator();
    }

    /**
     * Indique le temps qui sépare un indice du suivant
     * @param minutes Temps en minute
     */
    public void setTimeBetweenAdvices(int minutes){
        //verifier qu'il est bien inférieur à la durée maximale de la partie
        this.timeBetweenAdvices = minutes;
    }

    /**
     * Obtenir le temps qui sépare un indice du suivant
     * @return Temps en minute
     */
    public int getTimeBetweenAdvices() {
        return this.timeBetweenAdvices;
    }

    /**
     * Savoir si l'énigme à déjà été découverte
     * @return true si l'énigme à été découverte, false sinon
     */
    public boolean isKnown(){
        return this.known;
    }

    /**
     * Indique que l'énigme à été découverte
     */
    public void discovered(){
        this.known = true;
        this.timer = new Timer(this.timeBetweenAdvices * ONE_MINUTES_IN_MILLISECOND,this);
        this.timer.setRepeats(true);
        this.timer.start();
        System.out.println("Nouvelle enigme découverte!");
        System.out.println(this.getTitle()+" : "+this.getDescription());
    }

    /**
     * Passe à l'indice suivant
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println((this.currentAdvice + 1)+" "+this.advices.size());
        if(this.currentAdvice + 1 < this.advices.size()) {
            this.currentAdvice++;
        }else {
            this.timer.stop();
        }
    }

    /**
     * Compare deux énigmes
     * @param o Enigme
     * @return true si les deux énigles sont les même, false sinon
     */
    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Enigma)) return false;

        Enigma e = (Enigma)o;
        return (this.title.equals(e.getTitle()) && this.description.equals(e.getDescription()) && e.getAllConditions().equals(this.getAllConditions()) && e.getAllOperations().equals(this.getAllOperations()) && e.getAllAdvices().equals(this.getAllAdvices()) );
    }

    /**
     * Version texte de l'énigme
     * @return Texte représentant l'énigme
     */
    @Override
    public String toString(){
        return "[Enigma  : title = \"" + this.title + "\", descrption = \"" + this.description + "\", isKnown = " + this.isKnown() + ", timeBetweenAdvices = " + this.timeBetweenAdvices + ", currentAdviceIndex = " + this.currentAdvice + ", currentAdvice = \"" + this.getAdvice() + "\"";
    }

    /**
     * Version texte longue de l'énigme
     * @return Texte représentant l'énigme
     */
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
