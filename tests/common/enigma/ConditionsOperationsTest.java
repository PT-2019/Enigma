package common.enigma;

import common.enigmas.Enigma;
import common.enigmas.condition.Activated;
import common.enigmas.condition.Answer;
import common.enigmas.condition.HaveInHands;
import common.enigmas.condition.HaveInInventory;
import common.enigmas.operation.Give;
import common.enigmas.operation.Unlock;
import common.entities.consumable.Book;
import common.entities.items.Button;
import common.entities.items.Door;
import common.entities.players.Player;
import common.utils.Question;

import java.util.Arrays;

public class ConditionsOperationsTest {
    public static void main(String[] args){
        Enigma e = new Enigma();
        Book b = new Book(0);
        b.setContent("Mot de passe biach");
        Button bu = new Button(false);
        bu.setID(2);
        Player p = new Player(4);

        e.addCondition(new Answer(b,new Question("Mot de passe biach","mdp")));
        e.addCondition(new Activated(bu));
        e.addCondition(new HaveInHands(b));
        e.addCondition(new HaveInInventory(bu));

        e.addOperation(new Unlock(new Door(1)));
        e.addOperation(new Give(new Door(3)));

       e.verifyConditions(p);
       bu.interactsWith(p);
       p.getInventory().add(bu);
       p.setItemInLeftHand(b);
       e.verifyConditions(p);
       System.out.println(Arrays.toString(p.getInventory().getItems()));
    }
}
