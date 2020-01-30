package editor.enigma;

import editor.enigma.condition.Activated;
import editor.enigma.condition.Answer;
import editor.enigma.condition.HaveInHands;
import editor.enigma.condition.HaveInInventory;
import editor.enigma.operation.Give;
import editor.enigma.operation.Unlock;
import editor.entity.Player;
import game.entity.item.Book;
import game.entity.item.Button;
import game.entity.item.Door;

public class ConditionsOperationsTest {
    public static void main(String[] args){
        Enigma e = new Enigma();
        Book b = new Book(0);
        b.addContent("Mot de passe biach");
        Button bu = new Button(false);
        bu.setID(2);
        Player p = new Player(4);

        e.addCondition(new Answer(b,"mdp"));
        e.addCondition(new Activated(bu));
        e.addCondition(new HaveInHands(b));
        e.addCondition(new HaveInInventory(bu));

        e.addOperation(new Unlock(new Door(1)));
        e.addOperation(new Give(new Door(3)));

       e.verifyConditions(p);
       bu.interactsWith(p);
       p.getInventory().add(bu);
       p.setItemInHand(b);
       e.verifyConditions(p);
       System.out.println(p.getInventory().getItems().toString());
    }
}
