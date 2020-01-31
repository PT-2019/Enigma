package editor.enigma;

import general.enigmas.Enigma;
import general.enigmas.condition.Activated;
import general.enigmas.condition.Answer;
import general.enigmas.condition.HaveInHands;
import general.enigmas.condition.HaveInInventory;
import general.enigmas.operation.Give;
import general.enigmas.operation.Unlock;
import general.entities.consumable.Book;
import general.entities.items.Button;
import general.entities.items.Door;
import general.entities.players.Player;

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
